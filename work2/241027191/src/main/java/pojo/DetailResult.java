package pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailResult {
    String fullName;
    String preliminaryRank = "*";
    String semifinalRank = "*";
    String finalRank = "*";
    String preliminaryScore = "*";
    String semifinalScore = "*";
    String finalScore = "*";
}
