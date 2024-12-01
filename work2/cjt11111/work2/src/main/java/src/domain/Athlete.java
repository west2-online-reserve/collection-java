package domain;

public class Athlete {
    private String countryName;
    private String gender;
    private String lastName;
    private String firstName;

    public Athlete() {
    }

    public Athlete(String countryName, String gender, String preferredLastName, String preferredFirstName) {
        this.countryName = countryName;
        this.gender = gender;
        this.lastName = preferredLastName;
        this.firstName = preferredFirstName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

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

    @Override
    public String toString() {
        return "Full Name:" + lastName+" "+firstName + "\n" +
                "Gender:'" + gender +"\n"+
                "Country:" + countryName + "\n"
                +"-----";
    }
}
