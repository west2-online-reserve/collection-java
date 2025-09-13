package DWA;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

public class Athlete implements Comparable<Athlete>{
    @JSONField(name = "PreferredFirstName")
    protected String firstName;
    @JSONField(name = "PreferredLastName")
    protected String lastName;
    @JSONField(name = "Gender")
    protected String Gender;

    protected String country;

    public Athlete(String firstName, String lastName, String gender, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        Gender = gender;
        this.country = country;
    }

    public Athlete() {
    }

    public static String turnGenderString(int gender) {
        return switch (gender) {
            case 0 -> "Male";
            case 1 -> "Female";
            default -> "Unknown";
        };
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = turnGenderString(gender);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Full Name:" + firstName + " " + lastName + "\nGender:" + Gender + "\nCountry:" + country;
    }

    @Override
    public int compareTo(Athlete o) {
        if(o.getCountry().equals(this.getCountry())){
            return this.getLastName().compareTo(o.getLastName());
        }
        else{
            return this.getCountry().compareTo(o.getCountry());
        }
    }

}
