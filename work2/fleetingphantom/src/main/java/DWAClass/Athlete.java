package DWAClass;

public class Athlete {
    private String countryName;
    private String PreferredLastName;
    private String PreferredFirstName;
    private int gender;

    public Athlete() {
    }

    public Athlete(String countryName, String preferredLastName, String preferredFirstName, int gender) {
        this.countryName = countryName;
        PreferredLastName = preferredLastName;
        PreferredFirstName = preferredFirstName;
        this.gender = gender;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Full Name:" + PreferredLastName + " " + PreferredFirstName + "\n" +
                "Gender:" + (gender == 0 ? "Male" : "Female") + "\n" +
                "Country:" + countryName + "\n" +
                "-----";
    }
}
