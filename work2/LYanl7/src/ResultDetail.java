import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  记录了项目的选手详细信息
 *
 * @author LYanl7
 * @since 2025-9-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDetail {
    private String fullName;
    private String preliminaryRank;
    private String semifinalRank;
    private String finalRank;
    private String preliminaryScore;
    private String semifinalScore;
    private String finalScore;
}
