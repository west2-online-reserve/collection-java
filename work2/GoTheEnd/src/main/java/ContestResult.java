import java.util.HashMap;
import java.util.Map;

public class ContestResult {

    private String name;
    private String gender;
    private int firstRank;


    private int finalRank;


    private Map<String, ScheduleDetail> scheduleMap = new HashMap<>();

    public ContestResult(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public void setFirstRank(){
        ScheduleDetail scheduleDetail;
        if ((scheduleDetail = scheduleMap.get("Preliminary")) != null){
            firstRank = scheduleDetail.getRank();
        }else if ((scheduleDetail = scheduleMap.get("Semifinal")) != null){
            firstRank = scheduleDetail.getRank();
        }
    }
    public int getFirstRank() {
        return firstRank;
    }
    public int getFinalRank() {
        return finalRank;
    }

    public void setFinalRank() {
        if (scheduleMap.containsKey("Final")){
            finalRank = scheduleMap.get("Final").getRank();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setScheduleMap(String schedule, ScheduleDetail detail) {
        scheduleMap.put(schedule, detail);
    }

    public Map<String, ScheduleDetail> getScheduleMap() {
        return scheduleMap;
    }


}
