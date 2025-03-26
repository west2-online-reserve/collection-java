package com.org.Lskar.Swimming.lib.Contest;

import com.google.gson.annotations.SerializedName;

public class Dive {
    @SerializedName("DiveOrder")
    int order;
    @SerializedName("DivePoints")
    String points;

    public int getOrder() {
        return order;
    }
    public String getPoints() {
        return points;
    }
}
