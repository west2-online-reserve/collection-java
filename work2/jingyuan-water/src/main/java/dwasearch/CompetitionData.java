package dwasearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * data.json 的根对象。Jackson 按字段名自动对应："players" -> players，"events" -> events。
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitionData {

    /** 全部选手 */
    public List<Player> players;

    /** 全部项目及其决赛成绩 */
    public List<Event> events;

    /** 单个项目。仅在此处使用，故写成静态内部类。 */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Event {

        /** 查询关键字，例如 "women 3m springboard"，必须与输入完全一致 */
        public String key;

        /** 项目官方全名，例如 "Women's 3m Springboard" */
        public String name;

        /** 决赛成绩，已按名次排列 */
        public List<EventResult> results;
    }
}
