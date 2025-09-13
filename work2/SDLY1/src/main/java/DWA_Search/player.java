package DWA_Search;

public class player {

    private static String CountryName;
    private static int Gender;
    private static String PreferredLastName;
    private static String PreferredFirstName;
    public player(){}

    public player(String CountryName,int Gender,String PreferredLastName,String PreferredFirstName) {
        player.CountryName =CountryName;
        player.Gender =Gender;
        player.PreferredFirstName =PreferredFirstName;
        player.PreferredLastName =PreferredLastName;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getPreferredLastName() {
        return PreferredLastName;
    }

    public void setPreferredLastName(String preferredLastName) {
        PreferredLastName = preferredLastName;
    }

    public String getPreferredFirstName() {
        return PreferredFirstName;
    }

    public void setPreferredFirstName(String preferredFirstName) {
        PreferredFirstName = preferredFirstName;
    }

    public String toString() {
        return "Full Name:" + PreferredLastName + " " + PreferredFirstName + "\n" +
                "Gender:" + (Gender == 0 ? "Male" : "Female") + "\n" +
                "Country:" + CountryName + "\n" +
                "-----------";
    }
}