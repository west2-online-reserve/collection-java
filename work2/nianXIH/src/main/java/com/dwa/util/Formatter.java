// 输出格式化器 —— 负责将选手列表和赛事结果格式化为题目要求的文本格式，
// 包含：formatPlayers(List<Player>) 按国籍升序、Last Name 升序排序后输出，
// formatEventResult(Event) 输出决赛排名与分数（BigDecimal 精确计算），
// 每条记录后跟 "-----" 分割线；所有输出使用 \n 换行，编码 UTF-8。
package com.dwa.util;

import com.dwa.model.Event;
import com.dwa.model.Player;
import com.dwa.model.ResultEntry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Formatter {

    private static final String SEPARATOR = "-----";

    /**
     * 格式化所有选手信息：按国籍升序、Last Name 升序排列，
     * 每人输出 Full Name / Gender / Country，后跟 ----- 分割线。
     */
    public static String formatPlayers(List<Player> players) {
        List<Player> sorted = new ArrayList<>(players);
        sorted.sort(Comparator
                .comparing(Player::getCountry)
                .thenComparing(Player::getLastName));

        StringBuilder sb = new StringBuilder();
        for (Player p : sorted) {
            sb.append("Full Name:").append(p.getFullName()).append('\n');
            sb.append("Gender:").append(p.getGender()).append('\n');
            sb.append("Country:").append(p.getCountry()).append('\n');
            sb.append(SEPARATOR).append('\n');
        }
        return sb.toString();
    }

    /**
     * 格式化赛事决赛结果：按 rank 升序输出，
     * 每人输出 Full Name / Rank / Score（格式：s1 + s2 + ... = total），
     * 每条记录后跟 ----- 分割线。分数使用 BigDecimal 精确计算。
     */
    public static String formatEventResult(Event event) {
        StringBuilder sb = new StringBuilder();
        for (ResultEntry r : event.getResults()) {
            sb.append("Full Name:").append(r.getFullName()).append('\n');
            sb.append("Rank:").append(r.getRank()).append('\n');
            sb.append("Score:").append(formatScore(r)).append('\n');
            sb.append(SEPARATOR).append('\n');
        }
        return sb.toString();
    }

    /**
     * 将 scores 列表格式化为 "s1 + s2 + ... = total" 形式，
     * 使用 BigDecimal 确保精确计算。
     */
    private static String formatScore(ResultEntry r) {
        List<String> scores = r.getScores();
        StringBuilder expr = new StringBuilder();
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < scores.size(); i++) {
            BigDecimal val = new BigDecimal(scores.get(i));
            total = total.add(val);
            if (i > 0) expr.append(" + ");
            expr.append(scores.get(i));
        }
        expr.append(" = ").append(total.toPlainString());
        return expr.toString();
    }
}
