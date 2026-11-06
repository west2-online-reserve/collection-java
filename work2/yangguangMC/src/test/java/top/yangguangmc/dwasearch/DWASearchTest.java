package top.yangguangmc.dwasearch;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DWASearchTest {
    @Test
    void entryPointArgs() {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ByteArrayOutputStream errBuffer = new ByteArrayOutputStream();
        try (PrintStream out = new PrintStream(outBuffer);
             PrintStream err = new PrintStream(errBuffer)) {
            assertEquals(1, DWASearch.run(new String[1], out, err));
            err.flush();
            assertTrue(errBuffer.toString().startsWith("Wrong number of arguments. Required 2 (1: input file, 2: output file) but got 1."));
            errBuffer.reset();
            assertEquals(1, DWASearch.run(new String[3], out, err));
            err.flush();
            assertTrue(errBuffer.toString().startsWith("Wrong number of arguments. Required 2 (1: input file, 2: output file) but got 3."));
            errBuffer.reset();
            byte[] randomFileNameBytes = new byte[10];
            new Random().nextBytes(randomFileNameBytes);
            String fileName = "AFileThatDoesntExist-" + Base64.getUrlEncoder().encodeToString(randomFileNameBytes) + ".txt";
            assertEquals(1, DWASearch.run(new String[]{fileName, "output.txt"}, out, err));
            err.flush();
            assertTrue(errBuffer.toString().startsWith("Input file " + fileName + " does not exist or isn't readable."));
            err.flush();
            out.flush();
        }
    }

    @Test
    void entryPointWithEmptyInstructionsFile() throws IOException {
        Path input = Files.createTempFile("dwasearch-temp-input", "txt");
        Path output = Files.createTempFile("dwasearch-temp-output", "txt");
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ByteArrayOutputStream errBuffer = new ByteArrayOutputStream();
        try (PrintStream out = new PrintStream(outBuffer);
             PrintStream err = new PrintStream(errBuffer)) {
            assertEquals(0, DWASearch.run(new String[]{input.toAbsolutePath().toString(), output.toAbsolutePath().toString()}, out, err));
            err.flush();
            assertTrue(errBuffer.toString().isEmpty());
            out.flush();
            assertEquals("Query completed." + System.lineSeparator(), outBuffer.toString());
        }
        assertTrue(Files.readString(output, StandardCharsets.UTF_8).isEmpty());
        Files.delete(input);
        Files.delete(output);
    }

    @Test
    void entryPointWithDirectoriesOutputInsteadOfFile() throws IOException {
        Path input = Files.createTempFile("dwasearch-temp-input", "txt");
        Path output = Files.createTempDirectory("dwasearch-temp-output-dir");
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ByteArrayOutputStream errBuffer = new ByteArrayOutputStream();
        try (PrintStream out = new PrintStream(outBuffer);
             PrintStream err = new PrintStream(errBuffer)) {
            assertEquals(1, DWASearch.run(new String[]{input.toAbsolutePath().toString(), output.toAbsolutePath().toString()}, out, err));
            err.flush();
            assertTrue(errBuffer.toString().startsWith("Output file %s is not writable.%n".formatted(output.toAbsolutePath().toString())));
            out.flush();
            assertTrue(outBuffer.toString().isEmpty());
        }
        Files.delete(input);
        Files.delete(output);
    }

    @Test
    void entryPointWithDirectoriesInputAsInstructionsFile() throws IOException {
        Path input = Files.createTempDirectory("dwasearch-temp-input");
        Path output = Files.createTempFile("dwasearch-temp-output", "txt");
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ByteArrayOutputStream errBuffer = new ByteArrayOutputStream();
        try (PrintStream out = new PrintStream(outBuffer);
             PrintStream err = new PrintStream(errBuffer)) {
            assertEquals(1, DWASearch.run(new String[]{input.toAbsolutePath().toString(), output.toAbsolutePath().toString()}, out, err));
            err.flush();
            assertTrue(errBuffer.toString().startsWith("Input file %s does not exist or isn't readable.".formatted(input.toAbsolutePath().toString())));
            out.flush();
            assertTrue(outBuffer.toString().isEmpty());
        }
        assertTrue(Files.readString(output, StandardCharsets.UTF_8).isEmpty());
        Files.delete(input);
        Files.delete(output);
    }
}