package com.dwasearch;

import com.dwasearch.model.EventResult;
import com.dwasearch.model.FinalResult;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventResultTest {

    @Test
    void shouldKeepEventNameAndResults() {
        FinalResult first = new FinalResult(
                "CHEN Jia",
                1,
                List.of(new BigDecimal("73.50")),
                new BigDecimal("73.50")
        );

        FinalResult second = new FinalResult(
                "CHEN Yiwen",
                2,
                List.of(new BigDecimal("69.00")),
                new BigDecimal("69.00")
        );

        EventResult event = new EventResult(
                "women 3m springboard",
                List.of(first, second)
        );

        assertEquals("women 3m springboard", event.getEventName());
        assertEquals(2, event.getResults().size());
        assertEquals("CHEN Jia", event.getResults().get(0).getFullName());
        assertEquals(2, event.getResults().get(1).getRank());
    }
}