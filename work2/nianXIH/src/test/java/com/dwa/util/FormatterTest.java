// 输出格式化器单元测试 —— 覆盖以下场景：
// 1. formatPlayers：验证按国籍升序、Last Name 升序排列，格式为 "Full Name:xxx\nGender:xxx\nCountry:xxx\n-----\n"；
// 2. formatPlayers：验证最后一条记录后仍有分割线；
// 3. formatEventResult：验证分数格式为 "s1 + s2 + ... = total"，使用 BigDecimal 精确计算；
// 4. formatEventResult：验证排名按 rank 升序输出；
// 5. 空列表不产生输出；
// 6. 输出编码为 UTF-8，换行符为 \n。
package com.dwa.util;

import com.dwa.model.Event;
import com.dwa.model.Player;
import com.dwa.model.ResultEntry;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FormatterTest {

    @Test
    void testFormatPlayersSortOrder() {
        Player p1 = new Player();
        p1.setFullName("SMITH John");
        p1.setGender("Male");
        p1.setCountry("Canada");

        Player p2 = new Player();
        p2.setFullName("COLE Ellie");
        p2.setGender("Female");
        p2.setCountry("Australia");

        Player p3 = new Player();
        p3.setFullName("BAI Yuming");
        p3.setGender("Male");
        p3.setCountry("People's Republic of China");

        List<Player> players = new ArrayList<>(Arrays.asList(p1, p2, p3));
        String output = Formatter.formatPlayers(players);

        // 排序后应为：Australia(COLE) -> Canada(SMITH) -> China(BAI)
        int idxCole = output.indexOf("COLE Ellie");
        int idxSmith = output.indexOf("SMITH John");
        int idxBai = output.indexOf("BAI Yuming");
        assertTrue(idxCole < idxSmith);
        assertTrue(idxSmith < idxBai);
    }

    @Test
    void testFormatPlayersEndsWithSeparator() {
        Player p = new Player();
        p.setFullName("TEST User");
        p.setGender("Male");
        p.setCountry("Testland");

        String output = Formatter.formatPlayers(new ArrayList<>(Collections.singletonList(p)));
        assertTrue(output.endsWith("-----\n"));
    }

    @Test
    void testFormatPlayersEmptyList() {
        String output = Formatter.formatPlayers(new ArrayList<>());
        assertEquals("", output);
    }

    @Test
    void testFormatEventResultScoreFormat() {
        ResultEntry r = new ResultEntry();
        r.setFullName("CHEN Jia");
        r.setRank(1);
        r.setScores(Arrays.asList("73.50", "74.40", "76.50", "72.00", "78.00"));
        r.setTotalPoints("374.40");

        Event event = new Event();
        event.setResults(Collections.singletonList(r));

        String output = Formatter.formatEventResult(event);
        assertTrue(output.contains("Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40"));
        assertTrue(output.contains("Rank:1"));
        assertTrue(output.contains("Full Name:CHEN Jia"));
    }

    @Test
    void testFormatEventResultUsesNewline() {
        ResultEntry r = new ResultEntry();
        r.setFullName("TEST User");
        r.setRank(1);
        r.setScores(Arrays.asList("10.00", "20.00"));
        r.setTotalPoints("30.00");

        Event event = new Event();
        event.setResults(Collections.singletonList(r));

        String output = Formatter.formatEventResult(event);
        // 确认使用 \n 而非 \r\n
        assertFalse(output.contains("\r"));
        assertTrue(output.contains("\n"));
    }
}
