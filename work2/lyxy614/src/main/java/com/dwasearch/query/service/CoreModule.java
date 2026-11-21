package com.dwasearch.query.service;

import com.dwasearch.model.DataSnapshot;
import com.dwasearch.model.players.*;
import com.dwasearch.model.results.*;
import com.dwasearch.query.util.OutputFormatter;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CoreModule {
    private final DataSnapshot dataSnapshot;

    public CoreModule(DataSnapshot dataSnapshot) {
        this.dataSnapshot = dataSnapshot;
    }

    public void outputPlayers(Writer writer) throws IOException {
        //获取所有player的信息并将所有player添加到单独的ArrayList中，便于排序输出
        List<Player> playerList = new ArrayList<>();

        for (Country country : dataSnapshot.getCountries()){
            playerList.addAll(country.getPlayers());
        }

        //按照国籍升序、lastName升序排序，国籍优先
        Comparator<Player> comparator = Comparator.comparing(Player::getCountry)
                        .thenComparing(Player::getLastName);
        playerList.sort(comparator);

        for (Player player : playerList) {
            String content = OutputFormatter.formatPlayer(player) + "\n" + "-----\n";
            writer.write(content);
        }
    }

    public void outputResults(String disciplineName, Writer writer) throws IOException {
        List<Result> results = new ArrayList<>();
        //查找比赛结果
        for (Competition competition : dataSnapshot.getCompetitions()){
            if (competition.getDisciplineName().equals(disciplineName)){
                for (Heat heat : competition.getHeats()){
                    if (heat.getPhaseName().equals("Finals")){
                        results.addAll(heat.getResults());
                        break;
                    }
                }
                break;
            }
        }

        //按照rank升序排序(同一比赛中rank不可能相同)
        results.sort(Comparator.comparing(Result::getRank));

        for (Result result : results) {
            String content = OutputFormatter.formatResult(result) + "\n" + "-----\n";
            writer.write(content);
        }
    }

    public void outputWrongMessages(String wrongMessage, Writer writer) throws IOException {
        String content = wrongMessage + "\n" + "-----\n";
        writer.write(content);
    }
}
