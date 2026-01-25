package org.example.lib;

import java.util.ArrayList;

public interface OutputModule {
    public void writeWrongInstruction();

    public void displayAllPlayersInfo(ArrayList<Country> countries) throws InterruptedException;

    public void displayResultsForEachEvent(Event event, String disciplineName) throws InterruptedException;

    public void displayResultsForEachEventDetail(Event event, String disciplineName) throws InterruptedException;
}
