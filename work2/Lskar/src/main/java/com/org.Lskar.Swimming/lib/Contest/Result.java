package com.org.Lskar.Swimming.lib.Contest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {
    @SerializedName("FullName")
    String fullName;
    @SerializedName("Rank")
    int rank;
    @SerializedName("TotalPoints")
    String totalPoints;
    @SerializedName("Dives")
    List<Dive> dives;

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
