package com.org.Lskar.Swimming.lib.Contest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Event {
    @SerializedName("Heats")
    private List<Heat> heats;

    public List<Heat> getHeats() {
        return heats != null ? heats : new ArrayList<>();
    }

}
