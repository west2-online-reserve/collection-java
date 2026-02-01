package Core;

import Command.Command;
import Command.CommandParser;
import Command.PlayerCommand;
import Command.ResultCommand;
import Scheduler.DependencyAnalyzer;
import Scheduler.Task;
import Scheduler.TaskGroup;
import Scheduler.TaskScheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CoreModule {
    private final CommandParser commandParser;
    private final TaskScheduler scheduler;

    public CoreModule(CommandParser commandParser, TaskScheduler scheduler) {
        this.commandParser = commandParser;
        this.scheduler = scheduler;
    }

    public String getOutString(String inputPath) {
        String[] outStringarray = null;
        List<Command> commands = null;
        try {
             commands = commandParser.parse(inputPath);
            outStringarray = new String[commands.size()];
        } catch (IOException e) {
            e.printStackTrace();
            return "文件读取失败：" + e.getMessage();
        }
        Arrays.fill(outStringarray, "");
        fillImmediateOutputs(commands, outStringarray);
        List<Task> tasks = buildTasks(commands);
        if (!tasks.isEmpty()) {
            DependencyAnalyzer analyzer = new DependencyAnalyzer();
            Map<String, TaskGroup> groups = analyzer.analyze(tasks);
            scheduler.execute(groups, outStringarray);
        }
        return mergeOutputs(outStringarray);
    }

    private void fillImmediateOutputs(List<Command> commands, String[] outStringarray) {
        for (Command cmd : commands) {
            String output = cmd.immediateOutput();
            if (output == null) {
                continue;
            }
            outStringarray[cmd.getLine() - 1] = output;
        }
    }

    private List<Task> buildTasks(List<Command> commands) {
        List<Task> tasks = new ArrayList<>();
        for (Command cmd : commands) {
            if (cmd.immediateOutput() != null) {
                continue;
            }
            if (cmd instanceof PlayerCommand) {
                tasks.add(new Task(cmd.getLine(), "ATHLETES", ""));
            } else if (cmd instanceof ResultCommand) {
                ResultCommand rc = (ResultCommand) cmd;
                String key = "EVENT:" + rc.getProject();
                String option = rc.isDetail() ? "detail" : "";
                tasks.add(new Task(cmd.getLine(), key, option));
            }
        }
        return tasks;
    }

    private String mergeOutputs(String[] outStringarray) {
        StringBuilder sb = new StringBuilder();
        for (String output : outStringarray) {
            sb.append(output);
        }
        return sb.toString();
    }
}

