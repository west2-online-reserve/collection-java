package org.west2.learning;

import java.util.List;
import java.util.Map;

/** The small, offline JSON snapshot used by the query program. */
public record ContestData(String source, String capturedAt, List<Player> players,
                          Map<String, List<Result>> events) {
    public record Player(String fullName, String lastName, String gender, String country) {}
    public record Result(String fullName, int rank, List<String> scores, String total) {}
}
