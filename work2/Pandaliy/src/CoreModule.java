import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

// 查询与输出。数据从 DataStore 拿，自己不碰文件，方便测试直接调。
public class CoreModule {

    private static final String PLAYER_OUTPUT_FORMAT =
            "Full Name:%s\nGender:%s\nCountry:%s\n-----\n";

    private static final String RESULT_OUTPUT_FORMAT =
            "Full Name:%s\nRank:%d\nScore:%s = %s\n-----\n";

    private static final String EVENT_NOT_FOUND_OUTPUT = "N/A\n-----\n";

    private final PrintWriter output;

    // 默认输出到控制台
    public CoreModule() {
        this(new PrintWriter(
                new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true));
    }

    // 指定输出目标，测试时传内存 Writer
    public CoreModule(PrintWriter output) {
        this.output = Objects.requireNonNull(output);
    }

    // 全部选手，按国籍、姓名升序（不依赖 data.json 里的顺序）
    public List<Player> getAllPlayers() {
        return sortPlayers(DataStore.getPlayersByFullName().values());
    }

    // 国籍升序，同国籍按姓名升序
    static List<Player> sortPlayers(Collection<Player> players) {
        return players.stream()
                .sorted(Comparator.comparing(Player::country).thenComparing(Player::fullName))
                .toList();
    }

    // 全部项目，顺序跟 data.json 里一致
    public List<Event> getEvents() {
        return List.copyOf(DataStore.getEventsByCommand().values());
    }

    // 输出所有选手
    public void displayAllPlayersInfo() {
        for (Player player : getAllPlayers()) {
            output.printf(PLAYER_OUTPUT_FORMAT,
                    player.fullName(),
                    player.gender(),
                    player.country());
        }
    }

    // 输出一个项目的成绩，按名次
    public void displayResultsForEvent(Event event) {
        for (Result result : sortResults(event.results())) {
            output.printf(RESULT_OUTPUT_FORMAT,
                    result.fullName(),
                    result.rank(),
                    String.join(" + ", result.scores()),
                    result.totalPoints());
        }
    }

    // 名次升序
    static List<Result> sortResults(List<Result> results) {
        return results.stream()
                .sorted(Comparator.comparingInt(Result::rank))
                .toList();
    }

    // 按命令查项目，查不到输出 N/A
    public void displayEventResult(String command) {
        for (Event event : getEvents()) {
            if (event.command().equals(command)) {
                displayResultsForEvent(event);
                return;
            }
        }
        displayNotFoundEvent();
    }

    // 输出 N/A 加分隔线
    public void displayNotFoundEvent() {
        output.print(EVENT_NOT_FOUND_OUTPUT);
    }
}
