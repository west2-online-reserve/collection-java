package DWAClass;

import java.util.Arrays;

public class EventDetail {
    private Event[] events = new Event[3];

    public EventDetail(Event[] events) {
        this.events = events;
    }

    public EventDetail() {

    }

    public Event[] getEvent() {
        return events;
    }

    public void setEvent(Event[] contests) {
        this.events = contests;
    }

    public String RankString(){
        StringBuilder stringBuilder=new StringBuilder();
        //判断有没有参加比赛
        for (int i=0;i<3;i++){
            if(events[i].getRank()!=0){
                stringBuilder.append(events[i].getRank());
            }
            else{
                stringBuilder.append("*");
            }
            if (i!=2){
                stringBuilder.append("|");
            }
        }
        return stringBuilder.toString();
    }

    //各赛程的成绩
    public String PreliminaryString(){
        if(events[0].getRank()==0){
            return "*";
        }
        else {
            return events[0].ScoreString();
        }
    }

    public String SemifinalString(){
        if(events[1].getRank()==0){
            return "*";
        }
        else {
            return events[1].ScoreString();
        }
    }

    public String FinalString(){
        if(events[2].getRank()==0){
            return "*";
        }
        else {
            return events[2].ScoreString();
        }
    }

    @Override
    public String toString() {
        String name;
        if(events[0].getRank()!=0){
            name=events[0].getFullName();
        }
        else{
            name=events[2].getFullName();
        }

        return "Full Name:"+name+"\n"+
                "Rank:"+RankString()+"\n"+
                "Preliminary Score:"+PreliminaryString()+"\n"
                +"Semifinal Score:"+SemifinalString()+"\n"
                +"Final Score:"+FinalString()+"\n"
                +"-----";

    }
}
