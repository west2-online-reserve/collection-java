package com.west2.dwasearch.data;

public class DiveScore {
    private final int order;
    private final String points;

    public DiveScore(int order, String points) {
        this.order = order;
        this.points = points;
    }

    public int getOrder() {
        return order;
    }

    public String getPoints() {
        return points;
    }
}
