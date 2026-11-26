package com.dwa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    @JsonProperty("PreferredFirstName")
    private String firstName;
    @JsonProperty("PreferredLastName")
    private String lastName;
    @JsonProperty("Gender")
    private int gender;
    @JsonProperty("CountryName")
    private String country;

    public Player() {
    }
    public Player(String firstName, String lastName, int gender, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.country = country;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getGender() {
        if(gender==0){
            return "Male";
        }else if(gender==1){
            return "Female";
        }
        return "Unknown";
    }
    public String getCountry() {
        return country;
    }
}
