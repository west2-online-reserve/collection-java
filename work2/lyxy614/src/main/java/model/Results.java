package main.java.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {
    @JsonProperty("TotalPoints")
    private Double totalPoints;
    @JsonProperty("Rank")
    private Integer rank;
    @JsonProperty("FullName")
    private String fullName;
    @JsonProperty("Dives")
    private List<Dives> dives;
    @JsonProperty("Competitors")
    private List<Competitors> competitors;

    public Results() {}

    public Double getTotalPoints() {
        return totalPoints;
    }
    public void setTotalPoints(Double totalPoints) {
        this.totalPoints = totalPoints;
    }
    public Integer getRank() {
        return rank;
    }
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Dives> getDives() {
        return dives;
    }
    public void setDives(List<Dives> dives) {
        this.dives = dives;
    }

    public List<Competitors> getCompetitors() {
        return competitors;
    }
    public void setCompetitors(List<Competitors> competitors) {
        this.competitors = competitors;
    }
    //建立competitors的比较逻辑，实际上是为了控制双人项目中两名运动员名字的先后顺序
    public List<Competitors> getSortedCompetitors() {
        Comparator<Competitors> competitorsComparator = Comparator.comparing(Competitors::getFullName);
        competitors.sort(competitorsComparator);
        return competitors;
    }
    //Results中包含了需要输出的大多数信息，所以覆写其toString方法，整理好输出格式，简化输出模块的代码
    public String toString(){
        //构建小分相加的字符串
        StringBuilder connectDivePoints = new StringBuilder();
        Iterator<Dives> iterator = dives.iterator();
        while(iterator.hasNext()){
            Dives dive = iterator.next();
            connectDivePoints.append(dive);
            if(iterator.hasNext()){
                connectDivePoints.append(" + ");
            }
        }
        //输出结果分为两种情况，第一种是双人项目，用排序后的列表提取两个运动员的fullName输出，保证顺序符合要求
        //由于原数据中单人项目的Competitors也存在且为null，故可用来区分单人和双人项目
        if (getSortedCompetitors() != null){
            return  "Full Name:" + getSortedCompetitors().getFirst().getFullName() + " & " + getSortedCompetitors().getLast().getFullName() + "\n" +
                    "Rank:" + rank + "\n" +
                    "DivePoints:" + connectDivePoints + " = " + String.format("%.2f", totalPoints) + "\n" +
                    "-----" + "\n";
        }
        //第二种为单人项目，直接用Results下已有的fullName输出
        return  "Full Name:" + fullName + "\n" +
                "Rank:" + rank + "\n" +
                "DivePoints:" + connectDivePoints + " = " + String.format("%.2f", totalPoints) + "\n" +
                "-----" + "\n";
    }

}
