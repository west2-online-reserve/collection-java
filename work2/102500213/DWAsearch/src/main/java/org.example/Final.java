package org.example;

import java.util.List;
import java.util.ArrayList;

public class Final {
    private String disciplineName;
    private List<Result> results = new ArrayList<>();

    public Final(){}
    public String getDisciplineName(){
        return disciplineName;
    }
    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }
    public List<Result> getResults() { return results; }
    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getContent(){
        StringBuilder str = new StringBuilder();
        for(Result r:results){
            str.append(r.getContent());;
        }
        return str.toString();
    }

    public void showFinal(){
        System.out.println("-----"+disciplineName+"-----");
        for(Result r:results){
            r.showResult();
        }
        System.out.print("\n");
    }
}
