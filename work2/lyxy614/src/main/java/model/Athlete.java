package main.java.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Athlete {
    @JsonProperty("CountryName")
    private String country;

    @JsonProperty("Participations")
    private List<Participations> participations;

    public Athlete(){};

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String toString(){
        return String.format("Country: %s", country);
    }

    public List<Participations> getParticipations() {
        return participations;
    }
    public void setParticipations(List<Participations> participations) {
        this.participations = participations;
    }
}
