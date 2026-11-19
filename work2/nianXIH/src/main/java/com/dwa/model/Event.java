// 赛事项目数据模型 —— 对应 data.json 中 events 数组的每个元素，
// 包含 command（查询命令字符串，如 "result women 3m springboard"）、
// gender（Men/Women）、discipline（3m/10m）、eventType（springboard/platform/synchronised）
// 以及 results（该项目决赛结果列表）。
// DataRepository 通过 command 字段匹配用户输入的查询命令。
package com.dwa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    private String command;
    private String eventId;
    private String gender;
    private String discipline;
    private String eventType;
    private List<ResultEntry> results;

    public Event() {}

    public String getCommand() { return command; }
    public void setCommand(String command) { this.command = command; }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDiscipline() { return discipline; }
    public void setDiscipline(String discipline) { this.discipline = discipline; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public List<ResultEntry> getResults() { return results; }
    public void setResults(List<ResultEntry> results) { this.results = results; }
}
