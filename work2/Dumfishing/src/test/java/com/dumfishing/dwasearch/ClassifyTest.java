package com.dumfishing.dwasearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassifyTest {
    @Test
    void players() {
        assertEquals(DWASearch.Command.PLAYERS, DWASearch.classify("players"));
    }

    @Test
    void resultWithName() {
        assertEquals(DWASearch.Command.RESULT, DWASearch.classify("result women 3m springboard"));
    }

    @Test
    void bareResult() {
        assertEquals(DWASearch.Command.RESULT_NO_NAME, DWASearch.classify("result"));
    }

    @Test
    void playersExtraIsUnknown() {
        assertEquals(DWASearch.Command.UNKNOWN, DWASearch.classify("players extra"));
    }

    @Test
    void capitalIsUnknown() {
        assertEquals(DWASearch.Command.UNKNOWN, DWASearch.classify("Players"));
    }
}

