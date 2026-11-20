package com.west2online.dwasearch.core;

import com.west2online.dwasearch.model.Athlete;
import com.west2online.dwasearch.model.CompetitionData;
import com.west2online.dwasearch.model.Contest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 查询逻辑：players 和 result 两种查询，含排序与格式化。
 */
public class QueryService {

    private final CompetitionData data;

    public QueryService(CompetitionData data) {
        this.data = data;
    }

    /**
     * 输出全部选手。
     * 排序规则：按国籍（country）升序，再按姓氏（lastName）升序。
     */
    public String formatPlayers() {
        List<Athlete> players = new ArrayList<>(data.getPlayers());
        players.sort(Comparator
                .comparing(Athlete::getCountry)
                .thenComparing(Athlete::getLastName));

        StringBuilder sb = new StringBuilder();
        for (Athlete p : players) {
            sb.append("Full Name:").append(p.getLastName()).append(' ').append(p.getFirstName()).append('\n');
            sb.append("Gender:").append(p.getGender()).append('\n');
            sb.append("Country:").append(p.getCountry()).append('\n');
            sb.append("-----\n");
        }
        return sb.toString();
    }

    /**
     * 输出某个项目的决赛结果，按排名（rank）升序。
     */
    public String formatResult(String eventName) {
        List<Contest> contests = new ArrayList<>(data.getResults().get(eventName));
        contests.sort(Comparator.comparingInt(Contest::getRank));

        StringBuilder sb = new StringBuilder();
        for (Contest c : contests) {
            sb.append("Full Name:").append(c.getFullName()).append('\n');
            sb.append("Rank:").append(c.getRank()).append('\n');
            sb.append("Score:").append(formatScore(c)).append('\n');
            sb.append("-----\n");
        }
        return sb.toString();
    }

    /** 各跳成绩用 " + " 连接，等号后输出总分。 */
    private String formatScore(Contest c) {
        List<String> parts = new ArrayList<>();
        for (String dive : c.getDives()) {
            parts.add(twoDecimals(dive));
        }
        return String.join(" + ", parts) + " = " + twoDecimals(c.getTotalPoints());
    }

    /** 用 BigDecimal 把分数格式化成两位小数，避免二进制浮点误差。 */
    private String twoDecimals(String value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
