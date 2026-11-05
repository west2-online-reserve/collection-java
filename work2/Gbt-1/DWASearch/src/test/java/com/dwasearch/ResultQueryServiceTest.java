package com.dwasearch;

import com.dwasearch.model.CompetitionData;
import com.dwasearch.model.EventResult;
import com.dwasearch.model.FinalResult;
import com.dwasearch.query.ResultQueryService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ResultQueryServiceTest {

    @Test
    void shouldFindEventByName() {
        EventResult event = new EventResult(
                "women 3m springboard",
                List.of()
        );

        CompetitionData data = new CompetitionData(
                List.of(),
                List.of(event)
        );

        ResultQueryService service = new ResultQueryService();

        assertNotNull(service.findEvent(data, "women 3m springboard"));
        assertNull(service.findEvent(data, "women 10m platform"));
    }

    @Test
    void shouldSortByRank() {
        FinalResult second = new FinalResult(
                "CHEN Yiwen",
                2,
                List.of(new BigDecimal("69.00")),
                new BigDecimal("69.00")
        );

        FinalResult first = new FinalResult(
                "CHEN Jia",
                1,
                List.of(new BigDecimal("73.50")),
                new BigDecimal("73.50")
        );

        List<FinalResult> sorted = new ResultQueryService()
                .sortedResults(List.of(second, first));

        assertEquals(2, sorted.size());
        assertEquals("CHEN Jia", sorted.get(0).getFullName());
        assertEquals(1, sorted.get(0).getRank());
        assertEquals("CHEN Yiwen", sorted.get(1).getFullName());
    }

    @Test
    void shouldFormatScoresWithTwoDecimals() {
        FinalResult result = new FinalResult(
                "CHEN Jia",
                1,
                List.of(
                        new BigDecimal("73.5"),
                        new BigDecimal("74.40"),
                        new BigDecimal("76.50"),
                        new BigDecimal("72.00"),
                        new BigDecimal("78.00")
                ),
                new BigDecimal("374.4")
        );

        String actual = new ResultQueryService()
                .formatResults(List.of(result));

        String expected =
                "Full Name:CHEN Jia\n" +
                        "Rank:1\n" +
                        "Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40\n" +
                        "-----\n";

        assertEquals(expected, actual);
    }
}