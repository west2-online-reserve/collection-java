package main.java.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Participations {
    @JsonProperty("PreferredLastName")
    private String lastName;
    @JsonProperty("PreferredFirstName")
    private String firstName;
    @JsonProperty("Gender")
    private Integer gender;

    public Participations(){};

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public String getGenderString(){
        if(gender == null){
            return "";
        }
        return gender == 0 ? "Male" : "Female";
    }

    public String toString(){
        return String.format("Full Name:%s %s\nGender:%s", lastName, firstName, this.getGenderString());
    }


}
