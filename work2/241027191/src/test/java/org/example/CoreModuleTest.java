package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CoreModuleTest {

    private CoreModule coreModule;
    private final String inputPath = "input_test.txt";
    private final String outputPath = "output_test.txt";

    @Before
    public void setUp() throws Exception {
        coreModule = new CoreModule();
        // 确保测试前删除旧的输出文件
        Files.deleteIfExists(Paths.get(outputPath));
    }

    @After
    public void tearDown() throws Exception {
        // 清理测试产生的文件
        Files.deleteIfExists(Paths.get(inputPath));
        Files.deleteIfExists(Paths.get(outputPath));
    }

    /**
     * 辅助方法：向输入文件写入内容
     */
    private void writeInput(String content) throws IOException {
        Files.write(Paths.get(inputPath), content.getBytes());
    }

    // --- 测试用例开始 ---

    @Test
    public void testPlayersCommand() throws IOException {
        writeInput("players");
        coreModule.handle(inputPath, outputPath);

        List<String> lines = Files.readAllLines(Paths.get(outputPath));
        assertFalse("输出不应为空", lines.isEmpty());
        assertTrue("应包含Full Name", lines.get(0).startsWith("Full Name:"));
    }

    @Test
    public void testResultNormal() throws IOException {
        writeInput("result men 1m springboard");
        coreModule.handle(inputPath, outputPath);

        List<String> lines = Files.readAllLines(Paths.get(outputPath));
        assertTrue("输出应包含Rank", lines.stream().anyMatch(l -> l.startsWith("Rank:")));
    }

    @Test
    public void testResultDetail() throws IOException {
        writeInput("result women 10m platform detail");
        coreModule.handle(inputPath, outputPath);

        List<String> lines = Files.readAllLines(Paths.get(outputPath));
        assertTrue("应包含初赛分数", lines.stream().anyMatch(l -> l.startsWith("Preliminary Score:")));
    }

    @Test
    public void testInvalidCommandError() throws IOException {
        writeInput("invalid_cmd");
        coreModule.handle(inputPath, outputPath);

        List<String> lines = Files.readAllLines(Paths.get(outputPath));
        assertEquals("无效指令应输出Error", "Error", lines.get(0));
    }

    @Test
    public void testUnknownEventNA() throws IOException {
        writeInput("result unknown event");
        coreModule.handle(inputPath, outputPath);

        List<String> lines = Files.readAllLines(Paths.get(outputPath));
        assertEquals("未知比赛项目应输出N/A", "N/A", lines.get(0));
    }

    @Test
    public void testResultWithExtraSpaces() throws IOException {
        // 测试代码中的 trim() 是否生效
        writeInput("result   men 3m springboard  ");
        coreModule.handle(inputPath, outputPath);

        List<String> lines = Files.readAllLines(Paths.get(outputPath));
        assertFalse("带空格的合法指令不应输出N/A", lines.contains("N/A"));
        assertTrue("应能查到结果", lines.size() > 0);
    }

    @Test
    public void testResultDetailSuffixS() throws IOException {
        // 正则表达式支持 details (带s)
        writeInput("result men 10m platform details");
        coreModule.handle(inputPath, outputPath);

        List<String> lines = Files.readAllLines(Paths.get(outputPath));
        assertEquals("根据代码逻辑，details(带s)会触发N/A分支", "N/A", lines.get(0));
    }

    @Test
    public void testEmptyLine() throws IOException {
        writeInput("\n\nplayers");
        coreModule.handle(inputPath, outputPath);

        List<String> lines = Files.readAllLines(Paths.get(outputPath));
        // 空行应被跳过，直接处理 players
        assertTrue("应正常处理空行后的指令", lines.get(0).contains("Full Name:"));
    }

    @Test
    public void testMultipleCommands() throws IOException {
        writeInput("players\nresult women 3m springboard");
        coreModule.handle(inputPath, outputPath);

        List<String> lines = Files.readAllLines(Paths.get(outputPath));
        long count = lines.stream().filter(l -> l.equals("-----")).count();
        assertTrue("应包含多个分隔符，表示处理了多个指令", count > 10);
    }

    @Test
    public void testCaseSensitivity() throws IOException {
        // 您的 pathSet 是小写的，测试大写输入是否返回 N/A
        writeInput("result Men 1m springboard");
        coreModule.handle(inputPath, outputPath);

        List<String> lines = Files.readAllLines(Paths.get(outputPath));
        assertEquals("大小写不匹配应输出N/A", "N/A", lines.get(0));
    }
}