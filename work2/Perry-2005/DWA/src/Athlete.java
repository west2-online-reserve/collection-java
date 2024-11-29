public class Athlete {
    private String firstName;
    private String lastName;
    private String gender;
    private String country;

    public Athlete() {
    }

    public Athlete(String firstName, String lastName, String gender, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.country = country;
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
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String toString() {
        return firstName + "-" + lastName + "-" + gender + "-" + country;
    }
}

