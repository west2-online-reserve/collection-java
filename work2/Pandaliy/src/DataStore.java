import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// data.json 的读取、解析和缓存。进程内只读一次文件，之后的命令都走缓存。
public final class DataStore {

    private static final Path DATA_FILE = Path.of("src", "data", "data.json");

    private static final Gson GSON = new Gson();

    private static CompetitionData cache;
    private static Map<String, Event> eventsByCommand;
    private static Map<String, Player> playersByFullName;

    private DataStore() {
    }

    // 读文件、校验、建两份索引，全部成功后再替换缓存
    public static synchronized void loadDataToCache() {
        CompetitionData parsed = parseDataFile();
        Map<String, Event> commandIndex = indexEventsByCommand(parsed.events());
        Map<String, Player> playerIndex = indexPlayersByFullName(parsed.players());

        // 三个字段一起赋值：索引建到一半失败时，缓存还是空的
        cache = parsed;
        eventsByCommand = commandIndex;
        playersByFullName = playerIndex;
    }

    // 整场比赛数据，第一次调用时才读文件
    public static synchronized CompetitionData getCache() {
        ensureLoaded();
        return cache;
    }

    // command 到项目的索引
    public static synchronized Map<String, Event> getEventsByCommand() {
        ensureLoaded();
        return eventsByCommand;
    }

    // fullName 到选手的索引
    public static synchronized Map<String, Player> getPlayersByFullName() {
        ensureLoaded();
        return playersByFullName;
    }

    // 缓存为空就先加载
    private static void ensureLoaded() {
        if (cache == null) {
            loadDataToCache();
        }
    }

    // 文件存在性检查、打开、解析，IO 异常包一层
    private static CompetitionData parseDataFile() {
        if (!Files.isRegularFile(DATA_FILE)) {
            throw new IllegalStateException(
                    "找不到比赛数据文件（当前工作目录: "
                            + Path.of("").toAbsolutePath()
                            + "）: " + DATA_FILE);
        }

        try (Reader reader = Files.newBufferedReader(DATA_FILE, StandardCharsets.UTF_8)) {
            return parse(reader);
        } catch (IOException e) {
            throw new UncheckedIOException("读取比赛数据失败: " + DATA_FILE.toAbsolutePath(), e);
        }
    }

    // 解析、校验、冻结。不碰文件，测试可以直接喂字符串
    static CompetitionData parse(Reader reader) {
        CompetitionData data = GSON.fromJson(reader, CompetitionData.class);
        if (data == null || data.competition() == null
                || data.players() == null || data.events() == null) {
            throw new IllegalStateException("data.json 内容不符合比赛数据结构");
        }
        validate(data);
        return freeze(data);
    }

    // 查询要用到的字段逐个检查，缺了就报错，不让 null 混进输出
    private static void validate(CompetitionData data) {
        for (Player player : data.players()) {
            if (player == null) {
                throw new IllegalStateException("players 中存在空条目");
            }
            String owner = "选手 " + describe(player.fullName());
            requireField(player.fullName(), "fullName", owner);
            requireField(player.gender(), "gender", owner);
            requireField(player.country(), "country", owner);
            requireField(player.countryCode(), "countryCode", owner);
        }

        for (Event event : data.events()) {
            if (event == null) {
                throw new IllegalStateException("events 中存在空条目");
            }
            String command = event.command();
            if (command == null || command.isBlank()) {
                throw new IllegalStateException("存在缺少 command 的比赛项目");
            }
            if (event.results() == null) {
                throw new IllegalStateException("项目 " + command + " 缺少 results");
            }
            for (Result result : event.results()) {
                if (result == null) {
                    throw new IllegalStateException("项目 " + command + " 的 results 中存在空条目");
                }
                String owner = "项目 " + command + " 中 " + describe(result.fullName());
                requireField(result.fullName(), "fullName", owner);
                if (result.rank() < 1) {
                    throw new IllegalStateException(owner + " 的 rank 不合法: " + result.rank());
                }
                requireField(result.countryCode(), "countryCode", owner);
                if (result.scores() == null || result.scores().isEmpty()) {
                    throw new IllegalStateException(owner + " 缺少 scores");
                }
                for (String score : result.scores()) {
                    requireField(score, "scores", owner);
                }
                requireField(result.totalPoints(), "totalPoints", owner);
            }
        }
    }

    // 字段名缺失时也要能定位到是哪条数据，所以把名字拼进提示
    private static String describe(String name) {
        return (name == null || name.isBlank()) ? "(缺少姓名)" : name;
    }

    private static void requireField(String value, String field, String owner) {
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(owner + " 缺少 " + field);
        }
    }

    // 把 Gson 解析出来的可变 List 换成不可变副本，免得缓存被调用方改掉
    private static CompetitionData freeze(CompetitionData data) {
        List<Event> events = data.events().stream()
                .map(event -> new Event(
                        event.command(),
                        event.eventId(),
                        event.gender(),
                        event.discipline(),
                        event.eventType(),
                        event.results().stream()
                                .map(result -> new Result(
                                        result.fullName(),
                                        result.rank(),
                                        result.countryCode(),
                                        List.copyOf(result.scores()),
                                        result.totalPoints()))
                                .toList()))
                .toList();
        return new CompetitionData(
                data.schemaVersion(),
                data.competition(),
                List.copyOf(data.players()),
                events);
    }

    // command 重复会让查询串项目，直接当成数据错误
    private static Map<String, Event> indexEventsByCommand(List<Event> events) {
        Map<String, Event> index = new LinkedHashMap<>();
        for (Event event : events) {
            String command = event.command();
            if (command == null || command.isBlank()) {
                throw new IllegalStateException("存在缺少 command 的比赛项目");
            }
            if (index.putIfAbsent(command, event) != null) {
                throw new IllegalStateException("存在重复的 command: " + command);
            }
        }
        return Collections.unmodifiableMap(index);
    }

    // fullName 用来做唯一索引，重名的话后者会把前者顶掉，所以也要报错
    private static Map<String, Player> indexPlayersByFullName(List<Player> players) {
        Map<String, Player> index = new LinkedHashMap<>();
        for (Player player : players) {
            String fullName = player.fullName();
            if (fullName == null || fullName.isBlank()) {
                throw new IllegalStateException("存在缺少 fullName 的选手");
            }
            if (index.putIfAbsent(fullName, player) != null) {
                throw new IllegalStateException("存在重复的 fullName: " + fullName);
            }
        }
        return Collections.unmodifiableMap(index);
    }
}
