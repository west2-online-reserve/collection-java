import java.util.ArrayList;
import java.util.List;


//  数据访问层：从 data.json 全文按命令取出选手列表 / 某个项目的决赛结果
//
//  解析策略：手写 indexOf + substring，但先按 {} 圈定每条记录的边界
//  再在边界内取字段。这样字段缺失时 indexOf 只会返回 -1 而不会串到
//  下一条记录上，从而能明确失败

public class DataLoader {

    private static final String Q = "\"";

    // players

    static List<Player> loadPlayers(String content) {
        int playersKey = content.indexOf(Q + "players" + Q);
        if (playersKey == -1)
            throw new IllegalArgumentException("players key not found in data");

        int eventsKey = content.indexOf(Q + "events" + Q);
        if (eventsKey == -1)
            throw new IllegalArgumentException("events key not found in data");

        int arrStart = content.indexOf('[', playersKey);
        if (arrStart == -1 || arrStart > eventsKey)
            throw new IllegalArgumentException("players array not found");

        int arrEnd = findArrayEnd(content, arrStart);
        if (arrEnd == -1)
            throw new IllegalArgumentException("players array not closed");

        List<Player> list = new ArrayList<>();
        int pos = content.indexOf('{', arrStart);
        while (pos >= 0 && pos < arrEnd) {
            int end = findObjectEnd(content, pos);
            if (end < 0)
                throw new IllegalArgumentException("player object not closed");
            String obj = content.substring(pos, end);

            // 边界内取字段：缺失即抛异常，不会串到下一个人
            String fullName = stringValueOf(obj, "fullName");
            String gender = stringValueOf(obj, "gender");
            String country = stringValueOf(obj, "country");

            list.add(new Player(fullName, gender, country));
            pos = content.indexOf('{', end);
        }
        return list;
    }

    // results

    static List<String[]> loadResults(String dataContent, String command) {
        List<String[]> list = new ArrayList<>();

        int eventsKey = dataContent.indexOf(Q + "events" + Q);
        if (eventsKey == -1)
            throw new IllegalArgumentException("events key not found in data");

        int arrStart = dataContent.indexOf('[', eventsKey);
        if (arrStart == -1)
            throw new IllegalArgumentException("events array not found");

        int arrEnd = findArrayEnd(dataContent, arrStart);
        if (arrEnd == -1)
            throw new IllegalArgumentException("events array not closed");

        int pos = dataContent.indexOf('{', arrStart);
        while (pos >= 0 && pos < arrEnd) {
            int end = findObjectEnd(dataContent, pos);
            if (end < 0)
                throw new IllegalArgumentException("event object not closed");
            String ev = dataContent.substring(pos, end);

            String cmd = stringValueOf(ev, "command");
            if (cmd.equals(command)) {
                appendResults(list, ev, command);
                return list;
            }
            pos = dataContent.indexOf('{', end);
        }
        return list;        // 命令不存在 → 空列表，由 CoreModule 输出 N/A
    }

    // 在已圈定的单个 event 对象内，解析它的 results 数组。
    private static void appendResults(List<String[]> list, String ev, String command) {
        int rKey = ev.indexOf(Q + "results" + Q);
        if (rKey == -1)
            throw new IllegalArgumentException("results key not found in event: " + command);

        int rStart = ev.indexOf('[', rKey);
        if (rStart == -1)
            throw new IllegalArgumentException("results array not found in event: " + command);

        int rEnd = findArrayEnd(ev, rStart);
        if (rEnd == -1)
            throw new IllegalArgumentException("results array not closed in event: " + command);

        int p = ev.indexOf('{', rStart);
        while (p >= 0 && p < rEnd) {
            int e = findObjectEnd(ev, p);
            if (e < 0)
                throw new IllegalArgumentException("result object not closed in event: " + command);
            String obj = ev.substring(p, e);

            String fullName = stringValueOf(obj, "fullName");
            int rank = intValueOf(obj, "rank");
            String countryCode = stringValueOf(obj, "countryCode");
            List<String> scores = stringArrayValueOf(obj, "scores");
            String totalPoints = stringValueOf(obj, "totalPoints");

            list.add(new String[]{fullName, String.valueOf(rank),
                    String.join(",", scores), totalPoints});

            p = ev.indexOf('{', e);
        }
    }

    // 边界查找

    // 返回从 startBrace 处的 '{' 开始、配平后的对象结尾之后一位；失败返回 -1
    private static int findObjectEnd(String raw, int startBrace) {
        int depth = 0;
        boolean inString = false;
        for (int i = startBrace; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (inString) {
                if (c == '\\') i++;                 // 跳过转义字符
                else if (c == '"') inString = false;
                continue;
            }
            if (c == '"') inString = true;
            else if (c == '{') depth++;
            else if (c == '}') {
                depth--;
                if (depth == 0) return i + 1;
            }
        }
        return -1;
    }

    // 返回从 startBracket 处的 '[' 开始、配平后的数组结尾之后一位；失败返回 -1
    private static int findArrayEnd(String raw, int startBracket) {
        int depth = 0;
        boolean inString = false;
        for (int i = startBracket; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (inString) {
                if (c == '\\') i++;
                else if (c == '"') inString = false;
                continue;
            }
            if (c == '"') inString = true;
            else if (c == '[') depth++;
            else if (c == ']') {
                depth--;
                if (depth == 0) return i + 1;
            }
        }
        return -1;
    }

    // 取值（边界内严格）

    private static int keyIndexOf(String raw, String key) {
        int k = raw.indexOf(Q + key + Q);
        if (k == -1)
            throw new IllegalArgumentException("key not found: " + key);
        return k;
    }

    private static String stringValueOf(String raw, String key) {
        int k = keyIndexOf(raw, key);

        int colon = raw.indexOf(':', k + key.length() + 2);
        if (colon == -1)
            throw new IllegalArgumentException("colon not found after: " + key);

        int start = colon + 1;
        while (start < raw.length() && Character.isWhitespace(raw.charAt(start))) start++;

        if (start >= raw.length() || raw.charAt(start) != '"')
            throw new IllegalArgumentException("string value expected for: " + key);
        start++;

        int end = raw.indexOf('"', start);
        if (end == -1)
            throw new IllegalArgumentException("closing quote not found for: " + key);

        return raw.substring(start, end);
    }

    private static int intValueOf(String raw, String key) {
        int k = keyIndexOf(raw, key);

        int colon = raw.indexOf(':', k + key.length() + 2);
        if (colon == -1)
            throw new IllegalArgumentException("colon not found after: " + key);

        int start = colon + 1;
        while (start < raw.length() && Character.isWhitespace(raw.charAt(start))) start++;

        int end = start;
        while (end < raw.length()
                && (Character.isDigit(raw.charAt(end)) || raw.charAt(end) == '-')) {
            end++;
        }
        if (end == start)
            throw new IllegalArgumentException("number value expected for: " + key);

        return Integer.parseInt(raw.substring(start, end));
    }

    private static List<String> stringArrayValueOf(String raw, String key) {
        int k = keyIndexOf(raw, key);

        int start = raw.indexOf('[', k);
        if (start == -1)
            throw new IllegalArgumentException("array expected for: " + key);

        int end = findArrayEnd(raw, start);
        if (end == -1)
            throw new IllegalArgumentException("array not closed for: " + key);

        String body = raw.substring(start + 1, end - 1);
        List<String> out = new ArrayList<>();
        int cursor = 0;
        while (true) {
            int q1 = body.indexOf('"', cursor);
            if (q1 == -1) break;
            int q2 = body.indexOf('"', q1 + 1);
            if (q2 == -1)
                throw new IllegalArgumentException("unterminated string in array: " + key);
            out.add(body.substring(q1 + 1, q2));
            cursor = q2 + 1;
        }
        return out;
    }
}
