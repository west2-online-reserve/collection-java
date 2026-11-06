package dwasearch;

public class Player {

    public String fullName;
    public String gender;
    public String country;
    public String countryCode;

    public Player() {
    }

    public Player(String fullName, String gender, String country, String countryCode) {
        this.fullName = fullName;
        this.gender = gender;
        this.country = country;
        this.countryCode = countryCode;
    }

    public String getLastName() {
        if (fullName == null) {
            return "";
        }
        int i = fullName.indexOf(" ");
        if (i == -1) {
            return fullName;
        }
        return fullName.substring(0, i);
    }
}
