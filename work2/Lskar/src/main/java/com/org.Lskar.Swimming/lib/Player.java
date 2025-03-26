package com.org.Lskar.Swimming.lib;

import com.google.gson.annotations.SerializedName;

public class Player {
    private String countryName;
    private int gender;
    private String preferredFirstName;
    private String preferredLastName;


    public Player(String countryName, int gender, String preferredFirstName, String preferredLastName) {
        this.countryName = countryName;
        this.gender = gender;
        this.preferredFirstName = preferredFirstName;
        this.preferredLastName = preferredLastName;
    }
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPreferredFirstName() {
        return preferredFirstName;
    }

    public void setPreferredFirstName(String preferredFirstName) {
        this.preferredFirstName = preferredFirstName;
    }

    public String getPreferredLastName() {
        return preferredLastName;
    }

    public void setPreferredLastName(String preferredLastName) {
        this.preferredLastName = preferredLastName;
    }

    @Override
    public String toString() {
        return "Full Name: " + preferredFirstName+ " "+ preferredLastName + "\n" +
                "Gender: " + gender + "\n" +
                "Country: " + countryName;
    }

}
