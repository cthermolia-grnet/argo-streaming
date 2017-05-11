package argo.batch;

import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import com.mongodb.hadoop.mapred.MongoOutputFormat;
import com.mongodb.hadoop.io.BSONWritable;
import com.mongodb.hadoop.util.MongoConfigUtil;

import argo.avro.MetricData;

import org.slf4j.Logger;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.hadoop.mapred.HadoopOutputFormat;
import org.apache.flink.api.java.io.AvroInputFormat;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.core.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.JobConf;



/**
 * Represents an ARGO Batch Job in flink
 * 
 * Submit job in flink cluster using the following parameters 
 * --mdata: path to metric data file (For hdfs use: hdfs://namenode:port/path/to/file)
 * --mongo.url: path to mongo destination (eg mongodb://localhost:27017/database.table
 */
public class ArgoStatusBatch {
	// setup logger
	static Logger LOG = LoggerFactory.getLogger(ArgoStatusBatch.class);

	public static void main(String[] args) throws Exception {

		final ParameterTool params = ParameterTool.fromArgs(args);

		// set up the execution environment
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		// make parameters available in the web interface
		env.getConfig().setGlobalJobParameters(params);

		// input data
		Path in = new Path(params.get("mdata"));
		AvroInputFormat<MetricData> mdataAvro = new AvroInputFormat<MetricData>(in, MetricData.class);
		DataSet<MetricData> mdataDS = env.createInput(mdataAvro);

		/**
		 * Prepares the data in BSONWritable values for mongo storage Each tuple
		 * is in the form <K,V> and the key here must be empty for mongo to
		 * assign an ObjectKey NullWriteable as the first object of the tuple
		 * ensures an empty key
		 */
		DataSet<Tuple2<NullWritable, BSONWritable>> statusMetricBSON = mdataDS
				.map(new MapFunction<MetricData, Tuple2<NullWritable, BSONWritable>>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Tuple2<NullWritable, BSONWritable> map(MetricData md) throws Exception {

						// Create a mongo database object with the needed fields
						DBObject builder = BasicDBObjectBuilder.start().add("service", md.getService())
								.add("hostname", md.getHostname()).add("metric", md.getMetric())
								.add("status", md.getStatus()).add("timestamp", md.getTimestamp()).get();

						// Convert database object to BsonWriteable
						BSONWritable w = new BSONWritable(builder);

						return new Tuple2<NullWritable, BSONWritable>(NullWritable.get(), w);
					}
				});

		// Initialize a new hadoop conf object to add mongo connector related property
		JobConf conf = new JobConf();
		// Add mongo destination as given in parameters
		conf.set("mongo.output.uri", params.get("mongo.uri"));
		// Initialize MongoOutputFormat
		MongoOutputFormat<NullWritable, BSONWritable> mongoOutputFormat = new MongoOutputFormat<NullWritable, BSONWritable>();
		// Use HadoopOutputFormat as a wrapper around MongoOutputFormat to write results in mongo db
		statusMetricBSON.output(new HadoopOutputFormat<NullWritable, BSONWritable>(mongoOutputFormat, conf));

		env.execute("Flink Status Job");

	}

}