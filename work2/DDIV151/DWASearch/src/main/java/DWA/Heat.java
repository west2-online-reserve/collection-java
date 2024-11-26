package DWA;

import com.alibaba.fastjson.annotation.JSONField;

public class Heat {
    @JSONField(name = "Gender")
    private String gender;
    @JSONField(name = "Name")
    private String name;
    @JSONField(name = "Results")
    private String results;

    public Heat(String gender, String name, String results) {
        this.gender = gender;
        this.name = name;
        this.results = results;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public Heat() {
    }
    @Override
    public String toString() {
        return name;
    }
}
