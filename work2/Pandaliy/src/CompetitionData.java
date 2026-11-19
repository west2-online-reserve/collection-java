import java.util.List;

// data.json 最外层结构。
public record CompetitionData(
        int schemaVersion,
        Competition competition,
        List<Player> players,
        List<Event> events) {
}
