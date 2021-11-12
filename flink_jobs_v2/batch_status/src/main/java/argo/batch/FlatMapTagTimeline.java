package argo.batch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;
import profilesmanager.AggregationProfileManager;
import profilesmanager.MetricTagsManager;

/**
 * MapServices produces TimelineTrends for each service,that maps to the groups
 * of functions as described in aggregation profile groups endpoint , metric
 */
public class FlatMapTagTimeline extends RichFlatMapFunction<StatusTimeline, StatusTimeline> {

    //private AggregationProfileParser aggregationProfileParser;
    public FlatMapTagTimeline() {

    }

    private List<String> aps;
    private AggregationProfileManager apsMgr;
     private List<String> mtags;
    private MetricTagsManager mtagsMgr;
//    

    @Override
    public void open(Configuration parameters) throws IOException {

        // Initialize operations manager
         this.mtags = getRuntimeContext().getBroadcastVariable("mtags");
        // Initialize aggregation profile manager
        this.mtagsMgr = new MetricTagsManager();
        this.mtagsMgr.loadJsonString(mtags);
//        
    }

    /**
     * if the service exist in one or more function groups , timeline trends are
     * produced for each function that the service belongs and the function info
     * is added to the timelinetrend
     *
     * @param t
     * @param out
     * @throws Exception
     */
    @Override
    public void flatMap(StatusTimeline t, Collector<StatusTimeline> out) throws Exception {

        if (t.getMetric().equals("org.nagios.GridFTP-Check") || t.getMetric().equals("org.nagios.BDII-Check")) {
            ArrayList<String> tags = this.mtagsMgr.getTags(t.getMetric());
            for (String tag : tags) {

                StatusTimeline newT = t;
                newT.setTag(tag);
                out.collect(newT);
            }
        }
    }

}
