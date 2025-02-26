package org.Lskar.Swimming.lib;

import com.google.gson.annotations.SerializedName;

public class Player {
    //名
    @SerializedName("PreferredLastName")
    private String preferredLastName;

    //姓
    @SerializedName("PreferredFirstName")
    private String preferredFirstName;

    //姓别
    @SerializedName("Gender")
    private String gender;

    //国家
    @SerializedName("CountryName")
    private String countryName;

    public void setPreferredLastName(String preferredLastName) {
        this.preferredLastName = preferredLastName;
    }

    public void setPreferredFirstName(String preferredFirstName) {
        this.preferredFirstName = preferredFirstName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPreferredLastName() {
        return preferredLastName;
    }

    public String getPreferredFirstName() {
        return preferredFirstName;
    }

    public String getGender() {
        return gender;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getFullName() {
        return preferredFirstName + " " + preferredLastName;
    }
    @Override
    public String toString() {
        return "Full Name: " + preferredFirstName + " " + preferredLastName+"\n"
                + "Gender: " + gender + "\n"
                + "Country: " + countryName;
    }
}
