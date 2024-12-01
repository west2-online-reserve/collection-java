package org.example;

import java.util.ArrayList;


public class Conpetiton {
    private String FullName;
    private int Rank;
    private ArrayList<Float> Dives;

    public Conpetiton(String fullName, int rank, ArrayList<Float> dives) {
        FullName= fullName;
        Rank = rank;
        Dives = dives;
    }

    private String getPoint(){
        String s="";
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

    @Override
    public String toString() {
        return "Full Name:"+FullName+"\n"+
                "Rank:"+Rank+"\n"+
                "Score:"+getPoint()+"\n"+
                "-----";
    }

    public String getFullName() {
        return FullName;
    }

    public void setFirstName(String firstName) {
        FullName = firstName;
    }


    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public ArrayList<Float> getDives() {
        return Dives;
    }

    public void setDives(ArrayList<Float> dives) {
        Dives = dives;
    }
}
