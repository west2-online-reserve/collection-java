package Core;

import DWAClass.Athlete;
import DWAClass.Event;
import DWAClass.EventDetail;
import File_IO.FileIO;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CoreModule {
    private static final String[] PHRASE = {"Preliminary", "Semifinal", "Final"};

    // 输出所有选手信息
    public void displayAllPlayersInfo(FileIO fileIO) {

        //Json文件地址
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("athletes.json");

        String jsonString = "";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            //获得Json字符串
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();

            jsonString = sb.toString();

            //解析字符串（国家->人）
            JSONArray jsonArray = JSON.parseArray(jsonString);//国家

            for (int i = 0; i < jsonArray.size(); i++) {

                JSONObject country = jsonArray.getJSONObject(i);
                //获取国家名字
                String countryName = country.getString("CountryName");

                JSONArray participations = country.getJSONArray("Participations");//一个国家的人

                for (int j = 0; j < participations.size(); j++) {

                    //运动员信息
                    JSONObject participation = participations.getJSONObject(j);
                    String preferredLastName = participation.getString("PreferredLastName");
                    String preferredFirstName = participation.getString("PreferredFirstName");
                    int gender = participation.getInteger("Gender");

                    Athlete athlete = new Athlete(countryName, preferredLastName, preferredFirstName, gender);
                    fileIO.fileWrite(athlete.toString());
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //输出决赛信息
    public void displayResultsForEachEvent(String event,FileIO fileIO) {
        List<Event> events = displayResultsForEachEvent(event, 2);
        for (Event event1 : events) {
            fileIO.fileWrite(event1.toString());
        }
    }

    //比赛信息(0初赛,1半决赛,2决赛)
    public List<Event> displayResultsForEachEvent(String event, int phrase) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(event+".json");

        String jsonString = "";
        List<Event> events = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            //获得Json字符串
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();

            jsonString = sb.toString();

            //获取所有赛事的字符串
            JSONObject jsonObject = JSON.parseObject(jsonString);

            String jsonHeats = jsonObject.getString("Heats");

            JSONArray heats = JSON.parseArray(jsonHeats);
            //按照参数获取对应赛事的信息

            for (int i = 0; i < heats.size(); i++) {
                JSONObject heat = heats.getJSONObject(i);

                String eventPhrase = heat.getString("Name");

                //跳过不是要找的比赛阶段
                if (!eventPhrase.equals(PHRASE[phrase])) {
                    continue;
                }

                JSONArray jsonArray = heat.getJSONArray("Results");

                //获取选手信息
                for (int j = 0; j < jsonArray.size(); j++) {

                    JSONObject athlete = jsonArray.getJSONObject(j);

                    int rank = athlete.getInteger("Rank");
                    String fullName = athlete.getString("FullName").replace('/', '&');
                    double totalScore = athlete.getDouble("TotalPoints");

                    Event event1 = new Event(fullName, rank, new ArrayList<>(), totalScore);

                    JSONArray dives = athlete.getJSONArray("Dives");

                    for (int k = 0; k < dives.size(); k++) {
                        event1.getDives().add(dives.getJSONObject(k).getString("DivePoints"));
                    }
                    events.add(event1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    //详细比赛信息
    public void displayResultsForEachEventDetail(String event,FileIO fileIO) {
        //按插入时顺序排序的哈希表
        LinkedHashMap<String, Event> map1 = new LinkedHashMap<>();
        LinkedHashMap<String, Event> map2 = new LinkedHashMap<>();
        LinkedHashMap<String, Event> map3 = new LinkedHashMap<>();

        List<EventDetail> eventDetail = new ArrayList<>();

        //获取初赛
        for (Event PreliminaryEvent : displayResultsForEachEvent(event, 0)) {
            map1.put(PreliminaryEvent.getFullName(), PreliminaryEvent);
        }

        //获取半决赛
        for (Event SemifinalEvent : displayResultsForEachEvent(event, 1)) {
            map2.put(SemifinalEvent.getFullName(), SemifinalEvent);
        }

        //获取决赛
        for (Event FinalEvent : displayResultsForEachEvent(event, 2)) {
            map3.put(FinalEvent.getFullName(), FinalEvent);
        }

        //若初赛不为空则按照初赛遍历,为空则按决赛
        if (!map1.isEmpty()) {
            Set<String> names = map1.keySet();
            for (String name : names) {
                Event tmps[]=new Event[3];
                Event tmp1 = new Event();
                Event tmp2 = new Event();
                Event tmp3 = new Event();
                //若没有参加某一个赛程就标记排名为0
                tmp1 = map1.get(name);
                if (map2.get(name) == null) {
                    tmp2.setRank(0);
                } else {
                    tmp2 = map2.get(name);
                }
                if (map3.get(name) == null) {
                    tmp3.setRank(0);
                } else {
                    tmp3 = map3.get(name);
                }

                tmps[0]=tmp1;
                tmps[1]=tmp2;
                tmps[2]=tmp3;

                EventDetail tmpEvent = new EventDetail(tmps);
                //将一位运动员的信息存入
                eventDetail.add(tmpEvent);
            }
        } else {
            Set<String> names = map3.keySet();
            for (String name : names) {
                Event tmps[]=new Event[3];
                Event tmp1 = new Event();
                Event tmp2 = new Event();
                Event tmp3 = new Event();

                tmp1.setRank(0);
                tmp2.setRank(0);
                tmp3 = map3.get(name);

                tmps[0]=tmp1;
                tmps[1]=tmp2;
                tmps[2]=tmp3;

                EventDetail tmpEvent = new EventDetail(tmps);
                eventDetail.add(tmpEvent);
            }
        }

        for (EventDetail detail : eventDetail) {
            fileIO.fileWrite(detail.toString());
        }

    }

}
