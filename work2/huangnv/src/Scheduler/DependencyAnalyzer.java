package Scheduler;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DependencyAnalyzer {
    public Map<String, TaskGroup> analyze(List<Task> tasks) {
        Map<String, TaskGroup> groups = new LinkedHashMap<>();

        for (Task t : tasks) {
            String key = t.getKey(); // 假设你在 Task 类里写了 getKey()
            groups.computeIfAbsent(key, k -> new TaskGroup(k))
                    .addTask(t);
        }
        return groups;
    }
}
