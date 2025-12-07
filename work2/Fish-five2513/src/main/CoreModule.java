package main;

import java.io.BufferedWriter;
import java.io.IOException;

public class CoreModule {
    private AthleteManager athleteManager;
    private CompetitionManager competitionManager;
    private ResultManager resultManager;
    private DetailedResultManager detailedResultManager;

    public CoreModule() {
        this.athleteManager = new AthleteManager();
        this.competitionManager = new CompetitionManager(athleteManager);
        this.resultManager = new ResultManager(athleteManager, competitionManager);
        this.detailedResultManager = new DetailedResultManager(athleteManager, competitionManager);
    }

    public void initializeData() throws IOException {
        // 加载运动员数据
        athleteManager.loadAthletesData("data/Athletes.json");

        // 加载比赛数据
        competitionManager.loadCompetitionData("./competitions");
    }

    public void displayAllPlayers(BufferedWriter writer) throws IOException {
        resultManager.displayAllPlayers(writer);
    }

    public void displayFinalResults(BufferedWriter writer, String eventName) throws IOException {
        resultManager.displayFinalResults(writer, eventName);
    }

    public void displayDetailedResults(BufferedWriter writer, String eventName) throws IOException {
        detailedResultManager.displayDetailedResults(writer, eventName);
    }
}
