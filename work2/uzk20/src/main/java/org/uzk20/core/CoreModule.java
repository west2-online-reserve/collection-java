package org.uzk20.core;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.uzk20.domain.SingleMatchResult;
import org.uzk20.domain.DiveScore;
import org.uzk20.domain.EventDetailedResult;
import org.uzk20.domain.Athlete;
import org.uzk20.utils.Constants;
import org.uzk20.utils.Command;
import org.uzk20.utils.Utility;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;

public class CoreModule {
    //此处处理所有的选手的信息
    public List<Athlete> parseAllAthletes() {

        List<Athlete> athleteList = new ArrayList<>();

        /*此处为第一次output.txt内无输出的测试代码
        Athlete athlete1 = new Athlete("HART", "Alexander", 1, "Austria");
        Athlete athlete2 = new Athlete("LOTFI", "Dariush", 1, "Austria");
        Athlete athlete3 = new Athlete("DICK", "Elaena", 2, "Canada");

        athleteList.add(athlete1);
        athleteList.add(athlete2);
        athleteList.add(athlete3);
         */

        String jsonStr = Utility.readJsonFile("athletes.json");
        JSONArray nationArray = JSON.parseArray(jsonStr);

        for (int i = 0; i < nationArray.size(); i++) {
            JSONObject nationObj = nationArray.getJSONObject(i);
            String country = nationObj.getString("CountryName");
            JSONArray participations = nationObj.getJSONArray("Participations");

            for (int j = 0; j < participations.size(); j++) {
                JSONObject athleteObj = participations.getJSONObject(j);
                if (athleteObj.containsKey("PartnerName")) {
                    // 双人项目
                    String lastName1 = athleteObj.getString("PreferredLastName");
                    String firstName1 = athleteObj.getString("PreferredFirstName");
                    String lastName2 = athleteObj.getString("PartnerLastName");
                    String firstName2 = athleteObj.getString("PartnerFirstName");
                    int genderCode = athleteObj.getIntValue("Gender");
                    Athlete athlete = new Athlete(lastName1, firstName1, lastName2, firstName2, genderCode, country);athleteList.add(athlete);
                } else {
                    // 单人项目
                    String lastName = athleteObj.getString("PreferredLastName");
                    String firstName = athleteObj.getString("PreferredFirstName");
                    int genderCode = athleteObj.getIntValue("Gender");
                    Athlete athlete = new Athlete(lastName, firstName, genderCode, country);athleteList.add(athlete);
                }
            }
        }

        //此处实现按国籍为首要关键词排序，以LastName为次要关键词升序排序
        athleteList = athleteList.stream().sorted((a1, a2) -> {
                    int countryCompare = a1.getCountry().compareTo(a2.getCountry());
                    if (countryCompare != 0) return countryCompare;
                    return a1.getFullName().split(" ")[0].compareTo(a2.getFullName().split(" ")[0]); // 按Last Name排序
                }).collect(Collectors.toList());

        return athleteList;
    }

    //此处解析单场的比赛结果
    public List<SingleMatchResult> parseSingleMatchResult(String eventName,String stage) {

        List<SingleMatchResult> resultList = new ArrayList<>();
        String jsonStr = Utility.readJsonFile(eventName + ".json");
        JSONObject contestObj = JSON.parseObject(jsonStr);
        JSONArray heatsArray = contestObj.getJSONArray("Heats");

        for (int i = 0; i < heatsArray.size(); i++) {
            JSONObject heatObj = heatsArray.getJSONObject(i);
            if (!heatObj.getString("Name").equals(stage)) {
                continue;
            }

            JSONArray resultsArray = heatObj.getJSONArray("Results");

            for (int j = 0; j < resultsArray.size(); j++) {
                JSONObject resultObj = resultsArray.getJSONObject(j);
                String fullName = resultObj.getString("FullName").replace('/', '&');
                String rank = resultObj.getString("Rank");

                List<String> singleScores = new ArrayList<>();
                JSONArray divesArray = resultObj.getJSONArray("Dives");
                for (int k = 0; k < divesArray.size(); k++) {
                    singleScores.add(divesArray.getJSONObject(k).getString("DivePoints"));
                }
                String totalScore = resultObj.getString("TotalPoints");
                DiveScore diveScore = new DiveScore(singleScores, totalScore);

                //将处理好的数据加入列表
                SingleMatchResult smr = new SingleMatchResult(fullName, rank, diveScore);
                resultList.add(smr);
            }
        }
        return resultList;
    }

    //三场比赛的结果解析
    public List<EventDetailedResult> parseDetailedResult(String eventName){
        List<EventDetailedResult> detailedList=new ArrayList<>();
        List<SingleMatchResult> preResult = parseSingleMatchResult(eventName, Constants.STAGE_PRELIMINARY);
        List<SingleMatchResult> semiResult = parseSingleMatchResult(eventName, Constants.STAGE_SEMIFINAL);
        List<SingleMatchResult> finalResult = parseSingleMatchResult(eventName, Constants.STAGE_FINAL);
        //遍历结果并且匹配三场比赛
        for(SingleMatchResult pre:preResult){

            SingleMatchResult semi = semiResult.stream().filter(s -> s.getFullName().equals(pre.getFullName())).findFirst().orElse(null);
            SingleMatchResult finalR = finalResult.stream().filter(f -> f.getFullName().equals(pre.getFullName())).findFirst().orElse(null);

            EventDetailedResult edr=new EventDetailedResult(pre,semi,finalR);
            detailedList.add(edr);
        }
        return detailedList;
    }
    //处理输入的指令
    public String processCommand(Command cmd){
        /*System.out.println("指令解析结果");

        System.out.println("指令类型：" + cmd.getCmdType());
        System.out.println("事件名称：" + cmd.getEventName());
        System.out.println("指令是否合法：" + cmd.isLegal());
        System.out.println("错误信息：" + cmd.getErrorMsg());
        System.out.println();*/

        StringBuilder output=new StringBuilder();

        //非法情况处理
        if(!cmd.isLegal()){
            output.append(cmd.getErrorMsg()).append("\n").append(Constants.SEPARATOR);
            return output.toString();
        }
        //处理players
        if(cmd.getCmdType()==Command.CmdType.PLAYERS){
            List<Athlete> athletes=parseAllAthletes();
            if(athletes.isEmpty()){
                output.append("暂无数据\n").append(Constants.SEPARATOR);
            }
            else{
                for (Athlete a : athletes) {
                    output.append(a.toFormattedString());
                }
            }
        }
        //处理result
        if(cmd.getCmdType()==Command.CmdType.RESULT){
            String eventName= cmd.getEventName();
            //决赛处理
            if(!cmd.isDetail()){
                List<SingleMatchResult> finalResult=parseSingleMatchResult(eventName,Constants.STAGE_FINAL);
                for(SingleMatchResult smr:finalResult){
                    output.append(smr.toBasicResultString());
                }
            }//detailed情况处理
            else{
                List<EventDetailedResult> detailResults=parseDetailedResult(eventName);
                for(EventDetailedResult edr:detailResults){
                    output.append(edr.toDetailedResultString());
                }
            }
            return output.toString();
        }

        return output.toString();
    }
}
