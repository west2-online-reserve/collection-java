import java.util.Comparator;
import java.util.List;

public class CoreModule {
    private final String dataContent;
    private final String playerBlock;

    public CoreModule(String dataContent) {
        this.dataContent = dataContent;
        // 把 main 里的东西搬这里 —— load、sort、formatterPlayer,结果存 playerBlock
        List<Player> players = DataLoader.loadPlayers(dataContent);
        players.sort(Comparator.comparing(Player::getCountry).thenComparing(Player::getLastName));
        this.playerBlock = PlayerFormatter.formatterPlayer(players);   // players命令的输出，就这一段，可复用
    }

    public String handleLine(String line) {
        // 把 main 里 if-else 搬这里
        // 唯一改动:所有 out.append(X) 换成 return X(拼好的块);
        // result 成功分支:循环把各 formatResult 块拼进一个 StringBuilder 再 return
        if (line.equals("players")) {
            return this.playerBlock;
        } else if (line.equals("result") || line.startsWith("result ")) {
            // 单独的 result 也是已识别的命令（缺项目名）：下面的查询会返回空列表 → N/A。
            // 注意不能用 startsWith("result")，否则 resultwomen 3m springboard 会被误识别。
            List<String[]> rs = DataLoader.loadResults(dataContent, line);
            // 如果是空列表
            if (rs.isEmpty())
                return "N/A\n-----\n";

            StringBuilder sb = new StringBuilder();
            for (String[] r : rs) {
                sb.append(ResultFormatter.formatResult(r));
            }
            return sb.toString();
        } else {
            return "Error\n-----\n";
        }
    }
}