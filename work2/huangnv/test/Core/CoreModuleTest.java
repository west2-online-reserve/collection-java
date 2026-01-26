package Core;

import Command.Command;
import Command.ErrorCommand;
import Command.PlayerCommand;
import Command.ResultCommand;
import Command.CommandParser;
import Scheduler.Task;
import Scheduler.TaskGroup;
import Scheduler.TaskScheduler;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class CoreModuleTest {
    @Test
    public void immediateOutputsBypassScheduler() {
        CommandParser parser = new CommandParser() {
            @Override
            public List<Command> parse(String inputPath) {
                List<Command> commands = new ArrayList<>();
                commands.add(new ErrorCommand(1, "bad"));
                commands.add(new ResultCommand(2, "result bad", "N/A", false));
                commands.add(new ErrorCommand(3, "bad2"));
                return commands;
            }
        };

        TaskScheduler scheduler = new TaskScheduler(null, null) {
            @Override
            public void execute(Map<String, TaskGroup> groups, String[] output) {
                fail("Scheduler should not run for immediate outputs only.");
            }
        };

        CoreModule core = new CoreModule(parser, scheduler);
        String output = core.getOutString("ignored.txt");

        String expected =
                "Error\n-----\n" +
                "N/A\n-----\n" +
                "Error\n-----\n";
        assertEquals(expected, output);
    }

    @Test
    public void buildsTasksAndMergesOutputs() {
        CommandParser parser = new CommandParser() {
            @Override
            public List<Command> parse(String inputPath) {
                List<Command> commands = new ArrayList<>();
                commands.add(new PlayerCommand(1, "players"));
                commands.add(new ResultCommand(2, "result Women 10m Platform", "Women 10m Platform", false));
                commands.add(new ResultCommand(3, "result Men 3m Synchronised detail", "Men 3m Synchronised", true));
                commands.add(new ErrorCommand(4, "bad"));
                return commands;
            }
        };

        TaskScheduler scheduler = new TaskScheduler(null, null) {
            @Override
            public void execute(Map<String, TaskGroup> groups, String[] output) {
                TaskGroup athletes = groups.get("ATHLETES");
                TaskGroup eventA = groups.get("EVENT:Women 10m Platform");
                TaskGroup eventB = groups.get("EVENT:Men 3m Synchronised");

                assertNotNull(athletes, "ATHLETES group missing");
                assertNotNull(eventA, "EVENT:Women 10m Platform group missing");
                assertNotNull(eventB, "EVENT:Men 3m Synchronised group missing");
                assertEquals(1, athletes.getTasks().size());
                assertEquals(1, eventA.getTasks().size());
                assertEquals(1, eventB.getTasks().size());
                assertEquals("", athletes.getTasks().get(0).getOption());
                assertEquals("", eventA.getTasks().get(0).getOption());
                assertEquals("detail", eventB.getTasks().get(0).getOption());

                writeGroup(athletes, output, "ATH\n");
                writeGroup(eventA, output, "RES-A\n");
                writeGroup(eventB, output, "RES-B\n");
            }

            private void writeGroup(TaskGroup group, String[] output, String value) {
                if (group == null) {
                    return;
                }
                for (Task task : group.getTasks()) {
                    output[task.getLine() - 1] = value;
                }
            }
        };

        CoreModule core = new CoreModule(parser, scheduler);
        String output = core.getOutString("ignored.txt");

        String expected =
                "ATH\n" +
                "RES-A\n" +
                "RES-B\n" +
                "Error\n-----\n";
        assertEquals(expected, output);
    }

    @Test
    public void parseIOExceptionReturnsMessage() {
        CommandParser parser = new CommandParser() {
            @Override
            public List<Command> parse(String inputPath) throws IOException {
                throw new IOException("boom");
            }
        };

        TaskScheduler scheduler = new TaskScheduler(null, null) {
            @Override
            public void execute(Map<String, TaskGroup> groups, String[] output) {
                fail("Scheduler should not run when parse fails.");
            }
        };

        CoreModule core = new CoreModule(parser, scheduler);
        String output = core.getOutString("ignored.txt");

        assertEquals("文件读取失败：boom", output);
    }
}
