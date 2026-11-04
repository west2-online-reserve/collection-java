package com.dwasearch.crawler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sports {
    @JsonProperty("DisciplineList")
    private List<Discipline> disciplineList;

    public List<Discipline> getDisciplineList() {
        return disciplineList;
    }
    public void setDisciplineList(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }
}
