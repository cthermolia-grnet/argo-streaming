package profilesmanager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author cthermolia
 */
public class MetricTagsManager {

    private HashMap<String, ArrayList<String>> list;

    private static final Logger LOG = Logger.getLogger(MetricTagsManager.class.getName());

    public MetricTagsManager() {
        list = new HashMap<String, ArrayList<String>>();
//        ArrayList<String> tags = new ArrayList<>();
//        tags.add("tag1");
//        tags.add("tag2");
//        list.put("org.nagios.GridFTP-Check", tags);
//        tags.clear();
//        tags.add("tag1");
//        tags.add("tag3");
//        list.put("org.nagios.BDII-Check", tags);
    }

    /**
     * Clears the stored data of a MetricTagsManager object
     */
    public void clear() {
        this.list = new HashMap<String, ArrayList<String>>();

    }

    /**
     * Inserts new tag information (metric, <>tags) to the MetricTagsManager
     */
    public int insert(String metric, String tag) {
        if (this.list.containsKey(metric)) {
            this.list.get(metric).add(tag);
        } else {
            this.list.put(metric, new ArrayList<String>());
            this.list.get(metric).add(tag);
        }

        return 0; // All good
    }

    /**
     * Returns tags information by metric
     */
    public ArrayList<String> getTags(String metric) {
        if (list.containsKey(metric)) {

            return list.get(metric);
        }
        return new ArrayList<>();

    }

    /**
     * loads from a json file that contain the metric tags , and stores the info
     * to the corresponding fields
     *
     * @param jsonFile
     * @throws IOException
     */
    public void loadJson(File jsonFile) throws IOException {
        // Clear data
          this.clear();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(jsonFile));

            JsonParser jsonParser = new JsonParser();
            JsonElement jElement = jsonParser.parse(br);
            readJson(jElement);
        } catch (FileNotFoundException ex) {
            LOG.error("Could not open file:" + jsonFile.getName());
            throw ex;

        } catch (JsonParseException ex) {
            LOG.error("File is not valid json:" + jsonFile.getName());
            throw ex;
        } finally {
            // Close quietly without exceptions the buffered reader
            IOUtils.closeQuietly(br);
        }

    }

    /**
     * Loads Report config information from a config json string
     *
     */
    public void loadJsonString(List<String> confJson) throws JsonParseException {
        // Clear data
        this.clear();

        try {

            JsonParser jsonParser = new JsonParser();
            // Grab the first - and only line of json from ops data
            JsonElement jElement = jsonParser.parse(confJson.get(0));
            readJson(jElement);

        } catch (JsonParseException ex) {
            LOG.error("Not valid json contents");
            throw ex;
        }

    }

    /**
     * reads from a JsonElement array and stores the necessary information to
     * the ReportManager objects and add them to the list
     *
     * @param jElement , a JsonElement containing the tenant's report data
     * @return
     */
    public void readJson(JsonElement jElement) {

        JsonObject jObj = jElement.getAsJsonObject();
        JsonArray metricTags = jObj.getAsJsonArray("metric_tags");
        if (metricTags != null) {
            for (JsonElement mtags : metricTags) {
                JsonObject metric = mtags.getAsJsonObject();

                String metricname = metric.get("metric_name").getAsString();
                JsonArray jTags = metric.getAsJsonArray("tags");
                if (jTags != null) {
                    for (JsonElement tag : jTags) {
                        String jTag = tag.getAsJsonObject().toString();
                        this.insert(metricname, jTag);
                    }
                }

            }
        }
    }

}
