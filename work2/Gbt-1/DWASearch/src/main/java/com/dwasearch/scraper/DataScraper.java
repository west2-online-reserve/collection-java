package com.dwasearch.scraper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataScraper {

    private static final String BASE = "https://api.worldaquatics.com/fina";
    private static final int COMPETITION_ID = 5019;
    private static final long REQUEST_INTERVAL_MILLIS = 700;

    private static final Map<String, String> TARGET_EVENTS = new LinkedHashMap<>();

    static {
        TARGET_EVENTS.put("Women's 3m Springboard", "women 3m springboard");
        TARGET_EVENTS.put("Women's 10m Platform", "women 10m platform");
        TARGET_EVENTS.put("Women's 3m Synchronised", "women 3m synchronised");
        TARGET_EVENTS.put("Women's 10m Synchronised", "women 10m synchronised");
        TARGET_EVENTS.put("Men's 3m Springboard", "men 3m springboard");
        TARGET_EVENTS.put("Men's 10m Platform", "men 10m platform");
        TARGET_EVENTS.put("Men's 3m Synchronised", "men 3m synchronised");
        TARGET_EVENTS.put("Men's 10m Synchronised", "men 10m synchronised");
    }

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    private final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        try {
            Path output = Path.of("data.json");
            new DataScraper().scrape(output);
            System.out.println("已生成: " + output.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("抓取失败: " + e.getMessage());
            System.exit(1);
        }
    }

    public void scrape(Path output) throws IOException, InterruptedException {
        JsonNode athletes = getJson(
                BASE + "/competitions/" + COMPETITION_ID + "/athletes"
        );
        JsonNode competition = getJson(
                BASE + "/competitions/" + COMPETITION_ID + "/events"
        );

        ObjectNode root = mapper.createObjectNode();
        root.set("players", buildPlayers(athletes));
        root.set("events", buildEvents(competition));

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(output.toFile(), root);
    }

    private ArrayNode buildPlayers(JsonNode athletes) throws IOException {
        ArrayNode players = mapper.createArrayNode();

        for (JsonNode country : athletes) {
            String countryName = text(country, "CountryName");

            for (JsonNode person : country.path("Participations")) {
                ObjectNode player = players.addObject();
                player.put("firstName", text(person, "PreferredFirstName"));
                player.put("lastName", text(person, "PreferredLastName"));
                player.put("gender", gender(person));
                player.put("country", countryName);
            }
        }

        if (players.size() == 0) {
            throw new IOException("选手列表为空");
        }
        return players;
    }

    private ArrayNode buildEvents(JsonNode competition)
            throws IOException, InterruptedException {
        JsonNode sports = competition.path("Sports");
        if (!sports.isArray() || sports.size() == 0) {
            throw new IOException("赛事缺少 Sports 数组");
        }

        JsonNode disciplines = sports.get(0).path("DisciplineList");
        Map<String, String> disciplineIds = new LinkedHashMap<>();

        for (JsonNode discipline : disciplines) {
            disciplineIds.put(
                    discipline.path("DisciplineName").asText(),
                    discipline.path("Id").asText()
            );
        }

        ArrayNode events = mapper.createArrayNode();

        for (Map.Entry<String, String> target : TARGET_EVENTS.entrySet()) {
            String displayName = target.getKey();
            String eventName = target.getValue();
            String disciplineId = disciplineIds.get(displayName);

            if (disciplineId == null) {
                throw new IOException("缺少目标项目: " + displayName);
            }

            JsonNode eventJson = getJson(BASE + "/events/" + disciplineId);
            JsonNode finals = findFinals(eventJson, displayName);
            events.add(buildEvent(eventName, finals));
        }

        return events;
    }

    private JsonNode findFinals(JsonNode event, String displayName) throws IOException {
        for (JsonNode heat : event.path("Heats")) {
            if ("Finals".equals(heat.path("Name").asText())) {
                return heat;
            }
        }
        throw new IOException("找不到决赛: " + displayName);
    }

    private ObjectNode buildEvent(String eventName, JsonNode finals)
            throws IOException {
        JsonNode results = finals.path("Results");
        if (!results.isArray() || results.size() == 0) {
            throw new IOException(eventName + " 决赛没有成绩");
        }

        ObjectNode event = mapper.createObjectNode();
        event.put("eventName", eventName);
        ArrayNode resultArray = event.putArray("results");

        for (JsonNode result : results) {
            ObjectNode item = resultArray.addObject();
            item.put("fullName", text(result, "FullName"));
            item.put("rank", integer(result, "Rank"));

            JsonNode dives = result.path("Dives");
            if (!dives.isArray() || dives.size() == 0) {
                throw new IOException(eventName + " 缺少跳水成绩");
            }

            ArrayNode scores = item.putArray("diveScores");
            for (JsonNode dive : dives) {
                scores.add(text(dive, "DivePoints"));
            }

            item.put("totalScore", text(result, "TotalPoints"));
        }

        return event;
    }

    private String gender(JsonNode person) throws IOException {
        JsonNode gender = person.get("Gender");
        if (gender == null || !gender.isIntegralNumber()) {
            throw new IOException("选手缺少 Gender");
        }

        int value = gender.asInt();
        if (value == 1) {
            return "Female";
        }
        if (value == 0) {
            return "Male";
        }
        throw new IOException("未知 Gender: " + value);
    }

    private String text(JsonNode node, String field) throws IOException {
        JsonNode value = node.get(field);
        if (value == null || !value.isTextual() || value.asText().isBlank()) {
            throw new IOException("缺少字段: " + field);
        }
        return value.asText();
    }

    private int integer(JsonNode node, String field) throws IOException {
        JsonNode value = node.get(field);
        if (value == null || !value.isIntegralNumber()) {
            throw new IOException("缺少字段: " + field);
        }
        return value.asInt();
    }

    private JsonNode getJson(String url) throws IOException, InterruptedException {
        URI uri = URI.create(url);

        for (int attempt = 1; attempt <= 3; attempt++) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .timeout(Duration.ofSeconds(30))
                    .header("User-Agent", "DWASearch/1.0 (student project)")
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)
            );

            int status = response.statusCode();

            if (status == 200) {
                Thread.sleep(REQUEST_INTERVAL_MILLIS);
                return mapper.readTree(response.body());
            }

            if (status == 429 || status >= 500) {
                System.out.println("HTTP " + status + "，第 " + attempt + " 次重试: " + url);
                Thread.sleep(1000L * attempt);
                continue;
            }

            throw new IOException("HTTP " + status + "，无法获取: " + url);
        }

        throw new IOException("重试 3 次后仍失败: " + url);
    }
}