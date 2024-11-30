package com.dwa.Lib;

import java.io.IOException;
import java.util.Map;

public class displayFinalResults implements OutputService {
    @Override
    public String getOutputString(String fileName) throws IOException {
        Map<String, Detail> userMap = ReadFileUtils.getUserDetailMap(fileName);
        StringBuilder builder = new StringBuilder();
        for (Detail value : userMap.values()) {
            builder.append("\n");
            builder.append("Full Name:").append("\n").append(value.getFullName()).append("\n");
            builder.append("Rank:").append(value.getRank().split("\\|")[2]).append("\n");
            builder.append("Score:").append(value.getFinalScore()).append("\n");
            builder.append("-----\n");
        }
        return builder.toString();
    }
}

