package org.Lskar.Swimming;

import org.Lskar.Swimming.utils.CoreModule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class DWSearch {
    public static void main(String[] args) {
        String line;
        //String inputFile=args[0];
        //String outputFile=args[1];
        Scanner sc = new Scanner(System.in);
        String inputFile = sc.nextLine();
        String outputFile = sc.nextLine();


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
