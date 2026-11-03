package com.west2online.dwasearch.core;

import com.west2online.dwasearch.model.Athlete;
import com.west2online.dwasearch.model.CompetitionData;
import com.west2online.dwasearch.model.Contest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 查询逻辑测试：排序规则与输出格式。
 */
class QueryServiceTest {

    @Test
    void playersSortedByCountryThenLastName() {
        List<Athlete> players = new ArrayList<>(List.of(
                new Athlete("Canada", "CULLEN", "Matt", "Male"),
                new Athlete("Australia", "KEENEY", "Maddison", "Female"),
                new Athlete("Australia", "COLE", "Ellie", "Female")
        ));
        QueryService qs = new QueryService(new CompetitionData(players, Map.of()));

        String out = qs.formatPlayers();

        assertTrue(out.indexOf("COLE Ellie") < out.indexOf("KEENEY Maddison"),
                "同一国籍内部应按姓氏升序");
        assertTrue(out.indexOf("KEENEY Maddison") < out.indexOf("CULLEN Matt"),
                "Australia 应排在 Canada 之前");
    }

    @Test
    void playersFormattedAsSpec() {
        List<Athlete> players = new ArrayList<>(List.of(
                new Athlete("Australia", "COLE", "Ellie", "Female")
        ));
        QueryService qs = new QueryService(new CompetitionData(players, Map.of()));

        String out = qs.formatPlayers();

        assertEquals("Full Name:COLE Ellie\nGender:Female\nCountry:Australia\n-----\n", out);
    }

    @Test
    void resultSortedByRankAndTwoDecimals() {
        Map<String, List<Contest>> results = new LinkedHashMap<>();
        // 故意乱序 + 分数故意缺位（69.0、73.5、374.4），验证排序和两位小数格式化
        results.put("women 3m springboard", new ArrayList<>(List.of(
                new Contest(2, "CHEN Yiwen", List.of("69.0", "72.00", "76.50", "79.05", "76.50"), "373.05"),
                new Contest(1, "CHEN Jia", List.of("73.5", "74.4", "76.50", "72.00", "78.0"), "374.4")
        )));
        QueryService qs = new QueryService(new CompetitionData(List.of(), results));

        String out = qs.formatResult("women 3m springboard");

        assertTrue(out.indexOf("CHEN Jia") < out.indexOf("CHEN Yiwen"), "应按排名升序");
        assertTrue(out.contains("Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40"),
                "分数应统一保留两位小数，实际输出: " + out);
    }
}
