package org.west2.learning;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QueryEngineTest {
    private ContestData sample() {
        List<ContestData.Player> players = List.of(
                new ContestData.Player("ZHANG Li", "ZHANG", "Female", "China"),
                new ContestData.Player("COLE Ellie", "COLE", "Female", "Australia"),
                new ContestData.Player("KEENEY Maddison", "KEENEY", "Female", "Australia"));
        Map<String, List<ContestData.Result>> events = new LinkedHashMap<>();
        for (String name : QueryEngine.EVENT_NAMES) {
            events.put(name, new ArrayList<>(List.of(
                    new ContestData.Result("ZHANG Li", 2, List.of("69", "72.00"), "141.00"),
                    new ContestData.Result("COLE Ellie", 1, List.of("73.50", "74.4"), "147.90"))));
        }
        return new ContestData("https://example.test/results.xml", "2026-09-21T00:00:00Z", players, events);
    }

    @Test
    void sortsPlayersByCountryThenSurname() {
        String output = new QueryEngine(sample()).execute(List.of("players"));
        assertTrue(output.indexOf("COLE Ellie") < output.indexOf("KEENEY Maddison"));
        assertTrue(output.indexOf("KEENEY Maddison") < output.indexOf("ZHANG Li"));
        assertTrue(output.endsWith("-----\n"));
    }

    @Test
    void sortsResultsAndFormatsEveryScore() {
        String output = new QueryEngine(sample()).execute(List.of("result women 3m springboard"));
        assertTrue(output.startsWith("Full Name:COLE Ellie\nRank:1\nScore:73.50 + 74.40 = 147.90\n-----\n"));
        assertTrue(output.indexOf("Rank:1") < output.indexOf("Rank:2"));
        assertTrue(output.contains("Score:69.00 + 72.00 = 141.00\n-----\n"));
    }

    @Test
    void repeatsQueriesAndContinuesAfterBadCommand() {
        String once = new QueryEngine(sample()).execute(List.of("result men 10m synchronised"));
        String output = new QueryEngine(sample()).execute(List.of(
                "result men 10m synchronised", "player", "result", "result women 10m springboard",
                "result men 10m synchronised"));
        assertEquals(once + "Error\n-----\nN/A\n-----\nN/A\n-----\n" + once, output);
        assertEquals("Error\n-----\n", new QueryEngine(sample()).execute(List.of("Players")));
        assertEquals("Error\n-----\n", new QueryEngine(sample()).execute(List.of("players extra")));
        assertEquals("Error\n-----\n", new QueryEngine(sample()).execute(List.of("resultwomen 3m springboard")));
        assertEquals("N/A\n-----\n", new QueryEngine(sample()).execute(List.of("result women 3m springboard details")));
    }

    @Test
    void emptyFileAndEmptyLineProduceNoOutput() {
        assertEquals("", new QueryEngine(sample()).execute(List.of()));
        assertEquals("", new QueryEngine(sample()).execute(List.of("", "")));
        assertEquals("Error\n-----\n", new QueryEngine(sample()).execute(List.of(" ")));
    }

    @Test
    void rejectsMissingFieldsBadJsonAndBadScores() throws IOException {
        Path path = Files.createTempFile("dwa-broken", ".json");
        try {
            Files.writeString(path, "{broken}");
            assertThrows(IOException.class, () -> DataLoader.read(path));
            Map<String, List<ContestData.Result>> events = new LinkedHashMap<>(sample().events());
            events.remove("women 3m springboard");
            assertThrows(IOException.class, () -> DataLoader.validate(
                    new ContestData("source", "time", sample().players(), events)));
            events.put("women 3m springboard", List.of(
                    new ContestData.Result("COLE Ellie", 1, List.of("10.00"), "11.00")));
            assertThrows(IOException.class, () -> DataLoader.validate(
                    new ContestData("source", "time", sample().players(), events)));
        } finally {
            Files.deleteIfExists(path);
        }
    }

    @Test
    void entrypointHandlesMissingFilesAndUnwritableOutput() throws IOException {
        Path directory = Files.createTempDirectory("dwa-files");
        Path data = directory.resolve("data.json");
        Path input = directory.resolve("input.txt");
        Path output = directory.resolve("output.txt");
        ByteArrayOutputStream errors = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(errors, true, StandardCharsets.UTF_8);
        try {
            assertEquals(2, DWASearch.run(new String[0], stream, data));
            assertTrue(errors.toString(StandardCharsets.UTF_8).contains("用法"));
            errors.reset();
            assertEquals(1, DWASearch.run(new String[]{input.toString(), output.toString()}, stream, data));
            assertTrue(errors.toString(StandardCharsets.UTF_8).contains(input.toString()));
            Files.writeString(input, "players\n", StandardCharsets.UTF_8);
            errors.reset();
            assertEquals(1, DWASearch.run(new String[]{input.toString(), output.toString()}, stream, data));
            assertTrue(errors.toString(StandardCharsets.UTF_8).contains(data.toString()));
            new com.fasterxml.jackson.databind.ObjectMapper().writeValue(data.toFile(), sample());
            assertEquals(0, DWASearch.run(new String[]{input.toString(), output.toString()}, stream, data));
            assertTrue(Files.readString(output).contains("COLE Ellie"));
            assertEquals(1, DWASearch.run(new String[]{input.toString(), directory.toString()}, stream, data));
        } finally {
            Files.deleteIfExists(input);
            Files.deleteIfExists(output);
            Files.deleteIfExists(data);
            Files.deleteIfExists(directory);
        }
    }
}
