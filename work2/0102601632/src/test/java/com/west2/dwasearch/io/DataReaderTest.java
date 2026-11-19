package com.west2.dwasearch.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.west2.dwasearch.data.DataStore;
import com.west2.dwasearch.data.EventCatalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataReaderTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @TempDir
    Path tempDir;

    @Test
    void loadsRealDataFromTemporaryDirectory() throws Exception {
        copyAllDataFiles(tempDir);

        DataStore store = new DataReader().loadFromDirectory(tempDir);

        assertEquals(89, store.getPlayers().size());
        assertNotNull(store.getResults("women 3m springboard"));
        assertEquals(4, store.getResults("women 3m springboard").size());
        assertEquals("CHEN Jia", store.getResults("women 3m springboard").get(0).getFullName());
    }

    @Test
    void classpathDataCanBeLoaded() throws Exception {
        DataStore store = new DataReader().loadFromClasspath();
        assertEquals(89, store.getPlayers().size());
        String[] keywords = EventCatalog.keywords();
        for (int i = 0; i < keywords.length; i++) {
            assertNotNull(store.getResults(keywords[i]), "缺少项目：" + keywords[i]);
        }
    }

    @Test
    void missingAthletesFileIsReported() {
        DataException e = assertThrows(DataException.class,
                () -> new DataReader().loadFromDirectory(tempDir));
        assertTrue(e.getMessage().contains("athletes.json"), e.getMessage());
    }

    @Test
    void brokenJsonIsReported() throws Exception {
        copyAllDataFiles(tempDir);
        Files.write(tempDir.resolve("athletes.json"), "{ 这不是 json".getBytes(StandardCharsets.UTF_8));

        DataException e = assertThrows(DataException.class,
                () -> new DataReader().loadFromDirectory(tempDir));
        assertTrue(e.getMessage().contains("json"), e.getMessage());
        assertTrue(e.getMessage().contains("athletes.json"), e.getMessage());
    }

    @Test
    void missingFieldIsReported() throws Exception {
        copyAllDataFiles(tempDir);
        JsonNode root = MAPPER.readTree(Files.readAllBytes(tempDir.resolve("athletes.json")));
        ((ObjectNode) root.get(0).get("Participations").get(0)).remove("Gender");
        Files.write(tempDir.resolve("athletes.json"), MAPPER.writeValueAsBytes(root));

        DataException e = assertThrows(DataException.class,
                () -> new DataReader().loadFromDirectory(tempDir));
        assertTrue(e.getMessage().contains("Gender"), e.getMessage());
        assertTrue(e.getMessage().contains("athletes.json"), e.getMessage());
    }

    @Test
    void missingFinalsHeatIsReported() throws Exception {
        copyAllDataFiles(tempDir);
        Path file = tempDir.resolve("women_3m_springboard.json");
        ObjectNode root = (ObjectNode) MAPPER.readTree(Files.readAllBytes(file));
        JsonNode heats = root.get("Heats");

        for (int i = 0; i < heats.size(); i++) {
            ObjectNode heat = (ObjectNode) heats.get(i);
            if ("Finals".equals(heat.path("Name").asText())) {
                heat.put("Name", "Semifinals");
            }
        }
        Files.write(file, MAPPER.writeValueAsBytes(root));

        DataException e = assertThrows(DataException.class,
                () -> new DataReader().loadFromDirectory(tempDir));
        assertTrue(e.getMessage().contains("Finals"), e.getMessage());
    }

    @Test
    void emptyFinalsResultsIsReported() throws Exception {
        copyAllDataFiles(tempDir);
        Path file = tempDir.resolve("men_10m_platform.json");
        ObjectNode root = (ObjectNode) MAPPER.readTree(Files.readAllBytes(file));
        JsonNode heats = root.get("Heats");
        for (int i = 0; i < heats.size(); i++) {
            ObjectNode heat = (ObjectNode) heats.get(i);
            if ("Finals".equals(heat.path("Name").asText())) {
                heat.putArray("Results");
            }
        }
        Files.write(file, MAPPER.writeValueAsBytes(root));

        DataException e = assertThrows(DataException.class,
                () -> new DataReader().loadFromDirectory(tempDir));
        assertTrue(e.getMessage().contains("空"), e.getMessage());
    }

    @Test
    void missingRankIsReported() throws Exception {
        copyAllDataFiles(tempDir);
        Path file = tempDir.resolve("women_10m_platform.json");
        ObjectNode root = (ObjectNode) MAPPER.readTree(Files.readAllBytes(file));
        JsonNode heats = root.get("Heats");
        for (int i = 0; i < heats.size(); i++) {
            ObjectNode heat = (ObjectNode) heats.get(i);
            if ("Finals".equals(heat.path("Name").asText())) {
                ((ObjectNode) heat.get("Results").get(0)).remove("Rank");
            }
        }
        Files.write(file, MAPPER.writeValueAsBytes(root));

        DataException e = assertThrows(DataException.class,
                () -> new DataReader().loadFromDirectory(tempDir));
        assertTrue(e.getMessage().contains("Rank"), e.getMessage());
    }

    @Test
    void nonNumericScoreIsReported() throws Exception {
        copyAllDataFiles(tempDir);
        Path file = tempDir.resolve("men_3m_springboard.json");
        ObjectNode root = (ObjectNode) MAPPER.readTree(Files.readAllBytes(file));
        JsonNode heats = root.get("Heats");
        for (int i = 0; i < heats.size(); i++) {
            ObjectNode heat = (ObjectNode) heats.get(i);
            if ("Finals".equals(heat.path("Name").asText())) {
                ((ObjectNode) heat.get("Results").get(0)).put("TotalPoints", "abc");
            }
        }
        Files.write(file, MAPPER.writeValueAsBytes(root));

        DataException e = assertThrows(DataException.class,
                () -> new DataReader().loadFromDirectory(tempDir));
        assertTrue(e.getMessage().contains("TotalPoints"), e.getMessage());
    }

    @Test
    void emptyJsonFileIsReported() throws Exception {
        copyAllDataFiles(tempDir);
        Files.write(tempDir.resolve("athletes.json"), new byte[0]);

        DataException e = assertThrows(DataException.class,
                () -> new DataReader().loadFromDirectory(tempDir));
        assertTrue(e.getMessage().contains("空"), e.getMessage());
    }

    private void copyAllDataFiles(Path dir) throws IOException {
        copyResource("data/athletes.json", dir.resolve("athletes.json"));
        String[] keywords = EventCatalog.keywords();
        for (int i = 0; i < keywords.length; i++) {
            String name = EventCatalog.fileNameOf(keywords[i]);
            copyResource("data/" + name, dir.resolve(name));
        }
    }

    private void copyResource(String resourceName, Path target) throws IOException {
        InputStream in = DataReaderTest.class.getClassLoader().getResourceAsStream(resourceName);
        if (in == null) {
            throw new IllegalStateException("测试资源不存在：" + resourceName);
        }
        try (InputStream input = in) {
            Files.write(target, input.readAllBytes());
        }
    }
}
