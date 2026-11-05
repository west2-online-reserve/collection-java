package dwasearch;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
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
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据抓取脚本：从 World Aquatics 公开 API 抓取赛事 5019 的选手名单与决赛成绩，
 * 生成 data.json。可重复运行，失败时说明原因并以非 0 退出码结束。
 *
 * <p>运行方式：
 * <pre>
 *   mvn package
 *   java -cp target/DWASearch-1.0-SNAPSHOT.jar dwasearch.FetchData [输出路径]
 * </pre>
 * 不带参数时默认写入当前目录的 data.json。
 *
 * <p>三个数据源（无需登录或密钥）：
 * <ol>
 *   <li>{@code /competitions/5019/events}   —— 八个项目的 disciplineId</li>
 *   <li>{@code /competitions/5019/athletes} —— 选手名单（国家、姓名、性别）</li>
 *   <li>{@code /events/{disciplineId}}      —— 该项目全部轮次，取 Finals</li>
 * </ol>
 */
public class FetchData {

    private static final String BASE = "https://api.worldaquatics.com/fina";
    private static final int COMPETITION_ID = 5019;

    /** 请求头中的 User-Agent。 */
    private static final String UA = "DWASearch-study/1.0 (learning project)";

    /** 请求间隔（毫秒），控制请求频率。 */
    private static final long INTERVAL_MS = 600;

    private static final int MAX_RETRY = 3;

    /**
     * 八个目标项目。
     * 每行三个元素：输出用的关键字、接口中的性别、接口中的项目代码。
     */
    private static final String[][] TARGETS = {
            {"women 3m springboard",   "Women", "002"},
            {"women 10m platform",     "Women", "003"},
            {"women 3m synchronised",  "Women", "202"},
            {"women 10m synchronised", "Women", "203"},
            {"men 3m springboard",     "Men",   "002"},
            {"men 10m platform",       "Men",   "003"},
            {"men 3m synchronised",    "Men",   "202"},
            {"men 10m synchronised",   "Men",   "203"},
    };

    private final HttpClient client;
    private final ObjectMapper mapper;

    public FetchData() {
        // followRedirects：如果对方让我们跳转，自动跟着跳
        this.client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        this.mapper = new ObjectMapper();
    }

    public static void main(String[] args) {
        String out = (args.length > 0) ? args[0] : "data.json";

        FetchData fetcher = new FetchData();
        try {
            fetcher.run(Paths.get(out));
            System.out.println("完成，已写入：" + out);
        } catch (Exception e) {
            System.err.println("抓取失败：" + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /** 整个抓取流程 */
    public void run(Path outPath) throws Exception {

        System.out.println("[1/3] 抓取项目列表 ...");
        JsonNode eventsDoc = getJson(BASE + "/competitions/" + COMPETITION_ID + "/events");
        Thread.sleep(INTERVAL_MS);

        // 建索引：("Women","002") -> {disciplineId, 项目全名}
        Map<String, String[]> index = new HashMap<>();
        for (JsonNode sport : eventsDoc.path("Sports")) {
            for (JsonNode dis : sport.path("DisciplineList")) {
                String k = dis.path("Gender").asText() + "|" + dis.path("Code").asText();
                index.put(k, new String[]{dis.path("Id").asText(), dis.path("DisciplineName").asText()});
            }
        }

        System.out.println("[2/3] 抓取选手名单 ...");
        JsonNode athletesDoc = getJson(BASE + "/competitions/" + COMPETITION_ID + "/athletes");
        Thread.sleep(INTERVAL_MS);

        ArrayNode players = mapper.createArrayNode();
        List<String> seenPeople = new ArrayList<>();   // 去重：一个人可能报了多个项目
        for (JsonNode country : athletesDoc) {
            for (JsonNode p : country.path("Participations")) {
                String personId = p.path("PersonId").asText();
                if (seenPeople.contains(personId)) {
                    continue;
                }
                seenPeople.add(personId);

                String last = p.path("PreferredLastName").asText("").trim();
                String first = p.path("PreferredFirstName").asText("").trim();

                ObjectNode o = players.addObject();
                o.put("fullName", (last + " " + first).trim());
                o.put("lastName", last);
                o.put("firstName", first);
                // 接口中 Gender 为数字：0=男，1=女
                o.put("gender", genderWord(p.path("Gender").asInt()));
                o.put("country", country.path("CountryName").asText());
                o.put("countryCode", country.path("CountryCode").asText());
                o.put("personId", personId);
            }
        }

        System.out.println("[3/3] 抓取八个项目的决赛成绩 ...");
        ArrayNode events = mapper.createArrayNode();

        for (String[] target : TARGETS) {
            String key = target[0], gender = target[1], code = target[2];

            String[] meta = index.get(gender + "|" + code);
            if (meta == null) {
                throw new IOException("项目列表里找不到 " + gender + "/" + code + "，接口结构可能变了");
            }

            JsonNode doc = getJson(BASE + "/events/" + meta[0]);
            Thread.sleep(INTERVAL_MS);

            // 找 Finals（决赛）那一轮。一个项目有预赛、半决赛、决赛，我们只要决赛。
            JsonNode finals = null;
            for (JsonNode heat : doc.path("Heats")) {
                if ("Finals".equals(heat.path("Name").asText())) {
                    finals = heat;
                    break;
                }
            }
            if (finals == null) {
                throw new IOException(key + " 没有 Finals 轮次，比赛可能还没结束");
            }

            ArrayNode results = mapper.createArrayNode();
            for (JsonNode r : finals.path("Results")) {
                ArrayNode dives = mapper.createArrayNode();
                for (JsonNode d : r.path("Dives")) {
                    JsonNode pts = d.path("DivePoints");
                    if (pts.isNull() || pts.isMissingNode()) {
                        throw new IOException(key + " 中 " + r.path("FullName") + " 存在缺失的跳次成绩");
                    }
                    dives.add(pts.asText());
                }

                ObjectNode o = results.addObject();
                o.put("rank", r.path("Rank").asInt());
                o.put("fullName", r.path("FullName").asText());
                // 双人项目没有单独的姓/名（FullName 是 "A / B"），接口里就是 null，照原样写 null
                putTextOrNull(o, "lastName", r.get("LastName"));
                putTextOrNull(o, "firstName", r.get("FirstName"));
                o.put("countryCode", r.path("NAT").asText());
                o.set("dives", dives);
                o.put("total", r.path("TotalPoints").asText());
            }

            ObjectNode e = events.addObject();
            e.put("key", key);
            e.put("disciplineId", meta[0]);
            e.put("name", meta[1]);
            e.put("gender", gender.equals("Women") ? "Female" : "Male");
            e.set("results", results);

            System.out.println("      " + key + " -> " + results.size() + " 条决赛成绩");
        }

        // ---- 组装最终的 data.json ----
        ObjectNode root = mapper.createObjectNode();

        ObjectNode meta = root.putObject("meta");
        meta.put("competition", eventsDoc.path("OfficialName").asText());
        meta.put("competitionId", COMPETITION_ID);
        meta.put("city", eventsDoc.path("City").asText());
        meta.put("country", eventsDoc.path("CountryName").asText());
        meta.put("dateFrom", eventsDoc.path("From").asText("").substring(0, Math.min(10, eventsDoc.path("From").asText("").length())));
        meta.put("dateTo", eventsDoc.path("To").asText("").substring(0, Math.min(10, eventsDoc.path("To").asText("").length())));
        meta.put("fetchedAt", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        ObjectNode sources = meta.putObject("sources");
        sources.put("events", BASE + "/competitions/" + COMPETITION_ID + "/events");
        sources.put("athletes", BASE + "/competitions/" + COMPETITION_ID + "/athletes");
        sources.put("event", BASE + "/events/{disciplineId}");

        root.set("players", players);
        root.set("events", events);

        // 写文件：UTF-8、缩进 2 空格、换行用 \n（不跟操作系统变）
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        pp.indentObjectsWith(new DefaultIndenter("  ", "\n"));
        pp.indentArraysWith(new DefaultIndenter("  ", "\n"));
        mapper.writer(pp).writeValue(outPath.toFile(), root);

        System.out.println("      选手 " + players.size() + " 名，项目 " + events.size() + " 个");
    }

    /**
     * 发送 GET 请求并把响应体解析为 JsonNode。超时 30 秒；非 200 视为失败；
     * 失败后按次数递增的间隔重试，最多 {@value #MAX_RETRY} 次。
     */
    private JsonNode getJson(String url) throws Exception {
        Exception last = null;

        for (int attempt = 1; attempt <= MAX_RETRY; attempt++) {
            try {
                HttpRequest req = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("User-Agent", UA)
                        .header("Accept", "application/json")
                        .timeout(Duration.ofSeconds(30))
                        .GET()
                        .build();

                HttpResponse<String> resp = client.send(req,
                        HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

                if (resp.statusCode() != 200) {
                    throw new IOException("HTTP " + resp.statusCode());
                }
                return mapper.readTree(resp.body());

            } catch (Exception e) {
                last = e;
                if (attempt < MAX_RETRY) {
                    System.out.println("      第 " + attempt + " 次请求失败（" + e.getMessage() + "），重试中 ...");
                    Thread.sleep(INTERVAL_MS * attempt * 2);   // 退避：间隔逐次加长
                }
            }
        }
        throw new IOException("请求失败 " + url + " ，原因：" + last.getMessage());
    }

    /** 接口里 Gender 是数字：0=男，1=女 */
    private static String genderWord(int genderNum) {
        return genderNum == 1 ? "Female" : "Male";
    }

    /** 字段存在且不是 null 就写字符串，否则写 null（保持和接口原样一致） */
    private static void putTextOrNull(ObjectNode node, String field, JsonNode value) {
        if (value == null || value.isNull() || value.isMissingNode()) {
            node.putNull(field);
        } else {
            node.put(field, value.asText());
        }
    }
}
