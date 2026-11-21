package com.dwasearch.query.util;

import com.dwasearch.model.players.Player;
import com.dwasearch.model.results.*;

import java.util.Iterator;

public class OutputFormatter {
    public static String formatPlayer(Player player) {
        String genderString = player.getGender() == 0 ? "Male" : "Female";
        return "Full Name:" + player.getLastName() + " " + player.getFirstName() + "\n"
                + "Gender:" + genderString + "\n"
                + "Country:" + player.getCountry();
    }

    public static String formatResult(Result result) {
        StringBuilder divePoints = new StringBuilder();
        for (Iterator<Dive> iterator = result.getDives().iterator(); iterator.hasNext(); ) {
            Dive dive = iterator.next();
            divePoints.append(dive.getDivePoints());
            if (iterator.hasNext()) {
                divePoints.append(" + ");
            }
        }
        return "Full Name:" + result.getFullName() + "\n"
                + "Rank:" + result.getRank() + "\n"
                + "Score:" + divePoints + " = " + result.getTotalPoints();
    }
}
