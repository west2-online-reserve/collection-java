package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;

public class CoreModule {
    HashMap<String,String> playerhashMap=new HashMap<>();
    HashMap<String,String> resulthashMap=new HashMap<>();
    //
    //处理
    private String readJsonFromData(String fileName)throws IOException{
        FileInputStream fis=new FileInputStream("src/main/java/org/example/data"+fileName);
        if(fis == null){
            throw new IOException("not found file");
        }
        InputStreamReader inputStreamReader=new InputStreamReader(fis);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder=new StringBuilder();
        int len;
        while((len=bufferedReader.read())!=-1){
            stringBuilder.append((char)len);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
    private String getPlayerInfo(String fileName){
        if (playerhashMap.containsKey(fileName)){
            return playerhashMap.get(fileName);
        }
        String jsonStr;
        try{
            jsonStr=readJsonFromData(fileName);
        }catch (IOException e){
            if(e.toString().equals("java.io.IOException: not found file")){
                return "N/A\n-----\n";
            }
            else{
                return "Error\n-----\n";
            }
        }
        JSONArray jsonArray=JSON.parseArray(jsonStr);
        StringBuilder playerInfo=new StringBuilder();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject country=jsonArray.getJSONObject(i);
            JSONArray Participations=country.getJSONArray("Participations");
            for(int j=0;j<Participations.size();j++){
                JSONObject jo=Participations.getJSONObject(j);
                playerInfo.append("Full Name:").append(jo.get("PreferredFirstName")).append(" ").append(jo.get("PreferredLastName")).append("\n").append("Gender:").append(jo.get("Gender") == "0" ? "Male" : "Female").append("\n").append("Country:").append(country.get("CountryName")).append("\n-----\n");
            }
        }
        playerhashMap.put(fileName,playerInfo.toString());
        return playerInfo.toString();
    }
    public void displayAllPlayersInfo(String outPut,String JsonFileName){
        try(BufferedWriter br=new BufferedWriter(new FileWriter(outPut,true))){
            br.write(getPlayerInfo(JsonFileName));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //获得选手每个动作的分数
    private String[] getDivePoint(JSONObject Json){
        JSONArray Dives=Json.getJSONArray("Dives");
        String[] divePoint=new String[10];
        for(int i=0;i<Dives.size();i++){
            JSONObject jo=Dives.getJSONObject(i);
            divePoint[i]=jo.getString("DivePoints");
        }
        return divePoint;
    }
    //将每个动作的分数添加到resultInfo中
    private StringBuilder addDivePoint(JSONObject jo,StringBuilder resultInfo,String[] divePoint,int flag){
        for(int i=0;i<5;i++){
            if(i<4){
                resultInfo.append(divePoint[i] + " + ");
            }
            else{
                resultInfo.append(divePoint[i] + " = " + jo.get("TotalPoints") + "\n");
            }
        }
        if(flag==1){
            resultInfo.append("-----\n");
        }
        return resultInfo;
    }
    private String getResultInfo(String fileName,int flag){
        if(resulthashMap.containsKey(fileName)){
            return resulthashMap.get(fileName);
        }
        String jsonStr;
        try{
            jsonStr=readJsonFromData(fileName);
        }catch (IOException e){
            if(e.toString().equals("java.io.IOException: not found file")){
                return "N/A\n-----\n";
            }
            else{
                return "Error\n-----\n";
            }
        }
        JSONObject jsonObject=JSON.parseObject(jsonStr);
        JSONArray Heats=jsonObject.getJSONArray("Heats");
        StringBuilder resultInfo=new StringBuilder();
        //处理只有result没有detail 和synchronised
        if(flag==1){
            for(int i=0;i<1;i++){
                String fullname;
                JSONObject gameName=Heats.getJSONObject(i);
                JSONArray Results=gameName.getJSONArray("Results");
                String[] divePoint = new String[10];
                for(int j=0;j<Results.size();j++){
                    JSONObject playerResults=Results.getJSONObject(j);
                    fullname=playerResults.getString("FullName");
                    divePoint=getDivePoint(playerResults);
                    resultInfo.append("Full Name:" + fullname + "\n" + "Rank:" + playerResults.get("Rank") + "\n" +"Score:");
                    resultInfo=addDivePoint(playerResults,resultInfo,divePoint,1);
                }
            }
        }
        //处理men 3m springboard  women 3m springboard men 10m platform women 10m platform
        else if(flag==2){
            JSONObject gameName=Heats.getJSONObject(Heats.size()-1);
            JSONArray Results=gameName.getJSONArray("Results");
            int point=0;
            if(Heats.size()==2){
                JSONObject jo0=Heats.getJSONObject(0);
                JSONArray Rs=jo0.getJSONArray("Results");
                for(int i=0;i<Results.size();i++){
                    JSONObject player=Results.getJSONObject(i);
                    String name=player.getString("FullName");
                    int j,temp=0;
                    for(j=0;j<Rs.size();j++){
                        JSONObject player0=Rs.getJSONObject(j);
                        if(player0.getString("FullName").equals(name)){
                            temp=1;
                            break;
                        }
                    }
                    String[] divepoint=new String[10];
                    divepoint=getDivePoint(player);
                    //没有参加决赛
                    if(temp==0){
                        resultInfo.append("Full Name:" + name +"\nRank:" + player.get("Rank") + " | *\nPreliminary Score:");
                        resultInfo=addDivePoint(player,resultInfo,divepoint,0);
                        resultInfo.append("Final Score: *\n-----\n");
                    }
                    else if(temp == 1){
                        JSONObject player0=Rs.getJSONObject(j);
                        String[] divepoint0=new String[10];
                        divepoint0=getDivePoint(player0);
                        resultInfo.append("Full Name:" + name +"\nRank:" + player.get("Rank") + " | " + player0.get("Rank") + "\nPreliminary Score:");
                        resultInfo=addDivePoint(player,resultInfo,divepoint,0);
                        resultInfo.append("Final Score:");
                        resultInfo=addDivePoint(player0,resultInfo,divepoint0,1);
                    }
//                    JSONObject player0=Rs.getJSONObject(j);
//                    String[] divepoint=new String[10];
//                    divepoint=getDivePoint(player);
//                    String[] divepoint0=new String[10];
//                    divepoint0=getDivePoint(player0);
//                    resultInfo.append("Full Name:" + name +"\nRank:" + player.get("Rank") + " | " + player0.get("Rank") + "\nPreliminary Score:");
//                    resultInfo=addDivePoint(player,resultInfo,divepoint,0);
//                    resultInfo.append("Semifinal: *\n" +"Final Score:");
//                    resultInfo=addDivePoint(player0,resultInfo,divepoint0,1);
                }
            }
        }
        //处理剩下的
        else if(flag==3){
            JSONObject gameName=Heats.getJSONObject(Heats.size()-1);
            JSONArray Results=gameName.getJSONArray("Results");
            JSONObject jo0=Heats.getJSONObject(0);
            JSONArray Rs0=jo0.getJSONArray("Results");
            JSONObject jo1=Heats.getJSONObject(1);
            JSONArray Rs1=jo1.getJSONArray("Results");
            for(int i=0;i<Results.size();i++){
                JSONObject player=Results.getJSONObject(i);
                String name=player.getString("FullName");
                int j,k,temp0=0,temp1=0;
                //获取名字相同所对应的位置，利用temp来看是否在对应比赛中存在
                for(j=0;j<Rs1.size();j++){
                    JSONObject player1=Rs1.getJSONObject(j);
                    if(player1.getString("FullName").equals(name)){
                        temp1=1;
                        break;
                    }
                }
                for(k=0;k<Rs0.size();k++){
                    JSONObject player0=Rs0.getJSONObject(k);
                    if(player0.getString("FullName").equals(name)){
                        temp0=1;
                        break;
                    }
                }
                String[] divepoint=new String[10];
                divepoint=getDivePoint(player);
                //没有半决赛
                if(temp1==0){
                    resultInfo.append("Full Name" + name +"\nRank:" +player.get("Rank") + " | * | *\nPreliminary Score:");
                    resultInfo=addDivePoint(player,resultInfo,divepoint,0);
                    resultInfo.append("Semifinal:*\nFinal Score:*\n-----\n");
                }
                //有半决 但是没决赛
                else if(temp1==1 &&temp0==0){
                    JSONObject player1=Rs1.getJSONObject(j);
                    resultInfo.append("Full Name" + name +"\nRank:" +player.get("Rank") + " | " +player1.get("Rank")+ " | *\nPreliminary Score:");
                    resultInfo=addDivePoint(player,resultInfo,divepoint,0);
                    String[] divepoint1=new String[10];
                    divepoint1=getDivePoint(player1);
                    resultInfo.append("Semifinal:");
                    resultInfo=addDivePoint(player1,resultInfo,divepoint1,0);
                    resultInfo.append("Final Score:*\n-----\n");
                }
                //都有
                else{
                    JSONObject player0=Rs0.getJSONObject(k);
                    JSONObject player1=Rs1.getJSONObject(j);
                    String[] divepoint0=new String[10];
                    String[] divepoint1=new String[10];
                    divepoint0=getDivePoint(player0);
                    divepoint1=getDivePoint(player1);
                    resultInfo.append("Full Name:" + name +"\nRank: " +player.get("Rank") + " | " + player1.get("Rank") + " | "+ player0.get("Rank") +"\nPreliminary Score:");
                    resultInfo=addDivePoint(player,resultInfo,divepoint,0);
                    resultInfo.append("Semifinal:");
                    resultInfo=addDivePoint(player1,resultInfo,divepoint1,0);
                    resultInfo.append("Final Score:");
                    resultInfo=addDivePoint(player0,resultInfo,divepoint0,1);
                }
            }
        }
        resulthashMap.put(fileName,resultInfo.toString());
        return resultInfo.toString();
    }
    public void displayResultsForEachEvent(String outPut,String JsonFileName,int flag){
        try(BufferedWriter br=new BufferedWriter(new FileWriter(outPut,true))){
            br.write(getResultInfo(JsonFileName,flag));
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
