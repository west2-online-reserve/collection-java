package main.java;

import main.java.model.Athlete;
import main.java.model.CompetitionResultRoot;

import java.util.List;

public interface OutputModule {
    void displayAllPlayersInfo(String outputFile, List<Athlete> athletes);

    void displayResultsForEachEvent(String outputFile, CompetitionResultRoot competitionResultRoot);

    void displayDetailResultsForEachEvent(String outputFile, CompetitionResultRoot competitionResultRoot);

    void displayWrongMessage(String outputFile, String wrongMessage);
}
