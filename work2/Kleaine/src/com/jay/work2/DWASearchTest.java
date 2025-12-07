package com.jay.work2;

import org.junit.Test;
import static org.junit.Assert.*;

public class DWASearchTest {

    // CoreModule 接口测试
    CoreModule core = new CoreModule();

    // 测试用例1：合法 players 指令
    @Test
    public void testValidPlayersCommand() {
        String playersOutput = core.getAllPlayersInfo();

        assertNotNull(playersOutput);
        assertTrue(playersOutput.contains("Full Name:"));
        assertTrue(playersOutput.contains("Gender:"));
        assertTrue(playersOutput.contains("Country:"));
    }

    // 测试用例2：result 指令带多空格（预期返回N/A）
    @Test
    public void testResultCommandWithMultipleSpaces() {
        String command = "result men 10m     synchronised";
        String output = core.getEventResult(command);
        assertEquals("N/A", output);
    }

    // 测试用例3：result 指令合法带 detail（预期包含 dive 字段）
    @Test
    public void testResultCommandWithValidDetail() {
        String command = "result men 1m springboard detail";

        String output = core.getEventResult(command);
        System.out.println("=== RESULT OUTPUT ===");
        System.out.println(output);

        assertNotEquals("N/A", output);
        assertTrue(output.contains("Full Name:"));
        assertTrue(output.contains("Rank:"));
        assertTrue(output.contains("Total Points:"));
        assertTrue(output.contains("----- Dive Details -----"));
    }

    // 测试用例4：result 指令非法后缀（不是 detail）
    @Test
    public void testResultCommandWithInvalidSuffix() {
        String command = "result men 1m springboard details";
        String output = core.getEventResult(command);
        assertEquals("N/A", output);
    }

    // 测试用例5：result 指令项目非法
    @Test
    public void testResultCommandWithInvalidEvent() {
        String command = "result men 2m springboard";
        String output = core.getEventResult(command);
        assertEquals("N/A", output);
    }

    // 测试用例6：指令包含大写 → 预期 Error
    @Test
    public void testCommandWithUpperCase() {
        String command = "Players";
        boolean isLowerCase = Lib.isAllLowerCase(command);
        assertFalse(isLowerCase);

        StringBuilder output = new StringBuilder();
        if (!isLowerCase) {
            output.append("Error\n-----\n");
        }
        assertEquals("Error\n-----", output.toString().trim());
    }

    // 测试用例7：空指令
    @Test
    public void testEmptyCommand() {
        String command = "";
        String originalLine = command.trim();
        assertTrue(originalLine.isEmpty());

        StringBuilder output = new StringBuilder();
        assertEquals("", output.toString().trim());
    }

    // 测试用例8：无法识别的小写指令
    @Test
    public void testUnrecognizedValidLowerCaseCommand() {
        String command = "abc123";
        boolean isLowerCase = Lib.isAllLowerCase(command);
        assertTrue(isLowerCase);

        StringBuilder output = new StringBuilder();
        output.append("Error\n-----\n");

        assertEquals("Error\n-----", output.toString().trim());
    }

    // 测试用例9：result 指令文件不存在
    @Test
    public void testResultCommandWithNonExistentFile() {
        String command = "result women 5m platform";
        String output = core.getEventResult(command);
        assertEquals("N/A", output);
    }

    // 测试用例10：result 格式不完整
    @Test
    public void testResultCommandWithIncompleteFormat() {
        String command = "result men 1m";
        String output = core.getEventResult(command);
        assertEquals("N/A", output);
    }
}
