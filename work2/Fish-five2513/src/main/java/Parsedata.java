import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class Parsedata {
    public List<Athlete> parseathlete() {
        List<Athlete> athletes = new ArrayList<>();
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream("athletes.json");
            if (in == null) {
                throw new FileNotFoundException("athletes.json not found in resources.");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
            br.close();

            JSONArray jsonArray = JSON.parseArray(jsonContent.toString());

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject nation = jsonArray.getJSONObject(i);
                String countryName = nation.getString("CountryName");

                JSONArray participations = nation.getJSONArray("Participations");
                if (participations == null) continue;

                for (int k = 0; k < participations.size(); k++) {
                    JSONObject participation = participations.getJSONObject(k);
                    String id = participation.getString("PersonId");
                    String gender = "0".equals(participation.getString("Gender")) ? "Male" : "Female";
                    String preferredLastName = participation.getString("PreferredLastName");
                    String preferredFirstName = participation.getString("PreferredFirstName");

                    athletes.add(new Athlete(id, gender, preferredLastName, preferredFirstName, countryName));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse athlete data.", e); // 此处 e 不会为 null
        }
        return athletes;
    }

    public List<Contest> parseContest(String eventName) {
        List<Contest> contests = new ArrayList<>();
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(eventName + ".json");
            if (in == null) {
                throw new FileNotFoundException(eventName + ".json not found in resources.");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
            br.close();

            // 修复：解析为JSONObject而不是JSONArray
            JSONObject rootObject = JSON.parseObject(jsonContent.toString());
            JSONArray heats = rootObject.getJSONArray("Heats");
            if (heats == null) return contests;

            for (int j = 0; j < heats.size(); j++) {
                JSONObject heat = heats.getJSONObject(j);
                String level = heat.getString("Name");
                JSONArray results = heat.getJSONArray("Results");
                if (results == null) continue;

                for (int k = 0; k < results.size(); k++) {
                    JSONObject result = results.getJSONObject(k);
                    // 修复：TotalPoints是字符串，需要转换
                    String totalPointsStr = result.getString("TotalPoints");
                    Double totalPoints = totalPointsStr != null ? Double.parseDouble(totalPointsStr) : 0.0;
                    int rank = result.getIntValue("Rank");
                    String personId = result.getString("PersonId");
                    JSONArray divesArray = result.getJSONArray("Dives");

                    Athlete athlete = getAthleteById(personId);
                    if (athlete == null) continue;

                    List<Double> finalPoints = new ArrayList<>();
                    List<Double> semifinalPoints = new ArrayList<>();
                    List<Double> preliminaryPoints = new ArrayList<>();

                    int finalrank = 0;
                    int semifinalrank = 0;
                    int preliminaryrank = 0;
                    double finalTotalPoints = 0.0;
                    double semifinalTotalPoints = 0.0;
                    double preliminaryTotalPoints = 0.0;

                    if (divesArray != null) {
                        for (int l = 0; l < divesArray.size(); l++) {
                            JSONObject dive = divesArray.getJSONObject(l);
                            if (dive == null) continue;

                            String divePointsStr = dive.getString("DivePoints");
                            Double divePoint = divePointsStr != null ? Double.parseDouble(divePointsStr) : 0.0;

                            if ("Finals".equals(level)){
                                finalrank = rank;
                                finalTotalPoints = totalPoints;
                                finalPoints.add(divePoint);
                            } else if ("Semifinal".equals(level)) {
                                semifinalrank = rank;
                                semifinalTotalPoints = totalPoints;
                                semifinalPoints.add(divePoint);
                            } else if("Preliminaries".equals(level)) {
                                preliminaryrank = rank;
                                preliminaryTotalPoints = totalPoints;
                                preliminaryPoints.add(divePoint);
                            }
                        }
                    }
                    contests.add(new Contest(
                            eventName,
                            level,
                            finalrank,
                            semifinalrank,
                            preliminaryrank,
                            finalTotalPoints,
                            semifinalTotalPoints,
                            preliminaryTotalPoints,
                            finalPoints,
                            semifinalPoints,
                            preliminaryPoints,
                            athlete));
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Failed to parse contest data for event: " + eventName, e);
        }
        return contests;
    }


    public Athlete getAthleteById(String id) {
        List<Athlete> athletes = CoreModule.athletes;
        for (Athlete athlete : athletes) {
            if (athlete.getId().equals(id)) {
                return athlete;
            }
        }

        return null;
    }
    public String score(List<Double> points) {
        StringBuilder sb = new StringBuilder();
        double sum = 0;
        for (int i = 0; i < points.size(); i++) {
            if (i > 0) sb.append(" + ");
            sb.append(String.format("%.2f", points.get(i)));
            sum += points.get(i);
        }
        if (sum == 0){
            return "*";
        }
        sb.append(" = ").append(String.format("%.2f", sum));
        return sb.toString();


    }
}
