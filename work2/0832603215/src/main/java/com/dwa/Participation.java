package com.dwa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Participation {

    @JsonProperty("PreferredFirstName")
    private String firstName;

    @JsonProperty("PreferredLastName")
    private String lastName;

    @JsonProperty("Gender")
    private Integer gender;

    public Participation() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        if (gender == null) {
            return "Unknown";
        } else if (gender == 0) {
            return "Male";
        } else if (gender == 1) {
            return "Female";
        }
        return "Unknown";
    }
}