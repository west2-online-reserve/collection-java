package main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AthleteManager {
    private List<Athlete> athletes;

    public AthleteManager() {
        this.athletes = new ArrayList<>();
    }

    public List<Athlete> loadAthletesData(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("Athletes data file not found: " + path.toAbsolutePath());
        }

        String json = new String(Files.readAllBytes(path));
        JSONArray countries = JSON.parseArray(json);

        for (int i = 0; i < countries.size(); i++) {
            JSONObject country = countries.getJSONObject(i);
            String countryName = country.getString("CountryName");

            JSONArray participations = country.getJSONArray("Participations");
            for (int j = 0; j < participations.size(); j++) {
                JSONObject athleteData = participations.getJSONObject(j);

                Athlete athlete = new Athlete();
                athlete.setId(athleteData.getString("PersonId"));
                athlete.setFullName(athleteData.getString("PreferredLastName") + " " +
                        athleteData.getString("PreferredFirstName"));

                int genderCode = athleteData.getInteger("Gender");
                athlete.setGender(genderCode == 0 ? "Male" : "Female");
                athlete.setCountry(countryName);

                athletes.add(athlete);
            }
        }

        return athletes;
    }

    public Athlete findAthleteById(String id) {
        for (Athlete athlete : athletes) {
            if (athlete.getId().equals(id)) {
                return athlete;
            }
        }
        return null;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }
}
