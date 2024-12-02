package src;

import src.Lib.commandStringProcess;
import src.Lib.displayInformation;

import java.io.IOException;

public class DWASearch {
    public static String inputFile, outputFile;
    public static void main(String[] args){

        if(args.length!= 2){
            System.out.println("Usage: java DWASearch <input file> <output file>");
            System.exit(0);
        }

        inputFile = "src/Data/"+args[0];
        outputFile = "src/Data/"+args[1];

        commandStringProcess processCommand = new commandStringProcess();
        String[] command = commandStringProcess.commandInput(inputFile);
        for(String c: command){
            int result = processCommand.commandDistinguish(c);
            try {
                displayInformation.displayInfo(result,outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
