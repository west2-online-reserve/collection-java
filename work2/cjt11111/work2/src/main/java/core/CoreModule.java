package core;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import domain.Athlete;
import domain.Contest;
import domain.ContestDetailed;
import utils.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class CoreModule {
    private static final String[] TYPE = {"Preliminary", "Semifinal", "Final"};

    // 输出所有选手信息
    public void displayAllPlayersInfo() {
        try {
            // 读取 athletes.json 文件
            StringBuilder jsonContent = new StringBuilder();
            InputStream in = getClass().getClassLoader().getResourceAsStream("athletes.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
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

                for (int k = 0; k < participations.size(); k++) {
                    JSONObject participation = participations.getJSONObject(k);
                    int gender = participation.getIntValue("Gender");
                    String preferredLastName = participation.getString("PreferredLastName");
                    String preferredFirstName = participation.getString("PreferredFirstName");
                    Athlete athlete = new Athlete(countryName, gender, preferredLastName, preferredFirstName);
                    Utility.write(athlete.toString());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 输出每个比赛项目决赛的结果
    public void displayResults(String contestName) {
        List<Contest> listFinal = displayResults(contestName, 2);

        for (Contest contest : listFinal) {
            try {
              //  System.out.println(contest.toString());
                Utility.write(contest.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 查询每个比赛项目的结果
    private List<Contest> displayResults(String contestName, int contestType) {
        List<Contest> contests = new ArrayList<>();
        try {
            // 读取 JSON 文件
            StringBuilder jsonContent = new StringBuilder();
            String path = contestName + ".json";
            InputStream in = getClass().getClassLoader().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
            br.close();
            JSONObject jsonObject = JSON.parseObject(jsonContent.toString());
            JSONArray heats = jsonObject.getJSONArray("Heats");

            for (int i = 0; i < heats.size(); i++) {
                JSONObject heat = heats.getJSONObject(i);
                String name = heat.getString("Name");
                if (!name.equals(TYPE[contestType])) {
                    continue;
                }
                JSONArray results = heat.getJSONArray("Results");
                for (int k = 0; k < results.size(); k++) {
                    JSONObject result = results.getJSONObject(k);
                    String totalPoints = result.getString("TotalPoints");
                    int rank = result.getIntValue("Rank");
                    String fullName = result.getString("FullName").replace('/', '&');
                    Contest contest = new Contest(totalPoints, rank, fullName, new ArrayList<>());

                    JSONArray dives = result.getJSONArray("Dives");
                    for (int j = 0; j < dives.size(); j++) {
                        contest.getDives().add(dives.getJSONObject(j).getString("DivePoints"));
                    }
                    contests.add(contest);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contests;
    }

    // 输出每个比赛项目结果（详细）
    public void displayDetailedResults(String contestName) {
        HashMap<String, ContestDetailed> map = new HashMap<>();
        for (Contest preliminaryContest : displayResults(contestName, 0)) {
            map.computeIfAbsent(preliminaryContest.getFullName(), k -> new ContestDetailed()).contests[0] = preliminaryContest;
        }
        for (Contest semiFinalContest : displayResults(contestName, 1)) {
            map.computeIfAbsent(semiFinalContest.getFullName(), k -> new ContestDetailed()).contests[1] = semiFinalContest;
        }
        for (Contest finalContest : displayResults(contestName, 2)) {
            map.computeIfAbsent(finalContest.getFullName(), k -> new ContestDetailed()).contests[2] = finalContest;
        }

        List<ContestDetailed> lst = new ArrayList<>(map.values());
        lst.sort(new Comparator<ContestDetailed>() {
            @Override
            public int compare(ContestDetailed o1, ContestDetailed o2) {
                for (int i = 0; i < 3; i++) {
                    if (o1.contests[i] != null && o2.contests[i] != null) {
                        return o1.contests[i].getRank() - o2.contests[i].getRank();
                    }
                }
                // 如果所有对应的 Contest 都为 null，可以按其他逻辑排序，或者认为它们相等
                return 0;
            }
        });

        for (ContestDetailed contestDetailed : lst) {
            try {
                Utility.write(contestDetailed.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
