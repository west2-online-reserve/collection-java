package DWA_Search;

import java.io.*;

public class IOstream {
    private static String input = "";
    private static String output = "";
    public static BufferedReader br;
    public static BufferedWriter bw;


    public static String readLine() throws IOException {
        return br.readLine();
    }

    public static void close() throws IOException {
        br.close();
        bw.close();
    }

    public static void write(String file) throws IOException {
        bw.write(file);
        bw.newLine();
    }



    public static void setOutput(String output) throws IOException {
        IOstream.output = output;
        bw = new BufferedWriter(new FileWriter(output));
    }

    public static void setInput(String input) throws FileNotFoundException {
        IOstream.input = input;
        br = new BufferedReader(new FileReader(input));
    }
}
