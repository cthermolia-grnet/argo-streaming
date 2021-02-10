package argo.functions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import argo.avro.MetricData;
import java.util.TreeMap;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cthermolia LastTimeStampGroupReduce keeps data of the latest time
 * entry
 */
public class LastTimeStampGroupReduce implements GroupReduceFunction<MetricData, MetricData> {

    static Logger LOG = LoggerFactory.getLogger(LastTimeStampGroupReduce.class);

    /**
     *
     * @param in, the initial dataset of the MetricData
     * @param out , the output dataset containing the MetricData of the latest
     * timestamp
     * @throws Exception
     */
    @Override
    public void reduce(Iterable<MetricData> in, Collector<MetricData> out) throws Exception {
        TreeMap<String, MetricData> timeStatusMap = new TreeMap<>();
        for (MetricData md : in) {
            timeStatusMap.put(md.getTimestamp().toString(), md);

        }
        out.collect(timeStatusMap.lastEntry().getValue());
    }
}
