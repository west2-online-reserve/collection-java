import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

/** Command-line entry point: java -jar DWASearch.jar input.txt output.txt */
public final class DWASearch {
    private DWASearch() { }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -jar DWASearch.jar input.txt output.txt");
            System.exit(2);
        }
        try {
            CoreModule core = CoreModule.fromBundledData();
            run(Path.of(args[0]), Path.of(args[1]), core);
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("DWASearch: " + e.getMessage());
            System.exit(1);
        }
    }

    /** Stream commands to a temporary sibling, then replace the output on success. */
    static void run(Path input, Path output, CoreModule core) throws IOException {
        Path destination = output.toAbsolutePath().normalize();
        Path temporary = null;
        try {
            try (BufferedReader reader = Files.newBufferedReader(input, StandardCharsets.UTF_8)) {
                temporary = Files.createTempFile(destination.getParent(), ".dwa-", ".tmp");
                try (BufferedWriter writer = Files.newBufferedWriter(temporary, StandardCharsets.UTF_8)) {
                    String command;
                    boolean first = true;
                    while ((command = reader.readLine()) != null) {
                        if (first && command.startsWith("\uFEFF")) command = command.substring(1);
                        first = false;
                        writer.write(executeOne(command, core));
                    }
                }
            }
            Files.move(temporary, destination, StandardCopyOption.REPLACE_EXISTING);
            temporary = null;
        } finally {
            if (temporary != null) Files.deleteIfExists(temporary);
        }
    }

    static String execute(List<String> commands, CoreModule core) {
        StringBuilder output = new StringBuilder();
        for (String command : commands) output.append(executeOne(command, core));
        return output.toString();
    }

    static String executeOne(String command, CoreModule core) {
        if (command.equals("players")) return core.displayAllPlayersInfo();
        if (!command.equals("result") && !command.startsWith("result ")) {
            return Lib.block("Error");
        }
        String event = command.equals("result") ? "" : command.substring("result ".length());
        boolean detail = event.endsWith(" detail");
        if (detail) event = event.substring(0, event.length() - " detail".length());
        if (!core.supportsEvent(event)) return Lib.block("N/A");
        return core.displayResultsForEachEvent(event, detail);
    }
}
