package src.Lib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static src.Lib.CreateAthleteList.createPlayerlist;

public class displayInformation {
    public static void displayInfo(int result,String outputFile) throws IOException {
        if(result == -1)
            fileReadAndWrite.writeToFile("Error"+"\n"+"-----"+"\n",outputFile);
        else if(result == 1)
            fileReadAndWrite.writeToFile("N/A"+"\n"+"-----"+"\n",outputFile);
        else if(result == 10)
            displayInformation.displayAllAthletes(outputFile);
        else if(result == 21||result == 121)
            displayInformation.displaySingleMatch("women 1m springboard.txt",result,outputFile);
        else if(result == 22||result == 122)
            displayInformation.displaySingleMatch("women 3m springboard.txt",result,outputFile);
        else if(result == 23||result == 123)
            displayInformation.displaySingleMatch("women 10m platform.txt",result,outputFile);
        else if(result == 24||result == 124)
            displayInformation.displaySingleMatch("women 3m synchronised.txt",result,outputFile);
        else if(result == 25||result == 125)
            displayInformation.displaySingleMatch("women 10m synchronised.txt",result,outputFile);
        else if(result == 31||result == 131)
            displayInformation.displaySingleMatch("men 1m springboard.txt",result,outputFile);
        else if(result == 32||result == 132)
            displayInformation.displaySingleMatch("men 3m springboard.txt",result,outputFile);
        else if(result == 33||result == 133)
            displayInformation.displaySingleMatch("men 10m platform.txt",result,outputFile);
        else if(result == 34||result == 134)
            displayInformation.displaySingleMatch("men 3m synchronised.txt",result,outputFile);
        else if(result == 35||result == 135)
            displayInformation.displaySingleMatch("men 10m synchronised.txt",result,outputFile);
    }

    public static void displayAllAthletes(String outputFile) throws IOException {
        athlete athlete = new athlete();
        List<athlete> allAthleteList=new ArrayList<>();

        String allAthlete = fileReadAndWrite.readFile("src/Data/athleteInformation/allAthlete.txt");
        String[] allAthleteArray = allAthlete.split("\n+");

        for (String data : allAthleteArray) {
            if(data.startsWith("Country")){
                athlete.setCountry(data);
                continue;
            }
            if(data.startsWith("Gender")){
                athlete.setGender(data);
            }
            if(data.startsWith("Full Name")){
                athlete.setFullName(data);
                allAthleteList.add(new athlete(athlete.getFullName(),athlete.getGender(),athlete.getCountryName()));
            }
        }

        allAthleteList.sort(Comparator.comparing(athlete::getCountryName).thenComparing(athlete::getLastName));

        for (athlete player : allAthleteList) {
            fileReadAndWrite.writeToFile(player.allAthleteToString(),outputFile);
        }
    }

    //如果inDetail大于100，则显示详细信息，否则只粗略显示
    public static void displaySingleMatch(String matchName,int greaterOneHundredToBeInDetail,String outputFile) throws IOException {
        List<athlete> playerList = createPlayerlist(matchName);

        if(matchName.contains("synchronised")){
            for(int i=0;i<playerList.size();i++)
                playerList.get(i).processNameForSynchronisedMatch();
        }

        if(greaterOneHundredToBeInDetail>100){
            playerList.sort(Comparator.comparing(athlete::getFirstMatchRank));
            for (athlete athlete : playerList) {
                fileReadAndWrite.writeToFile(athlete.MatchToStringInDetail(),outputFile);
            }
        }
        if(greaterOneHundredToBeInDetail<100){
            playerList.removeIf(athlete -> athlete.getFinalRank().equals("*"));
            playerList.sort(Comparator.comparing(athlete::getFinalRankForSort));
            for (athlete athlete : playerList) {
                fileReadAndWrite.writeToFile(athlete.MatchToString(),outputFile);
            }
        }
    }
}
