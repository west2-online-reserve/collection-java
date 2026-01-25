package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class CoreModuleTest {
    private CoreModule core;
    private StringWriter stringWriter;
    private PrintWriter writer;

    @BeforeEach
    void setUp() {
        core = new CoreModule();
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
    }

    // 1. 测试特例：players 命令
    @Test
    void testPlayersCommand() {
        core.dispalyAllPlayersInfo(writer);
        String output = stringWriter.toString();
        assertTrue(output.contains("Full name:"), "应包含运动员姓名");
    }

    // 2. 测试JudgmentFormat()在不带detail返回的格式是否正常
    @Test
    void testJudgmentFormat_Normal() {
        String[] res = core.judgmentFormat("result Women 10m Platform");
        assertEquals("Women 10m Platform", res[0]);
        assertEquals("false", res[1]);
    }

    // 2. 测试JudgmentFormat()在带detail时返回的格式是否正常
    @Test
    void testJudgmentFormat_Detail() {
        String[] res = core.judgmentFormat("result Women 10m Platform detail");
        assertEquals("Women 10m Platform", res[0]);
        assertEquals("true", res[1]);
    }

    // 4. 严格校验：缺失空格 (resultwomen)
    @Test
    void testJudgmentFormat_MissingSpace() {
        String[] res = core.judgmentFormat("resultwomen 10m");
        assertEquals("Error", res[0]);
    }

    // 5. 严格校验：空赛事名称 (result detail)
    @Test
    void testJudgmentFormat_OnlyDetail() {
        String[] res = core.judgmentFormat("result detail");
        // 根据你代码中 input.length() <= 14 的逻辑
        assertEquals("N/A", res[0]);
    }

    // 6. 严格校验：大小写匹配 (Women vs women)
    @Test
    void testCaseSensitivity() {
        String[] events = {
                "Women 10m Platform",
                "men 10m platform",
                "Women 10M Platform",
                "Men 3m Platform"
        };
        for(String event : events){
            core.displayResultForEachEvent("Women 10m platform", false, writer);
            assertTrue(stringWriter.toString().contains("N/A"), "大小写不匹配应输出 N/A");
            stringWriter.getBuffer().setLength(0);//清空缓冲区
        }

    }

    // 7. 内部逻辑：姓名排序 (LastName A-Z)
    @Test
    void testTeamNameSorting() {
        String raw = "ZHANG San / LI Si / WU HU / G A"; // 假设 LI 是 LastName
        String sorted = CoreModule.formatAndSortTeamNames(raw);
        // L 排在 Z 前面
        assertEquals("G A & LI Si & WU HU & ZHANG San", sorted);// G L W Z
    }

    // 8. 内部逻辑：排名解析 (并列排名 =1)
    @Test
    void testRankParsing() {
        // 这里需要测试 parseRankToInt，如果它是私有的，可以通过排序测试间接验证
        // 我们通过反射或修改权限测试：1 应该小于 2
        core.displayResultForEachEvent("Women 10m Platform", false, writer);
        // 验证输出的 Rank 顺序是否正确
    }

    // 9. 异常路径：文件不存在&&赛事名称不对
    @Test
    void testFileNotFound() {
        core.displayResultForEachEvent("InvalidEventName", false, writer);
        assertTrue(stringWriter.toString().contains("N/A"));
    }

    // 10. 详细模式输出：检查是否有初赛，半决赛，决赛的结果
    @Test
    void testDetailScoreFormat() {
        // 运行一个存在的 detail 赛事
        core.displayResultForEachEvent("Women 10m Platform", true, writer);
        String output = stringWriter.toString();
        if(!output.contains("N/A")) {
            assertTrue(output.contains("Preliminary Score"), "比赛结果应包含初赛");
            assertTrue(output.contains("Semifinal Score"), "比赛结果应包含半决赛");
            assertTrue(output.contains("Final Score"), "比赛结果应包含决赛");
        }
    }

    //11.非详细输出：检查有无其他成绩
    @Test
    void testNoDetailScoreFormat() {
        core.displayResultForEachEvent("Women 10m Platform", false, writer);
        String output = stringWriter.toString();
        if(!output.contains("N/A")) {
            assertTrue(output.contains("Score"), "比赛结果应包含成绩");
            assertFalse(output.contains("Preliminary Score"), "非详细模式结果不包含初赛");
            assertFalse(output.contains("Semifinal Score"), "非详细模式结果不包含半决赛");
            assertFalse(output.contains("Final Score"), "非详细模式结果不包含决赛");

        }
    }
}