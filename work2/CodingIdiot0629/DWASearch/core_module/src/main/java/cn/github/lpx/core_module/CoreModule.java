package cn.github.lpx.core_module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import cn.github.lpx.model.Athlete;
import cn.github.lpx.model.ResultShow;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoreModule {
    static private ObjectMapper mapper = new ObjectMapper();
    static private Athlete[] athletes;//存参赛选手解析结果
    static private Map<String, ResultShow> resultShows = new HashMap<String, ResultShow>();//存一下每个项目解析结果，避免重复解析

    static {
        //忽略未找到的字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    //输出 Error或者 N/A 到 output.txt
    public static void displayErrorType(String message,String outputPath) throws IOException {
        try(PrintStream ps = new PrintStream(new FileOutputStream(outputPath, true))){
            ps.println(message);
            ps.println("-----");
        }
    }

    //输出运动员信息到output.txt
    public static void displayAllPlayersInfo(String outputPath) throws IOException {
        try (PrintStream ps = new PrintStream(new FileOutputStream(outputPath, true))) {
            if (athletes == null) athletes = mapper.readValue(Athlete[].class.getResourceAsStream("/data/athletes.json"), Athlete[].class);
            for (int i = 0; i < athletes.length; i++) {
                for (int j = 0; j < athletes[i].getParticipations().length; j++) {
                    ps.println("Full Name:" + athletes[i].getParticipations()[j].getPreferredLastName() + " " + athletes[i].getParticipations()[j].getPreferredFirstName());
                    ps.println("Gender:" + (athletes[i].getParticipations()[j].getGender() == 0 ? "Male" : "Female"));
                    ps.println("Country:" + athletes[i].getCountryName());
                    ps.println("-----");
                }
            }
        }
    }

    //输出决赛结果到output.txt
    public static void displayFinalsResultsForEachEvent(String eventName,String outputPath)throws IOException {
        try (PrintStream ps = new PrintStream(new FileOutputStream(outputPath, true))) {
            ResultShow resultShow = null;
            if (resultShows.get(eventName) == null) {
                resultShows.put(eventName, mapper.readValue(ResultShow.class.getResourceAsStream("/data/" + eventName + ".json"), ResultShow.class));
            }
            resultShow = resultShows.get(eventName);
            for (int i = 0; i < resultShow.getHeats().length; i++) {
                if (resultShow.getHeats()[i].getPhaseName().equals("Finals"))
                    for (int j = 0; j < resultShow.getHeats()[i].getResults().length; j++) {
                        ps.println("Full Name:" + resultShow.getHeats()[i].getResults()[j].getFullName().replace("/", "&"));
                        ps.println("Rank:" + resultShow.getHeats()[i].getResults()[j].getRank());
                        ps.println("Score:" + resultShow.getHeats()[i].getResults()[j].showScore());
                        ps.println("-----");
                    }
            }
        }
    }


    //输出比赛详细结果
    public static void displayDetailResultsForEachEvent(String eventName,String outputPath) throws  IOException {
        try (PrintStream ps = new PrintStream(new FileOutputStream(outputPath, true))) {
            ResultShow resultShow = null;
            if (resultShows.get(eventName) == null) {
                resultShows.put(eventName, mapper.readValue(ResultShow.class.getResourceAsStream("/data/" + eventName + ".json"), ResultShow.class));
            }
            resultShow = resultShows.get(eventName);

            //先拿到姓名与各个数据间的联系，用map建立一一对应的关系，那我们只要知道姓名的顺序就能知道其它信息的顺序了
            //至于为啥这里用这么多map映射各个数据，是因为解析json时这些数据并不是能一起拿到的(预赛，半决，决赛分开),要是把数据封装起来，还要涉及map映射的对象的成员的修改
            Map<String, String> preliminaryScore = new HashMap<>();
            Map<String, String> semifinalScore = new HashMap<>();
            Map<String, String> finalScore = new HashMap<>();
            Map<String, String> preliminaryRank = new HashMap<>();
            Map<String, String> semifinalRank = new HashMap<>();
            Map<String, String> finalRank = new HashMap<>();
            for (int i = 0; i < resultShow.getHeats().length; i++) {
                for (int j = 0; j < resultShow.getHeats()[i].getResults().length; j++) {
                    String name = resultShow.getHeats()[i].getResults()[j].getFullName();
                    if (resultShow.getHeats()[i].getPhaseName().equals("Finals")) {
                        finalScore.put(name, resultShow.getHeats()[i].getResults()[j].showScore());
                        finalRank.put(name, resultShow.getHeats()[i].getResults()[j].getRank());
                    } else if (resultShow.getHeats()[i].getPhaseName().equals("Preliminaries")) {
                        preliminaryScore.put(name, resultShow.getHeats()[i].getResults()[j].showScore());
                        preliminaryRank.put(name, resultShow.getHeats()[i].getResults()[j].getRank());
                    } else {
                        semifinalScore.put(name, resultShow.getHeats()[i].getResults()[j].showScore());
                        semifinalRank.put(name, resultShow.getHeats()[i].getResults()[j].getRank());
                    }
                }
            }

            //下面代码是按第一次参赛排名升序拿到姓名的顺序，然后再依次输出
            Map<String, String> firstRank;
            if (preliminaryRank.size() > 0) firstRank = preliminaryRank;
            else if (semifinalRank.size() > 0) firstRank = semifinalRank;
            else firstRank = finalRank;
            ArrayList<NameAndRank>  nameAndRanks = new ArrayList<>();
            for (Map.Entry<String, String> entry : firstRank.entrySet()) {
                nameAndRanks.add(new NameAndRank(entry.getKey(), entry.getValue()));
            }
            nameAndRanks.sort((a,b)->{
                if(a.getRank()==null)return 1;
                if(b.getRank()==null)return -1;
                return Integer.parseInt(a.getRank())-Integer.parseInt(b.getRank());
            });
            for(NameAndRank nameAndRank : nameAndRanks){
                String name = nameAndRank.getName();
                ps.println("Full Name:"+name.replace("/","&"));
                ps.print("Rank:"+(preliminaryRank.get(name)==null?"*":preliminaryRank.get(name)));
                ps.print(" | "+(semifinalRank.get(name)==null?"*":semifinalRank.get(name)));
                ps.println(" | "+(finalRank.get(name)==null?"*":finalRank.get(name)));
                ps.println("Preliminary Score:"+(preliminaryScore.get(name)==null?"*":preliminaryScore.get(name)));
                ps.println("Semifinal Score:"+(semifinalScore.get(name)==null?"*":semifinalScore.get(name)));
                ps.println("Final Score:"+(finalScore.get(name)==null?"*":finalScore.get(name)));
                ps.println("-----");
            }
        }
    }

    /*
    内部类，用于封装姓名和第一次参赛的排名
     */
    public static class NameAndRank {
        private String name;
        private String rank;
        public NameAndRank(String name, String rank) {
            this.name = name;
            this.rank = rank;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getRank() {
            return rank;
        }
        public void setRank(String rank) {
            this.rank = rank;
        }
    }
}
