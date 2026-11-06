package com.west2.dwasearch.query;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreFormatterTest {
    @Test
    void keepsTwoDecimals() {
        assertEquals("73.50", ScoreFormatter.format("73.50"));
        assertEquals("374.40", ScoreFormatter.format("374.40"));
    }

    @Test
    void padsMissingDecimals() {
        assertEquals("73.50", ScoreFormatter.format("73.5"));
        assertEquals("374.40", ScoreFormatter.format("374.4"));
        assertEquals("0.00", ScoreFormatter.format("0"));
        assertEquals("81.00", ScoreFormatter.format("81"));
    }

    @Test
    void roundsExtraDecimals() {
        assertEquals("73.46", ScoreFormatter.format("73.456"));
        assertEquals("3.33", ScoreFormatter.format("3.3333333"));
    }

    @Test
    void decimalSumHasNoFloatingPointError() {
        BigDecimal total = new BigDecimal("0.1").add(new BigDecimal("0.2"));
        assertEquals("0.30", ScoreFormatter.format(total.toPlainString()));
    }
}
