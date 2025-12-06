package main.java.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dives {
    @JsonProperty("DivePoints")
    private String divePoints;

    public Dives(){}

    public String getDivePoints(){
        return divePoints;
    }
    public void setDivePoints(String divePoints){
        this.divePoints = divePoints;
    }
    public String toString(){
        return divePoints;
    }
}
