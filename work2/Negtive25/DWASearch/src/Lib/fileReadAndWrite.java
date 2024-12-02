package src.Lib;

import java.io.*;
import src.DWASearch;

public class FileReadAndWrite {
    public static String readFile(String directory) throws IOException{
        File file = new File(directory);
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        if (!file.exists()) {
            System.out.println("Input file does not exist, creating new file");
            file.createNewFile();
            return "";
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine())!= null) {
                sb.append(line).append("\n");
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        return sb.toString();
    }

    public static void writeToFile(String data,String outputFile) throws IOException{
        if (data == null)
            return;
        File file = new File(outputFile);
        try {
            if (!file.exists()) {
                System.out.println("Output file does not exist, creating new file");
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsolutePath(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(data);
            bw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}
