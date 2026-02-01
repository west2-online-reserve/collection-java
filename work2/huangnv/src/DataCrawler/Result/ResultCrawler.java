package DataCrawler.Result;
import  Common.DataFetcher;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ResultCrawler implements DataFetcher{

    public static final String ResultSumURL =
            "https://api.worldaquatics.com/fina/competitions/3337/events";
    public static final String EVENT_DETAIL_URL_TEMPLATE =
            "https://api.worldaquatics.com/fina/events/%s";
    public static final String DEFAULT_OUTPUT_PATH = "stage_2/data/result.json";

    public  String fetch() throws IOException {
        return httpGet(ResultSumURL);
    }

    // Fetch event JSON by discipline name (e.g., "Women 1m Springboard").
    public String fetchEventJsonByDisciplineName(String disciplineName) throws IOException {
        String summaryJson = fetch();
        String disciplineId = getDisciplineIdByName(summaryJson, disciplineName);
        if (disciplineId == null) {
            return null;
        }
        return fetchEventJsonByDisciplineId(disciplineId);
    }

    public Map<String, String> fetchEventIdMap() throws IOException {
        String summaryJson = fetch();
        return parseEventIdMap(summaryJson);
    }

    // Save raw JSON to disk using UTF-8.
    public void saveJsonToFile(String json, String outputPath) throws IOException {
        Path path = Paths.get(outputPath);
        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.write(path, json.getBytes(StandardCharsets.UTF_8));
    }

    // Manual test entry: fetch by discipline name and save event JSON.
    public static void main(String[] args) {
        ResultCrawler crawler = new ResultCrawler();
        try {
            String disciplineName = args.length > 0 ? String.join(" ", args) : "Women 1m Springboard";
            String eventJson = crawler.fetchEventJsonByDisciplineName(disciplineName);
            if (eventJson == null) {
                System.out.println("Discipline not found: " + disciplineName);
                return;
            }
            String outputPath = crawler.buildEventOutputPath(disciplineName);
            crawler.saveJsonToFile(eventJson, outputPath);
            System.out.println("Saved: " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String fetchEventJsonByDisciplineId(String disciplineId) throws IOException {
        return httpGet(String.format(EVENT_DETAIL_URL_TEMPLATE, disciplineId));
    }

    private Map<String, String> parseEventIdMap(String summaryJson) {
        Map<String, String> map = new HashMap<>();
        JsonObject result = JsonParser.parseString(summaryJson).getAsJsonObject();
        JsonArray sportsArray = result.getAsJsonArray("Sports");
        for (JsonElement sportElement : sportsArray) {
            JsonObject sportObj = sportElement.getAsJsonObject();
            JsonArray disciplineList = sportObj.getAsJsonArray("DisciplineList");
            for (JsonElement discipline : disciplineList) {
                JsonObject disciplineObj = discipline.getAsJsonObject();
                String name = getAsString(disciplineObj, "DisciplineName");
                String id = getAsString(disciplineObj, "Id");
                map.put(name, id);
            }
        }
        return map;
    }

    private String buildEventOutputPath(String disciplineName) {
        String name = disciplineName == null ? "" : disciplineName.trim();
        String safeName = name.isEmpty() ? "event" : name.replaceAll("[^A-Za-z0-9]+", "_");
        return "stage_2/data/" + safeName + ".json";
    }

    private String httpGet(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(20000);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        int code = conn.getResponseCode();
        InputStream in = (code >= 200 && code < 300) ? conn.getInputStream() : conn.getErrorStream();
        if (in == null) {
            throw new IOException("HTTP " + code + " empty response");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            if (code < 200 || code >= 300) {
                throw new IOException("HTTP " + code + " " + sb);
            }
            return sb.toString();
        } finally {
            conn.disconnect();
        }
    }

    // Return the discipline id for the given discipline name.
    private String getDisciplineIdByName(String json, String disciplineName) {
        if (disciplineName == null) {
            return null;
        }
        String target = disciplineName.trim();
        if (target.isEmpty()) {
            return null;
        }

        JsonObject result = JsonParser.parseString(json).getAsJsonObject();
        JsonArray sportsArray = result.getAsJsonArray("Sports");
        if (sportsArray == null) {
            return null;
        }

        for (JsonElement sportElement : sportsArray) {
            JsonObject sportObj = sportElement.getAsJsonObject();
            JsonArray disciplineList = sportObj.getAsJsonArray("DisciplineList");
            if (disciplineList == null) {
                continue;
            }
            for (JsonElement discipline : disciplineList) {
                JsonObject disciplineObj = discipline.getAsJsonObject();
                String name = getAsString(disciplineObj, "DisciplineName");
                if (target.equalsIgnoreCase(name)) {
                    return getAsString(disciplineObj, "Id");
                }
            }
        }
        return null;
    }

    private String getAsString(JsonObject obj, String key) {
        if (!obj.has(key) || obj.get(key).isJsonNull()) {
            return "";
        }
        return obj.get(key).getAsString();
    }
}
