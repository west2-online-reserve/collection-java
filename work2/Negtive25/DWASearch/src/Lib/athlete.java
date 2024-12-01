package src.Lib;


import java.util.Arrays;
import java.util.Comparator;

public class Athlete {
    private String fullName;
    private String gender;
    private String country;
    private String preRank,semiRank,finalRank;
    public String preScore,semiScore,finalScore;

    public Athlete(){
        fullName = "Full Name:*";
        gender = "Gender:*";
        country = "Country:*";
        preRank = "*";
        semiRank = "*";
        finalRank = "*";
        preScore = "Preliminary Score:*";
        semiScore = "Semifinal Score:*";
        finalScore = "Final Score:*";
    }
    public Athlete(String fullName, String gender, String country) {
        this.fullName = fullName;
        this.gender = gender;
        this.country = country;
    }

    public Athlete(String preScore, String semiScore, String finalScore, String preRank, String semiRank, String finalRank, String fullName){
        this.preScore = preScore;
        this.semiScore = semiScore;
        this.finalScore = finalScore;
        this.preRank = preRank;
        this.semiRank = semiRank;
        this.finalRank = finalRank;
        this.fullName = fullName;
    }

    public void resetAthleteInfo(){
        fullName = "Full Name:*";
        gender = "Gender:*";
        country = "Country:*";
        preRank = "*";
        semiRank = "*";
        finalRank = "*";
        preScore = "Preliminary Score:*";
        semiScore = "Semifinal Score:*";
        finalScore = "Final Score:*";
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public String getFullName(){
        return fullName;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return gender;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getCountryName(){
        return country;
    }
    public void setPreRank(String preRank){
        this.preRank = preRank;
    }
    public String getPreRank(){
        return preRank;
    }
    public void setSemiRank(String semiRank){
        this.semiRank = semiRank;
    }
    public String getSemiRank(){
        return semiRank;
    }
    public void setFinalRank(String finalRank){
        this.finalRank = finalRank;
    }
    public String getFinalRank(){
        return finalRank;
    }
    public String getLastName(){
        String[] names = fullName.split(" ");
        return names[names.length-1];
    }

    public int getFirstMatchRank(){
        if(!preRank.equals("*"))
            return Integer.parseInt(preRank);
        if(!semiRank.equals("*"))
            return Integer.parseInt(semiRank);
        return Integer.parseInt(finalRank);
    }

    public int getFinalRankForSort(){
        return Integer.parseInt(finalRank);
    }

    //选手姓名格式命名为'A & B'按照选手名（Last Name）从小到大排序，例如'JACHIM Filip & LUKASZEWICZ Robert'
    public void processNameForSynchronisedMatch(){
        fullName = fullName.replace("Full Name:","");
        String[] names = fullName.split(" & ");
        Arrays.sort(names, new Comparator<String>() {
            public int compare(String o1, String o2) {
               String[] n1 = o1.split(" ");
               String[] n2 = o2.split(" ");
               return n1[n1.length-1].compareTo(n2[n2.length-1]);
            }
        });
        fullName="Full Name:"+names[0]+" & "+names[1];
    }

    public String allAthleteToString(){
        return fullName +"\n"+gender+"\n"+country+"\n"+"-----"+"\n";
    }

    public String MatchToString(){
        if(finalRank.equals("*"))
            return null;
        return fullName+"\n"+"Rank:"+finalRank+"\n"+
                finalScore.replace("Final ","")+"\n"+"-----"+"\n";
    }

    public String MatchToStringInDetail(){
        return fullName+"\n"+"Rank:"+preRank+" | "+semiRank+" | "+finalRank+"\n"
                +preScore+"\n"+semiScore+"\n"+finalScore+"\n"+"-----"+"\n";
    }
}
