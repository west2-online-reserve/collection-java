package core.data;

public class Player {
    private String fullName;
    private String gender;
    private String country;
    private String countryCode;

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void printAll() {
        System.out.println("Player{fullName=" + fullName
                + ", gender=" + gender
                + ", country=" + country
                + ", countryCode=" + countryCode + "}");
    }
}
