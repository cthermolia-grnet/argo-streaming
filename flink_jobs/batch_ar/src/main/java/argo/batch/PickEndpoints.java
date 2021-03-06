package argo.batch;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.flink.api.common.functions.RichFlatMapFunction;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import argo.avro.GroupEndpoint;
import argo.avro.GroupGroup;
import argo.avro.MetricData;
import argo.avro.MetricProfile;
import ops.ConfigManager;
import ops.OpsManager;
import ops.ThresholdManager;
import sync.AggregationProfileManager;
import sync.EndpointGroupManager;
import sync.GroupGroupManager;
import sync.MetricProfileManager;
import sync.RecomputationManager;

/**
 * Accepts a metric data entry and converts it to a status metric object by appending endpoint group information
 * Filters out entries that do not appear in topology and metric profiles
 */
public class PickEndpoints extends RichFlatMapFunction<MetricData,MonData> {

	private static final long serialVersionUID = 1L;

	
	final ParameterTool params;
	
	public PickEndpoints(ParameterTool params){
		this.params = params;
	}
	
	static Logger LOG = LoggerFactory.getLogger(ArgoArBatch.class);

	private List<MetricProfile> mps;
	private List<GroupEndpoint> egp;
	private List<GroupGroup> ggp;
	private List<String> apr;
	private List<String> rec;
	private List<String> conf;
	private List<String> thr;
	private List<String> ops;
	private MetricProfileManager mpsMgr;
	private EndpointGroupManager egpMgr;
	private GroupGroupManager ggpMgr;
	private AggregationProfileManager aprMgr;
	private RecomputationManager recMgr;
	private ConfigManager confMgr;
	private OpsManager opsMgr;
	private ThresholdManager thrMgr;
	
	private String egroupType;

	/**
	 * Initialization method of the RichFlatMapFunction operator
	 * <p>
	 * This runs at the initialization of the operator and receives a configuration
	 * parameter object. It initializes all required structures used by this operator
	 * such as profile managers, operations managers, topology managers etc.
	 *
	 * @param	parameters	A flink Configuration object
	 */
	@Override
	public void open(Configuration parameters) throws IOException, ParseException {
		// Get data from broadcast variable
		this.mps = getRuntimeContext().getBroadcastVariable("mps");
		this.egp = getRuntimeContext().getBroadcastVariable("egp");
		this.ggp = getRuntimeContext().getBroadcastVariable("ggp");
		this.apr = getRuntimeContext().getBroadcastVariable("apr");
		this.rec = getRuntimeContext().getBroadcastVariable("rec");
		this.conf = getRuntimeContext().getBroadcastVariable("conf");
		this.ops = getRuntimeContext().getBroadcastVariable("ops");
		this.thr = getRuntimeContext().getBroadcastVariable("thr");
		
		// Initialize metric profile manager
		this.mpsMgr = new MetricProfileManager();
		this.mpsMgr.loadFromList(mps);
		// Initialize endpoint group manager
		this.egpMgr = new EndpointGroupManager();
		this.egpMgr.loadFromList(egp);
		
		this.ggpMgr = new GroupGroupManager();
		this.ggpMgr.loadFromList(ggp);
		
		// Initialize Aggregation Profile Manager ;
		this.aprMgr = new AggregationProfileManager();
		this.aprMgr.loadJsonString(apr);
		
		// Initialize Recomputations Manager;
		this.recMgr = new RecomputationManager();
		this.recMgr.loadJsonString(rec);
		
		// Initialize Configurations Manager;
		this.confMgr = new ConfigManager();
		this.confMgr.loadJsonString(conf);
		
		// Initialize endpoint group type
		this.egroupType = this.confMgr.egroup;
		
		// Initialize Ops Manager
		this.opsMgr = new OpsManager();
		this.opsMgr.loadJsonString(ops);
		
		// Initialize Threshold manager
		this.thrMgr = new ThresholdManager();
		if (!this.thr.get(0).isEmpty()){
			this.thrMgr.parseJSON(this.thr.get(0));
		}
		
	}

	
	/**
	 * The main operator business logic of filtering a collection of MetricData
	 * <p>
	 * This runs for a dataset of Metric data items and returns a collection of MonData items after filtering out the 
	 * unwanted ones. 
	 * The filtering happens in 5 stages:
	 * 1) Filter out by checking if monitoring engine is excluded (Recomputation Manager used)
	 * 2) Filter out by checking if service belongs to aggregation profile (Aggregation Profile Manager used)
	 * 3) Filter out by checking if service and metric belongs to metric profile used (Metric Profile Manager used)
	 * 4) Filter out by checking if service endpoint belongs to group endpoint topology (Group Endpoint  Manager used
	 * 5) Filter out by checking if group endpoint belongs to a valid upper group (Group of Groups Manager used)
	 *
	 * @param	in	An Iterable collection of MetricData objects
	 * @param	out	A Collector list of valid MonData objects after filtering
	 */
	@Override
	public void flatMap(MetricData md, Collector<MonData> out) throws Exception {

		String prof = mpsMgr.getProfiles().get(0);
		String aprof = aprMgr.getAvProfiles().get(0);
		String hostname = md.getHostname();
		String service = md.getService();
		String metric = md.getMetric();
		String monHost = md.getMonitoringHost();
		String ts = md.getTimestamp();
		
		// Filter by monitoring engine 
		if (recMgr.isMonExcluded(monHost, ts) == true) return;

		// Filter By aggregation profile
		if (aprMgr.checkService(aprof, service) == false) return;
		
		// Filter By metric profile
		if (mpsMgr.checkProfileServiceMetric(prof, service, metric) == false) return;
		
	
		// Filter by endpoint group 
		if (egpMgr.checkEndpoint(hostname, service) == false) return;
		
		// Filter By endpoint group if belongs to supergroup
		ArrayList<String> groupnames = egpMgr.getGroup(egroupType, hostname, service);
		
		for (String groupname : groupnames) {
			
			if (ggpMgr.checkSubGroup(groupname) == true){
				
				String status = md.getStatus();
				String actualData = md.getActualData();
				
				if (actualData != null) {
					// Check for relevant rule
					String rule = thrMgr.getMostRelevantRule(groupname, md.getHostname(), md.getMetric());
					// if rule is indeed found 
					if (rule != ""){
						// get the retrieved values from the actual data
						Map<String, Float> values = thrMgr.getThresholdValues(actualData);
						// calculate 
						String[] statusNext = thrMgr.getStatusByRuleAndValues(rule, this.opsMgr, "AND", values);
						if (statusNext[0] == "") statusNext[0] = status;
						LOG.info("{},{},{} data:({}) {} --> {}",groupname,md.getHostname(),md.getMetric(),values,status,statusNext[0]);
						if (status != statusNext[0]) {
							status = statusNext[0];
						}
					}
					
					
				}
				
				MonData mn = new MonData();
				mn.setGroup(groupname);
				mn.setHostname(hostname);
				mn.setService(service);
				mn.setMetric(metric);
				mn.setMonHost(monHost);
				mn.setStatus(status);
				mn.setTimestamp(ts);
				mn.setMessage(md.getMessage());
				mn.setSummary(md.getSummary());
				// transfer the actual data to the enriched monitoring data object
				mn.setActualData(actualData);
				
				out.collect(mn);
			}
				
				
		}
		
	}



	
}
