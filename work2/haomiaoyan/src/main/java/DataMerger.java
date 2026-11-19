import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;

public class DataMerger {

    private static final String Q = "\"";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  ").append(Q).append("schemaVersion").append(Q).append(": 1,\n");

        // players 已验证
        String raw = Files.readString(Path.of("athletes.json"), UTF_8);
        sb.append("  ").append(Q).append("players").append(Q).append(": [\n");

        boolean firstPlayer = true;
        int playerCount = 0;
        int gPos = raw.indexOf('{');
        while (gPos >= 0) {
            int gEnd = findObjectEnd(raw, gPos);
            if (gEnd < 0) break;
            String group = raw.substring(gPos, gEnd);

            String country = stringValueOf(group, "CountryName", 0);
            String countryCode = stringValueOf(group, "CountryCode", 0);

            int partKey = group.indexOf(Q + "Participations" + Q);
            if (partKey >= 0) {
                int pPos = group.indexOf('{', partKey);
                while (pPos >= 0) {
                    int pEnd = findObjectEnd(group, pPos);
                    if (pEnd < 0) break;
                    String person = group.substring(pPos, pEnd);

                    String last = stringValueOf(person, "PreferredLastName", 0);
                    String first = stringValueOf(person, "PreferredFirstName", 0);
                    int gender = intValueOf(person, "Gender", 0);

                    String fullName = last + " " + first;
                    String genderText;
                    if (gender == 1) genderText = "Female";
                    else if (gender == 0) genderText = "Male";
                    else genderText = "Unknown";

                    if (!firstPlayer) sb.append(",\n");
                    firstPlayer = false;
                    sb.append("    {\n");
                    sb.append("      ").append(Q).append("fullName").append(Q).append(": ")
                            .append(Q).append(fullName).append(Q).append(",\n");
                    sb.append("      ").append(Q).append("gender").append(Q).append(": ")
                            .append(Q).append(genderText).append(Q).append(",\n");
                    sb.append("      ").append(Q).append("country").append(Q).append(": ")
                            .append(Q).append(country).append(Q).append(",\n");
                    sb.append("      ").append(Q).append("countryCode").append(Q).append(": ")
                            .append(Q).append(countryCode).append(Q).append("\n");
                    sb.append("    }");
                    playerCount++;

                    pPos = group.indexOf('{', pEnd);
                }
            }

            gPos = raw.indexOf('{', gEnd);
        }
        sb.append("\n  ],\n");

        // 循环 8 个 event
        sb.append("  ").append(Q).append("events").append(Q).append(": [\n");

        String[][] targets = {
                {"Women", "002"}, {"Women", "003"}, {"Women", "202"}, {"Women", "203"},
                {"Men",   "002"}, {"Men",   "003"}, {"Men",   "202"}, {"Men",   "203"}
        };
        for (int i = 0; i < targets.length; i++) {
            if (i > 0) sb.append(",\n");        // appendEvent 自身不带尾换行，这里补分隔
            appendEvent(sb, targets[i][0], targets[i][1]);
        }

        sb.append("\n  ]\n");

        sb.append("}\n");

        Files.writeString(Path.of("data_test.json"), sb.toString(), UTF_8);
        System.out.println("OK schemaVersion=1 players=" + playerCount + " events=" + targets.length);
    }

    private static void appendEvent(StringBuilder sb, String gender, String code) throws IOException {
        String fileName = "event_" + gender + "_" + code + ".json";
        String ev = Files.readString(Path.of(fileName), UTF_8);

        String genderRaw = stringValueOf(ev, "Gender", 0);       // "Women"
        String command = "result " + genderRaw.toLowerCase() + " " + commandSuffix(code);

        // 找 Finals heat
        int fIdx = ev.indexOf(Q + "Name" + Q);
        while (fIdx >= 0 && !"Finals".equals(stringValueOf(ev, "Name", fIdx))) {
            fIdx = ev.indexOf(Q + "Name" + Q, fIdx + 1);
        }
        if (fIdx < 0) throw new IllegalStateException("Finals not found in " + fileName);

        // 圈出 Finals 的 Results 数组
        int rKey = ev.indexOf(Q + "Results" + Q, fIdx);
        if (rKey < 0) throw new IllegalStateException("Results not found in " + fileName);
        int arrStart = ev.indexOf('[', rKey);
        int arrEnd = findArrayEnd(ev, arrStart);
        if (arrEnd < 0) throw new IllegalStateException("Results array end not found");

        sb.append("    {\n");
        sb.append("      ").append(Q).append("command").append(Q).append(": ")
                .append(Q).append(command).append(Q).append(",\n");
        sb.append("      ").append(Q).append("results").append(Q).append(": [\n");

        boolean firstResult = true;
        int pos = ev.indexOf('{', arrStart);
        while (pos >= 0 && pos < arrEnd) {
            int end = findObjectEnd(ev, pos);
            String obj = ev.substring(pos, end);

            String fullName = stringValueOf(obj, "FullName", 0);
            int rank = intValueOf(obj, "Rank", 0);
            String nat = stringValueOf(obj, "NAT", 0);
            String totalPoints = stringValueOf(obj, "TotalPoints", 0);

            // Dives 数组 → scores（按数组顺序，数据本身按 DiveOrder 排；这里依赖该顺序）
            int divesKey = obj.indexOf(Q + "Dives" + Q);
            int dArrStart = obj.indexOf('[', divesKey);
            int dArrEnd = findArrayEnd(obj, dArrStart);

            StringBuilder scores = new StringBuilder("[");
            boolean firstDive = true;
            int dPos = obj.indexOf('{', dArrStart);
            while (dPos >= 0 && dPos < dArrEnd) {
                int dEnd = findObjectEnd(obj, dPos);
                String dive = obj.substring(dPos, dEnd);
                String dp = stringValueOf(dive, "DivePoints", 0);  // 单跳得分，不是 TotalPoints
                if (!firstDive) scores.append(", ");
                firstDive = false;
                scores.append(Q).append(dp).append(Q);
                dPos = obj.indexOf('{', dEnd);
            }
            scores.append("]");

            if (!firstResult) sb.append(",\n");
            firstResult = false;
            sb.append("        {\n");
            sb.append("          ").append(Q).append("fullName").append(Q).append(": ")
                    .append(Q).append(fullName).append(Q).append(",\n");
            sb.append("          ").append(Q).append("rank").append(Q).append(": ")
                    .append(rank).append(",\n");
            sb.append("          ").append(Q).append("countryCode").append(Q).append(": ")
                    .append(Q).append(nat).append(Q).append(",\n");
            sb.append("          ").append(Q).append("scores").append(Q).append(": ")
                    .append(scores).append(",\n");
            sb.append("          ").append(Q).append("totalPoints").append(Q).append(": ")
                    .append(Q).append(totalPoints).append(Q).append("\n");
            sb.append("        }");

            pos = ev.indexOf('{', end);
        }

        sb.append("\n      ]\n");
        sb.append("    }");
    }

    // 8 个固定关键字；default 抛异常，不静默返回空串
    private static String commandSuffix(String code) {
        switch (code) {
            case "002": return "3m springboard";
            case "003": return "10m platform";
            case "202": return "3m synchronised";
            case "203": return "10m synchronised";
            default: throw new IllegalArgumentException("未知 Code: " + code);
        }
    }

    // 边界查找

    private static int findObjectEnd(String raw, int startBrace) {
        int depth = 0;
        for (int i = startBrace; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (c == '{') {
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0) return i + 1;
            }
        }
        return -1;
    }

    private static int findArrayEnd(String raw, int startBracket) {
        int depth = 0;
        for (int i = startBracket; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (c == '[') {
                depth++;
            } else if (c == ']') {
                depth--;
                if (depth == 0) return i + 1;
            }
        }
        return -1;
    }

    // 取值工具（不变）

    private static String stringValueOf(String raw, String key, int fromIndex) {
        String needle = Q + key + Q;
        int k = raw.indexOf(needle, fromIndex);
        if (k < 0) throw new IllegalArgumentException("key not found: " + key);

        int colon = raw.indexOf(':', k + needle.length());
        if (colon < 0) throw new IllegalArgumentException("colon not found after: " + key);

        int start = colon + 1;
        while (start < raw.length() && Character.isWhitespace(raw.charAt(start))) start++;

        if (start >= raw.length() || raw.charAt(start) != '"') {
            throw new IllegalArgumentException("string value expected for: " + key);
        }
        start++;

        int end = raw.indexOf('"', start);
        if (end < 0) throw new IllegalArgumentException("closing quote not found for: " + key);

        return raw.substring(start, end);
    }

    private static int intValueOf(String raw, String key, int fromIndex) {
        String needle = Q + key + Q;
        int k = raw.indexOf(needle, fromIndex);
        if (k < 0) throw new IllegalArgumentException("key not found: " + key);

        int colon = raw.indexOf(':', k + needle.length());
        if (colon < 0) throw new IllegalArgumentException("colon not found after: " + key);

        int start = colon + 1;
        while (start < raw.length() && Character.isWhitespace(raw.charAt(start))) start++;

        int end = start;
        while (end < raw.length()
                && (Character.isDigit(raw.charAt(end)) || raw.charAt(end) == '-')) {
            end++;
        }
        if (end == start) throw new IllegalArgumentException("number value expected for: " + key);

        return Integer.parseInt(raw.substring(start, end));
    }
}