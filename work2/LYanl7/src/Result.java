import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 比赛的结果类， 用于存储比赛的结果信息
 *
 * @author LYanl7
 * @since 2025-9-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @JsonProperty("FullName")
    private String fullName;
    @JsonProperty("PhaseName")
    private String phaseName;
    @JsonProperty("Rank")
    private int rank;
    @JsonProperty("TotalScore")
    private String totalScore;
    @JsonProperty("Scores")
    private String[] scores;
    @JsonProperty("PersonId")
    private String personId;
}
