package com.example.taskdive;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// 国家类包含国家名和国名缩写，以及所有运动员的信息
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    @JsonProperty("CountryName")
    private String CountryName;

    @JsonProperty("CountryCode")
    private String CountryCode;

    @JsonProperty("Participations")
    public List<Athlete> Participations;

    public Country(String CountryName, String CountryCode) {
        this.CountryName = CountryName;
        this.CountryCode = CountryCode;
    }

    public Country() {
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String CountryName) {
        this.CountryName = CountryName;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    public List<Athlete> getParticipations() {
        return Participations;
    }

    public void setParticipations(List<Athlete> participations) {
        this.Participations = participations;
    }
}