package com.dwa.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CoreModuleTest {

    private static CoreModule core;

    @TempDir
    Path tempDir;

    @BeforeAll
    static void setUp() throws IOException {
        DataRepository repository = new DataRepository();
        core = new CoreModule(repository);
    }

    @Test
    void testPlayersOutputFormat() {
        String output = core.processCommand("players");
        assertNotNull(output);
        assertTrue(output.contains("Full Name:"));
        assertTrue(output.contains("Gender:"));
        assertTrue(output.contains("Country:"));
        assertTrue(output.contains("-----"));
        // 第一个选手应为 Australia 的 COLE Ellie（按国籍和 Last Name 排序）
        assertTrue(output.startsWith("Full Name:COLE Ellie"));
    }

    @Test
    void testResultOutputFormat() {
        String output = core.processCommand("result women 3m springboard");
        assertNotNull(output);
        assertTrue(output.contains("Full Name:CHEN Jia"));
        assertTrue(output.contains("Rank:1"));
        assertTrue(output.contains("Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40"));
        assertTrue(output.contains("-----"));
    }

    @Test
    void testErrorCommand() {
        assertEquals("Error\n-----\n", core.processCommand("player"));
        assertEquals("Error\n-----\n", core.processCommand("Players"));
        assertEquals("Error\n-----\n", core.processCommand(""));
    }

    @Test
    void testNACommand() {
        assertEquals("N/A\n-----\n", core.processCommand("result sss"));
        assertEquals("N/A\n-----\n", core.processCommand("result women 10m springboard"));
        assertEquals("N/A\n-----\n", core.processCommand("result women 3m springboard details"));
    }

    @Test
    void testMultiLineFile() throws IOException {
        Path inputFile = tempDir.resolve("input.txt");
        Path outputFile = tempDir.resolve("output.txt");

        String input = "players\nresult women 3m springboard\nresult women 3m springboard\nplayers\n";
        Files.writeString(inputFile, input, StandardCharsets.UTF_8);

        core.processFile(inputFile.toString(), outputFile.toString());

        String output = Files.readString(outputFile, StandardCharsets.UTF_8);
        assertNotNull(output);
        // 确认输出中无多余空行（两个命令的输出直接相连）
        assertFalse(output.contains("\n\n-----\n\n"));
        // 确认包含选手和结果
        assertTrue(output.contains("Full Name:COLE Ellie"));
        assertTrue(output.contains("Full Name:CHEN Jia"));
    }

    @Test
    void testEmptyInputFile() throws IOException {
        Path inputFile = tempDir.resolve("empty.txt");
        Path outputFile = tempDir.resolve("output.txt");
        Files.writeString(inputFile, "", StandardCharsets.UTF_8);

        core.processFile(inputFile.toString(), outputFile.toString());

        String output = Files.readString(outputFile, StandardCharsets.UTF_8);
        assertEquals("", output);
    }
}
