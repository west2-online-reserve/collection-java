package com.west2.dwasearch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DWASearchTest {
    @TempDir
    Path tempDir;

    private String lastError = "";

    @Test
    void wrongArgumentCountPrintsUsageAndFails() {
        assertEquals(1, run(new String[] {}));
        assertEquals(1, run(new String[] {"input.txt"}));
        assertEquals(1, run(new String[] {"a", "b", "c"}));
        assertTrue(lastError.contains("用法"), lastError);
    }

    @Test
    void missingInputFileFails() throws IOException {
        Path output = tempDir.resolve("out.txt");
        Path missing = tempDir.resolve("no-such-input.txt");
        int code = run(new String[] {missing.toString(), output.toString()});

        assertEquals(1, code);
        assertTrue(lastError.contains("输入文件不存在"), lastError);
        assertTrue(lastError.contains("no-such-input.txt"), lastError);
        assertFalse(Files.exists(output), "出错的时候不应该生成输出文件");
    }

    @Test
    void directoryAsInputFileFails() throws IOException {
        Path dir = Files.createDirectory(tempDir.resolve("somedir"));
        int code = run(new String[] {dir.toString(), tempDir.resolve("out.txt").toString()});

        assertEquals(1, code);
        assertTrue(lastError.contains("不是普通文件"), lastError);
    }

    @Test
    void playersAndResultCommandsAreWrittenToOutput() throws IOException {
        Path input = writeInput("players\nresult women 3m springboard\n");
        Path output = tempDir.resolve("out.txt");

        assertEquals(0, run(new String[] {input.toString(), output.toString()}));
        assertEquals("", lastError);

        List<String> lines = Files.readAllLines(output, StandardCharsets.UTF_8);
        assertEquals("Full Name:COLE Ellie / Gender:Female / Country:Australia", lines.get(0));
        assertEquals("-----", lines.get(1));
        assertTrue(lines.contains(
                        "Full Name:CHEN Jia / Rank:1 / Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40"),
                "结果里应该有 CHEN Jia 这一条");
        assertEquals("-----", lines.get(lines.size() - 1));
    }

    @Test
    void outputUsesLfOnly() throws IOException {
        Path input = writeInput("result sss\n");
        Path output = tempDir.resolve("out.txt");

        assertEquals(0, run(new String[] {input.toString(), output.toString()}));

        String content = new String(Files.readAllBytes(output), StandardCharsets.UTF_8);
        assertFalse(content.contains("\r"), "输出里不能有 CR");
        assertEquals("N/A\n-----\n", content);
    }

    @Test
    void inputWithCrLfIsHandled() throws IOException {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, "players\r\nresult\r\n".getBytes(StandardCharsets.UTF_8));
        Path output = tempDir.resolve("out.txt");

        assertEquals(0, run(new String[] {input.toString(), output.toString()}));
        List<String> lines = Files.readAllLines(output, StandardCharsets.UTF_8);
        assertEquals("N/A", lines.get(lines.size() - 2), "最后一条命令是 result，应该输出 N/A");
        assertEquals("-----", lines.get(lines.size() - 1));
    }

    @Test
    void emptyInputFileProducesEmptyOutput() throws IOException {
        Path input = writeInput("");
        Path output = tempDir.resolve("out.txt");

        assertEquals(0, run(new String[] {input.toString(), output.toString()}));
        assertEquals(0, Files.size(output));
    }

    @Test
    void outputPathIsDirectoryFails() throws IOException {
        Path input = writeInput("players\n");
        Path outputDir = Files.createDirectory(tempDir.resolve("outdir"));

        assertEquals(1, run(new String[] {input.toString(), outputDir.toString()}));
        assertTrue(lastError.contains("写入输出文件失败"), lastError);
    }

    @Test
    void outputInMissingDirectoryFails() throws IOException {
        Path input = writeInput("players\n");
        Path output = tempDir.resolve("no-such-dir").resolve("out.txt");

        assertEquals(1, run(new String[] {input.toString(), output.toString()}));
        assertTrue(lastError.contains("写入输出文件失败"), lastError);
    }

    @Test
    void nonUtf8InputFails() throws IOException {
        Path input = tempDir.resolve("bad-encoding.txt");

        Files.write(input, new byte[] {(byte) 0xC4, (byte) 0xE3, '\n'});

        assertEquals(1, run(new String[] {input.toString(), tempDir.resolve("out.txt").toString()}));
        assertTrue(lastError.contains("UTF-8"), lastError);
    }

    private Path writeInput(String content) throws IOException {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, content.getBytes(StandardCharsets.UTF_8));
        return input;
    }

    private int run(String[] args) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(buffer, true, StandardCharsets.UTF_8);
        int code = DWASearch.run(args, err);
        err.flush();
        lastError = new String(buffer.toByteArray(), StandardCharsets.UTF_8);
        return code;
    }
}
