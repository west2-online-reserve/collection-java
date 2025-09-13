package utils;

import java.io.*;

public class Utility {
    private static String output = "";
    private static String input = "";
    public static BufferedWriter bw;
    public static BufferedReader br;

    public static String readLine() throws IOException {
        return br.readLine();
    }
    public static void initBufferedWriter() {
        try {
            bw = new BufferedWriter(new FileWriter("output.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void write(String s) throws IOException {
        if (bw != null) {
            try {
                bw.write(s);
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void close() throws IOException {
        br.close();
        bw.close();
    }

    public static void setOutput(String output) throws IOException {
        Utility.output = output;
        bw = new BufferedWriter(new FileWriter(output));
    }

    public static void setInput(String input) throws FileNotFoundException {
        Utility.input = input;
        br = new BufferedReader(new FileReader(input));
    }
}
