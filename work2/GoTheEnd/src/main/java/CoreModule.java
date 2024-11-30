import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CoreModule {
    public static final String[] SCHEDULE_STATUS = new String[]{"Preliminary", "Semifinal", "Final"};

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
        Map<String, ContestResult> contestResultMap = getContest(contestType);
        ArrayList<ContestResult> contestResultArrayList = new ArrayList<>(contestResultMap.values());
        for (ContestResult result : contestResultArrayList) {
            result.setFinalRank();
        }
        contestResultArrayList.sort(new Comparator<ContestResult>() {
            @Override
            public int compare(ContestResult o1, ContestResult o2) {
                Integer rank1 = o1.getFinalRank();
                Integer rank2 = o2.getFinalRank();
                return rank1.compareTo(rank2);
            }
        });

        for (ContestResult result : contestResultArrayList) {
            if (result.getScheduleMap().get("Final") == null) {
                continue;
            }
            try {
                StringBuilder info = new StringBuilder();
                info.append(("""
                        Full name:%s
                        Rank:%d
                        Score:""").formatted(result.getName(), result.getFinalRank()));
                ArrayList<Double> divePoints = result.getScheduleMap().get("Final").getDivePoints();
                for (int i = 0; i < divePoints.size(); i++) {
                    info.append(("%.2f ").formatted(divePoints.get(i)));
                    if (i != divePoints.size() - 1) {
                        info.append("+ ");
                    } else {
                        info.append("= ");
                    }
                }
                info.append(("%.2f\n-----\n").formatted(result.getScheduleMap().get("Final").getTotalPoints()));
                bw.write(info.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public Map<String, ContestResult> getContest(String contestType) {
        Map<String, ContestResult> contestResultArrayMap = new HashMap<>();
        try (
                InputStream is = getClass().getResourceAsStream(contestType + ".json");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
        ) {
            StringBuilder contest = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                contest.append(line);
            }

            JSONObject contestJson = JSON.parseObject(contest.toString());
            JSONArray heats = contestJson.getJSONArray("Heats");
            String gender = contestJson.getString("Gender");

            //开始遍历不同赛程。
            for (int m = 0; m < heats.size(); m++) {
                //表示其中一个赛程对象。
                JSONObject heatsJSONObject = heats.getJSONObject(m);
                String name = heatsJSONObject.getString("Name");
                JSONArray results = heatsJSONObject.getJSONArray("Results");

                for (int i = 0; i < results.size(); i++) {
                    JSONObject resultsJSONObject = results.getJSONObject(i);
                    String fullName = resultsJSONObject.getString("FullName");
                    JSONArray dives = resultsJSONObject.getJSONArray("Dives");
                    ArrayList<Double> divePoints = new ArrayList<>();
                    double totalPoints = resultsJSONObject.getDoubleValue("TotalPoints");

                    for (int j = 0; j < dives.size(); j++) {
                        JSONObject divesJSONObject = dives.getJSONObject(j);
                        divePoints.add(divesJSONObject.getDoubleValue("DivePoints"));
                    }
                    //转化为contestResult.

                    ScheduleDetail scheduleDetail = new ScheduleDetail(totalPoints, divePoints, i + 1);
                    if (!contestResultArrayMap.containsKey(fullName)) {
                        ContestResult contestResult = new ContestResult(fullName, gender);
                        contestResultArrayMap.put(fullName, contestResult);
                    }
                    contestResultArrayMap.get(fullName).setScheduleMap(name, scheduleDetail);

                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contestResultArrayMap;
    }

    public void displayDetailResult(String contestType, BufferedWriter bw) {
        Map<String, ContestResult> contestResultMap = getContest(contestType);
        ArrayList<ContestResult> contestResultArrayList = new ArrayList<>(contestResultMap.values());
        for (ContestResult contestResult : contestResultArrayList) {
            contestResult.setFirstRank();
        }

        contestResultArrayList.sort(new Comparator<ContestResult>() {
            @Override
            public int compare(ContestResult o1, ContestResult o2) {
                Integer rank1 = o1.getFirstRank();
                Integer rank2 = o2.getFirstRank();
                return rank1.compareTo(rank2);
            }
        });

        StringBuilder info = new StringBuilder();
        for (ContestResult contestResult : contestResultArrayList) {
            info.append(("""
                    Full name:%s
                    Rank:""").formatted(contestResult.getName()));
            for (int i = 0; i < 3; i++) {
                ScheduleDetail detail = contestResult.getScheduleMap().get(SCHEDULE_STATUS[i]);
                if (detail != null) {
                    info.append(("%d").formatted(detail.getRank()));
                }else {
                    info.append("*");
                }
                if (i != 2) {
                    info.append(" | ");
                } else {
                    info.append("\n");
                }
            }

            for (int i = 0; i < 3; i++) {
                ArrayList<Double> divePoints;
                if (contestResult.getScheduleMap().get(SCHEDULE_STATUS[i]) != null) {
                    divePoints = contestResult.getScheduleMap().get(SCHEDULE_STATUS[i]).getDivePoints();
                    info.append(("%s Score:").formatted(SCHEDULE_STATUS[i]));
                    for (int j = 0; j < divePoints.size(); j++) {
                        info.append(("%.2f").formatted(divePoints.get(j)));
                        if (j != divePoints.size() - 1) {
                            info.append(" + ");
                        } else {
                            info.append(" = ");
                        }
                    }
                    info.append(("%.2f\n").formatted(contestResult.getScheduleMap().get(SCHEDULE_STATUS[i]).getTotalPoints()));
                }
            }
            info.append("-----\n");
        }
        try {
            bw.write(info.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

