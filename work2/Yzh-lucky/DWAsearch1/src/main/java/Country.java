import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Country {
    @JsonProperty("CountryName")
    private String name;
    @JsonProperty("Participations")
    private ArrayList<Athlete> participations = new ArrayList<>();

    //set
    public void setName(String name){
        this.name = name;
    }
    public void setParticipations(ArrayList<Athlete> participations){
        this.participations = participations;
    }

    //get
    public String getName(){
        return this.name;
    }
    public ArrayList<Athlete> getParticipations(){
        return this.participations;
    }

}
