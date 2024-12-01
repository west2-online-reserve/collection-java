package lib;

public class Athlete {
    private String fullName;
    private String gender;
    private String country;

    public Athlete(String fullName, String gender, String country) {
        this.fullName = fullName;
        this.gender = gender;
        this.country = country;
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

    public String getLastName() {
        return fullName.split(" ")[1];
    }
}
