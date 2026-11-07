package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class ResultWriterTest {
    private final ResultWriter writer = new ResultWriter();

    @Test
    void testWriteContent(@TempDir Path tempDir) throws IOException {
        Path outFile = tempDir.resolve("output.txt");
        String text = "test content 123";
        writer.write(outFile, text);

        String readBack = Files.readString(outFile);
        assertEquals(text, readBack);
    }
}
