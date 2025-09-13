
import java.util.ArrayList;

public class Athlete {
    //全名
    private String fullName;
    private String preferredLastName;
    private String preferredFirstName;
    //性别
    private int gender;
    enum Gender {
        MALE,FEMALE;
    }
    //国籍
    private String country;
    //排名
    private int rank;
    private ArrayList<Integer> ranks = new ArrayList<Integer>();

    //构造方法
    public Athlete(String fullName,String preferredLastName,String preferredFirstName, int gender, String country) {
        this.fullName = fullName;
        this.preferredLastName = preferredLastName;
        this.preferredFirstName = preferredFirstName;
        this.gender = gender;
        this.country = country;
    }
    public Athlete(String fullName,int gender,String country) {
        this.fullName = fullName;
        this.gender = gender;
        this.country = country;
    }

    //set
    public void setCountry(String country) {
        this.country = country;
    }
    public void setRank(int rank){
        this.rank = rank;
    }

    //get
    public String getFullName() {
        return fullName;
    }
    public int getGender() {
        return gender;
    }
    public String getcountry() {
        return country;
    }


    public int getRank(){
        return rank;
    }




}
