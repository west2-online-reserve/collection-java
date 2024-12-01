package org.example;

public class Athlete {
    private String NAT;
    private String PreferredLastName;
    private String PreferredFirstName;
    private int Gender;
    public Athlete(){

    }

    public Athlete(String country, String firstname, String lastname, int gender) {
        this.NAT = country;
        this.PreferredLastName = firstname;
        this.PreferredFirstName = lastname;
        this.Gender = gender;
    }

    @Override
    public String toString() {
        return "Full Name:"+ PreferredLastName +" "+ PreferredFirstName +"\n"
               +"Gender:"+(Gender == 0 ? "Male" : "Female")+"\n"
                +"Country:" + NAT + "\n"
                +"-----";
    }

    public String getNAT() {
        return NAT;
    }

    public void setNAT(String NAT) {
        this.NAT = NAT;
    }

    public String getPreferredLastName() {
        return PreferredLastName;
    }

    public void setPreferredLastName(String preferredLastName) {
        this.PreferredLastName = preferredLastName;
    }

    public String getPreferredFirstName() {
        return PreferredFirstName;
    }

    public void setPreferredFirstName(String preferredFirstName) {
        this.PreferredFirstName = preferredFirstName;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        this.Gender = gender;
    }
}
