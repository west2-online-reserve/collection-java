package com.org.Lskar.Swimming.lib;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Country {
    @SerializedName("CountryName")
    private String countryName;

    @SerializedName("Participations")
    private List<Participation> participations;

    // Getters and Setters
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }
}