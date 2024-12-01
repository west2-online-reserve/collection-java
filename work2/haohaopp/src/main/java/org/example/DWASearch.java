package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DWASearch {
    public static void main(String[] args) {
        final Pattern RE_players=Pattern.compile("^players$");
        final Pattern RE_results=Pattern.compile(("^result .*$"));
        final Pattern RE_conpetiton=Pattern.compile("^result (women 1m springboard|women 3m springboard|women 10m platform|women 3m synchronised|women 10m synchronised|men 1m springboard|men 3m springboard|men 10m platform|men 3m synchronised|men 10m synchronised)$");
        final Pattern RE_detailconpetiton=Pattern.compile("^result (women 1m springboard|women 3m springboard|women 10m platform|women 3m synchronised|women 10m synchronised|men 1m springboard|men 3m springboard|men 10m platform|men 3m synchronised|men 10m synchronised) detial$");
        String input=args[0];
        String output=args[1];
        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            String line;
            while ((line = reader.readLine()) != null) {
            Matcher playerMatcher=RE_players.matcher(line);
            Matcher resultMatcher=RE_results.matcher(line);
            Matcher conpetitonMatcher=RE_conpetiton.matcher(line);
            Matcher detailconpetitonMatcher=RE_detailconpetiton.matcher(line);
            if(playerMatcher.matches()){
            Athlete[] athletes=CoreModule.getAthlete("athletes.json");
            for(Athlete i:athletes){
                writer.write(i.toString());
                writer.newLine();
            }
            } else if (resultMatcher.matches()) {
                String path=""+line.substring(7)+".json";
                if(conpetitonMatcher.matches()){
                    Conpetiton[] conpetitons=CoreModule.getConpetition(path);
                    for(Conpetiton i:conpetitons){
                        writer.write(i.toString());
                        writer.newLine();
                    }
                } else if (detailconpetitonMatcher.matches()) {
                    ConpetitonDetial[] conpetitonDetials=CoreModule.getConpetitionDetial(path);
                    for (ConpetitonDetial i:conpetitonDetials){
                        writer.write(i.toString());
                        writer.newLine();
                    }
                }
                else{
                    writer.write("N/A\n-----");
                    writer.newLine();
                }
            }
            else {
                writer.write("Error\n-----");
                writer.newLine();
            }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
