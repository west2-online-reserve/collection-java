package com.org.Lskar.Swimming.lib.Contest;

import com.google.gson.annotations.SerializedName;

public class Dive {
    @SerializedName("DiveOrder")
    private int order;
    @SerializedName("DivePoints")
    private String points;

    public int getOrder() {
        return order;
    }
    public String getPoints() {
        return points;
    }
}
