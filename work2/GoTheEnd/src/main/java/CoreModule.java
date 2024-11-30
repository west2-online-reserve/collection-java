import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class CoreModule {

    public void displayAllPlayersInfo(BufferedWriter out) {
        try (

                InputStream is = getClass().getResourceAsStream("athletes.json");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
        ) {
            ArrayList<Athlete> athletes = new ArrayList<>();
            StringBuilder athleteList = new StringBuilder();
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                athleteList.append(line);
            }

            JSONArray athleteJSON = JSON.parseArray(athleteList.toString());

            for (int i = 0; i < athleteJSON.size(); i++) {
                JSONObject countryCategory = athleteJSON.getJSONObject(i);
                String countryName = countryCategory.getString("CountryName");
                JSONArray participations = countryCategory.getJSONArray("Participations");

                for (int j = 0; j < participations.size(); j++) {
                    JSONObject jsonObject = participations.getJSONObject(j);
                    String preferredFirstName = jsonObject.getString("PreferredFirstName");
                    String preferredLastName = jsonObject.getString("PreferredLastName");
                    int gender = jsonObject.getIntValue("Gender");
                    Athlete athlete = new Athlete(countryName, gender, preferredFirstName, preferredLastName);
                    athletes.add(athlete);
                }
            }

            for (Athlete athlete : athletes) {
                output.append(athlete.toString());
            }
            out.write(output.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void displayResultsForEachEvent(String contestType, BufferedWriter bw) {
        try (
                InputStream is = getClass().getResourceAsStream(contestType + ".json");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
        ) {
            ArrayList<ContestResult> contestResultArrayList = new ArrayList<>();
            StringBuilder contest = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                contest.append(line);
            }

            JSONObject contestJson = JSON.parseObject(contest.toString());
            JSONArray heats = contestJson.getJSONArray("Heats");
            String gender = contestJson.getString("Gender");
            JSONObject finalResult = heats.getJSONObject(0);
            JSONArray results = finalResult.getJSONArray("Results");

            for (int i = 0; i < results.size(); i++) {
                JSONObject resultsJSONObject = results.getJSONObject(i);
                String fullName = resultsJSONObject.getString("FullName");
                JSONArray dives = resultsJSONObject.getJSONArray("Dives");
                double[] divePoints = new double[6];
                double totalPoints = resultsJSONObject.getDoubleValue("TotalPoints");

                for (int j = 0; j < dives.size(); j++) {
                    JSONObject divesJSONObject = dives.getJSONObject(j);
                    divePoints[j] = divesJSONObject.getDoubleValue("DivePoints");
                }

                ContestResult contestResult = new ContestResult(fullName, totalPoints, divePoints, i + 1, gender);
                contestResultArrayList.add(contestResult);
            }
            for (ContestResult result : contestResultArrayList) {
                System.out.println(result.toString());
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void displayDetailResult(String contestType, BufferedWriter bw){

    }
}
