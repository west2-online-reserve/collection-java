package dwasearch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 指令解析器：把输入的一行指令转换成对应的输出文本。
 *
 * <p>拆成独立类，是为了让查询与格式化逻辑可以被直接实例化调用，
 * 单元测试无需启动整个命令行程序。
 */
public class CommandRunner {

    /** 合法的项目名，必须与输入完全一致（区分大小写、空格数量一致）。 */
    private static final Set<String> EVENT_KEYS = Set.of(
            "women 3m springboard",
            "women 10m platform",
            "women 3m synchronised",
            "women 10m synchronised",
            "men 3m springboard",
            "men 10m platform",
            "men 3m synchronised",
            "men 10m synchronised"
    );

    /** 记录结束标记，每条记录之后都要输出，包括最后一条。 */
    private static final String SEPARATOR = "-----\n";

    private final CompetitionData data;

    public CommandRunner(CompetitionData data) {
        this.data = data;
    }

    /**
     * 处理一行指令，返回该指令对应的输出文本。
     *
     * <p>判定顺序有优先级：{@code result} 必须早于 {@code result <项目名>} 判断，
     * 否则单独一个 {@code result} 会落进前缀匹配分支。
     */
    public String runLine(String rawLine) {

        // 去掉行尾的 \r（Windows 文本文件是 CRLF，readLine 会留下它）
        String line = rawLine.replace("\r", "");

        // 空行视为"这一行没有指令"，忽略；输出空字符串，不产生任何记录
        if (line.trim().isEmpty()) {
            return "";
        }

        if (line.equals("players")) {
            return formatPlayers();
        }

        if (line.equals("result")) {
            return error("N/A");
        }

        if (line.startsWith("result ")) {
            String name = line.substring("result ".length());
            if (EVENT_KEYS.contains(name)) {
                return formatResult(name);
            }
            return error("N/A");
        }

        return error("Error");
    }

    /** 输出全部选手：按国家升序，同国家按姓氏升序。 */
    public String formatPlayers() {

        List<Player> sorted = new ArrayList<>(data.players);
        sorted.sort(
                Comparator.comparing((Player p) -> p.country)
                        .thenComparing(p -> p.lastName)
        );

        StringBuilder sb = new StringBuilder();
        for (Player p : sorted) {
            sb.append("Full Name:").append(p.fullName).append("\n");
            sb.append("Gender:").append(p.gender).append("\n");
            sb.append("Country:").append(p.country).append("\n");
            sb.append(SEPARATOR);
        }
        return sb.toString();
    }

    /** 输出指定项目的决赛成绩，按名次升序。 */
    public String formatResult(String eventKey) {

        CompetitionData.Event event = null;
        for (CompetitionData.Event e : data.events) {
            if (e.key.equals(eventKey)) {
                event = e;
                break;
            }
        }
        if (event == null) {
            return error("N/A");
        }

        List<EventResult> sorted = new ArrayList<>(event.results);
        sorted.sort(Comparator.comparingInt(r -> r.rank));

        StringBuilder sb = new StringBuilder();
        for (EventResult r : sorted) {
            sb.append("Full Name:").append(r.fullName).append("\n");
            sb.append("Rank:").append(r.rank).append("\n");
            sb.append("Score:").append(joinDives(r.dives))
                    .append(" = ").append(twoDecimals(r.total)).append("\n");
            sb.append(SEPARATOR);
        }
        return sb.toString();
    }

    /** 把每一跳的分数用 " + " 连接起来，每个分数统一保留两位小数。 */
    private String joinDives(List<String> dives) {
        return dives.stream()
                .map(CommandRunner::twoDecimals)
                .collect(Collectors.joining(" + "));
    }

    /** 用 BigDecimal 格式化，避免 double 的二进制浮点误差。 */
    private static String twoDecimals(String value) {
        return new BigDecimal(value)
                .setScale(2, RoundingMode.HALF_UP)
                .toPlainString();
    }

    /** 错误输出：错误信息一行，后接分隔线。 */
    private String error(String message) {
        return message + "\n" + SEPARATOR;
    }
}
