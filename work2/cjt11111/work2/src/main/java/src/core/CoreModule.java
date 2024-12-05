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
    private static final String[] TYPE={"Preliminary","Semifinal","Final"};


//输出所有选手信息
    public void displayAllPlayersInfo(){
        try{
            StringBuilder jsonContent=new StringBuilder();
            InputStream in=getClass().getClassLoader().getResourceAsStream("athletes.json");
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            String line;
            while((line=br.readLine())!=null){
                jsonContent.append(line);
            }
            br.close();

           JSONArray jsonArray= JSON.parseArray(jsonContent.toString());

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject nation=jsonArray.getJSONObject(i);
                String countryName=nation.getString("countryName");
                JSONArray paticipants=nation.getJSONArray("paticipants");
                for (int j = 0; j < paticipants.size(); j++) {
                   JSONObject paticipant=paticipants.getJSONObject(j);
                   String gender=paticipant.getString("Gender");
                   String lastName=paticipant.getString("PreferredLastName");
                   String firstName=paticipant.getString("PreferredFirstName");
                    Athlete athlete=new Athlete(countryName,gender,lastName,firstName);
                    Utility.write(athlete.toString());
                }
            }

        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    //输出比赛项目结果
public void displayResults(String contestName){
    List<Contest> listFinal=displayResults(contestName,2);

    for(int i=0;i<listFinal.size();i++){
               Contest contest=listFinal.get(i);
                try{
                    Utility.write(contest.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
    }
}
//查询比赛项目结果
private List<Contest> displayResults(String contestName,int contestType){
        List<Contest> contests=new ArrayList<>();
        try {
          StringBuilder jsonContent=new StringBuilder();
          String path=contestName+".json";
          InputStream in=getClass().getClassLoader().getResourceAsStream(path);
          BufferedReader br=new BufferedReader(new InputStreamReader(in));
          String line;
          while((line=br.readLine())!=null){
              jsonContent.append(line);
          }
            br.close();
          JSONObject jsonObject=JSON.parseObject(jsonContent.toString());
          //提取Heats
         JSONArray heats=jsonObject.getJSONArray("Heats");
            for (int i = 0; i < heats.size(); i++) {
                JSONObject heat=heats.getJSONObject(i);
                String name=heat.getString("Name");
                if(!name.equals(TYPE[contestType])){
                    continue;
                }
                JSONArray results=heat.getJSONArray("Results");
                for (int j = 0; j < results.size(); j++) {
                    JSONObject result=results.getJSONObject(j);
                    String totalPoints=result.getString("TotalPoints");
                    int rank=result.getIntValue("Rank");
                    String fullName=result.getString("FullName").replace('/','&');
                    Contest contest=new Contest(totalPoints,rank,fullName,new ArrayList<>());
                    JSONArray dives=result.getJSONArray("Dives");

                for (int k = 0; k < dives.size(); k++) {
                        contest.getDives().add(dives.getJSONObject(k).getString("DivePoints"));
                    }
                    contests.add(contest);

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    return contests;
}
//输出比赛的详细结果
public void displayDetailedResults(String contestName){
    HashMap<String, ContestDetailed> map=new HashMap<>();

    List<Contest> contestList = displayResults(contestName, 0);
    for (int i = 0; i < contestList.size(); i++) {
        Contest preliminaryContest = contestList.get(i);
        String fullName = preliminaryContest.getFullName();
        ContestDetailed detailed = map.computeIfAbsent(fullName, k -> new ContestDetailed());
        detailed.contests[0] = preliminaryContest;
    }

    for (int i = 0; i < contestList.size(); i++) {
        Contest semiFinalContest = contestList.get(i);
        String fullName = semiFinalContest.getFullName();
        ContestDetailed detailed = map.computeIfAbsent(fullName, k -> new ContestDetailed());
        detailed.contests[1] = semiFinalContest;

    }
    for (int i = 0; i < contestList.size(); i++) {
        Contest finalContest = contestList.get(i);
        String fullName = finalContest.getFullName();
        ContestDetailed detailed = map.computeIfAbsent(fullName, k -> new ContestDetailed());
        detailed.contests[1] = finalContest;

    }
        List<ContestDetailed> lst=new ArrayList<>(map.values());
    lst.sort(new Comparator<ContestDetailed>() {
        @Override
        public int compare(ContestDetailed o1, ContestDetailed o2) {
            if (o1.contests[0] != null) {
                return o1.contests[0].getRank() - o2.contests[0].getRank();
            } else if (o1.contests[1] != null) {
                return o1.contests[1].getRank() - o2.contests[1].getRank();
            } else {
                return o1.contests[2].getRank() - o2.contests[2].getRank();
            }
        }
    });
    //
    for (int i = 0; i < lst.size(); i++) {
        ContestDetailed contestDetailed = lst.get(i);
        try {
            Utility.write(contestDetailed.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
}
