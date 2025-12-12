package org.example.lib;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Participation {


    @JsonProperty("ResultId")
    private int resultId;

    @JsonProperty("Gender")
    private boolean gender;

    @JsonProperty("PreferredLastName")
    private String preferredLastName;

    @JsonProperty("PreferredFirstName")
    private String preferredFirstName;


    @JsonProperty("PersonId")
    private String personId;


    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPreferredLastName() {
        return preferredLastName;
    }

    public void setPreferredLastName(String preferredLastName) {
        this.preferredLastName = preferredLastName;
    }

    public String getPreferredFirstName() {
        return preferredFirstName;
    }

    public void setPreferredFirstName(String preferredFirstName) {
        this.preferredFirstName = preferredFirstName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
