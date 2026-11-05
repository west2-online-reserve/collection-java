import java.util.List;

// 一个比赛项目及其完整成绩列表。
public record Event(
        String command,
        String eventId,
        String gender,
        String discipline,
        String eventType,
        List<Result> results) {
}
