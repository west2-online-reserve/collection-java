package com.dwa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dive {

    @JsonProperty("DivePoints")
    private BigDecimal divePoints;

    public BigDecimal getDivePoints(){
        return divePoints;
    }
}
