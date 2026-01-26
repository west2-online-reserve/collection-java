package Scheduler;

import Common.QueryService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskSchedulerTest {
    private static final String NETWORK_ERROR = "网络不好 请求失败";

    @Test
    public void athletesGroupWritesAllLines() {
        QueryService athleteService = (target, option) -> "ATH\n-----\n";
        QueryService resultService = (target, option) -> "R\n";
        TaskScheduler scheduler = new TaskScheduler(athleteService, resultService);

        TaskGroup group = new TaskGroup("ATHLETES");
        group.addTask(new Task(1, "ATHLETES", ""));
        group.addTask(new Task(3, "ATHLETES", ""));

        Map<String, TaskGroup> groups = new LinkedHashMap<>();
        groups.put("ATHLETES", group);

        String[] output = new String[3];
        Arrays.fill(output, "");

        scheduler.execute(groups, output);

        assertEquals("ATH\n-----\n", output[0]);
        assertEquals("", output[1]);
        assertEquals("ATH\n-----\n", output[2]);
    }

    @Test
    public void eventGroupWritesPerOption() {
        QueryService athleteService = (target, option) -> "ATH\n";
        QueryService resultService = (target, option) -> "R:" + target + ":" + option + "\n";
        TaskScheduler scheduler = new TaskScheduler(athleteService, resultService);

        TaskGroup group = new TaskGroup("EVENT:Women 10m Platform");
        group.addTask(new Task(1, "EVENT:Women 10m Platform", ""));
        group.addTask(new Task(2, "EVENT:Women 10m Platform", "detail"));

        Map<String, TaskGroup> groups = new LinkedHashMap<>();
        groups.put("EVENT:Women 10m Platform", group);

        String[] output = new String[2];
        Arrays.fill(output, "");

        scheduler.execute(groups, output);

        assertEquals("R:Women 10m Platform:\n", output[0]);
        assertEquals("R:Women 10m Platform:detail\n", output[1]);
    }

    @Test
    public void exceptionsReturnNetworkMessage() {
        QueryService athleteService = (target, option) -> {
            throw new RuntimeException("boom");
        };
        QueryService resultService = (target, option) -> {
            throw new RuntimeException("boom");
        };
        TaskScheduler scheduler = new TaskScheduler(athleteService, resultService);

        TaskGroup athletes = new TaskGroup("ATHLETES");
        athletes.addTask(new Task(1, "ATHLETES", ""));

        TaskGroup events = new TaskGroup("EVENT:Men 3m Synchronised");
        events.addTask(new Task(2, "EVENT:Men 3m Synchronised", ""));
        events.addTask(new Task(3, "EVENT:Men 3m Synchronised", "detail"));

        Map<String, TaskGroup> groups = new LinkedHashMap<>();
        groups.put("ATHLETES", athletes);
        groups.put("EVENT:Men 3m Synchronised", events);

        String[] output = new String[3];
        Arrays.fill(output, "");

        scheduler.execute(groups, output);

        assertEquals(NETWORK_ERROR, output[0]);
        assertEquals(NETWORK_ERROR, output[1]);
        assertEquals(NETWORK_ERROR, output[2]);
    }

    @Test
    public void executeRunsThreeGroups() {
        QueryService athleteService = (target, option) -> "ATH\n";
        QueryService resultService = (target, option) -> "RES:" + target + "\n";
        TaskScheduler scheduler = new TaskScheduler(athleteService, resultService);

        TaskGroup athletes = new TaskGroup("ATHLETES");
        athletes.addTask(new Task(1, "ATHLETES", ""));

        TaskGroup eventA = new TaskGroup("EVENT:Women 10m Platform");
        eventA.addTask(new Task(2, "EVENT:Women 10m Platform", ""));

        TaskGroup eventB = new TaskGroup("EVENT:Men 3m Synchronised");
        eventB.addTask(new Task(3, "EVENT:Men 3m Synchronised", ""));

        Map<String, TaskGroup> groups = new LinkedHashMap<>();
        groups.put("ATHLETES", athletes);
        groups.put("EVENT:Women 10m Platform", eventA);
        groups.put("EVENT:Men 3m Synchronised", eventB);

        String[] output = new String[3];
        Arrays.fill(output, "");

        scheduler.execute(groups, output);

        assertEquals("ATH\n", output[0]);
        assertEquals("RES:Women 10m Platform\n", output[1]);
        assertEquals("RES:Men 3m Synchronised\n", output[2]);
    }
}
