package com.org.Lskar.Swimming.lib;

import com.google.gson.annotations.SerializedName;

public class Participation {
    @SerializedName("Gender")
    private int gender;

    @SerializedName("PreferredLastName")
    private String preferredLastName;

    @SerializedName("PreferredFirstName")
    private String preferredFirstName;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
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
}