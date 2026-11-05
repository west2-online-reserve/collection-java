package com.dwasearch;

import com.dwasearch.data.DataException;
import com.dwasearch.data.DataRepository;
import com.dwasearch.model.CompetitionData;
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

    @Test
    void shouldLoadSampleData() throws Exception {
        URL resource = getClass().getClassLoader().getResource("sample-data.json");
        assertNotNull(resource, "sample-data.json not found");

        Path path = Path.of(resource.toURI());
        CompetitionData data = new DataRepository().load(path);

        assertEquals(2, data.getPlayers().size());
        assertEquals("COLE Ellie", data.getPlayers().get(0).getFullName());

        assertEquals(1, data.getEvents().size());
        assertEquals("women 3m springboard", data.getEvents().get(0).getEventName());

        assertEquals(2, data.getEvents().get(0).getResults().size());
        assertEquals(1, data.getEvents().get(0).getResults().get(0).getRank());
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
}