import java.io.*;
import java.util.Scanner;

public class DWASearch {
    public static void main(String[] args) {
        String content = null;
        if (args.length != 2) {
            System.out.println("Usage: java -jar DWASearch.jar <inputFilePath> <outputFilePath>");
            return;
        }
        String inputFilePath = "src/data/" + args[0];
        String outputFilePath = "src/data/" + args[1];
        ProcessContent.setOutputFilePath(outputFilePath);
        File inputFile = new File(inputFilePath);
        final long MAX_FILE_SIZE = 10 * 1024 * 1024;
        try {
            ProcessContent.clear();
        } catch (IOException e) {
            System.out.println("Failed to clear: " + e.getMessage());
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            if (inputFile.length() > MAX_FILE_SIZE) {
                throw new IOException("File too large to process: " + inputFile.getPath());
            }
            while ((content = reader.readLine()) != null) {
                ProcessContent.processContent(content);
            }
            reader.close();
            System.out.println("The data was successfully formatted and output to " + args[1]);
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
