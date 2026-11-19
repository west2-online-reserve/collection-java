package com.example.taskdive;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;          // 用来将相应属性连接到相应键


// 运动员参与赛事
@JsonIgnoreProperties(ignoreUnknown = true)
public class Disciplines {
    @JsonProperty("DisciplineName")
    private String DisciplineName;

    private List<Phase> Phases;

    public Disciplines(){}
    public Disciplines(String DisciplineName){
        this.DisciplineName = DisciplineName;
    }

    public String getDisciplineName() {
           return DisciplineName;
    }
    public void setDisciplineName(String DisciplineName) {
           this.DisciplineName = DisciplineName;
    }

    public List<Phase> getPhases() {
        return Phases;
    }
    public void setPhases(List<Phase> Phases) {
        this.Phases = Phases;
    }

    public void addPhase(Phase phase){
        this.Phases.add(phase);
    }

    @Override
    public String toString() {
        return "赛事名称='" + DisciplineName + '\'' +
                ", 赛事阶段=" + Phases;
    }

    public int hashCode()
    {
        return DisciplineName.hashCode();
    }
    public boolean equals(Object object)
    {
        if(this == object)
        {
            return true;
        }
        if(!(object instanceof Disciplines))
        {
            return false;
        }
        Disciplines other = (Disciplines)object;
        boolean b = this.DisciplineName.equals(other.DisciplineName);
        return b;
    }
}
