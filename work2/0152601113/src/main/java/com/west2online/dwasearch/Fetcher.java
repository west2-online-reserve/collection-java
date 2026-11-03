package com.west2online.dwasearch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.west2online.dwasearch.model.Athlete;
import com.west2online.dwasearch.model.CompetitionData;
import com.west2online.dwasearch.model.Contest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 抓取脚本（联网）。
 *
 * 作用：从 World Aquatics 官网接口抓取赛事数据，整理成我们定义的 data.json 结构，
 *      写到 src/main/resources/data.json（这份就是「数据快照」）。
 *
 * 运行方式（在项目根目录执行）：
 *   mvn compile exec:java -Dexec.mainClass=com.west2online.dwasearch.Fetcher
 *
 * 守规矩：请求之间至少间隔 800ms；对 429（限流）/ 5xx（服务器错误）做指数退避重试。
 */
public class Fetcher {

    private static final String BASE = "https://api.worldaquatics.com";
    private static final int COMPETITION_ID = 5019;
    private static final String USER_AGENT = "learn-backend-round1-data-maintainer/1.0";

    // 8 个项目：DisciplineId（UUID） -> 作业规定的查询关键字。
    // DisciplineId 来自 GET /fina/competitions/5019/events 的 Sports[].DisciplineList[].Id。
    private static final Map<String, String> EVENTS = new LinkedHashMap<>();

    static {
        EVENTS.put("2b08da89-8232-4ffc-8df3-95865ba41b82", "women 3m springboard");
        EVENTS.put("581a26af-8e97-427c-9adb-5fdd8f4fc9c1", "women 10m platform");
        EVENTS.put("61b483ec-4022-4555-9960-fba9cd1d702d", "women 3m synchronised");
        EVENTS.put("c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b", "women 10m synchronised");
        EVENTS.put("4c18d241-ad14-436a-bd38-25dfb47f1cb7", "men 3m springboard");
        EVENTS.put("450a9e84-5db0-40c4-a45c-4c3bb2392f48", "men 10m platform");
        EVENTS.put("08d17e1c-94c2-4c56-bf2b-603eeba24f8c", "men 3m synchronised");
        EVENTS.put("f86bd80d-a342-40f7-a521-90a50ad9d5c5", "men 10m synchronised");
    }

    // JDK 自带的 HTTP 客户端（java.net.http）
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(30))
            .build();
    private final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        // 部分网络环境 IPv6 不通，Java 默认优先 IPv6 会让连接卡住；强制走 IPv4 更稳。
        System.setProperty("java.net.preferIPv4Stack", "true");
        Fetcher fetcher = new Fetcher();
        CompetitionData data = fetcher.fetchAll();
        Path out = Path.of("src", "main", "resources", "data.json");
        Files.createDirectories(out.getParent());
        fetcher.mapper.writerWithDefaultPrettyPrinter().writeValue(out.toFile(), data);
        System.out.println("抓取完成，已写入: " + out.toAbsolutePath());
    }

    public CompetitionData fetchAll() throws Exception {
        List<Athlete> players = fetchPlayers();

        Map<String, List<Contest>> results = new LinkedHashMap<>();
        for (Map.Entry<String, String> event : EVENTS.entrySet()) {
            results.put(event.getValue(), fetchFinalResults(event.getKey()));
            sleep(800); // 请求间隔，控制频率
        }
        return new CompetitionData(players, results);
    }

    /** 抓选手名单，展开成平铺的选手列表。 */
    private List<Athlete> fetchPlayers() throws Exception {
        JsonNode root = getJson(BASE + "/fina/competitions/" + COMPETITION_ID + "/athletes");
        List<Athlete> players = new ArrayList<>();
        for (JsonNode nation : root) {
            String country = nation.path("CountryName").asText();
            for (JsonNode p : nation.path("Participations")) {
                String lastName = p.path("PreferredLastName").asText();
                String firstName = p.path("PreferredFirstName").asText();
                int gender = p.path("Gender").asInt(); // 0=Male, 1=Female
                players.add(new Athlete(country, lastName, firstName, gender == 0 ? "Male" : "Female"));
            }
        }
        return players;
    }

    /** 抓某个项目的「决赛（Finals）」成绩。 */
    private List<Contest> fetchFinalResults(String disciplineId) throws Exception {
        JsonNode root = getJson(BASE + "/fina/events/" + disciplineId);
        for (JsonNode heat : root.path("Heats")) {
            // 个人项目有 Head-To-Head / Semifinals / Finals 三个轮次，只取 Finals
            if (!"Finals".equals(heat.path("Name").asText())) {
                continue;
            }
            List<Contest> contests = new ArrayList<>();
            for (JsonNode r : heat.path("Results")) {
                int rank = r.path("Rank").asInt();
                String fullName = r.path("FullName").asText();
                String totalPoints = r.path("TotalPoints").asText();
                List<String> dives = new ArrayList<>();
                for (JsonNode d : r.path("Dives")) {
                    dives.add(d.path("DivePoints").asText());
                }
                contests.add(new Contest(rank, fullName, dives, totalPoints));
            }
            return contests;
        }
        throw new IllegalStateException("未找到 Finals 数据: " + disciplineId);
    }

    /** 发 GET 请求并解析成 JSON 树，带重试。 */
    private JsonNode getJson(String url) throws Exception {
        int maxRetries = 4;
        long delayMs = 1000;
        Exception lastError = null;

        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            try {
                HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                        .header("User-Agent", USER_AGENT)
                        .timeout(Duration.ofSeconds(30))
                        .GET()
                        .build();
                HttpResponse<String> response =
                        client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

                if (response.statusCode() == 200) {
                    return mapper.readTree(response.body());
                }
                // 只有限流或服务器错误才值得重试，其它错误直接抛出说明原因
                if (!isRetryable(response.statusCode())) {
                    throw new IllegalStateException("请求失败 " + url + " -> HTTP " + response.statusCode());
                }
                lastError = new IllegalStateException("HTTP " + response.statusCode() + " " + url);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw e;
            } catch (IOException e) {
                lastError = e;
            }

            sleep(delayMs); // 指数退避：1s、2s、4s、8s
            delayMs *= 2;
        }
        throw new IllegalStateException("多次重试后仍失败: " + url, lastError);
    }

    private static boolean isRetryable(int status) {
        return status == 429 || status >= 500;
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
