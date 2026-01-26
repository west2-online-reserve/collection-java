package Scheduler;

import DataCrawler.Athlete.AthleteQueryService;
import DataCrawler.Result.ResultQueryService;
import Common.QueryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskScheduler {
    private final QueryService athleteService;
    private final QueryService resultService;

    public TaskScheduler() {
        this(new AthleteQueryService(), new ResultQueryService());
    }

    public TaskScheduler(QueryService athleteService, QueryService resultService) {
        this.athleteService = athleteService;
        this.resultService = resultService;
    }

    public void execute(Map<String, TaskGroup> groups, String[] output) {
        List<Thread> threads = new ArrayList<>();
        for (TaskGroup group : groups.values()) {
            Thread thread = new Thread(() -> runGroup(group, output));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void runGroup(TaskGroup group, String[] output) {
        String key = group.getName();
        if ("ATHLETES".equals(key)) {
            runAthletesGroup(group, output);
        } else if (key.startsWith("EVENT:")) {
            runEventGroup(group, output);
        }
    }

    private void runAthletesGroup(TaskGroup group, String[] output) {

        String result ;
        try {
            result = athleteService.query(null, "");
        } catch (Exception e) {
            System.err.println("[ERROR] runAthletesGroup: " + e.getMessage());
            e.printStackTrace();
            result="网络不好 请求失败";
        }
        for (Task task : group.getTasks()) {
            writeOutput(output, task.getLine(), result);
        }
    }

    private void runEventGroup(TaskGroup group, String[] output) {
        String key = group.getName();
        String eventName = key.substring("EVENT:".length()).trim();
        for (Task task : group.getTasks()) {
            String result ;
            try {
                result = resultService.query(eventName, task.getOption());
            } catch (Exception e) {
                System.err.println("[ERROR] runEventGroup: " + e.getMessage());
                e.printStackTrace();
                result="网络不好 请求失败";
            }
            writeOutput(output, task.getLine(), result);
        }
    }

    private void writeOutput(String[] output, int line, String value) {
        output[line - 1] = value;
    }
}
