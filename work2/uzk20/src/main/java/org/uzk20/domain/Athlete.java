package org.uzk20.domain;

import org.uzk20.utils.Constants;

public class Athlete {
    private String fullName;
    private String gender;
    private String country;

    //单人项目
    public Athlete(String firstname, String lastname, int gender, String country) {
        this.fullName = lastname+" "+firstname;
        this.gender = gender==1? Constants.GENDER_MALE : Constants.GENDER_FEMALE;
        this.country = country;
    }
    //双人
    public Athlete(String lastName1, String firstName1, String lastName2, String firstName2, int genderCode, String country) {
        if (lastName1.compareTo(lastName2) <= 0) {
            this.fullName = lastName1 + " " + firstName1 + " & " + lastName2 + " " + firstName2;
        } else {
            this.fullName = lastName2 + " " + firstName2 + " & " + lastName1 + " " + firstName1;
        }
        this.gender = genderCode == 1 ? Constants.GENDER_MALE : Constants.GENDER_FEMALE;
        this.country = country;
    }

    public String toFormattedString() {
        return String.format("Full Name:%s\nGender:%s\nCountry:%s\n%s", fullName, gender, country, Constants.SEPARATOR);
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }
}
