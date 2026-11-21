package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResultWriter {
    public void write(Path outputFile, String text) throws IOException {
        Files.writeString(outputFile, text, StandardCharsets.UTF_8);
    }
}
