package com.dwasearch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DWASearchTest {

    @TempDir
    Path tempDir;

    private Path sampleDataPath() throws Exception {
        URL resource = getClass().getClassLoader().getResource("sample-data.json");
        assertNotNull(resource, "sample-data.json not found");
        return Path.of(resource.toURI());
    }

    @Test
    void shouldReadCommandsAndWriteOutput() throws Exception {
        Path dataPath = sampleDataPath();
        Path inputPath = tempDir.resolve("input.txt");
        Path outputPath = tempDir.resolve("output.txt");

        Files.writeString(
                inputPath,
                "players\n" +
                        "result women 3m springboard\n",
                StandardCharsets.UTF_8
        );

        DWASearch.run(dataPath, inputPath, outputPath);

        String actual = Files.readString(outputPath, StandardCharsets.UTF_8);

        String expected =
                "Full Name:COLE Ellie\n" +
                        "Gender:Female\n" +
                        "Country:Australia\n" +
                        "-----\n" +
                        "Full Name:KEENEY Maddison\n" +
                        "Gender:Female\n" +
                        "Country:Australia\n" +
                        "-----\n" +
                        "Full Name:CHEN Jia\n" +
                        "Rank:1\n" +
                        "Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40\n" +
                        "-----\n" +
                        "Full Name:CHEN Yiwen\n" +
                        "Rank:2\n" +
                        "Score:69.00 + 72.00 + 76.50 + 79.05 + 76.50 = 373.05\n" +
                        "-----\n";

        assertEquals(expected, actual);
    }

    @Test
    void shouldFailWhenInputFileDoesNotExist() throws Exception {
        Path dataPath = sampleDataPath();
        Path missingInput = tempDir.resolve("missing-input.txt");
        Path outputPath = tempDir.resolve("output.txt");

        IOException error = assertThrows(
                IOException.class,
                () -> DWASearch.run(dataPath, missingInput, outputPath)
        );

        assertTrue(error.getMessage().contains("无法读取输入文件"));
    }

    @Test
    void shouldFailWhenDataFileDoesNotExist() throws Exception {
        Path missingData = tempDir.resolve("missing-data.json");
        Path inputPath = tempDir.resolve("input.txt");
        Path outputPath = tempDir.resolve("output.txt");

        Files.writeString(inputPath, "players\n", StandardCharsets.UTF_8);

        IOException error = assertThrows(
                IOException.class,
                () -> DWASearch.run(missingData, inputPath, outputPath)
        );

        assertTrue(error.getMessage().contains("无法读取数据文件"));
    }

    @Test
    void shouldFailWhenOutputCannotBeWritten() throws Exception {
        Path dataPath = sampleDataPath();
        Path inputPath = tempDir.resolve("input.txt");
        Path outputDirectory = tempDir.resolve("output-dir");

        Files.writeString(inputPath, "players\n", StandardCharsets.UTF_8);
        Files.createDirectory(outputDirectory);

        IOException error = assertThrows(
                IOException.class,
                () -> DWASearch.run(dataPath, inputPath, outputDirectory)
        );

        assertTrue(error.getMessage().contains("无法写入输出文件"));
    }

    @Test
    void shouldWriteEmptyOutputForEmptyInput() throws Exception {
        Path dataPath = sampleDataPath();
        Path inputPath = tempDir.resolve("empty-input.txt");
        Path outputPath = tempDir.resolve("empty-output.txt");

        Files.writeString(inputPath, "", StandardCharsets.UTF_8);

        DWASearch.run(dataPath, inputPath, outputPath);

        assertEquals("", Files.readString(outputPath, StandardCharsets.UTF_8));
    }

    @Test
    void shouldProcessDuplicateCommandsTwice() throws Exception {
        Path dataPath = sampleDataPath();
        Path inputPath = tempDir.resolve("duplicate-input.txt");
        Path outputPath = tempDir.resolve("duplicate-output.txt");

        Files.writeString(inputPath, "players\nplayers\n", StandardCharsets.UTF_8);

        DWASearch.run(dataPath, inputPath, outputPath);

        String actual = Files.readString(outputPath, StandardCharsets.UTF_8);
        int first = actual.indexOf("Full Name:COLE Ellie");
        int second = actual.indexOf("Full Name:COLE Ellie", first + 1);

        assertTrue(first >= 0);
        assertTrue(second > first);
    }

    @Test
    void shouldContinueAfterInvalidCommand() throws Exception {
        Path dataPath = sampleDataPath();
        Path inputPath = tempDir.resolve("invalid-then-valid.txt");
        Path outputPath = tempDir.resolve("invalid-then-valid-output.txt");

        Files.writeString(inputPath, "player\nplayers\n", StandardCharsets.UTF_8);

        DWASearch.run(dataPath, inputPath, outputPath);

        String actual = Files.readString(outputPath, StandardCharsets.UTF_8);

        assertTrue(actual.startsWith("Error\n-----\n"));
        assertTrue(actual.contains("Full Name:COLE Ellie"));
    }
}