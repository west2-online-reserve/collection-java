package domain;

//Athlete类,用于储存json文件中的反序列化出的运动员信息
public class Athlete {
    //国家名
    private String countryName;
    //性别
    private int gender;
    //姓
    private String preferredLastName;
    //名
    private String preferredFirstName;

    public Athlete() {
    }

    public Athlete(String countryName,int gender,String preferredLastName,String preferredFirstName) {
        this.countryName = countryName;
        this.gender = gender;
        this.preferredLastName = preferredLastName;
        this.preferredFirstName = preferredFirstName;
    }

    @Override
    public String toString() {
        return "Full Name:" + preferredLastName + " " + preferredFirstName + "\n" +
                "Gender:" + (gender==0 ? "Male" : "Female") + "\n" +
                "Country:" + countryName +"\n" + "-----" + "\n";
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPreferredLastName() {
        return preferredLastName;
    }

    public void setPreferredLastName(String preferredLastName) {
        this.preferredLastName = preferredLastName;
    }

    public String getPreferredFirstName() {
        return preferredFirstName;
    }

    public void setPreferredFirstName(String preferredFirstName) {
        this.preferredFirstName = preferredFirstName;
    }


}
