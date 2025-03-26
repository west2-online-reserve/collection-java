package com.org.Lskar.Swimming.lib.Contest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {
    @SerializedName("FullName")
    private String fullName;
    @SerializedName("Rank")
    private int rank;
    @SerializedName("TotalPoints")
    private String totalPoints;
    @SerializedName("Dives")
    private List<Dive> dives;

    public List<Dive> getDives() {
        return dives != null ? dives : new ArrayList<>();
    }


    public int getRank() {
        return rank;
    }
    public String getFullName() {
        return fullName;
    }
    public String getTotalPoints() {
        return totalPoints;
    }
}
