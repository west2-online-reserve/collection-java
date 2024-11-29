import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class Core extends Tool {
    public void displayAllPlayersInfo() throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        InputStream isr = getClass().getClassLoader().getResourceAsStream("athletes.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(isr));
        String line;
        while ((line = br.readLine()) != null) {
            jsonContent.append(line);
        }
        br.close();
        //解析json数据,提取各国家代表团列表
        JSONArray jsonArray = JSON.parseArray(jsonContent.toString());
        //遍历每个国家
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject nation = jsonArray.getJSONObject(i);
            String countryName = nation.getString("CountryName");
            JSONArray participations = nation.getJSONArray("Participations");
            for (int i1 = 0; i1 < participations.size(); i1++) {
                JSONObject participation = participations.getJSONObject(i1);
                String gender = participation.getString("Gender");
                String preferredFirstName = participation.getString("PreferredFirstName");
                String preferredLastName = participation.getString("PreferredLastName");

                Athlete athlete = new Athlete(preferredFirstName, preferredLastName, gender, countryName);
                writeing(athlete.toString());
            }
        }
    }
    public void displayContest(File file) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        InputStream isr = getClass().getClassLoader().getResourceAsStream(file.getPath());
        BufferedReader br = new BufferedReader(new InputStreamReader(isr));
        String line;
        while ((line = br.readLine()) != null) {
            jsonContent.append(line);
        }
        br.close();
        String jsonstr = JSON.parse(jsonContent.toString()).toString();
        JSONObject jsonObject = JSON.parseObject(jsonstr);
        Map map = JSONObject.toJavaObject(jsonObject, Map.class);
        JSONArray heats = JSON.parseArray(map.get("Heats").toString());
        JSONObject heat = heats.getJSONObject(0);
        JSONArray results = JSON.parseArray(heat.get("Results").toString());
        for (int i2 = 0; i2 < results.size(); i2++) {
            JSONObject result = results.getJSONObject(i2);
            String fullName = result.getString("FullName");
            int rank = result.getInteger("Rank");
            double totalPoints = result.getDouble("TotalPoints");
            ArrayList<String> list = new ArrayList<>();
            JSONArray dives = JSON.parseArray(result.get("Dives").toString());
            for (int i1 = 0; i1 < dives.size(); i1++) {
                JSONObject dive = dives.getJSONObject(i1);
                String divePoints = dive.getString("DivePoints");
                list.add(divePoints);
            }
            Contest contest = new Contest(fullName, rank, list);
            writeingContest(contest.toString(), totalPoints);
        }
    }
    public void displayContestsDetailed(File file) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        InputStream isr = getClass().getClassLoader().getResourceAsStream(file.getPath());
        BufferedReader br = new BufferedReader(new InputStreamReader(isr));
        String line;
        while ((line = br.readLine()) != null) {
            jsonContent.append(line);
        }
        br.close();
        String jsonstr = JSON.parse(jsonContent.toString()).toString();
        JSONObject jsonObject = JSON.parseObject(jsonstr);
        Map map = JSONObject.toJavaObject(jsonObject, Map.class);
        //System.out.println(map);
        JSONArray heats = JSON.parseArray(map.get("Heats").toString());
        JSONObject heatFianl = heats.getJSONObject(0);
        JSONObject heatPreliminary = heats.getJSONObject(heats.size() - 1);
        //初赛的heat
        JSONArray resultsFianl = JSON.parseArray(heatFianl.get("Results").toString());
        JSONArray resultsPreliminary = JSON.parseArray(heatPreliminary.get("Results").toString());
        /*System.out.println(heats.size());
        System.out.println(results_1.size());//final
        System.out.println(results_2.size());//Preliminary*/
        if (heats.size() == 2) {
            for (int i1 = 0; i1 < resultsPreliminary.size(); i1++) {
                //总共共有人数按初赛决定
                Contest[] contests = new Contest[3];
                double[] point = new double[3];
                //决赛人数有两次数据
                JSONObject result = JSON.parseArray(heats.getJSONObject(1).get("Results").toString()).getJSONObject(i1);
                String fullName = result.getString("FullName");
                int rank = result.getInteger("Rank");
                double totalPoints = result.getDouble("TotalPoints");
                point[0] = totalPoints;
                ArrayList<String> list = new ArrayList<>();
                JSONArray dives = JSON.parseArray(result.get("Dives").toString());
                for (int i2 = 0; i2 < dives.size(); i2++) {
                    JSONObject dive = dives.getJSONObject(i2);
                    String divePoints = dive.getString("DivePoints");
                    list.add(divePoints);
                }
                contests[0] = new Contest(fullName, rank, list);
                //第一次数据,即参加决赛的人
                for (int i = 0; i < resultsFianl.size(); i++) {
                    JSONObject obj = resultsFianl.getJSONObject(i);
                    String getFullName = obj.getString("FullName");
                    if (getFullName.equals(fullName)) {
                        String fullName2 = obj.getString("FullName");
                        int rank2 = obj.getInteger("Rank");
                        double totalPoints2 = obj.getDouble("TotalPoints");
                        point[2] = totalPoints2;
                        ArrayList<String> list2 = new ArrayList<>();
                        JSONArray dives2 = JSON.parseArray(obj.get("Dives").toString());
                        for (int i2 = 0; i2 < dives.size(); i2++) {
                            JSONObject dive = dives2.getJSONObject(i2);
                            String divePoints = dive.getString("DivePoints");
                            list2.add(divePoints);
                        }
                        contests[2] = new Contest(fullName2, rank2, list2);
                    }
                }
                writeingContestDetailed(contests,point);
            }
        } else {
            //fianl,semifinal,preliminary
            JSONObject heatSemifianl = heats.getJSONObject(1);
            JSONArray resultsSemifinal = JSON.parseArray(heatSemifianl.get("Results").toString());
            for (int i = 0; i < resultsPreliminary.size(); i++) {
                //数据总共有初赛人数那么多
                Contest[] contests = new Contest[3];
                double[] point = new double[3];
                //决赛人选有三组数据
                JSONObject result = JSON.parseArray(resultsPreliminary.toString()).getJSONObject(i);
                String fullName = result.getString("FullName");
                int rank = result.getInteger("Rank");
                double totalPoints = result.getDouble("TotalPoints");
                point[0] = totalPoints;
                ArrayList<String> list = new ArrayList<>();
                JSONArray dives = JSON.parseArray(result.get("Dives").toString());
                for (int i2 = 0; i2 < dives.size(); i2++) {
                    JSONObject dive = dives.getJSONObject(i2);
                    String divePoints = dive.getString("DivePoints");
                    list.add(divePoints);
                }
                contests[0] = new Contest(fullName, rank, list);
                for (int i1 = 0; i1 < resultsSemifinal.size(); i1++) {
                    JSONObject obj = resultsSemifinal.getJSONObject(i1);
                    String getFullName = obj.getString("FullName");
                    if (getFullName.equals(fullName)) {
                        String fullName2 = obj.getString("FullName");
                        int rank2 = obj.getInteger("Rank");
                        double totalPoints2 = obj.getDouble("TotalPoints");
                        point[1] = totalPoints2;
                        ArrayList<String> list2 = new ArrayList<>();
                        JSONArray dives2 = JSON.parseArray(obj.get("Dives").toString());
                        for (int i2 = 0; i2 < dives2.size(); i2++) {
                            JSONObject dive = dives2.getJSONObject(i2);
                            String divePoints = dive.getString("DivePoints");
                            list2.add(divePoints);
                        }
                        contests[1] = new Contest(fullName2, rank2, list2);
                    }
                }
                for (int i1 = 0; i1 < resultsFianl.size(); i1++) {
                    JSONObject obj = resultsFianl.getJSONObject(i1);
                    String getFullName = obj.getString("FullName");
                    if (getFullName.equals(fullName)) {
                        String fullName2 = obj.getString("FullName");
                        int rank2 = obj.getInteger("Rank");
                        double totalPoints2 = obj.getDouble("TotalPoints");
                        point[2] = totalPoints2;
                        ArrayList<String> list2 = new ArrayList<>();
                        JSONArray dives2 = JSON.parseArray(obj.get("Dives").toString());
                        for (int i2 = 0; i2 < dives2.size(); i2++) {
                            JSONObject dive = dives2.getJSONObject(i2);
                            String divePoints = dive.getString("DivePoints");
                            list2.add(divePoints);
                        }
                        contests[2] = new Contest(fullName2, rank2, list2);
                    }
                }
                writeingContestDetailed(contests,point);
            }
        }
    }public void displayContestSynchronised(File file) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        InputStream isr = getClass().getClassLoader().getResourceAsStream(file.getPath());
        BufferedReader br = new BufferedReader(new InputStreamReader(isr));
        String line;
        while ((line = br.readLine()) != null) {
            jsonContent.append(line);
        }
        br.close();
        String jsonstr = JSON.parse(jsonContent.toString()).toString();
        JSONObject jsonObject = JSON.parseObject(jsonstr);
        Map map = JSONObject.toJavaObject(jsonObject, Map.class);
        JSONArray heats = JSON.parseArray(map.get("Heats").toString());
        JSONObject heat = heats.getJSONObject(0);
        JSONArray results = JSON.parseArray(heat.get("Results").toString());
        for (int i = 0; i < results.size(); i++) {
            Contest[] contests = new Contest[3];
            JSONObject result = results.getJSONObject(i);
            String fullName = result.getString("FullName");
            int rank = result.getInteger("Rank");
            double totalPoints = result.getDouble("TotalPoints");
            ArrayList<String> list = new ArrayList<>();
            double[] point = new double[3];
            point[0] = totalPoints;
            JSONArray dives = JSON.parseArray(result.get("Dives").toString());
            for (int i2 = 0; i2 < dives.size(); i2++) {
                JSONObject dive = dives.getJSONObject(i2);
                String divePoints = dive.getString("DivePoints");
                list.add(divePoints);
            }
            contests[0] = new Contest(fullName, rank, list);
            writeingContestSynchronised(contests,point);
        }
    }
}
