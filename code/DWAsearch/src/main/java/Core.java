import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class Core implements CoreInterface,ParseInterface {
    //创建一个player类来使用
    private static class Player{
        public String fullName;
        String[] ranks = new String[3];
        String preliminaryScore;
        String semifinalScore;
        String finalScore;
        int firstRank;
        String[] divesScores1 = new String[10];
        String[] divesScores2 = new String[10];
        String[] divesScores3 = new String[10];

        public static String toString(String[] scores) {
            String str=new String();
            for (int i=0;i<scores.length;i++){
                if (scores[i]!=null) {
                    str.concat((scores[i]+"+"));
                } else {
                    break;
                }
            }
            return str;
        }
    }

    //显示所有选手的信息
    public void showPlayerInfo(String fileName){
        File file = new File(fileName);
        parserPlayerInfo(fileName);
    }
    //显示所有项目的结果(简略)
    public void showProjectResult(String fileName){
        File file = new File(fileName);
        parserResult(fileName);
    }
    //显示所有项目的结果(详细)
    public void showProjectResultDetail(String fileName){
        File file = new File(fileName);
        parserResultDetail(fileName);
    }

    //解析运动员信息json文件
    public void parserPlayerInfo(String fileName) {
        File file = new File(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Athlete> athletes = new ArrayList<>();
        try( BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\java\\code\\ParserDate\\src\\main\\java\\Output.txt",true));){
            JsonNode rootNode = objectMapper.readTree(file);
            for (JsonNode node : rootNode) {
                String country = node.get("CountryName").asText();
                JsonNode participationNode = node.get("Participations");
                if (participationNode!=null && participationNode.isArray()) {
                    for (JsonNode participation : participationNode) {
                        int gender = participation.get("Gender").asInt();
                        String preferredLastName = participation.get("PreferredLastName").asText();
                        String preferredFirstName = participation.get("PreferredFirstName").asText();
                        athletes.add(new Athlete((preferredLastName+" "+preferredFirstName),gender,country));
                    }
                }
            }
            Collections.sort(athletes,new Comparator<Athlete>(){
                @Override
                public int compare(Athlete a1, Athlete a2) {
                    int countryCompare = a1.getcountry().compareTo(a2.getcountry());
                    //先以国家首字母排序
                    if (countryCompare!=0)
                        return countryCompare;
                    return a1.getFullName().compareTo(a2.getFullName());
                }
            });
            for (Athlete athlete : athletes){
                StringBuilder sb = new StringBuilder();
                sb.append("FullName:" +  athlete.getFullName() + "\nGender:" + (athlete.getGender()==0 ? "MALE" : "FEMALE") + "\nCountry:" + athlete.getcountry() + "\n" + "-----\n");
                bw.write(sb.toString() + "\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //parser所有项目的决赛结果（简略）
    public void parserResult(String fileName) {
        File file = new File(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/Output.txt", true))) {
            JsonNode rootNode = objectMapper.readTree(file);
            JsonNode heatsNode = rootNode.get("Heats");
            if (heatsNode!=null && heatsNode.isArray()) {
                for (JsonNode heat : heatsNode) {
                    String heatName = heat.get("Name").asText();
                    if (heatName.equals("Final")){
                        JsonNode resultNode = heat.get("Results");
                        if  (resultNode!=null && resultNode.isArray()) {
                            for (JsonNode result : resultNode) {
                                String totalPoints = result.get("TotalPoints").asText();
                                String fullName = result.get("FullName").asText();
                                String rank = result.get("Rank").asText();
                                ArrayList<String> divePoints = new ArrayList<>();
                                JsonNode diveNode = result.get("Dives");
                                if (diveNode!=null && diveNode.isArray()) {
                                    for (JsonNode dive : diveNode) {
                                        divePoints.add(dive.get("DivePoints").asText());
                                    }
                                }
                                StringBuilder sb = new StringBuilder();
                                sb.append("FullName:"+fullName+"\nRank:"+rank+"\n");
                                for (int i=0;i<divePoints.size();i++) {
                                    sb.append(divePoints.get(i) + "+");
                                    if (i==divePoints.size()-1)
                                        sb.append(divePoints.get(i) + "=");
                                }
                                sb.append(totalPoints + "\n" + "-----\n");
                                bw.write(sb.toString());
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //parser所有项目的结果(详细）
    public void parserResultDetail(String fileName) {
        File file = new File(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Player> athletes = new ArrayList<>();
        Map<String,Player> playerMap = new HashMap<>();
        String fullName;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/Output.txt", true))) {
            JsonNode rootNode = objectMapper.readTree(file);
            JsonNode heatsNode = rootNode.get("Heats");
            if (heatsNode!=null && heatsNode.isArray()) {
                for (JsonNode heat : heatsNode) {
                    //表示比赛的名字
                    String heatName = heat.get("Name").asText();
                    JsonNode resultsNode = heat.get("Results");
                    if (resultsNode!=null && resultsNode.isArray()) {
                        for (JsonNode result : resultsNode) {
                            String name = result.get("FullName").asText();
                            //给双人项目组的名字进行排序
                            if (name.contains("/")){
                                String[] parts = name.split("/");
                                Arrays.sort(parts,Comparator.comparing(s->{
                                    String[] names = s.split(" ");
                                    return names.length>1 ? names[0] : s;
                                }));
                                fullName = String.join(" & ",parts);
                            } else {
                                fullName = name;
                            }
                            //检查是否已经存在该运动员（们）
                            Player player = playerMap.get(fullName);
                            if (player==null) {
                                player = new Player();
                                player.fullName = fullName;
                                player.ranks = new String[]{"*","*","*"};
                                player.preliminaryScore = "*";
                                player.semifinalScore = "*";
                                player.finalScore = "*";
                            }
                            //获取排名和分别对应的总分数
                            String rank = result.get("Rank").asText();
                            if (heatName.equals("Final")) {
                                player.ranks[2]=rank;
                                player.finalScore = result.get("TotalPoints").asText();
                                JsonNode divesNode = result.get("Dives");
                                if (divesNode!=null && divesNode.isArray()) {
                                    int i = 0;
                                    for (JsonNode dive : divesNode) {
                                        player.divesScores3[i] = "*";
                                        if (dive.get("DivePoints").asText()!=null) {
                                            player.divesScores3[i] = dive.get("DivePoints").asText();
                                        }
                                        i++;
                                    }
                                }
                            } else if (heatName.equals("Semifinal")) {
                                player.ranks[1]=rank;
                                player.semifinalScore = result.get("TotalPoints").asText();
                                JsonNode divesNode = result.get("Dives");
                                if (divesNode!=null && divesNode.isArray()) {
                                    int i = 0;
                                    for (JsonNode dive : divesNode) {
                                        player.divesScores2[i] = "*";
                                        if (dive.get("DivePoints").asText()!=null) {
                                            player.divesScores2[i] = dive.get("DivePoints").asText();
                                        }
                                        i++;
                                    }
                                }
                            }  else if (heatName.equals("Preliminary")) {
                                player.ranks[0]=rank;
                                player.preliminaryScore = result.get("TotalPoints").asText();
                                JsonNode divesNode = result.get("Dives");
                                if (divesNode!=null && divesNode.isArray()) {
                                    int i = 0;
                                    for (JsonNode dive : divesNode) {
                                        player.divesScores1[i] = "*";
                                        if (dive.get("DivePoints").asText()!=null) {
                                            player.divesScores1[i] = dive.get("DivePoints").asText();
                                        }
                                        i++;
                                    }
                                }
                            }
                            playerMap.put(fullName,player);
                        }
                    }
                }
                athletes.addAll(playerMap.values());
                //排序
                Collections.sort(athletes,new Comparator<Player>(){
                    @Override
                    public int compare(Player a1,Player a2){
                        if (a1.ranks[0].equals("*")) {
                            return 1;  // 带*的排在后面
                        }
                        if (a2.ranks[0].equals("*")) {
                            return -1;  // 不带*的排在前面
                        }
                        int p1 = Integer.parseInt(a1.ranks[0]);
                        int p2 = Integer.parseInt(a2.ranks[0]);
                        return Integer.compare(p1, p2);
                    }
                });
            }
            //输入结果
            for (Player player : athletes) {
                StringBuilder sb = new StringBuilder();
                sb.append("FullName:" + player.fullName + "\n");
                sb.append("Rank:" + player.ranks[0] + " | " + player.ranks[1] + " | " + player.ranks[2] + "\n");
                sb.append("Final Score:"+player.toString(player.divesScores3) + player.finalScore + "\n");
                sb.append("Semifinal Score:"+player.toString(player.divesScores2) + player.semifinalScore + "\n");
                sb.append("Preliminary Score:"+player.toString(player.divesScores1) + player.preliminaryScore + "\n");
                sb.append("-----\n");
                bw.write(sb.toString());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
