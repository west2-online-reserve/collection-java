package com.west2.dwasearch.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class OutputWriter {
    private OutputWriter() {
    }

    public static void write(Path file, List<String> lines) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8);
        try {
            for (int i = 0; i < lines.size(); i++) {
                writer.write(lines.get(i));
                writer.write("\n");
            }
        } finally {
            writer.close();
        }
    }
}
