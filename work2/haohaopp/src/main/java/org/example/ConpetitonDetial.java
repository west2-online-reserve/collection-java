package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class ConpetitonDetial {
    private String name;
    private Conpetiton[] conpetitons=new Conpetiton[3];//0为初赛，1为半决赛，2为决赛

    @Override
    public String toString() {
        return "Full Name:"+name+"\n"
                +"Rank:"+getRank(conpetitons[0])+" | "+getRank(conpetitons[1])+" | "+getRank(conpetitons[2])+"\n"
                +"Preliminary Score:"+getPoint(conpetitons[0])+"\n"
                +"Semifinal Score:"+getPoint(conpetitons[1])+"\n"
                +"Final Score:"+getPoint((conpetitons[2]))+"\n"
                +"-----";
    }
    private String getPoint(Conpetiton c){
        if(c==null) return "*";
        else {
            String s="";
            ArrayList<Float> Dives=c.getDives();
            float point=0;
            for(int i=0;i<Dives.size();i++){
                point+= Dives.get(i);
                if(i<Dives.size()-1)
                    s=s+Dives.get(i)+" + ";
                else
                    s=s+Dives.get(i)+" = ";
            }
            return s+point;
        }
    }
    private String getRank(Conpetiton c){
        if(c==null) return "*";
        else return c.getRank()+"";
    }

    public ConpetitonDetial(String name, Conpetiton[] conpetitons) {
        this.name = name;
        this.conpetitons = conpetitons;
    }
    public ConpetitonDetial(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Conpetiton[] getConpetitons() {
        return conpetitons;
    }

    public void setConpetitons(Conpetiton[] conpetitons) {
        this.conpetitons = conpetitons;
    }
}
