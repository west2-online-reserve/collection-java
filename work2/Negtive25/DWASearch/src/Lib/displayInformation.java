package src.Lib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static src.Lib.createAthleteList.createPlayerlist;

public class displayInformation {
    public static void displayInfo(int result) throws IOException {
        if(result == -1)
            fileReadAndWrite.writeToFile("Error"+"\n"+"-----"+"\n");
        else if(result == 1)
            fileReadAndWrite.writeToFile("N/A"+"\n"+"-----"+"\n");
        else if(result == 10)
            displayInformation.displayAllAthletes();
        else if(result == 21||result == 121)
            displayInformation.displaySingleMatch("women 1m springboard.txt",result);
        else if(result == 22||result == 122)
            displayInformation.displaySingleMatch("women 3m springboard.txt",result);
        else if(result == 23||result == 123)
            displayInformation.displaySingleMatch("women 10m springboard.txt",result);
        else if(result == 24||result == 124)
            displayInformation.displaySingleMatch("women 3m synchronised.txt",result);
        else if(result == 25||result == 125)
            displayInformation.displaySingleMatch("women 10m synchronised.txt",result);
        else if(result == 31||result == 131)
            displayInformation.displaySingleMatch("men 1m springboard.txt",result);
        else if(result == 32||result == 132)
            displayInformation.displaySingleMatch("men 3m springboard.txt",result);
        else if(result == 33||result == 133)
            displayInformation.displaySingleMatch("men 10m springboard.txt",result);
        else if(result == 34||result == 134)
            displayInformation.displaySingleMatch("men 3m synchronised.txt",result);
        else if(result == 35||result == 135)
            displayInformation.displaySingleMatch("men 10m synchronised.txt",result);
    }

    public static void displayAllAthletes() throws IOException {
        athlete athlete = new athlete();
        List<athlete> allAthleteList=new ArrayList<>();

        String allAthlete = fileReadAndWrite.readFile("com/src/Data/athleteInformation/allAthlete.txt");
        String[] allAthleteArray = allAthlete.split("\n+");

        for (String data : allAthleteArray) {
            if(data.startsWith("Country")){
                athlete.country=data;
                continue;
            }
            if(data.startsWith("Gender")){
                athlete.gender=data;
            }
            if(data.startsWith("Full Name")){
                athlete.fullName=data;
                allAthleteList.add(new athlete(athlete.fullName,athlete.gender,athlete.country));
            }
        }

        allAthleteList.sort(Comparator.comparing(src.Lib.athlete::getCountryName).thenComparing(src.Lib.athlete::getLastName));

        for (src.Lib.athlete player : allAthleteList) {
            fileReadAndWrite.writeToFile(player.allAthleteToString());
        }
    }

    //如果inDetail大于100，则显示详细信息，否则只粗略显示
    public static void displaySingleMatch(String matchName,int greaterOneHundredToBeInDetail) throws IOException {
        List<athlete> playerList = createPlayerlist(matchName);

        if(matchName.contains("synchronised")){
            for(int i=0;i<playerList.size();i++)
                playerList.get(i).processNameForSynchronisedMatch();
        }

        if(greaterOneHundredToBeInDetail>100){
            playerList.sort(Comparator.comparing(athlete::getFirstMatchRank));
            for (athlete athlete : playerList) {
                fileReadAndWrite.writeToFile(athlete.MatchToStringInDetail());
            }
        }
        if(greaterOneHundredToBeInDetail<100){
            playerList.removeIf(athlete -> athlete.finalRank.equals("*"));
            playerList.sort(Comparator.comparing(athlete::getFinalRank));
            for (athlete athlete : playerList) {
                fileReadAndWrite.writeToFile(athlete.MatchToString());
            }
        }
    }
}
