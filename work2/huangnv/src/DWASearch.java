import Command.CommandParser;
import Core.CoreModule;
import Scheduler.TaskScheduler;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DWASearch {
    public static void main(String[] args) {
        String inputPath = args.length > 0 ? args[0] : "stage_2/input.txt";
        String outputPath = args.length > 1 ? args[1] : "stage_2/output.txt";
        CommandParser parser = new CommandParser();
        TaskScheduler scheduler = new TaskScheduler();
        CoreModule core = new CoreModule(parser, scheduler);
        String output = core.getOutString(inputPath);
        try {
            Files.write(Paths.get(outputPath), output.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
