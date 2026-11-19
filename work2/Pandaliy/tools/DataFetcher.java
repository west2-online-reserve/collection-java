import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

// 抓取脚本：从 World Aquatics 接口取回 2026 年跳水世界杯总决赛的选手名单和 8 个项目的决赛成绩，
// 归一化后生成 src/data/data.json 与 manifest.json。DWASearch 只读生成出来的文件，不联网。
// 用法（在项目根目录执行，Gson 已打包进 jar）：
//   java -cp target/DWASearch.jar tools/DataFetcher.java [--out 目录] [--raw 目录] [--delay 毫秒] [--retries 次数] [--offline] [--refresh] [--dry-run]

public final class DataFetcher {

    private static final String API_BASE = "https://api.worldaquatics.com/fina";
    private static final String COMPETITION_ID = "5019";
    private static final int SCHEMA_VERSION = 1;
    private static final int EXPECTED_EVENTS = 8;
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(30);
    private static final Set<Integer> RETRYABLE_STATUS = Set.of(429, 500, 502, 503, 504);
    private static final List<String> EXPECTED_COMMANDS = List.of(
            "result men 10m platform",
            "result men 10m synchronised",
            "result men 3m springboard",
            "result men 3m synchronised",
            "result women 10m platform",
            "result women 10m synchronised",
            "result women 3m springboard",
            "result women 3m synchronised");

    private static final String USER_AGENT = "west2-learn-backend-round1-fetcher/1.1 (personal study)";
    private static final String USAGE = """
            用法: java tools/DataFetcher.java [选项]

              --out <目录>     输出目录（默认 src/data）
              --raw <目录>     原始响应缓存目录（默认 <out>/raw）
              --delay <毫秒>   两次真实请求之间的间隔（默认 800）
              --retries <次数> 单个请求最多尝试次数（默认 4）
              --offline        只读缓存，不联网
              --refresh        忽略缓存，全部重新请求
              --dry-run        只抓取并校验，不写文件
              -h, --help       显示本帮助
            """;

    // 解析后的命令行选项
    private record Options(Path outDir, Path rawDir, long delayMs, int maxAttempts,
                           boolean offline, boolean refresh, boolean dryRun) {
    }

    private static final class UsageException extends RuntimeException {
        UsageException(String message) {
            super(message);
        }
    }

    private final Options options;
    private final HttpClient client;
    private final Gson gson;

    private int requestsSent;

    // 15 秒连接超时，输出带缩进，"&" 保持原样
    private DataFetcher(Options options) {
        this.options = options;
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()      // 输出带缩进，人能看懂
                .disableHtmlEscaping()    // 保持 "A & B" 里的 & 原样输出
                .create();
    }

    // --help 直接打印用法，其余交给 run()
    public static void main(String[] args) {
        if (args.length == 1 && ("--help".equals(args[0]) || "-h".equals(args[0]))) {
            System.out.println(USAGE);
            return;
        }
        try {
            new DataFetcher(parseOptions(args)).run();
        } catch (UsageException e) {
            System.err.println("参数错误: " + e.getMessage());
            System.err.println();
            System.err.println(USAGE);
            System.exit(2);
        } catch (Exception e) {
            System.err.println("抓取失败: " + explain(e));
            System.err.println("已放弃这次运行；输出目录里的旧文件保持原样，没有被替换。");
            System.exit(1);
        }
    }

    // 逐个读参数，没写到的用默认值
    private static Options parseOptions(String[] args) {
        Path outDir = Path.of("src", "data");
        Path rawDir = null;
        long delayMs = 800;
        int maxAttempts = 4;
        boolean offline = false;
        boolean refresh = false;
        boolean dryRun = false;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "--out" -> outDir = Path.of(valueOf(args, ++i, arg));
                case "--raw" -> rawDir = Path.of(valueOf(args, ++i, arg));
                case "--delay" -> delayMs = number(valueOf(args, ++i, arg), arg, 0, 60_000);
                case "--retries" -> maxAttempts = (int) number(valueOf(args, ++i, arg), arg, 1, 10);
                case "--offline" -> offline = true;
                case "--refresh" -> refresh = true;
                case "--dry-run" -> dryRun = true;
                default -> throw new UsageException("未知选项 " + arg);
            }
        }
        if (offline && refresh) {
            throw new UsageException("--offline 与 --refresh 不能同时使用（一个只用缓存，一个忽略缓存）");
        }
        if (rawDir == null) {
            rawDir = outDir.resolve("raw");
        }
        return new Options(outDir, rawDir, delayMs, maxAttempts, offline, refresh, dryRun);
    }

    // 取选项后面那个值，缺了就报用法错误
    private static String valueOf(String[] args, int index, String option) {
        if (index >= args.length) {
            throw new UsageException(option + " 后面缺少参数值");
        }
        return args[index];
    }

    // 解析成整数并检查范围
    private static long number(String text, String option, long min, long max) {
        long value;
        try {
            value = Long.parseLong(text);
        } catch (NumberFormatException e) {
            throw new UsageException(option + " 的值必须是整数，收到的是 " + text);
        }
        if (value < min || value > max) {
            throw new UsageException(option + " 的值必须在 " + min + " 到 " + max + " 之间，收到的是 " + value);
        }
        return value;
    }

    // 取数 → 归一化 → 自校验 → 原子写盘
    private void run() throws IOException, InterruptedException {
        System.out.println("赛事 ID: " + COMPETITION_ID);
        System.out.println("输出目录: " + options.outDir().toAbsolutePath());
        System.out.println("缓存目录: " + options.rawDir().toAbsolutePath());

        // 赛事信息 + 项目清单
        JsonObject competition = fetchJson(
                "/competitions/" + COMPETITION_ID + "/events", "competition.json").getAsJsonObject();
        System.out.println("赛事名称: " + str(competition, "OfficialName"));

        // 选手名单
        JsonArray athletes = fetchJson(
                "/competitions/" + COMPETITION_ID + "/athletes", "athletes.json").getAsJsonArray();

        // 逐个项目的决赛成绩（先筛出项目，才知道要请求哪些 ID）
        List<JsonObject> disciplines = divingDisciplines(competition);
        System.out.println("跳水项目数: " + disciplines.size());
        List<JsonObject> events = new ArrayList<>();
        for (JsonObject discipline : disciplines) {
            String eventId = str(discipline, "Id");
            JsonObject event = normalizeEvent(discipline,
                    fetchJson("/events/" + eventId, "event-" + eventId + ".json").getAsJsonObject());
            events.add(event);
            System.out.println("  " + str(event, "command") + "  " + array(event, "results").size() + " 条");
        }
        events.sort(Comparator.comparing(event -> str(event, "command")));

        // 归一化选手
        JsonArray players = normalizePlayers(athletes);

        // 写盘之前先自校验，不合格就不动旧文件
        int resultCount = validate(players, events);
        String json = gson.toJson(buildDocument(competition, players, events)) + "\n";
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        String sha256 = sha256(bytes);

        if (options.dryRun()) {
            System.out.println();
            System.out.println("[dry-run] 校验通过：" + players.size() + " 选手 / "
                    + events.size() + " 项目 / " + resultCount + " 条成绩；未写任何文件");
            return;
        }

        Path dataPath = options.outDir().resolve("data.json");
        Path manifestPath = options.outDir().resolve("manifest.json");
        Files.createDirectories(options.outDir());
        writeAtomically(dataPath, bytes);
        writeAtomically(manifestPath, (gson.toJson(
                buildManifest(sha256, players, events, resultCount)) + "\n").getBytes(StandardCharsets.UTF_8));

        System.out.println();
        System.out.println("已写出 " + dataPath.toAbsolutePath());
        System.out.println("  选手 " + players.size() + " 人 / 项目 " + events.size()
                + " 个 / 成绩 " + resultCount + " 条");
        System.out.println("  sha256 " + sha256);
        System.out.println("已写出 " + manifestPath.toAbsolutePath());
    }

    // 拼 data.json：版本、赛事、选手、项目
    private JsonObject buildDocument(JsonObject competition, JsonArray players, List<JsonObject> events) {
        JsonObject root = new JsonObject();
        root.addProperty("schemaVersion", SCHEMA_VERSION);

        JsonObject competitionNode = new JsonObject();
        competitionNode.addProperty("id", COMPETITION_ID);
        competitionNode.addProperty("name", str(competition, "OfficialName"));
        competitionNode.addProperty("from", str(competition, "From").substring(0, 10));
        competitionNode.addProperty("to", str(competition, "To").substring(0, 10));
        root.add("competition", competitionNode);

        root.add("players", players);
        JsonArray eventsNode = new JsonArray();
        events.forEach(eventsNode::add);
        root.add("events", eventsNode);
        return root;
    }

    // 拼 manifest.json：来源、时间、数量、sha256
    private JsonObject buildManifest(String sha256, JsonArray players, List<JsonObject> events, int resultCount) {
        JsonObject manifest = new JsonObject();
        manifest.addProperty("schemaVersion", SCHEMA_VERSION);
        manifest.addProperty("retrievedAt", Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());

        JsonObject source = new JsonObject();
        source.addProperty("provider", "World Aquatics");
        source.addProperty("competitionEndpoint", API_BASE + "/competitions/" + COMPETITION_ID + "/events");
        source.addProperty("athletesEndpoint", API_BASE + "/competitions/" + COMPETITION_ID + "/athletes");
        source.addProperty("eventEndpointTemplate", API_BASE + "/events/{eventId}");
        manifest.add("source", source);

        JsonObject counts = new JsonObject();
        counts.addProperty("players", players.size());
        counts.addProperty("events", events.size());
        counts.addProperty("results", resultCount);
        manifest.add("counts", counts);

        JsonObject files = new JsonObject();
        JsonObject dataFile = new JsonObject();
        dataFile.addProperty("sha256", sha256);
        files.add("data.json", dataFile);
        manifest.add("files", files);
        return manifest;
    }

    // 数量、项目集合、排名连续性，有一条不对就不写盘
    private static int validate(JsonArray players, List<JsonObject> events) {
        if (events.size() != EXPECTED_EVENTS) {
            throw new IllegalStateException("项目数应为 " + EXPECTED_EVENTS + "，实际 " + events.size()
                    + "；官网可能改了项目设置");
        }
        List<String> actualCommands = events.stream().map(event -> str(event, "command")).toList();
        if (!actualCommands.equals(EXPECTED_COMMANDS)) {
            throw new IllegalStateException("项目名称与规定的 8 个关键字不一致，实际为: " + actualCommands);
        }
        if (players.isEmpty()) {
            throw new IllegalStateException("选手名单为空");
        }
        Set<String> names = new HashSet<>();
        for (JsonElement element : players) {
            JsonObject player = element.getAsJsonObject();
            String name = str(player, "fullName");
            if (name == null || name.isBlank()) {
                throw new IllegalStateException("存在没有姓名的选手");
            }
            if (!names.add(name)) {
                throw new IllegalStateException("选手姓名重复: " + name);
            }
        }

        int resultCount = 0;
        for (JsonObject event : events) {
            String command = str(event, "command");
            JsonArray results = array(event, "results");
            if (results.isEmpty()) {
                throw new IllegalStateException(command + " 没有任何决赛成绩");
            }
            int expectedRank = 1;
            for (JsonElement element : results) {
                JsonObject result = element.getAsJsonObject();
                String name = str(result, "fullName");
                if (name == null || name.isBlank()) {
                    throw new IllegalStateException(command + " 中有成绩缺少姓名");
                }
                if (array(result, "scores").isEmpty()) {
                    throw new IllegalStateException(command + " 中 " + name + " 没有任何一跳成绩");
                }
                if (str(result, "totalPoints") == null) {
                    throw new IllegalStateException(command + " 中 " + name + " 缺少总分");
                }
                JsonElement rank = result.get("rank");
                if (rank == null || rank.isJsonNull()) {
                    System.err.println("  [提醒] " + command + " 中 " + name + " 没有排名，已排到末尾");
                } else {
                    if (rank.getAsInt() != expectedRank) {
                        throw new IllegalStateException(command + " 的排名不连续：期望 " + expectedRank
                                + "，实际 " + rank.getAsInt() + "（" + name + "）");
                    }
                    expectedRank++;
                }
                resultCount++;
            }
        }
        return resultCount;
    }

    // 有缓存读缓存，没有就下载并写缓存
    private JsonElement fetchJson(String path, String cacheName) throws IOException, InterruptedException {
        Path cacheFile = options.rawDir().resolve(cacheName);
        if (!options.refresh() && Files.isRegularFile(cacheFile)) {
            System.out.println("  [缓存] " + cacheName);
            return JsonParser.parseString(Files.readString(cacheFile, StandardCharsets.UTF_8));
        }
        if (options.offline()) {
            throw new IllegalStateException("--offline 模式下缺少缓存文件: " + cacheFile.toAbsolutePath());
        }
        String body = request(path);
        Files.createDirectories(cacheFile.getParent());
        Files.writeString(cacheFile, body, StandardCharsets.UTF_8);
        System.out.println("  [下载] " + cacheName + "（" + body.length() + " 字符）");
        return JsonParser.parseString(body);
    }

    // 两次请求之间 sleep，5xx/429 退避重试
    private String request(String path) throws InterruptedException {
        if (requestsSent > 0 && options.delayMs() > 0) {
            sleep(options.delayMs());
        }
        requestsSent++;

        HttpRequest request = HttpRequest.newBuilder(URI.create(API_BASE + path))
                .header("User-Agent", USER_AGENT)
                .header("Accept", "application/json")
                .timeout(REQUEST_TIMEOUT)
                .GET()
                .build();

        String lastProblem = null;
        for (int attempt = 1; attempt <= options.maxAttempts(); attempt++) {
            try {
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
                if (response.statusCode() == 200) {
                    return response.body();
                }
                lastProblem = "HTTP " + response.statusCode();
                if (!RETRYABLE_STATUS.contains(response.statusCode())) {
                    throw new IllegalStateException("GET " + path + " 返回 " + lastProblem
                            + "（该状态码重试没有意义，检查路径或访问权限）");
                }
            } catch (IOException e) {
                lastProblem = e.getClass().getSimpleName() + ": " + e.getMessage();
            }
            if (attempt < options.maxAttempts()) {
                long backoffMs = (1L << attempt) * 1000;
                System.err.println("  第 " + attempt + " 次失败（" + lastProblem + "），"
                        + backoffMs + " ms 后重试");
                sleep(backoffMs);
            }
        }
        throw new IllegalStateException("GET " + path + " 连续失败 " + options.maxAttempts()
                + " 次，最后一次原因: " + lastProblem + "（检查网络、代理或官网是否可用）");
    }

    // 只要跳水（DV）的男子、女子项目，混合团体排除
    private static List<JsonObject> divingDisciplines(JsonObject competition) {
        JsonObject diving = null;
        for (JsonElement sport : array(competition, "Sports")) {
            JsonObject node = sport.getAsJsonObject();
            if ("DV".equals(str(node, "Code"))) {
                diving = node;
            }
        }
        if (diving == null) {
            throw new IllegalStateException("赛事响应里没有跳水（DV）项目，官网可能改了分组编码");
        }
        List<JsonObject> disciplines = new ArrayList<>();
        for (JsonElement element : array(diving, "DisciplineList")) {
            JsonObject discipline = element.getAsJsonObject();
            String gender = str(discipline, "Gender");
            if ("Men".equals(gender) || "Women".equals(gender)) {
                disciplines.add(discipline);   // 过滤掉 Mixed 混合团体
            }
        }
        return disciplines;
    }

    // 按国家分组展开成选手，姓名+性别+国家码去重
    private static JsonArray normalizePlayers(JsonArray groups) {
        Map<String, JsonObject> unique = new LinkedHashMap<>();
        for (JsonElement element : groups) {
            JsonObject group = element.getAsJsonObject();
            String country = str(group, "CountryName");
            String countryCode = str(group, "CountryCode");
            for (JsonElement participation : array(group, "Participations")) {
                JsonObject athlete = participation.getAsJsonObject();
                if (!"DV".equals(str(athlete, "Sports"))) {
                    continue;   // 同一赛事里还有游泳、花样游泳等其它项目的运动员
                }
                String fullName = (str(athlete, "PreferredLastName") + " "
                        + str(athlete, "PreferredFirstName")).trim();
                JsonElement gender = athlete.get("Gender");
                if (gender == null || gender.isJsonNull()) {
                    throw new IllegalStateException("选手 " + fullName + " 缺少性别字段");
                }
                String genderText = gender.getAsInt() == 0 ? "Male" : "Female";

                JsonObject player = new JsonObject();
                player.addProperty("fullName", fullName);
                player.addProperty("gender", genderText);
                player.addProperty("country", country);
                player.addProperty("countryCode", countryCode);
                unique.putIfAbsent(fullName + "|" + genderText + "|" + countryCode, player);
            }
        }
        List<JsonObject> players = new ArrayList<>(unique.values());
        players.sort(Comparator
                .comparing((JsonObject player) -> str(player, "country"))
                .thenComparing(player -> str(player, "fullName")));
        JsonArray result = new JsonArray();
        players.forEach(result::add);
        return result;
    }

    // 从 Heats 里找 Finals，抽出每跳分数和总分
    private static JsonObject normalizeEvent(JsonObject discipline, JsonObject eventData) {
        String eventId = str(discipline, "Id");
        JsonObject finals = null;
        for (JsonElement element : array(eventData, "Heats")) {
            JsonObject heat = element.getAsJsonObject();
            if ("Finals".equals(str(heat, "PhaseName"))) {
                finals = heat;
            }
        }
        if (finals == null) {
            throw new IllegalStateException("项目 " + eventId + " 没有决赛（Finals）数据");
        }

        List<JsonObject> results = new ArrayList<>();
        for (JsonElement element : array(finals, "Results")) {
            JsonObject raw = element.getAsJsonObject();
            JsonObject result = new JsonObject();
            result.addProperty("fullName", displayName(raw));
            JsonElement rank = raw.get("Rank");
            result.add("rank", (rank == null || rank.isJsonNull()) ? JsonNull.INSTANCE : rank);
            result.addProperty("countryCode", str(raw, "NAT"));
            JsonArray scores = new JsonArray();
            for (JsonElement dive : array(raw, "Dives")) {
                scores.add(str(dive.getAsJsonObject(), "DivePoints"));
            }
            if (scores.isEmpty()) {
                throw new IllegalStateException("项目 " + eventId + " 中 "
                        + str(raw, "FullName") + " 没有跳次数据");
            }
            result.add("scores", scores);
            result.addProperty("totalPoints", str(raw, "TotalPoints"));
            results.add(result);
        }
        if (results.isEmpty()) {
            throw new IllegalStateException("项目 " + eventId + " 的决赛没有成绩记录");
        }
        // 按排名升序，缺失排名放最后
        results.sort(Comparator.comparingInt(result ->
                result.get("rank").isJsonNull() ? Integer.MAX_VALUE : result.get("rank").getAsInt()));

        JsonObject event = new JsonObject();
        event.addProperty("command", commandFor(discipline));
        event.addProperty("eventId", eventId);
        event.addProperty("gender", str(discipline, "Gender"));
        event.addProperty("discipline", str(discipline, "Name"));
        event.addProperty("eventType", eventTypeOf(discipline));
        JsonArray resultsNode = new JsonArray();
        results.forEach(resultsNode::add);
        event.add("results", resultsNode);
        return event;
    }

    // 拼查询关键字 result <性别> <板台> [synchronised]
    private static String commandFor(JsonObject discipline) {
        return "result " + str(discipline, "Gender").toLowerCase(Locale.ROOT)
                + " " + str(discipline, "Name") + " " + eventTypeOf(discipline);
    }

    // Code 以 2 开头是双人，10m 是跳台，其余是跳板
    private static String eventTypeOf(JsonObject discipline) {
        if (str(discipline, "Code").startsWith("2")) {
            return "synchronised";
        }
        return "10m".equals(str(discipline, "Name")) ? "platform" : "springboard";
    }

    // 双人拼成 "A & B"，单人用官方全名
    private static String displayName(JsonObject result) {
        JsonArray competitors = array(result, "Competitors");
        if (competitors.size() > 0) {
            List<String> names = new ArrayList<>();
            for (JsonElement element : competitors) {
                JsonObject competitor = element.getAsJsonObject();
                names.add((str(competitor, "LastName") + " " + str(competitor, "FirstName")).trim());
            }
            names.sort(Comparator.naturalOrder());
            return String.join(" & ", names);
        }
        String fullName = str(result, "FullName");
        return fullName != null ? fullName
                : (str(result, "LastName") + " " + str(result, "FirstName")).trim();
    }

    // 先写 .tmp 再改名，中途失败不会留下半个文件
    private static void writeAtomically(Path target, byte[] content) throws IOException {
        Path temp = target.resolveSibling(target.getFileName() + ".tmp");
        Files.write(temp, content);
        try {
            Files.move(temp, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(temp, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    // 把异常链拼成一句，方便看是哪一层出的问题
    private static String explain(Throwable error) {
        String message = error.getMessage();
        StringBuilder text = new StringBuilder(message == null ? error.getClass().getSimpleName() : message);
        Throwable cause = error.getCause();
        while (cause != null) {
            String causeMessage = cause.getMessage();
            if (causeMessage != null && !causeMessage.isBlank() && !text.toString().contains(causeMessage)) {
                text.append(" ← ").append(causeMessage);
            }
            cause = cause.getCause();
        }
        return text.toString();
    }

    // 取数组字段，缺失时给空数组
    private static JsonArray array(JsonObject object, String key) {
        JsonElement element = object.get(key);
        return (element == null || !element.isJsonArray()) ? new JsonArray() : element.getAsJsonArray();
    }

    // 取字符串字段，缺失时给 null
    private static String str(JsonObject object, String key) {
        JsonElement element = object.get(key);
        return (element == null || element.isJsonNull()) ? null : element.getAsString();
    }

    // 算即将写入内容的哈希，不依赖文件是否已落盘
    private static String sha256(byte[] content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(digest.digest(content));
        } catch (Exception e) {
            throw new IllegalStateException("无法计算 SHA-256", e);
        }
    }

    // 被中断时恢复中断标记再抛，别把信号吞掉
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();   // 恢复中断标记，别把信号吞掉
            throw new IllegalStateException("抓取被中断", e);
        }
    }
}
