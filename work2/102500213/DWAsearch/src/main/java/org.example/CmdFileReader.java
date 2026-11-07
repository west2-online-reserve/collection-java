package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CmdFileReader {
    public List<String> read(Path inputFile) throws IOException {
        List<String> cmdList = new ArrayList<>();
        try (Stream<String> lineStream = Files.lines(inputFile, StandardCharsets.UTF_8)) {
            lineStream.forEach(line -> {
                if (!line.isBlank()) {
                    cmdList.add(line);
                }
            });
        }
        return cmdList;
    }
}
