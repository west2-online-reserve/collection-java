package org.Lskar.Swimming.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.Lskar.Swimming.utils.OutputData;
import com.google.gson.Gson;
import org.Lskar.Swimming.lib.Player;



public class CoreModule {
    private static final String DATAPATH="C:\\Users\\Lenovo\\Desktop\\Swimming\\src\\main\\java\\org.Lskar.Swimming\\data";
    private static final String PLAYERPATH=DATAPATH+"\\players.json";
    //获取运动员数据，Core内部使用
    private List<Player> getAllPlayers() {
        List<Player> players=new ArrayList<Player>();
        try{
            FileReader fileReader=new FileReader(PLAYERPATH);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            Gson gson=new Gson();
            Player [] playersArray=gson.fromJson(bufferedReader, Player[].class);
            players= Arrays.asList(playersArray);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return players;
    }
    //处理命令
    public String  ProcessingCommands(String command) {
        String lowerCommand=command.toLowerCase();
        OutputData outputData=new OutputData();
        if(lowerCommand.equals("player")){
            if(command.equals(lowerCommand)){
                try{
                    List<Player> players=getAllPlayers();
                    players.sort(Comparator.comparing((Player p)->p.getCountryName()).thenComparing(p->p.getPreferredLastName()));
                    return outputData.outputPlayersData(players);
                }
                catch(Exception e){

                }
            }
            //处理错误情况
            else return "Error";

        }

        return "Error";
    }

}
