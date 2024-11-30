package com.dwa.Lib;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadFileUtils {

    public static List<Player> toPlayerList(String fileName){
        try {
            JSONArray jsonArray = toJsonArray(fileName);
            List<Player> resultList = new ArrayList<>();
            for (int j = 0;j<jsonArray.size();j++){
                JSONObject countryJson = jsonArray.getJSONObject(j);
                String countryName = countryJson.getString("CountryName");
                JSONArray participations = countryJson.getJSONArray("Participations");
                for (int z = 0;z<participations.size();z++){
                    JSONObject participation = participations.getJSONObject(z);
                    String genderStr = null;
                    Integer gender = participation.getInteger("Gender");
                    if (gender == 0){
                        genderStr = "Male";
                    } else {
                        genderStr = "Female";
                    }
                    Player player = new Player();
                    player.setFirstName(participation.getString("PreferredFirstName"));
                    player.setLastName(participation.getString("PreferredLastName"));
                    player.setFullName(player.getLastName()+" "+player.getFirstName());
                    player.setCountryName(countryName);
                    player.setGender(genderStr);
                    resultList.add(player);
                }
            }
            // 进行排序
            resultList.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    int i = o1.getCountryName().compareTo(o2.getCountryName());
                    if (i == 0){
                        return o1.getLastName().compareTo(o2.getLastName());
                    }
                    return i;
                }
            });
            return resultList;
        } catch (IOException e){
            System.out.println("读取文件出错");
            e.printStackTrace();
            return null;
        }
    }
    //利用HashMap和循环重复读取比赛数据
    public static Map<String, Detail> getUserDetailMap(String fileName) {
        try {
            JSONObject jsonObject = ReadFileUtils.toJsonObject(fileName);
            JSONArray heats = jsonObject.getJSONArray("Heats");
            Map<String, Detail> userMap = new HashMap<>();
            for (int i = 0; i< heats.size(); i++){
                JSONObject heat = heats.getJSONObject(i);
                JSONArray results = heat.getJSONArray("Results");
                for (int j = 0;j< results.size();j++){
                    JSONObject result = results.getJSONObject(j);
                    Detail detail = userMap.get(result.getString("FullName"));
                    if (detail == null){
                        detail = new Detail();
                        detail.setFullName(result.getString("FullName"));
                        userMap.put(result.getString("FullName"), detail);
                    }

                    String[] ranks = detail.getRank().split("\\|");
                    JSONArray dives = result.getJSONArray("Dives");
                    List<String> divePointsList = new ArrayList<>();
                    for (int z = 0;z < dives.size();z++){
                        JSONObject dive = dives.getJSONObject(z);
                        String divePoints = dive.getString("DivePoints");
                        divePointsList.add(divePoints);
                    }
                    String points = StringUtils.join(divePointsList," + ");
                    if ("Preliminary".equals(heat.getString("Name"))){
                        // 初赛
                        detail.setPreliminaryScore(points + " = " + result.getString("TotalPoints"));
                        ranks[0] = result.getInteger("Rank").toString();
                    } else if ("Semifinal".equals(heat.getString("Name"))){
                        // 半决赛
                        detail.setSemifinalScore(points + " = " + result.getString("TotalPoints"));
                        ranks[1] = result.getInteger("Rank").toString();
                    } else if ("Final".equals(heat.getString("Name"))){
                        // 总决赛
                        detail.setFinalScore(points + " = " + result.getString("TotalPoints"));
                        ranks[2] = result.getInteger( "Rank").toString();
                    }
                    String rank = StringUtils.join(ranks, "|");// *|*|1
                    detail.setRank(rank);
                }
            }
            return userMap;
        } catch (IOException e){
            System.out.println("读取文件出错");
            e.printStackTrace();
            return null;
        }


    }


    public static JSONArray toJsonArray(String fileName) throws IOException {
        String dirUrl = System.getProperty("user.dir") + "\\src\\main\\java\\com\\dwa\\data\\";
        File inputFile = new File(dirUrl + fileName);

        String readValue = readFile(inputFile);
        return JSONArray.parseArray(readValue);
    }

    public static JSONObject toJsonObject(String fileName) throws IOException{
        String dirUrl = System.getProperty("user.dir") + "\\src\\main\\java\\com\\dwa\\data\\";
        File inputFile = new File(dirUrl + fileName);

        String readValue = readFile(inputFile);
        return JSONObject.parseObject(readValue);
    }

    public static String readFile(File inputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String str = null;
        StringBuilder builder = new StringBuilder();
        while ((str = reader.readLine()) != null){
            builder.append(str).append("\n");
        }
        return builder.toString();
    }
}
