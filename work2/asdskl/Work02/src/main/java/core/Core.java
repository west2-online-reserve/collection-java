package core;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import java.util.Comparator;

import domain.Athlete;
import domain.Contest;
import domain.ContestDetailed;
//Core类,核心输出函数,包含解析json文件内容并转化成对象输出到output文件的函数
public class Core {

    //输出所有选手的信息
    public void outputPlayer(){
        try {
            //读取json文件里的内容
            StringBuilder jsonContent = new StringBuilder();
            InputStream in = getClass().getClassLoader().getResourceAsStream("athletes.json");

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null){
                  jsonContent.append(line);
            }

            br.close();

            //解析json文件的内容，反序列化为对象Array
            JSONArray countries = JSON.parseArray(jsonContent.toString());
            //遍历国家Array,提取单个国家对象解析
            for (int i = 0; i < countries.size(); i++){

                JSONObject country = countries.getJSONObject(i);
                //获取国家名
                String countryName = country.getString("CountryName");
                //提取对应国家的运动员Array
                JSONArray players = country.getJSONArray("Participations");
                //遍历运动员Array,提取单个运动员对象解析
                for (int j = 0; j < players.size(); j++){

                    JSONObject player = players.getJSONObject(j);
                    //获取该运动员的必要信息
                    int gender = player.getIntValue("Gender");
                    String preferredFirstName = player.getString("PreferredFirstName");
                    String preferredLastName = player.getString("PreferredLastName");
                    //据信息构建对象,输入进output文件
                    Athlete athlete = new Athlete(countryName,gender,preferredLastName,preferredFirstName);
                    FileIo.outputWrite(athlete.toString());
                }




            }


        } catch (IOException e){
            throw new RuntimeException(e);
        }



    }


    //获取单项目某种类型的比赛结果，储存在对应的contest的List里
    public List<Contest> outputContest(String contestName,String contestType){
        ArrayList<Contest> contests = new ArrayList<Contest>();
        try{
            //读取json文件
            StringBuilder jsonContent = new StringBuilder();
            String path = contestName + ".json";
            InputStream in = getClass().getClassLoader().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));


            String line;
            while ((line = br.readLine()) != null){
                jsonContent.append(line);
            }

            br.close();
            //先解析出整个jsonObject对象
            JSONObject jsonObject = JSON.parseObject(jsonContent.toString());
            //再提取出Heat的Array
            JSONArray heats = jsonObject.getJSONArray("Heats");

            //遍历Heat的Array
            for (int i = 0; i < heats.size(); i++){
                JSONObject heat = heats.getJSONObject(i);
                //获取当前Heat对应的比赛类型
                String name = heat.getString("Name");
                //不为所需类型,则跳过
                if(!name.equals(contestType)){
                    continue;
                }
                //读取所需类型的比赛结果
                JSONArray results = heat.getJSONArray("Results");
                //遍历当前类型比赛的每一场比赛
                for (int j=0; j < results.size(); j++){

                    JSONObject result = results.getJSONObject(j);
                    //获取当前比赛的必要信息
                    String totalPoints = result.getString("TotalPoints");
                    int rank = result.getIntValue("Rank");
                    String fullName = result.getString("FullName").replace('/','&');
                    //获取该名运动员在该场比赛里的所有得分点
                    ArrayList<String> divescore = new ArrayList<String>();

                    JSONArray dives = result.getJSONArray("Dives");
                    for (int k = 0; k < dives.size(); k++){

                        JSONObject dive = dives.getJSONObject(k);
                        divescore.add(dive.getString("DivePoints"));

                    }

                   contests.add(new Contest(totalPoints,rank,fullName,divescore));
                }

            }




        }catch (IOException e){
            throw new RuntimeException(e);
        }

        return contests;
    }
    //输出单项目决赛结果
    public void outputFinal(String contestName){
        try{
            //获取决赛的contest的List
            List<Contest> contests = outputContest(contestName,"Final");
            //遍历List,依次写入output文件里
            for (int i =0; i < contests.size(); i++){
              FileIo.outputWrite(contests.get(i).toString());
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }


    }

    //获取并输出单项目所有比赛类型的对应比赛数据
    public void outputContestDetailed(String contestName){
        //建立一个以字符串(选手名字)为索引下标的ContestDetailed哈希表
        HashMap<String, ContestDetailed> detailedHashMap= new HashMap<String,ContestDetailed>();
        //分别获取三种比赛类型的contest的List
        List<Contest> preContests = outputContest(contestName,"Preliminary");
        List<Contest> semiContests = outputContest(contestName,"Semifinal");
        List<Contest> finalContests = outputContest(contestName,"Final");
        //分别根据三种contest的List,将它们逐个放入哈希表里对应选手名字的contestDetailed里
        for (int i = 0; i < preContests.size(); i++){

            ContestDetailed contestDetailed = new ContestDetailed();
            contestDetailed.setContests(preContests.get(i),0);
            detailedHashMap.put(preContests.get(i).getFullname(),contestDetailed);

        }

        for (int i = 0; i < semiContests.size(); i++){

            String name = semiContests.get(i).getFullname();
            if(detailedHashMap.containsKey(name)){
                detailedHashMap.get(name).setContests(semiContests.get(i),1);
            }
            else {
                ContestDetailed contestDetailed = new ContestDetailed();
                contestDetailed.setContests(semiContests.get(i),1);
                detailedHashMap.put(semiContests.get(i).getFullname(),contestDetailed);
            }


        }

        for (int i = 0; i < finalContests.size(); i++){

            String name = finalContests.get(i).getFullname();
            if(detailedHashMap.containsKey(name)){
                detailedHashMap.get(name).setContests(finalContests.get(i),2);
            }
            else {
                ContestDetailed contestDetailed = new ContestDetailed();
                contestDetailed.setContests(finalContests.get(i),2);
                detailedHashMap.put(finalContests.get(i).getFullname(),contestDetailed);
            }


        }
        //自定义Array的排序顺序,将哈希表转成Array并排序
        List<ContestDetailed> contestList = new ArrayList<ContestDetailed>(detailedHashMap.values());

        contestList.sort(new Comparator<ContestDetailed>() {
            @Override
            public int compare(ContestDetailed a, ContestDetailed b) {
                // 比较第一个元素
                if (a.contests[0] != null && b.contests[0] != null) {
                    return a.contests[0].getRank() - b.contests[0].getRank();
                }
                // 如果a的第一个元素为null，但b的不为null，则a应该排在b后面
                else if (a.contests[0] == null && b.contests[0] != null) {
                    return 1;
                }
                // 如果b的第一个元素为null，但a的不为null，则a应该排在b前面
                else if (a.contests[0] != null) {
                    return -1;
                }
                // 如果第一个元素都为null，则比较第二个元素
                else if (a.contests[1] != null && b.contests[1] != null) {
                    return a.contests[1].getRank() - b.contests[1].getRank();
                }
                // 如果a的第二个元素为null，但b的不为null，则a应该排在b后面
                else if (a.contests[1] == null && b.contests[1] != null) {
                    return 1;
                }
                // 如果b的第二个元素为null，但a的不为null，则a应该排在b前面
                else if (a.contests[1] != null) {
                    return -1;
                }
                // 如果第二个元素都为null，则比较第三个元素
                else {
                    return a.contests[2].getRank() - b.contests[2].getRank();
                }

            }
        });
       try{
           for (int i =0; i < contestList.size(); i++){
               //将排序后的结果写入output文件里
               FileIo.outputWrite(contestList.get(i).toString());
           }
       }catch (IOException e){
           throw new RuntimeException(e);
       }




    }
}

