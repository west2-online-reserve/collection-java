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
        } else if (line.startsWith("result ")) { // 匹配 “ results ”
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