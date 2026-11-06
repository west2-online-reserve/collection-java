package com.west2.dwasearch.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventResult {
    private final String fullName;
    private final int rank;
    private final String totalPoints;
    private final List<DiveScore> dives;

    public EventResult(String fullName, int rank, String totalPoints, List<DiveScore> dives) {
        this.fullName = fullName;
        this.rank = rank;
        this.totalPoints = totalPoints;
        this.dives = new ArrayList<>(dives);
        Collections.sort(this.dives, new DiveOrderComparator());
    }

    public String getFullName() {
        return fullName;
    }

    public int getRank() {
        return rank;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public List<DiveScore> getDives() {
        return Collections.unmodifiableList(dives);
    }

    private static class DiveOrderComparator implements java.util.Comparator<DiveScore> {
        @Override
        public int compare(DiveScore a, DiveScore b) {
            return Integer.compare(a.getOrder(), b.getOrder());
        }
    }
}
