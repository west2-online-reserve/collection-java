package com.org.Lskar.Swimming.lib.Contest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Heat {
    @SerializedName("Name")
    String name;
    @SerializedName("Results")
    List<Result> results;

    public String getName() {
        return name != null ? name : "";
    }

    public List<Result> getResults() {
        return results != null ? results : new ArrayList<>();
    }
}
