package com.dwasearch.query;

import com.dwasearch.model.CompetitionData;
import com.dwasearch.model.EventResult;
import com.dwasearch.model.FinalResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResultQueryService {

    public EventResult findEvent(CompetitionData data, String eventName) {
        for (EventResult event : data.getEvents()) {
            if (event.getEventName().equals(eventName)) {
                return event;
            }
        }
        return null;
    }

    public List<FinalResult> sortedResults(List<FinalResult> results) {
        List<FinalResult> copy = new ArrayList<>(results);

        copy.sort(
                Comparator.comparingInt(FinalResult::getRank)
                        .thenComparing(FinalResult::getFullName)
        );

        return copy;
    }

    public String formatResults(List<FinalResult> results) {
        StringBuilder text = new StringBuilder();

        for (FinalResult result : results) {
            text.append("Full Name:").append(result.getFullName()).append('\n');
            text.append("Rank:").append(result.getRank()).append('\n');
            text.append("Score:");

            for (int i = 0; i < result.getDiveScores().size(); i++) {
                if (i > 0) {
                    text.append(" + ");
                }
                text.append(formatScore(result.getDiveScores().get(i)));
            }

            text.append(" = ").append(formatScore(result.getTotalScore())).append('\n');
            text.append("-----\n");
        }

        return text.toString();
    }

    private String formatScore(BigDecimal score) {
        return score.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}