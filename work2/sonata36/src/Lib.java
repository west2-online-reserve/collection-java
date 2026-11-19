import java.util.List;

/** Formatting helpers shared by the console and Core module. */
public final class Lib {
    private Lib() { }

    public static String block(String... fields) {
        StringBuilder result = new StringBuilder();
        for (String field : fields) result.append(field).append('\n');
        return result.append("-----\n").toString();
    }

    public static String score(String dives, String total) {
        return dives + " = " + total;
    }

    public static String rank(String preliminary, String semifinal, String fin) {
        return String.join(" | ", List.of(preliminary, semifinal, fin));
    }
}
