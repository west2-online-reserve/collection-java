package Scheduler;

import java.util.ArrayList;
import java.util.List;

public class TaskGroup {
    private final String name;
    private  List<Task> group = new ArrayList<>();

    public TaskGroup(String name){
        this.name = name;
    }

    public void addTask(Task t){
        group.add(t);
    }
    public String getName() {
        return name;

    }

    public List<Task> getTasks() {
        return group;
    }

}
