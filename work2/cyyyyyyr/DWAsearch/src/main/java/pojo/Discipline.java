package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Discipline {

    @JsonProperty("DisciplineName")
    private String DisciplineName;

    private List<player> players;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class player{

        private String fullName;
        private String rank;
        private String score;

    }
}
