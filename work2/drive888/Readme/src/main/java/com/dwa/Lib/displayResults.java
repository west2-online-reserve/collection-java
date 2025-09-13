package com.dwa.Lib;


import java.io.IOException;
import java.util.Map;

public class displayResults implements OutputService {
    @Override
    public String getOutputString(String fileName) throws IOException {
        Map<String, Detail> userMap = ReadFileUtils.getUserDetailMap(fileName);
        StringBuilder builder = new StringBuilder();
        for (Detail value : userMap.values()) {
            builder.append("\n");
            builder.append("Full Name:").append(value.getFullName()).append("\n");
            builder.append("Rank:").append(value.getRank()).append("\n");
            builder.append("Preliminary Score:").append(value.getPreliminaryScore()).append("\n");
            builder.append("Semifinal Score:").append(value.getSemifinalScore()).append("\n");
            builder.append("Final Score:").append(value.getFinalScore()).append("\n");
            builder.append("-----\n");
        }
        return builder.toString();

    }
}

