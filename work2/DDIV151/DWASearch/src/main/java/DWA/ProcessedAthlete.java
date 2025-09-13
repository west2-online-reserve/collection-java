package DWA;

import com.alibaba.fastjson.annotation.JSONField;

public class ProcessedAthlete extends Athlete {
    @JSONField(name = "Gender")
    private String Gender;
    public ProcessedAthlete() {}
    @Override
    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    @Override
    public String toString() {
        return "Full Name:" + getFirstName() + " " + getLastName() + "\nGender:" + getGender() + "\nCountry:" + getCountry();
    }
}
