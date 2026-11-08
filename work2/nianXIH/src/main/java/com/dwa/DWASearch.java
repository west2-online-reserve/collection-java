package com.dwa;

import com.dwa.core.CoreModule;
import com.dwa.core.DataRepository;

import java.io.IOException;

public class DWASearch {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -jar DWASearch.jar <input.txt> <output.txt>");
            System.exit(1);
        }

        String inputPath = args[0];
        String outputPath = args[1];

        try {
            DataRepository repository = new DataRepository();
            CoreModule core = new CoreModule(repository);
            core.processFile(inputPath, outputPath);
            System.out.println("Done. Output written to " + outputPath);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
