package domain;

import java.util.Arrays;
import java.util.List;

//ContestDetailed类,用于储存json文件中的反序列化出的单项目不同类型的三场比赛信息
public class ContestDetailed {

    //对应单项目的三场不同类型比赛信息
    public Contest[] contests = new Contest[3];

    public ContestDetailed() {
    }

    //据输入的比赛类型,为对应的比赛赋值
    public void setContests(Contest contest,int i){
        contests[i]=contest;
    }

    @Override
    public String toString() {
        String fullname = "";
        if(contests[0]!=null){
            fullname = contests[0].getFullname();
        }else if(contests[1]!=null){
            fullname = contests[1].getFullname();
        }else if(contests[2]!=null){
            fullname = contests[2].getFullname();
        }

        return "Fullname:" + fullname + "\n" +
                "Rank:" + (contests[0]!=null ? contests[0].getRank() : "*") + " | " + (contests[1]!=null ? contests[1].getRank() : "*") + " | " + (contests[2]!=null ? contests[2].getRank() : "*") + "\n" +
                "Preliminary Score:" + (contests[0]!=null ? contests[0].getScore() : "*") + "\n" +
                "Semifinal Score:" + (contests[1]!=null ? contests[1].getScore() : "*") + "\n" +
                "Final Score:" + (contests[2]!=null ? contests[2].getScore() : "*") + "\n" +
                "-----" + "\n";
    }
}
