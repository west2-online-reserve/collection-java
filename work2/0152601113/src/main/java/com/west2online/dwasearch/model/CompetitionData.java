package com.west2online.dwasearch.model;

import java.util.List;
import java.util.Map;

/**
 * data.json 的顶层结构，也就是抓取脚本最终写出的那份「数据快照」。
 *
 * 形如：
 * {
 *   "players": [ { Athlete }, ... ],
 *   "results": {
 *     "women 3m springboard": [ { Contest }, ... ],
 *     "women 10m platform":  [ ... ],
 *     ... 共 8 个项目 ...
 *   }
 * }
 *
 * 查询程序只读这个文件，不访问网络。
 */
public class CompetitionData {

    private List<Athlete> players;               // 全部选手
    private Map<String, List<Contest>> results;  // 项目关键字 -> 该项目决赛结果

    public CompetitionData() {
    }

    public CompetitionData(List<Athlete> players, Map<String, List<Contest>> results) {
        this.players = players;
        this.results = results;
    }

    public List<Athlete> getPlayers() {
        return players;
    }

    public void setPlayers(List<Athlete> players) {
        this.players = players;
    }

    public Map<String, List<Contest>> getResults() {
        return results;
    }

    public void setResults(Map<String, List<Contest>> results) {
        this.results = results;
    }
}
