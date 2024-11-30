package com.dwa.Lib;


import java.io.IOException;
import java.util.List;

public class PlayerService implements OutputService {
    @Override
    public String getOutputString(String fileName) throws IOException {
        // 输出选手信息
        List<Player> playerList = ReadFileUtils.toPlayerList(fileName);

        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (Player player : playerList) {
            builder.append("Full Name:").append(player.getFullName()).append("\n");
            builder.append("Gender:").append(player.getGender()).append("\n");
            builder.append("Country:").append(player.getCountryName()).append("\n");
            builder.append("-----\n");
        }
        return builder.toString();
    }


}
