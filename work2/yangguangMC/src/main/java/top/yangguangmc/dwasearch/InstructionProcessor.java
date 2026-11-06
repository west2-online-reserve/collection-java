package top.yangguangmc.dwasearch;

import java.util.List;

public class InstructionProcessor {
    private final DWADataSet dwaDataSet;

    public InstructionProcessor(DWADataSet dwaDataSet) {
        this.dwaDataSet = dwaDataSet;
    }

    public void process(IODataSet ioDataSet) {
        for (String command : ioDataSet.getInstructions()) {
            if (command.isBlank()) {
                continue;
            }
            if ("players".equals(command)) {
                for (Player player : dwaDataSet.players()) {
                    player.print(ioDataSet);
                    ioDataSet.nextOutputSection();
                }
            } else if (command.startsWith("result")) {
                if (command.length() == 6) {
                    ioDataSet.println("N/A").nextOutputSection();
                    continue;
                }
                if (command.charAt(6) != ' ') {
                    ioDataSet.println("Error").nextOutputSection();
                    continue;
                }
                String target = command.substring(7);
                List<Result> results = dwaDataSet.results().get(target);
                if (results == null) {
                    ioDataSet.println("N/A").nextOutputSection();
                    continue;
                }
                for (Result result : results) {
                    result.print(ioDataSet);
                    ioDataSet.nextOutputSection();
                }
            } else {
                ioDataSet.println("Error").nextOutputSection();
            }
        }
    }
}
