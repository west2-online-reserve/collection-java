package com.dwasearch;

import com.dwasearch.model.FinalResult;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FinalResultTest {

    @Test
    void shouldKeepFinalResultFields() {
        FinalResult result = new FinalResult(
                "CHEN Jia",
                1,
                List.of(new BigDecimal("73.50"), new BigDecimal("74.40")),
                new BigDecimal("147.90")
        );

        assertEquals("CHEN Jia", result.getFullName());
        assertEquals(1, result.getRank());
        assertEquals(2, result.getDiveScores().size());
        assertEquals(new BigDecimal("73.50"), result.getDiveScores().get(0));
        assertEquals(new BigDecimal("147.90"), result.getTotalScore());
    }
}