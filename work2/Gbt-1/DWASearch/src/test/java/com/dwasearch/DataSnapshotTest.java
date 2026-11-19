package com.dwasearch;

import com.dwasearch.data.DataRepository;
import com.dwasearch.model.CompetitionData;
import com.dwasearch.model.EventResult;
import com.dwasearch.model.FinalResult;
import com.dwasearch.query.PlayerQueryService;
import com.dwasearch.query.ResultQueryService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataSnapshotTest {

    private static final List<String> EVENT_NAMES = List.of(
            "women 3m springboard",
            "women 10m platform",
            "women 3m synchronised",
            "women 10m synchronised",
            "men 3m springboard",
            "men 10m platform",
            "men 3m synchronised",
            "men 10m synchronised"
    );

    @Test
    void shouldMatchCommittedSnapshotSample() throws Exception {
        CompetitionData data = new DataRepository().load(Path.of("data.json"));

        assertEquals(89, data.getPlayers().size());
        assertEquals(8, data.getEvents().size());

        PlayerQueryService playerQuery = new PlayerQueryService();
        assertEquals(
                "COLE Ellie",
                playerQuery.sortedPlayers(data.getPlayers()).get(0).getFullName()
        );

        ResultQueryService resultQuery = new ResultQueryService();

        for (String eventName : EVENT_NAMES) {
            EventResult event = resultQuery.findEvent(data, eventName);
            assertNotNull(event, "missing event: " + eventName);
            assertFalse(event.getResults().isEmpty(), "empty event: " + eventName);
        }

        EventResult women3m = resultQuery.findEvent(data, "women 3m springboard");
        assertNotNull(women3m);

        FinalResult first = resultQuery.sortedResults(women3m.getResults()).get(0);

        assertEquals("CHEN Jia", first.getFullName());
        assertEquals(new BigDecimal("374.40"), first.getTotalScore());
        assertEquals(
                List.of(
                        new BigDecimal("73.50"),
                        new BigDecimal("74.40"),
                        new BigDecimal("76.50"),
                        new BigDecimal("72.00"),
                        new BigDecimal("78.00")
                ),
                first.getDiveScores()
        );
    }
}