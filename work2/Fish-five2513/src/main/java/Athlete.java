import java.util.ArrayList;
import java.util.List;

public class Athlete {
    private String id;
    private String gender;
    private String country;
    private String LastName;
    private String FirstName;
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getLastName() { return LastName; }
    public String getFirstName() { return FirstName; }
    public void setFirstName(String FirstName) { this.FirstName = FirstName; }
    public Athlete (String id, String gender, String country, String FirstName, String LastName) {
        this.id = id;
        this.gender = gender;
        this.country = country;
        this.FirstName = FirstName;
        this.LastName = LastName;
}
    }
