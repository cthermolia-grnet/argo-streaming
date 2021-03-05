/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package argo.utils;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author cthermolia
 */
public class RequestManager {

    private static JSONObject request(String uri, String key, String proxy) throws ParseException {
        JSONObject jsonresult = null;

        Request request = Request.Get(uri);
        // add request headers
        request.addHeader("x-api-key", key);
        request.addHeader(HttpHeaders.ACCEPT, "application/json");
        if (proxy != null) {
            request = request.viaProxy(proxy);
        }
        String content = "{}";
        try {
            CloseableHttpClient httpClient = HttpClients.custom().build();
            Executor executor = Executor.newInstance(httpClient);
            content = executor.execute(request).returnContent().asString();

            JSONParser parser = new JSONParser();
            jsonresult = (JSONObject) parser.parse(content);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonresult;
    }

    public static JSONObject getReportRequest(String baseUri, String key, String proxy, String reportId) throws IOException, ParseException {
        JSONObject jsonresult = null;
        String uri = baseUri + "/reports/" + reportId;

        jsonresult = request(uri, key, proxy);
        return jsonresult;

    }

    public static JSONObject getAggregationProfileRequest(String baseUri, String key, String proxy, String aggregationId,String date) throws IOException, ParseException {
        JSONObject jsonresult = null;
        String uri = baseUri + "/aggregation_profiles";
        if(date==null){
            uri=uri+"/"+aggregationId;
        }else{
            uri=uri+"?date="+date;
        }
        jsonresult = request(uri, key, proxy);
        return jsonresult;

    }

    public static JSONObject getMetricProfileRequest(String baseUri, String key, String proxy, String metricId,String date) throws IOException, ParseException {
        JSONObject jsonresult = null;
        String uri = baseUri + "/metric_profiles" ;
        System.out.println("date --" +date);
        if(date==null){
            uri=uri+ "/"+metricId;
        }else{
            uri=uri+"?date="+date;
        }
        System.out.println("uri--- "+uri);
        jsonresult = request(uri, key, proxy);
        return jsonresult;

    }

    public static JSONObject getTopologyEndpointRequest(String baseUri, String key, String proxy, String date) throws IOException, ParseException {
        JSONObject jsonresult = null;
        String uri = baseUri + "/topology/endpoints";
        if (date != null) {
            uri = uri + "?date=" + date;
        }
        jsonresult = request(uri, key, proxy);

        return jsonresult;

    }

    public static JSONObject getOperationProfileRequest(String baseUri, String key, String proxy, String operationsId, String date) throws IOException, ParseException {
        JSONObject jsonresult = null;
        String url = null;

        String uri = baseUri + "/operations_profiles";
        if (date == null) {
            uri = uri + operationsId;
        } else {
            uri = uri + "?date=" + date;
        }
        jsonresult = request(uri, key, proxy);
        return jsonresult;

    }

    public static JSONObject getTopologyGroupRequest(String baseUri, String key, String proxy, String date) throws IOException, ParseException {
        JSONObject jsonresult = null;
        String uri = baseUri + "/topology/groups";
        if (date != null) {
            uri = uri + "?date=" + date;
        }
        jsonresult = request(uri, key, proxy);

        return jsonresult;

    }

}