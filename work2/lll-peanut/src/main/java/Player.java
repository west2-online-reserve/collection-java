import java.util.Objects;

public class Player implements Comparable<Player>{

    @Override
    public int compareTo(Player o) {
        int countryComparison = this.country.compareTo(o.country);
        if (countryComparison != 0) {
            return countryComparison;
        }

        return this.fullName.compareTo(o.fullName);
    }

    String fullName;
    String gender;
    String country;

    public Player(String fullName, String gender, String country) {
        this.fullName = fullName;
        this.gender = gender;
        this.country = country;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    @Override
    public String toString() {
        return "Full Name:" + fullName + "\n" +
                "Gender:" + gender + "\n" +
                "Country:" + country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(fullName, player.fullName) &&
                Objects.equals(country, player.country) && Objects.equals(gender, player.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, country, gender);
    }
}
