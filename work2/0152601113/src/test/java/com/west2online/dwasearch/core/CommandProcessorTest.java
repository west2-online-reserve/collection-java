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
 * 命令解析测试：正常、边界、错误后继续、重复命令等。
 */
class CommandProcessorTest {

    /** 构造一个 8 个项目齐全、含 1 名选手和 1 条成绩的数据。 */
    private static CommandProcessor processor() {
        Map<String, List<Contest>> results = new LinkedHashMap<>();
        for (String name : Events.NAMES) {
            results.put(name, new ArrayList<>());
        }
        results.put("women 3m springboard", new ArrayList<>(List.of(
                new Contest(1, "CHEN Jia", List.of("73.50", "74.40", "76.50", "72.00", "78.00"), "374.40")
        )));
        List<Athlete> players = new ArrayList<>(List.of(
                new Athlete("Australia", "COLE", "Ellie", "Female")
        ));
        return new CommandProcessor(new CompetitionData(players, results));
    }

    @Test
    void playersCommandOutputsAllPlayers() {
        String out = processor().process("players");
        assertTrue(out.contains("Full Name:COLE Ellie"));
        assertTrue(out.endsWith("-----\n"), "最后一条记录也要有 -----");
    }

    @Test
    void unknownCommandsProduceError() {
        CommandProcessor p = processor();
        for (String cmd : List.of("player", "Players", "players extra", "resultwomen 3m springboard")) {
            assertEquals("Error\n-----\n", p.process(cmd), "输入: " + cmd);
        }
    }

    @Test
    void resultWithoutOrWrongProjectProducesNA() {
        CommandProcessor p = processor();
        for (String cmd : List.of(
                "result",
                "result sss",
                "result women 10m springboard",
                "result women 3m springboard details",
                "result men 10m     synchronised")) {
            assertEquals("N/A\n-----\n", p.process(cmd), "输入: " + cmd);
        }
    }

    @Test
    void validResultCommandOutputsResults() {
        String out = processor().process("result women 3m springboard");
        assertTrue(out.contains("Full Name:CHEN Jia"));
        assertTrue(out.contains("Rank:1"));
        assertTrue(out.endsWith("-----\n"));
    }

    @Test
    void blankLinesAreIgnored() {
        assertEquals("", processor().process(""));
        assertEquals("", processor().process("   "));
    }

    @Test
    void errorThenValidBothHandled() {
        CommandProcessor p = processor();
        assertEquals("Error\n-----\n", p.process("player"));
        assertTrue(p.process("players").contains("Full Name:COLE Ellie"),
                "一条非法命令之后，合法命令仍应正常输出");
    }

    @Test
    void repeatedCommandsOutputTwice() {
        CommandProcessor p = processor();
        String once = p.process("result women 3m springboard");
        assertEquals(once, p.process("result women 3m springboard"), "重复命令应再次输出");
    }
}
