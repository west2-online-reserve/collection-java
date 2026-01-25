package org.example;



import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class DWASearch {
    public static void main(String[] args) {
        CoreModule coreModule = new CoreModule();
//        coreModule.displayAllPlayerInfo();
//        coreModule.diaplayResult();
//        coreModule.displayDetailResult();
        String input = args[0];
        String output = args[1];
//        List<String> lines = new ArrayList<>();
        coreModule.handle(input,output);

    }


}