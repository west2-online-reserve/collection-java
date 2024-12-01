package DWASearch;

import DWA.Athlete;
import DWA.DetailedResult;
import DWA.Result;

import java.io.File;
import java.util.ArrayList;

public interface CoreModule {

    public void displayAllPlayersInfo();

    public void displayResultsForEachEvent();

    public void displayResult(String name);

    public void displayDetailedResult(String name);

    public void displayAllDetailedResults();


    public ArrayList<Result> getResultsForEvent(String eventName);

    public ArrayList<DetailedResult> getDetailedResultsForEvent(String eventName);


    public void setData(String path);
}
