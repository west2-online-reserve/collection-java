package com.org.Lskar.Swimming;

import com.org.Lskar.Swimming.utils.CoreModule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DWASearch {
    public static void main(String[] args) {
        String line;
        String inputFile=args[0];
        String outputFile=args[1];
        //String inputFile="src\\main\\java\\com\\org.Lskar.Swimming\\data\\input.txt";
        //String outputFile="src\\main\\java\\com\\org.Lskar.Swimming\\data\\output.txt";

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
            CoreModule coreModule = new CoreModule();
            while ((line = bufferedReader.readLine()) != null) {
                String result=coreModule.ProcessingCommands(line);
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
            bufferedReader.close();
            bufferedWriter.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
