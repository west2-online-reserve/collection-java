package com.diving;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainApplicationTest {

    private static final String DATA_JSON = """
            {
              "players": [
                { "fullName": "CHEN Yiwen", "gender": "Female", "country": "China" }
              ],
              "results": [
                {
                  "event": "women 3m springboard",
                  "rank": 1,
                  "diverName": "CHEN Yiwen",
                  "scores": [72.00, 74.80, 75.60],
                  "totalScore": 374.40
                }
              ]
            }
            """;

    @TempDir
    Path tempDir;

    /** 每个用例使用独立的工作目录，data.json 与输入/输出文件都放在其中。 */
    private Path prepareWorkDir() throws IOException {
        Path workDir = Files.createTempDirectory(tempDir, "app");
        Files.writeString(workDir.resolve("data.json"), DATA_JSON, StandardCharsets.UTF_8);
        return workDir;
    }

    private Path writeInput(Path workDir, String content) throws IOException {
        Path input = workDir.resolve("input.txt");
        Files.writeString(input, content, StandardCharsets.UTF_8);
        return input;
    }

    /** 主程序会用 System.exit 结束进程，因此单独启动一个 JVM 来验证真实退出码。 */
    private int runApp(Path workDir, String... args) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add(Paths.get(System.getProperty("java.home"), "bin", "java").toString());
        command.add("-Dfile.encoding=UTF-8");
        command.add("-cp");
        command.add(testClassPath());
        command.add(MainApplication.class.getName());
        command.addAll(List.of(args));

        Process process = new ProcessBuilder(command)
                .directory(workDir.toFile())
                .redirectErrorStream(true)
                .redirectOutput(workDir.resolve("console.log").toFile())
                .start();
        if (!process.waitFor(120, TimeUnit.SECONDS)) {
            process.destroyForcibly();
            fail("主程序执行超时");
        }
        return process.exitValue();
    }

    private static String testClassPath() {
        String classPath = System.getProperty("surefire.test.class.path");
        return classPath != null ? classPath : System.getProperty("java.class.path");
    }

    @Test
    @DisplayName("缺少参数时以退出码 1 结束")
    void missingArgumentsExitsWithOne() throws IOException, InterruptedException {
        Path workDir = prepareWorkDir();

        assertEquals(1, runApp(workDir));
    }

    @Test
    @DisplayName("输入文件不存在时以退出码 1 结束且不生成输出文件")
    void nonexistentInputFileExitsWithOne() throws IOException, InterruptedException {
        Path workDir = prepareWorkDir();

        int exitCode = runApp(workDir, "missing.txt", "result.txt");

        assertEquals(1, exitCode);
        assertFalse(Files.exists(workDir.resolve("result.txt")), "输入无效时不应生成输出文件");
    }

    @Test
    @DisplayName("正常执行时结果写入输出文件且退出码为 0")
    void writesCommandOutputToFile() throws IOException, InterruptedException {
        Path workDir = prepareWorkDir();
        writeInput(workDir, "players\nresult women 3m springboard\nunknown command\n");

        int exitCode = runApp(workDir, "input.txt", "result.txt");

        assertEquals(0, exitCode);
        String content = Files.readString(workDir.resolve("result.txt"), StandardCharsets.UTF_8);
        assertTrue(content.contains("Full Name: CHEN Yiwen"), "应输出选手信息");
        assertTrue(content.contains("Gender: Female"), "应输出性别");
        assertTrue(content.contains("Country: China"), "应输出国家");
        assertTrue(content.contains("women 3m springboard"), "应输出项目名");
        assertTrue(content.contains("1st Full Name: CHEN Yiwen"), "应输出排名");
        assertTrue(content.contains("Score: 72.00 + 74.80 + 75.60 = 374.40"), "应输出分项与总分");
        assertTrue(content.contains("Error"), "未知命令应输出 Error");
    }

    @Test
    @DisplayName("选手与成绩之间使用五个横杠作为分隔线")
    void usesFiveHyphensAsSeparator() throws IOException, InterruptedException {
        Path workDir = prepareWorkDir();
        writeInput(workDir, "players\nresult women 3m springboard\n");

        int exitCode = runApp(workDir, "input.txt", "result.txt");

        assertEquals(0, exitCode);
        List<String> lines = Files.readAllLines(workDir.resolve("result.txt"), StandardCharsets.UTF_8);
        assertTrue(lines.stream().anyMatch("-----"::equals), "应包含五个横杠的分隔线");
        assertFalse(lines.stream().anyMatch("---"::equals), "不应再输出三个横杠");
    }

    @Test
    @DisplayName("输出文件不可写时以退出码 3 结束")
    void unwritableOutputFileExitsWithThree() throws IOException, InterruptedException {
        Path workDir = prepareWorkDir();
        writeInput(workDir, "players\n");
        // 用同名目录占位，模拟无法写入的输出文件
        Files.createDirectory(workDir.resolve("output.txt"));

        assertEquals(3, runApp(workDir, "input.txt", "output.txt"));
    }

    @Test
    @DisplayName("空输入覆盖已有输出文件并生成空内容")
    void blankInputTruncatesExistingOutputFile() throws IOException, InterruptedException {
        Path workDir = prepareWorkDir();
        writeInput(workDir, "\n   \n");
        Path output = workDir.resolve("result.txt");
        Files.writeString(output, "旧内容", StandardCharsets.UTF_8);

        int exitCode = runApp(workDir, "input.txt", "result.txt");

        assertEquals(0, exitCode);
        assertEquals("", Files.readString(output, StandardCharsets.UTF_8));
    }

    @Test
    @DisplayName("数据加载失败时以退出码 4 结束")
    void dataLoadFailureExitsWithFour() throws IOException, InterruptedException {
        Path workDir = prepareWorkDir();
        writeInput(workDir, "players\n");
        Files.writeString(workDir.resolve("data.json"), "{ 这不是合法 JSON", StandardCharsets.UTF_8);

        int exitCode = runApp(workDir, "input.txt", "result.txt");

        assertEquals(4, exitCode);
        assertFalse(Files.exists(workDir.resolve("result.txt")), "数据加载失败时不应生成输出文件");
    }
}
