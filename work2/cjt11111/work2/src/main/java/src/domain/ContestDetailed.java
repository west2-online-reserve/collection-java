package domain;

public class ContestDetailed {
    public Contest[] contests=new Contest[3];

    public ContestDetailed() {
    }

    @Override
    public String toString() {
        String fullName="";
        if(contests[0]!=null){
            fullName=contests[0].getFullName();
        }else if(contests[1]!=null){
            fullName=contests[1].getFullName();
        }else {
            fullName=contests[2].getFullName();
        }
        return "Full Name:" +fullName+"\n"+
                "Rank:" + (contests[0]!=null?contests[0].getRank():"*")+" | "
                +(contests[1]!=null?contests[1].getRank():"*")+" | "
                +(contests[2]!=null?contests[2].getRank():"*")+"\n"+
                "Preliminary Score:"+(contests[0]!=null?contests[0].getScore():"*")+"\n"+
                "Semifinal Score:"+(contests[1]!=null?contests[1].getScore():"*")+"\n"+
                "Final Score:"+(contests[1]!=null?contests[1].getScore():"*")+"\n"+
                "-----";
    }
}
