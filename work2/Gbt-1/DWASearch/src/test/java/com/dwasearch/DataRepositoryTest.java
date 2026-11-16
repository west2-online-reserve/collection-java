package com.dwasearch;

import com.dwasearch.data.DataException;
import com.dwasearch.data.DataRepository;
import com.dwasearch.model.CompetitionData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataRepositoryTest {

    @TempDir
    Path tempDir;

    private final ObjectMapper mapper = new ObjectMapper();

    private Path sampleDataPath() throws Exception {
        URL resource = getClass().getClassLoader().getResource("sample-data.json");
        assertNotNull(resource, "sample-data.json not found");
        return Path.of(resource.toURI());
    }

    private ObjectNode sampleRoot() throws Exception {
        return (ObjectNode) mapper.readTree(sampleDataPath().toFile());
    }

    private Path writeTemp(String fileName, JsonNode root) throws Exception {
        Path path = tempDir.resolve(fileName);
        mapper.writeValue(path.toFile(), root);
        return path;
    }

    @Test
    void shouldLoadSampleData() throws Exception {
        CompetitionData data = new DataRepository().load(sampleDataPath());

        assertEquals(2, data.getPlayers().size());
        assertEquals("COLE Ellie", data.getPlayers().get(0).getFullName());

        assertEquals(8, data.getEvents().size());
        assertEquals("women 3m springboard", data.getEvents().get(0).getEventName());
        assertEquals(2, data.getEvents().get(0).getResults().size());
        assertEquals(
                new BigDecimal("374.40"),
                data.getEvents().get(0).getResults().get(0).getTotalScore()
        );
    }

    @Test
    void shouldFailWhenPlayersFieldIsMissing() throws Exception {
        Path badFile = tempDir.resolve("missing-players.json");
        Files.writeString(badFile, "{\"events\":[]}", StandardCharsets.UTF_8);

        DataException error = assertThrows(
                DataException.class,
                () -> new DataRepository().load(badFile)
        );

        assertTrue(error.getMessage().contains("players"));
    }

    @Test
    void shouldFailWhenJsonIsBroken() throws Exception {
        Path badFile = tempDir.resolve("broken.json");
        Files.writeString(badFile, "{ this is not json", StandardCharsets.UTF_8);

        DataException error = assertThrows(
                DataException.class,
                () -> new DataRepository().load(badFile)
        );

        assertTrue(error.getMessage().contains("无法解析"));
    }

    @Test
    void shouldFailWhenPlayersAreEmpty() throws Exception {
        Path badFile = tempDir.resolve("empty-players.json");
        Files.writeString(
                badFile,
                "{\"players\":[],\"events\":[]}",
                StandardCharsets.UTF_8
        );

        DataException error = assertThrows(
                DataException.class,
                () -> new DataRepository().load(badFile)
        );

        assertTrue(error.getMessage().contains("缺少选手"));
    }

    @Test
    void shouldFailWhenRequiredEventIsMissing() throws Exception {
        ObjectNode root = sampleRoot();
        ArrayNode events = (ArrayNode) root.get("events");
        events.remove(0);

        Path badFile = writeTemp("missing-event.json", root);

        DataException error = assertThrows(
                DataException.class,
                () -> new DataRepository().load(badFile)
        );

        assertTrue(error.getMessage().contains("women 3m springboard"));
    }

    @Test
    void shouldFailWhenFinalsAreEmpty() throws Exception {
        ObjectNode root = sampleRoot();
        ArrayNode events = (ArrayNode) root.get("events");
        ((ObjectNode) events.get(0)).putArray("results");

        Path badFile = writeTemp("empty-finals.json", root);

        DataException error = assertThrows(
                DataException.class,
                () -> new DataRepository().load(badFile)
        );

        assertTrue(error.getMessage().contains("没有决赛成绩"));
    }

    @Test
    void shouldFailWhenDiveScoresAreEmpty() throws Exception {
        ObjectNode root = sampleRoot();
        ArrayNode events = (ArrayNode) root.get("events");
        JsonNode results = events.get(0).get("results");
        ((ObjectNode) results.get(0)).putArray("diveScores");

        Path badFile = writeTemp("empty-dives.json", root);

        DataException error = assertThrows(
                DataException.class,
                () -> new DataRepository().load(badFile)
        );

        assertTrue(error.getMessage().contains("缺少各跳成绩"));
    }
}