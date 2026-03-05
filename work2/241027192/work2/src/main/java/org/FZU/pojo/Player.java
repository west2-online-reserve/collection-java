package org.FZU.pojo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

public class Player {
    private String FullName;
    private String gender;
    private String country;

    public Player(String playerName, String gender, String country) {
        this.FullName = playerName;
        this.gender = gender;
        this.country = country;
    }
    public static String displayAllPlayersInfo (HttpResponse response) {
//      存储运动员信息
        ArrayList<Player> players = new ArrayList<Player>();
        if (response==null){
            return "";
        }
//      解析获取数据的参数
        JSONArray dataInfo = JSONArray.parseArray(response.getContent());
        String FullName;
        String Gender;
        String Country;
//      解析数据
        for (int i = 0; i < dataInfo.size(); i++) {
            JSONObject data = dataInfo.getJSONObject(i);
            Country = data.getString("CountryName");
            JSONArray participations = data.getJSONArray("Participations");
//            遍历选手信息
            for (int j = 0; j < participations.size(); j++) {
                JSONObject player = participations.getJSONObject(j);
                FullName = player.getString("PreferredFirstName")+" "+player.getString("PreferredLastName");
                Gender = player.getInteger("Gender")==0? "男":"女";
                players.add(new Player(FullName, Gender, Country));
            }
        }
        //       按题目要求排序
        players.sort(Comparator.comparing(Player::getCountry).thenComparing((p)->p.getFullName().split(" ")[1]));
        StringBuilder output = new StringBuilder();
        for (Player player : players) {
            output.append("Full Name:")
                    .append(player.getFullName())
                    .append("\n").append("Gender:")
                    .append(player.getGender())
                    .append("\n").append("Country:")
                    .append(player.getCountry())
                    .append("\n")
                    .append("-----")
                    .append("\n");
        }
        return output.toString();
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
