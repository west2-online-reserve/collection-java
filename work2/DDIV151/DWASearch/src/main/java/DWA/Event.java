package DWA;

import com.alibaba.fastjson.annotation.JSONField;

public class Event {
    @JSONField(name = "DisciplineName")
    private String disciplineName;
    @JSONField(name = "Heats")
    private String heats;
    @JSONField(name = "Gender")
    private String gender;

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getHeats() {
        return heats;
    }

    public Event(String disciplineName, String heats) {
        this.disciplineName = disciplineName;
        this.heats = heats;
    }

    public void setHeats(String heats) {
        this.heats = heats;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    public String toString() {
        return disciplineName;
    }
}
