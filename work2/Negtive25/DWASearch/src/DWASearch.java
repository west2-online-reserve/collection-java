package com.src;
import com.src.Lib.CommandStringProcess;
import com.src.Lib.DisplayInformation;

import java.io.IOException;

public class DWASearch {
    public static String inputFile, outputFile;
    public static void main(String[] args){

        if(args.length!= 2){
            System.out.println("Usage: java DWASearch <input file> <output file>");
            System.exit(0);
        }

        inputFile = "src/main/java/com/src/Data/"+args[0];
        outputFile = "src/main/java/com/src/Data/"+args[1];

        CommandStringProcess processCommand = new CommandStringProcess();
        String[] command = CommandStringProcess.commandInput(inputFile);
        for(String c: command){
            int result = processCommand.commandDistinguish(c);
            try {
                DisplayInformation.displayInfo(result,outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
