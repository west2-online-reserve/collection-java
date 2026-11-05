package com.dwasearch;

import com.dwasearch.model.CompetitionData;
import com.dwasearch.model.EventResult;
import com.dwasearch.model.FinalResult;
import com.dwasearch.model.Player;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompetitionDataTest {

    @Test
    void shouldKeepPlayersAndEvents() {
        Player player = new Player("Ellie", "COLE", "Female", "Australia");

        FinalResult result = new FinalResult(
                "CHEN Jia",
                1,
                List.of(new BigDecimal("73.50")),
                new BigDecimal("73.50")
        );

        EventResult event = new EventResult(
                "women 3m springboard",
                List.of(result)
        );

        CompetitionData data = new CompetitionData(
                List.of(player),
                List.of(event)
        );

        assertEquals(1, data.getPlayers().size());
        assertEquals(1, data.getEvents().size());
        assertEquals("COLE Ellie", data.getPlayers().get(0).getFullName());
        assertEquals("women 3m springboard", data.getEvents().get(0).getEventName());
    }
}