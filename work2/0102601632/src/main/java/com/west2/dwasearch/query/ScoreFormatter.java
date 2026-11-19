package com.west2.dwasearch.query;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ScoreFormatter {
    private ScoreFormatter() {
    }

    public static String format(String score) {
        return new BigDecimal(score.trim()).setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
