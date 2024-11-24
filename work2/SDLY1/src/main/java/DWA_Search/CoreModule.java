package DWA_Search;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Comparator;
import java.util.HashMap;

public class CoreModule {
    // 输出所有选手信息
    private static final String[] TYPE = {"Preliminary", "Semifinal", "Final"};
    public void displayAllPlayersInfo() {
        // 实现代码...

        try {
            //读取文件
            StringBuilder stringBuilder = new StringBuilder();
            String path= "athletes"+".json";
            InputStream in = getClass().getClassLoader().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            br.close();
                //分析 json 文件数据
            JSONArray jsonArray = JSON.parseArray(stringBuilder.toString());
            // 遍历每个国家
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject nation = jsonArray.getJSONObject(i);
                // 获得 国家名字
                String countryName = nation.getString("CountryName");
                // 提取 参赛队员 列表
                JSONArray participations = nation.getJSONArray("Participations");

                for (int k = 0; k < participations.size(); k++) {
                    // 获得 参赛队员
                    JSONObject participation = participations.getJSONObject(k);
                    int gender = participation.getIntValue("Gender");
                    String preferredLastName = participation.getString("PreferredLastName");
                    String preferredFirstName = participation.getString("PreferredFirstName");
                    player plr = new player(countryName, gender, preferredLastName, preferredFirstName);

                    IOstream.write(plr.toString());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 输出每个比赛项目的结果
    private List<Contest> displayResults(String contestName, int contestType) {
        List<Contest> contests = new ArrayList<>();
        try {
            // 读取 JSON 文件
            StringBuilder stringBuilder = new StringBuilder();
            String path = contestName + ".json";
            InputStream in = getClass().getClassLoader().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            br.close();

            JSONObject jsonObject = JSON.parseObject(stringBuilder.toString());

            // 获取 Heats 列表
            JSONArray heats = jsonObject.getJSONArray("Heats");

            for (int i = 0; i < heats.size(); i++) {
                JSONObject heat = heats.getJSONObject(i);
                // 获取当前比赛级别
                String name = heat.getString("Name");
                // 如果不是目标类型, 退出
                if (!name.equals(TYPE[contestType])) {
                    continue;
                }
                // 获取当前比赛结果
                JSONArray results = heat.getJSONArray("Results");
                for (int j = 0; j < results.size(); j++) {
                    // 获取每位运动员的成绩
                    JSONObject result = results.getJSONObject(j);
                    // 总成绩
                    String totalPoints = result.getString("TotalPoints");
                    // 排名
                    int rank = result.getIntValue("Rank");
                    // FullName (注意：如果是双人跳水，FullName为 "A / B" 格式，需要 replace)
                    String fullName = result.getString("FullName").replace('/', '&');

                    Contest contest = new Contest(totalPoints, rank, fullName, new ArrayList<>());
                    // 将获取的 score 添加到 contest 中的 data 集合
                    JSONArray data = result.getJSONArray("Dives");
                    for (int k = 0; k < data.size(); k++) {
                        contest.getData().add(data.getJSONObject(k).getString("DivePoints"));
                    }
                    // 加入 contests 集合中
                    contests.add(contest);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contests;
    }
    public void displayResults(String contestName) {
        // 实现代码...
        List<Contest> listFinal = displayResults(contestName, 2);
        for (Contest contest : listFinal) {
            try {
                IOstream.write(contest.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

         public void displayDetailedResults(String contestName) {
        HashMap<String, Detailed> map = new HashMap<>();
        for (Contest preliminaryContest : displayResults(contestName, 0)) {
            map.computeIfAbsent(preliminaryContest.getFullName(), k -> new Detailed()).contests[0] = preliminaryContest;
        }
        for (Contest semiFinalContest : displayResults(contestName, 1)) {
            map.computeIfAbsent(semiFinalContest.getFullName(), k -> new Detailed()).contests[1] = semiFinalContest;
        }
        for (Contest finalContest : displayResults(contestName, 2)) {
            map.computeIfAbsent(finalContest.getFullName(), k -> new Detailed()).contests[2] = finalContest;
        }

        List<Detailed> lst = new ArrayList<>(map.values());
        lst.sort(new Comparator<Detailed>() {
            //重载sort运算，使其可以按照特定的顺序排序
            @Override
            public int compare(Detailed a, Detailed b) {
                if (a.contests[0] != null) {
                    return a.contests[0].getRank() - b.contests[0].getRank();
                }
                else if (a.contests[1] != null) {
                    return a.contests[1].getRank() - b.contests[1].getRank();
                }
                else {
                    return a.contests[2].getRank() - b.contests[2].getRank();
                }
            }
        });

        for (Detailed Detailed : lst) {
            try {
                IOstream.write(Detailed.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


