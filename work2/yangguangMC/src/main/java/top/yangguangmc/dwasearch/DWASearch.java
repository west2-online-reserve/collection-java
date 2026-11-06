package top.yangguangmc.dwasearch;

import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.exc.JsonNodeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class DWASearch {
    private final Path input;
    private final Path output;
    private DWADataSet dwaDataSet;

    public DWASearch(Path input, Path output) throws FileNotFoundException, FileNotWritableException {
        if (!Files.isReadable(input) || !Files.isRegularFile(input)) {
            throw new FileNotFoundException("Input file %s does not exist or isn't readable.%n".formatted(input));
        }
        if (Files.exists(output) && (!Files.isWritable(output) || !Files.isRegularFile(output))) {
            throw new FileNotWritableException("Output file %s is not writable.%n".formatted(output));
        }
        this.input = input;
        this.output = output;
    }

    public static void main(String[] args) {
        int statusCode;
        try {
            statusCode = run(args, System.out, System.err);
        } catch (Throwable t) {
            statusCode = -1;
            t.printStackTrace(System.err);
        }
        if (statusCode != 0) {
            System.exit(statusCode);
        }
    }

    public static int run(String[] args, PrintStream out, PrintStream err) {
        if (args.length != 2) {
            err.printf(
                    "Wrong number of arguments. Required 2 (1: input file, 2: output file) but got %d.%n",
                    args.length
            );
            return 1;
        }
        DWASearch instance;
        try {
            instance = new DWASearch(Paths.get(args[0]), Paths.get(args[1]));
        } catch (FileNotFoundException | FileNotWritableException e) {
            err.printf(e.getMessage());
            e.printStackTrace(err);
            return 1;
        }

        try {
            instance.prepareData();
        } catch (FileNotFoundException e) {
            err.println("Resource not found: " + e.getMessage());
            return 1;
        } catch (DWADataSet.DataFormatException e) {
            err.println("A falt was found in the resource JSON: " + e.getMessage());
            return 1;
        } catch (StreamReadException e) {
            err.println("Data JSON syntax is invalid! Tree location: " + e.getLocation());
            e.printStackTrace(err);
            return 1;
        } catch (JsonNodeException e) {
            err.println("JSON node has invalid type! Node: " + e.getNode());
            e.printStackTrace(err);
            return 1;
        } catch (Exception e) {
            err.println("Failed to prepare data!");
            e.printStackTrace(err);
            return 1;
        }

        try {
            instance.processInstructions();
            out.println("Query completed.");
            return 0;
        } catch (Exception e) {
            err.printf("Exception while handling instructions! The output might be incomplete. Input file: %s, output file: %s%n", Paths.get(args[0]), Paths.get(args[1]));
            e.printStackTrace(err);
            return 1;
        }
    }

    public void prepareData() throws IOException {
        dwaDataSet = DWADataSet.load(DWADataSet.class::getResourceAsStream);
    }

    public void processInstructions() throws IOException {
        Files.createDirectories(output.getParent());
        try (Stream<String> lines = Files.lines(input, StandardCharsets.UTF_8);
             Writer out = Files.newBufferedWriter(output, StandardCharsets.UTF_8)) {
            IODataSet set = new IODataSet();
            lines.forEach(set::addInstruction);
            InstructionProcessor processor = new InstructionProcessor(dwaDataSet);
            processor.process(set);
            out.write(set.getOutputs());
            out.flush();
        }
    }

    public static class FileNotWritableException extends IOException {
        public FileNotWritableException(String message) {
            super(message);
        }
    }
}
