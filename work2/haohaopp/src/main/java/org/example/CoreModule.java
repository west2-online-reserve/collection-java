package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoreModule {
    private static String getJsonString(String path){
        String jsonString= "";

        try {
            // 读取整个 JSON 文件的内容为字节数组
            byte[] bytes = Files.readAllBytes(Paths.get(path));

            // 将字节数组转换为字符串
            jsonString = new String(bytes);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
    private static List<Conpetiton> getEachConpetiton(JSONArray results){
        //从jsonArray中获取一场比赛里所有选手的数据
        List<Conpetiton> conpetitions=new ArrayList<>();
        //数据提取
        for(int i=0;i<results.size();i++){
            JSONObject athlete=results.getJSONObject(i);
            JSONArray dives=athlete.getJSONArray("Dives");
            ArrayList<Float> divepoints=new ArrayList<Float>();
            for(int j=0;j<dives.size();j++){
                JSONObject dive=dives.getJSONObject(j);
                divepoints.add(dive.getFloatValue("DivePoints"));
            }
            //赋值
            Conpetiton conpetiton=new Conpetiton(athlete.getString("FullName"), athlete.getIntValue("Rank"), divepoints);
            //输出
            conpetitions.add(conpetiton);
        }
        return conpetitions;
    }
    private static Conpetiton findConpetiton(String name,List<Conpetiton> conpetitons){
        if(conpetitons==null)return null;
        else {
            boolean find=false;
            Conpetiton c=null;
            for(Conpetiton i:conpetitons){
                if (i.getFullName().equals(name)) {
                    c=i;
                    find=true;
                    break;
                }
            }
            if(find) return c;
            else return null;
        }

    }
    public static Athlete[] getAthlete(String s){

        // 解析 JSON 字符串为 JSONArray
        JSONArray firstArray=JSON.parseArray(getJsonString(s));
        // 获取嵌套的数组
        List<Athlete> athletes=new ArrayList<>();
        for(int i=0;i<firstArray.size();i++){
            //解析出participation数组
            JSONArray participations = firstArray.getJSONObject(i).getJSONArray("Participations");
            //获取信息
            for(int j=0;j<participations.size();j++){
                JSONObject participation =participations.getJSONObject(j);
                Athlete athlete=participation.toJavaObject(Athlete.class);
              athletes.add(athlete);
            }
        }
        Athlete[] a=athletes.toArray(athletes.toArray(new Athlete[0]));
        return a;
    }
    public static  Conpetiton[] getConpetition(String s){
    // 解析 JSON 字符串为 JSONArray
    JSONObject contest=JSON.parseObject(getJsonString(s));
    JSONArray heats=contest.getJSONArray("Heats");
    JSONArray results=heats.getJSONObject(0).getJSONArray("Results");
    //输出
        List<Conpetiton> conpetitions=getEachConpetiton(results);
        Conpetiton[] c=conpetitions.toArray(conpetitions.toArray(new Conpetiton[0]));
        return c;
    }
    public static  ConpetitonDetial[] getConpetitionDetial(String s){
    JSONObject contest=JSON.parseObject(getJsonString(s));
    JSONArray heats=contest.getJSONArray("Heats");
    //获取三场比赛的数据
    List<Conpetiton> preConpetiton=null;
    List<Conpetiton> semiConpetiton=null;
    List<Conpetiton> finalConpetiton=null;
    for(int i=0;i<heats.size();i++){
        JSONObject eachContest=heats.getJSONObject(i); //提取出每场比赛
        if (eachContest.getString("Name").equals("Preliminary")){
           preConpetiton=new ArrayList<>();
           preConpetiton=getEachConpetiton(eachContest.getJSONArray("Results"));
        } else if (eachContest.getString("Name").equals("Semifinal")) {
            semiConpetiton=new ArrayList<>();
            semiConpetiton=getEachConpetiton(eachContest.getJSONArray("Results"));
        } else if (eachContest.getString("Name").equals("Final")) {
            finalConpetiton=new ArrayList<>();
            finalConpetiton=getEachConpetiton(eachContest.getJSONArray("Results"));
        }
    }
    if(preConpetiton!=null){//如果有预赛
        ConpetitonDetial[] conpetitonDetials=new ConpetitonDetial[preConpetiton.size()];
        for(int i=0;i<preConpetiton.size();i++){
            String name=preConpetiton.get(i).getFullName();
            //查找后两场比赛
            Conpetiton semi=findConpetiton(name,semiConpetiton);
            Conpetiton finl=findConpetiton(name,finalConpetiton);
           conpetitonDetials[i]=new ConpetitonDetial(name, new Conpetiton[]{preConpetiton.get(i), semi, finl});
        }
        return conpetitonDetials;
    }
    else{
        if(semiConpetiton!=null){//没有预赛，看看有没有半决赛
            ConpetitonDetial[] conpetitonDetials=new ConpetitonDetial[semiConpetiton.size()];
            for(int i=0;i<semiConpetiton.size();i++){
                String name=semiConpetiton.get(i).getFullName();
                //查找决赛
                Conpetiton finl=findConpetiton(name,finalConpetiton);
                conpetitonDetials[i]=new ConpetitonDetial(name, new Conpetiton[]{null, semiConpetiton.get(i), finl});
            }
            return conpetitonDetials;
        }
        else {//也没有半决赛，那就只有决赛
            ConpetitonDetial[] conpetitonDetials=new ConpetitonDetial[finalConpetiton.size()];
            for(int i=0;i<finalConpetiton.size();i++){
                conpetitonDetials[i]=new ConpetitonDetial(finalConpetiton.get(i).getFullName(),new Conpetiton[]{null,null,finalConpetiton.get(i)});
            }
            return conpetitonDetials;
        }
    }
    }
}
