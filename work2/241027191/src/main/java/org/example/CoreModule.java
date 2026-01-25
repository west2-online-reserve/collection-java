package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.DetailResult;
import pojo.Players;
import pojo.Result;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CoreModule {
    Set<String> pathSet = Set.of("women 1m springboard",
            "women 3m springboard",
            "women 10m platform",
            "women 3m synchronised",
            "women 10m synchronised",
            "men 1m springboard",
            "men 3m springboard",
            "men 10m platform",
            "men 3m synchronised",
            "men 10m synchronised");
    public void handle(String input,String output){
        try(BufferedReader reader = new BufferedReader(new FileReader(input))){
            String line;
            while((line = reader.readLine()) != null){
                if(!line.isEmpty()){
                    if(line.equals("players")){
                        displayAllPlayerInfo(output);
                    }else if(line.matches("^result (.+?)(?: detail(s)?)?$")){
                        String substring = line.substring(line.length() - 7);
                        if(substring.equals("details")){
                            errorOrNA(output,"N/A");
                        }
                        else{
                            int index = line.indexOf("detail");
                            if(index != -1){
                                String path = line.substring(7, index).trim();
                                if(pathSet.contains(path)){
                                    displayDetailResult(output,path);
                                }else{
                                    errorOrNA(output,"N/A");
                                }
                            }else{
                                String path = line.substring(7).trim();
                                if(pathSet.contains(path)){
                                    diaplayResult(output,path);
                                }else{
                                    errorOrNA(output,"N/A");
                                }

                            }
                        }
                    }else{
                        errorOrNA(output,"Error");
                    }
                }
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }





    public void errorOrNA(String outPath,String error) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outPath,true))){
            writer.write(error);
            writer.newLine();
            writer.write("-----");
            writer.newLine();
            writer.flush();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private void displayAllPlayerInfo(String outPath){
        try(BufferedWriter out = new BufferedWriter(new FileWriter(outPath,true))) {
            List<Players> playersList = new ArrayList<>();
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/data/athletes.json")));
            JSONArray jsonArray = JSON.parseArray(content);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String country = jsonObject.getString("CountryName");
                JSONArray Participations = jsonObject.getJSONArray("Participations");
                for (int j = 0; j < Participations.size(); j++) {
                    JSONObject Participation = Participations.getJSONObject(j);
                    String PreferredLastName =  Participation.getString("PreferredLastName");
                    String PreferredFirstName =  Participation.getString("PreferredFirstName");
                    int Gender = Participation.getIntValue("Gender");
                    String gender = Gender == 0?"Female":"Male";
                    playersList.add(new Players(PreferredFirstName,PreferredLastName,gender,country));
                }
            }
            playersList.sort(Comparator.comparing(Players::getCountry).thenComparing(Players::getFirstName));
//            playersList.forEach(System.out::println);
            for (Players players : playersList) {
                out.write("Full Name:" + players.getLastName() + " " + players.getFirstName());
                out.newLine();
                out.write("Gender:" + players.getGender());
                out.newLine();
                out.write("Country:" + players.getCountry());
                out.newLine();
                out.write("-----");
                out.newLine();
                out.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void diaplayResult(String outPath,String path){
        try(BufferedWriter out = new BufferedWriter(new FileWriter(outPath,true))) {
            List<Result> resultList = new ArrayList<>();
            String  content = new String(Files.readAllBytes(Paths.get("src/main/resources/data/"+path+".json")));
            JSONObject jsonObject = JSON.parseObject(content);
            JSONArray heatsArray = jsonObject.getJSONArray("Heats");
            for (int i = 0; i < heatsArray.size(); i++) {
                JSONObject heat = heatsArray.getJSONObject(i);
                String name = heat.getString("Name");
                if(name.equals("Finals")){
                    JSONArray results = heat.getJSONArray("Results");
                    for (int j = 0; j < results.size(); j++) {
                        String fullName = results.getJSONObject(j).getString("FullName");
                        int rank = results.getJSONObject(j).getIntValue("Rank");
                        String totalScore = results.getJSONObject(j).getString("TotalPoints");
                        JSONArray dives = results.getJSONObject(j).getJSONArray("Dives");
                        String score = "";
                        for (int k = 0; k < dives.size(); k++) {
                            JSONObject dive = dives.getJSONObject(k);
                            score = score + "+" + dive.getString("DivePoints");
                        }
                        score = score + "=" + totalScore;
                        resultList.add(new Result(fullName,String.valueOf(rank),score));
                    }
                }
            }
            for (Result result : resultList) {
                out.write("Full Name:" + result.getFullName());
                out.newLine();
                out.write("Rank:" + result.getRank());
                out.newLine();
                out.write("Score:" + result.getScore());
                out.newLine();
                out.write("-----");
                out.newLine();
                out.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void displayDetailResult(String outPath,String path){
        try(BufferedWriter out = new BufferedWriter(new FileWriter(outPath,true))) {
            HashMap<String, DetailResult> detailResultHashMap = new HashMap<>();
            String  content = new String(Files.readAllBytes(Paths.get("src/main/resources/data/"+ path + ".json")));
            JSONObject jsonObject = JSON.parseObject(content);
            JSONArray heatsArray = jsonObject.getJSONArray("Heats");
            for (int i = 0; i < heatsArray.size(); i++) {// 一个类型的比赛  初赛  半决赛  决赛
                JSONObject heat = heatsArray.getJSONObject(i);
                JSONArray results = heat.getJSONArray("Results");
                for (int j = 0; j < results.size(); j++) {
                    JSONObject result = results.getJSONObject(j);
                    String fullName = result.getString("FullName");
                    if(fullName.contains("/")){
                        String[] playersName = fullName.split("/");
                        Arrays.sort(playersName);
                        fullName = String.join("&", playersName);
                    }
                    if(!detailResultHashMap.containsKey(fullName)){
                        DetailResult detailResult = new DetailResult();
                        detailResult.setFullName(fullName);
                        detailResultHashMap.put(fullName, detailResult);
                    }
                    DetailResult detailResult = detailResultHashMap.get(fullName);
                    int rank = result.getIntValue("Rank");
                    String totalScore = result.getString("TotalPoints");
                    JSONArray dives = results.getJSONObject(j).getJSONArray("Dives");
                    String score = "";
                    for (int k = 0; k < dives.size(); k++) {
                        JSONObject dive = dives.getJSONObject(k);
                        score = score + "+" + dive.getString("DivePoints");
                    }
                    score = score + "=" + totalScore;
                    if("Finals".equals(heat.getString("Name"))){
                        // 这是决赛
                        detailResult.setFinalRank(String.valueOf(rank));
                        detailResult.setFinalScore(score);
                    }
                    else if("Semifinals".equals(heat.getString("Name"))){
                        // 半决赛
                        detailResult.setSemifinalRank(String.valueOf(rank));
                        detailResult.setSemifinalScore(score);
                    }
                    else if("Preliminaries".equals(heat.getString("Name"))){
                        // 初赛
                        detailResult.setPreliminaryRank(String.valueOf(rank));
                        detailResult.setPreliminaryScore(score);
                    }
                }
            }
            List<DetailResult> detailResultList = new ArrayList<>(detailResultHashMap.values());
            detailResultList.sort(Comparator.comparing(DetailResult::getPreliminaryRank));
            for (DetailResult detailResult : detailResultList) {
                out.write("Full Name:" + detailResult.getFullName());
                out.newLine();
                out.write("Rank:" + detailResult.getPreliminaryRank() + "|" + detailResult.getSemifinalRank() + "|" + detailResult.getFinalRank());
                out.newLine();
                out.write("Preliminary Score:" + detailResult.getPreliminaryScore());
                out.newLine();
                out.write("Semifinal Score:" + detailResult.getSemifinalScore());
                out.newLine();
                out.write("Final Score:" + detailResult.getFinalScore());
                out.newLine();
                out.write("-----");
                out.newLine();
                out.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
