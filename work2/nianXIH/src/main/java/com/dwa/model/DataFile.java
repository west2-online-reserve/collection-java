// 数据包根模型 —— 对应整个 data.json 文件的顶层 JSON 对象，
// 包含 schemaVersion（数据版本号）、competition（赛事名称/日期等信息）、
// players（所有选手列表）、events（所有赛事项目列表）。
// DataRepository 加载 JSON 时先反序列化为此对象，再通过 getPlayers() 和 getEvents() 提取数据。
package com.dwa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataFile {
    private int schemaVersion;
    private Competition competition;
    private List<Player> players;
    private List<Event> events;

    public int getSchemaVersion() { return schemaVersion; }
    public void setSchemaVersion(int schemaVersion) { this.schemaVersion = schemaVersion; }

    public Competition getCompetition() { return competition; }
    public void setCompetition(Competition competition) { this.competition = competition; }

    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Competition {
        private String id;
        private String name;
        private String from;
        private String to;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getFrom() { return from; }
        public void setFrom(String from) { this.from = from; }
        public String getTo() { return to; }
        public void setTo(String to) { this.to = to; }
    }
}
