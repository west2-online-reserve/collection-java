package domain;

import java.util.List;

//Contest类,用于储存json文件中的反序列化出的单项目单类型的一场比赛信息
public class Contest {

    //总分
    private String totalPoints;
    //排名
    private int rank;
    //全名
    private String fullname;
    //得分点
    private List<String> dives;

    public Contest() {
    }

    public Contest(String totalPoints,int rank,String fullname,List<String> dives) {
        this.totalPoints = totalPoints;
        this.rank = rank;
        this.fullname = fullname;
        this.dives = dives;
    }

    //计算得分点,同时进行相加,返回对应字符串
    public String getScore(){
        StringBuilder score = new StringBuilder();
        for(int i = 0; i < dives.size(); i++){

            if(i==dives.size()-1) {
                score.append(dives.get(i)).append(" = ");
                break;
            }

            score.append(dives.get(i)).append(" + ");

        }
        score.append(totalPoints);
        return score.toString();
    }

    @Override
    public String toString() {
        return "Full Name:" + fullname + "\n" +
                "Rank:" + rank + "\n" +
                "Score:" + getScore() + "\n" +
                "-----" + "\n";
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<String> getDives() {
        return dives;
    }

    public void setDives(List<String> dives) {
        this.dives = dives;
    }


}
