package com.dwa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    @JsonProperty("CountryName")
    private String countryName;

    @JsonProperty("CountryCode")
    private String countryCode;

    @JsonProperty("Participations")
    private List<Participation> participations;

    public Country() {
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public List<Participation> getParticipations() {
        return participations;
    }
}