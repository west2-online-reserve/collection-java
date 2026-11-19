package com.west2.dwasearch.crawler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.west2.dwasearch.data.EventCatalog;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Crawler {
    private static final String COMPETITION_ID = "5019";
    private static final String ATHLETES_URL =
            "https://api.worldaquatics.com/fina/competitions/" + COMPETITION_ID + "/athletes";
    private static final String EVENT_URL = "https://api.worldaquatics.com/fina/events/";

    private static final String[][] EVENTS = {
            {"women 3m springboard", "2b08da89-8232-4ffc-8df3-95865ba41b82"},
            {"women 10m platform", "581a26af-8e97-427c-9adb-5fdd8f4fc9c1"},
            {"women 3m synchronised", "61b483ec-4022-4555-9960-fba9cd1d702d"},
            {"women 10m synchronised", "c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b"},
            {"men 3m springboard", "4c18d241-ad14-436a-bd38-25dfb47f1cb7"},
            {"men 10m platform", "450a9e84-5db0-40c4-a45c-4c3bb2392f48"},
            {"men 3m synchronised", "08d17e1c-94c2-4c56-bf2b-603eeba24f8c"},
            {"men 10m synchronised", "f86bd80d-a342-40f7-a521-90a50ad9d5c5"}
    };

    private static final int MAX_TRY = 3;
    private static final long SLEEP_MILLIS = 800;

    public static void main(String[] args) {
        Path dataDir = Paths.get("src", "main", "resources", "data");

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        ObjectMapper mapper = new ObjectMapper();

        try {
            Files.createDirectories(dataDir);

            System.out.println("开始抓取，数据目录：" + dataDir.toAbsolutePath());
            byte[] athletes = download(client, ATHLETES_URL);
            Files.write(dataDir.resolve("athletes.json"), athletes);
            int playerCount = mapper.readTree(athletes).size();
            System.out.println("  athletes.json  OK，共 " + playerCount + " 个国家/地区条目");

            for (int i = 0; i < EVENTS.length; i++) {
                String keyword = EVENTS[i][0];
                String disciplineId = EVENTS[i][1];
                sleep();

                byte[] body = download(client, EVENT_URL + disciplineId);
                String fileName = EventCatalog.fileNameOf(keyword);
                Files.write(dataDir.resolve(fileName), body);
                System.out.println("  " + fileName + "  OK，决赛成绩 " + countFinalResults(mapper, body)
                        + " 条（" + keyword + "）");
            }

            writeFetchInfo(dataDir);
            System.out.println("抓取完成。");
        } catch (Exception e) {
            System.err.println("抓取失败：" + e.getMessage());
            System.err.println("请确认网络可以访问 https://api.worldaquatics.com ，然后重新运行。");
            System.exit(1);
        }
    }

    private static byte[] download(HttpClient client, String url) throws Exception {
        Exception lastError = null;
        for (int i = 1; i <= MAX_TRY; i++) {
            try {
                HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                        .timeout(Duration.ofSeconds(30))
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                        .header("Accept", "application/json")
                        .GET()
                        .build();
                HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
                if (response.statusCode() != 200) {
                    throw new IllegalStateException("HTTP 状态码 " + response.statusCode() + "：" + url);
                }
                if (response.body().length == 0) {
                    throw new IllegalStateException("返回内容为空：" + url);
                }
                return response.body();
            } catch (Exception e) {
                lastError = e;
                System.err.println("  第 " + i + " 次请求失败：" + e.getMessage());
                if (i < MAX_TRY) {
                    sleep();
                }
            }
        }
        throw new Exception("重试 " + MAX_TRY + " 次仍然失败：" + url, lastError);
    }

    private static int countFinalResults(ObjectMapper mapper, byte[] body) {
        try {
            JsonNode root = mapper.readTree(body);
            JsonNode heats = root.get("Heats");
            if (heats == null || !heats.isArray()) {
                return 0;
            }
            for (int i = 0; i < heats.size(); i++) {
                JsonNode heat = heats.get(i);
                if ("Finals".equals(heat.path("Name").asText())) {
                    return heat.path("Results").size();
                }
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static void writeFetchInfo(Path dataDir) throws Exception {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String text = "{\n"
                + "  \"competitionId\": " + COMPETITION_ID + ",\n"
                + "  \"competition\": \"World Aquatics Diving World Cup 2026 - Super Final\",\n"
                + "  \"city\": \"Beijing\",\n"
                + "  \"fetchedAt\": \"" + time + "\"\n"
                + "}\n";
        Files.write(dataDir.resolve("fetch-info.json"), text.getBytes(StandardCharsets.UTF_8));
        System.out.println("  fetch-info.json  OK，抓取时间 " + time);
    }

    private static void sleep() {
        try {
            Thread.sleep(SLEEP_MILLIS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
