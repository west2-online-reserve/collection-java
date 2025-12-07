package main;
import java.util.ArrayList;
import java.util.List;

public class Athlete {
    private String id;
    private String fullName;
    private String gender;
    private String country;
    private List<CompetitionDetail> competitions = new ArrayList<>();

    public Athlete() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public List<CompetitionDetail> getCompetitions() { return competitions; }
    public void setCompetitions(List<CompetitionDetail> competitions) { this.competitions = competitions; }

    public void addCompetition(CompetitionDetail competition) {
        competitions.add(competition);
    }
}
