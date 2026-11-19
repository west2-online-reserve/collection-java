import java.util.List;

// 单个项目中的一条成绩。
public record Result(
        String fullName,
        int rank,
        String countryCode,
        List<String> scores,
        String totalPoints) {
}
