package com.dwasearch.query.util;

import com.dwasearch.model.DataSnapshot;
import com.dwasearch.model.players.*;
import com.dwasearch.model.results.*;
import com.dwasearch.query.exception.InvalidDataException;

import java.util.HashSet;
import java.util.List;


public class SnapshotValidator {
    private static final int COUNTRY_TOTAL_NUM = 16;
    private static final int COMPETITION_TOTAL_NUM = 9;
    private static final List<String> expectedDisciplineNames = List.of(
            "Women's 3m Springboard",
            "Women's 10m Platform",
            "Women's 3m Synchronised",
            "Women's 10m Synchronised",
            "Men's 3m Springboard",
            "Men's 10m Platform",
            "Men's 3m Synchronised",
            "Men's 10m Synchronised",
            "Mixed 3m & 10m Team"
    );

    public void validate(DataSnapshot snapshot) throws InvalidDataException {
        if (snapshot == null) {
            throw new InvalidDataException("DataSnapshot is null");
        }
        validateCountries(snapshot);
        validateCompetitions(snapshot);
    }

    //检查players的相关数据是否有缺失
    void validateCountries(DataSnapshot snapshot) throws InvalidDataException {
        List<Country> countries = snapshot.getCountries();

        //检查国家数量是否正确
        if (countries.size() != COUNTRY_TOTAL_NUM) {
            throw new InvalidDataException("Country count is incorrect");
        }
        for (Country country : countries) {
            List<Player> players = country.getPlayers();
            if (players.isEmpty()) {
                throw new InvalidDataException("Exists country which is empty");
            }
            else {
                //逐个检查运动员的字段信息是否丢失
                for (Player player : players) {
                    if (player.getCountry() == null
                            || player.getFirstName() == null
                            || player.getLastName() == null
                            || player.getGender() == null) {
                        throw new InvalidDataException("Exists missing players' fields");
                    }
                }
            }
        }
    }

    //检查competitions的相关数据是否有缺失
    void validateCompetitions(DataSnapshot snapshot) throws InvalidDataException {
        List<Competition> competitions = snapshot.getCompetitions();

        //检查比赛数量是否正确
        if (competitions.size() != COMPETITION_TOTAL_NUM) {
            throw new InvalidDataException("Competition count is incorrect");
        }

        //统计比赛名称，检查是否有误
        HashSet<String> disciplineNames = new HashSet<>();
        for (Competition competition : competitions) {
            if (competition.getDisciplineName() == null) {
                throw new InvalidDataException("Exists competition which has null discipline name");
            }
            else {
                disciplineNames.add(competition.getDisciplineName()); //添加比赛名称
                //逐层检查
                List<Heat> heats = competition.getHeats();
                if (heats.isEmpty()) {
                    throw new InvalidDataException("Exists competition which has empty heats");
                }
                else {
                    boolean containsFinals = false; //标记决赛数据是否有找到
                    for (Heat heat : heats) {
                        if (heat.getPhaseName() == null){
                            throw new InvalidDataException("Exists competition's heat which has null phase name");
                        }
                        else if (heat.getPhaseName().equals("Finals")) {
                            containsFinals = true;
                            List<Result> results = heat.getResults();
                            if (results.isEmpty()) {
                                throw new InvalidDataException("Exists competition's heat which has empty results");
                            }
                            else {
                                for (Result result : results) {
                                    if (result.getFullName() == null) {
                                        throw new InvalidDataException("Exists result which has null fullName");
                                    }
                                    else if (result.getCountry() == null) {
                                        throw new InvalidDataException("Exists result which has null country");
                                    }
                                    else if (result.getTotalPoints() == null) {
                                        throw new InvalidDataException("Exists result which has empty totalPoints");
                                    }
                                    else if (result.getRank() == null) {
                                        throw new InvalidDataException("Exists result which has null rank");
                                    }
                                    else {
                                        List<Dive> dives = result.getDives();
                                        if (dives.isEmpty()){
                                            throw new InvalidDataException("Exists result which has empty dives");
                                        }
                                        else {
                                            for (Dive dive : dives) {
                                                if (dive.getDivePoints() == null || dive.getDivePoints().isEmpty()) {
                                                    throw new InvalidDataException("Exists dive which has empty divePoints");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!containsFinals) {
                        throw new InvalidDataException(competition.getDisciplineName() + " does not contain \"Finals\"");
                    }
                }
            }
        }
        if (!disciplineNames.containsAll(expectedDisciplineNames)) {
            throw new InvalidDataException("Exists missing disciplineName");
        }
    }
}
