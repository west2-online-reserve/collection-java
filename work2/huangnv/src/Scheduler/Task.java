package Scheduler;

public class Task {
    private int  line;
    private String key;
    private  String option;
    public Task(int line, String key, String option) {
        this.line = line;
        this.key = key;
        this.option = option;
    }

    public int getLine() {
        return line;
    }
    public String getKey() {
        return key;
    }

    public String getOption(){
        return option;
    }



}
