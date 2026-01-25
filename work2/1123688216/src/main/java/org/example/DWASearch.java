package org.example;

import java.io.*;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class DWASearch {
    public static void main(String[] args) {
        File inputFile = new File(args[0]);//input.txt
        File outputFile = new File(args[1]);//output.txt

        //读取input.txt文件内容
        try(
                Scanner scanner = new Scanner(inputFile,"UTF-8");
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)),true)
        ){
            while (scanner.hasNextLine()){
                String command = scanner.nextLine();
                CoreModule coreModule = new CoreModule();
                String[]  judgmentResult = coreModule.judgmentFormat(command);
                if(judgmentResult[0].equals("players")){
                    coreModule.dispalyAllPlayersInfo(writer);
                }
                else if(judgmentResult[0].equals("N/A")){
                    writer.println("N/A");
                    writer.println("-----");
                }
                else if(judgmentResult[0].equals("Error")){
                    writer.println("Error");
                    writer.println("-----");
                }else{
                    coreModule.displayResultForEachEvent(judgmentResult[0],judgmentResult[1].equals("true"),writer);
                }
            }

        }catch (IOException e){
            System.out.println("Error:"+e.getMessage());
        }
    }


}