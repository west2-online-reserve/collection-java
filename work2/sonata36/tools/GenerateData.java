import java.io.IOException;
import java.math.BigDecimal;
import java.text.Normalizer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Converts the locally archived official OMEGA PDF text into compact UTF-8 TSV files. */
public final class GenerateData {
    private static final Path RAW = Path.of("src/data/raw");
    private static final Path DATA = Path.of("src/data");
    private static final Pattern ENTRY = Pattern.compile("^(.+?) ([A-Z]{3}) ([MF]) \\d{1,2} [A-Z]{3} \\d{4} .+$");
    private static final Pattern RANK = Pattern.compile("^(\\d+) ([A-Za-zÀ-ž].*)$");
    private static final Pattern DIVE = Pattern.compile("\\b[0-9]{3,5}[A-D]\\s+[0-9]+(?:\\.[0-9]+)?\\b");
    private static final Map<String, String> COUNTRIES = countries();

    private record Player(String name, String gender, String countryCode, String country) {}
    private record Row(String event, String stage, String rank, String name,
                       List<String> dives, String total) {}

    public static void main(String[] args) throws IOException {
        Map<String, Player> players = readPlayers();
        List<Row> rows = new ArrayList<>();
        try (var paths = Files.list(RAW)) {
            for (Path path : paths.filter(p -> p.getFileName().toString().endsWith(".txt"))
                    .filter(p -> !p.getFileName().toString().equals("entry-list.txt")).sorted().toList()) {
                rows.addAll(readResults(path, players));
            }
        }
        if (rows.size() < 300) throw new IllegalStateException("Too few result rows: " + rows.size());
        List<String> playerLines = new ArrayList<>();
        players.values().stream().sorted(Comparator.comparing(Player::country)
                .thenComparing(Player::name, String.CASE_INSENSITIVE_ORDER)).forEach(p ->
                playerLines.add(p.name + "\t" + p.gender + "\t" + p.country));
        Files.write(DATA.resolve("players.tsv"), playerLines, StandardCharsets.UTF_8);
        List<String> resultLines = new ArrayList<>();
        for (Row row : rows) {
            resultLines.add(String.join("\t", row.event, row.stage, row.rank,
                    row.name, String.join(" + ", row.dives), row.total));
        }
        Files.write(DATA.resolve("results.tsv"), resultLines, StandardCharsets.UTF_8);
        System.out.printf("Generated %d players and %d stage results%n", players.size(), rows.size());
    }

    private static Map<String, Player> readPlayers() throws IOException {
        Map<String, Player> players = new LinkedHashMap<>();
        for (String text : Files.readAllLines(RAW.resolve("entry-list.txt"), StandardCharsets.UTF_8)) {
            Matcher m = ENTRY.matcher(content(text));
            if (m.matches()) {
                String country = COUNTRIES.get(m.group(2));
                if (country == null) throw new IllegalStateException("Unknown country " + m.group(2));
                Player p = new Player(m.group(1).trim(), m.group(3).equals("M") ? "Male" : "Female",
                        m.group(2), country);
                if (players.putIfAbsent(p.name, p) != null) throw new IllegalStateException("Duplicate " + p.name);
            }
        }
        if (players.size() < 150) throw new IllegalStateException("Too few players: " + players.size());
        return players;
    }

    private static List<Row> readResults(Path path, Map<String, Player> players) throws IOException {
        String filename = path.getFileName().toString().replace(".txt", "");
        String stage = filename.substring(filename.lastIndexOf('-') + 1);
        String event = filename.substring(0, filename.lastIndexOf('-'));
        boolean sync = event.contains("synchronised");
        int judges = sync ? 11 : 7;
        List<Row> result = new ArrayList<>();
        String rank = null;
        StringBuilder name = new StringBuilder();
        String country = null;
        List<String> dives = null;
        String total = null;
        boolean inResults = false;
        for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
            String value = content(line);
            if (value.equals("Detailed Results")) inResults = true;
            if (!inResults) continue;
            Matcher dive = DIVE.matcher(value);
            if (dive.find()) {
                if (value.endsWith(" List") || value.endsWith(" WD")) continue;
                String[] tokens = value.substring(dive.start()).split("\\s+");
                int scoreIndex = 2 + judges;
                if (tokens.length <= scoreIndex + 2) continue; // no judged dive (withdrawal)
                String before = value.substring(0, dive.start()).trim();
                Matcher rankedBefore = RANK.matcher(before);
                if (rankedBefore.matches()) {
                    if (dives != null) result.add(finish(event, stage, rank, name.toString(),
                            country, dives, total, players, sync));
                    rank = rankedBefore.group(1);
                    name = new StringBuilder();
                    country = null;
                    dives = null;
                    total = null;
                    before = rankedBefore.group(2);
                } else if (dives != null && !before.isBlank()) {
                    // A withdrawal may still have judged dives but no published rank.
                    result.add(finish(event, stage, rank, name.toString(),
                            country, dives, total, players, sync));
                    rank = "*";
                    name = new StringBuilder();
                    country = null;
                    dives = null;
                    total = null;
                }
                if (rank == null) throw new IllegalStateException(path + ": score without rank: " + value);
                if (dives == null) {
                    Matcher nat = Pattern.compile("^(.*?)(?:\\s+)?([A-Z]{3})$").matcher(before);
                    if (!nat.matches()) throw new IllegalStateException(path + ": missing nation: " + value);
                    if (!nat.group(1).isBlank()) name.append(' ').append(nat.group(1));
                    country = nat.group(2);
                    dives = new ArrayList<>();
                }
                if (!tokens[scoreIndex].matches("\\*?\\d+\\.\\d{2}")) {
                    throw new IllegalStateException(path + ": malformed scores: " + value);
                }
                dives.add(tokens[scoreIndex].replace("*", ""));
                total = tokens[scoreIndex + 2];
                continue;
            }
            Matcher ranked = RANK.matcher(value);
            if (ranked.matches()) {
                if (dives != null) result.add(finish(event, stage, rank, name.toString(),
                        country, dives, total, players, sync));
                rank = ranked.group(1);
                name = new StringBuilder(ranked.group(2));
                country = null;
                dives = null;
                total = null;
            } else if (rank != null && dives == null && !value.isBlank()
                    && !value.startsWith("Official") && !value.startsWith("Source:")) {
                name.append(' ').append(value);
            }
        }
        if (dives != null) result.add(finish(event, stage, rank, name.toString(),
                country, dives, total, players, sync));
        if (result.isEmpty()) throw new IllegalStateException(path + ": no results");
        return result;
    }

    private static Row finish(String event, String stage, String rank, String rawName,
                              String country, List<String> dives, String total,
                              Map<String, Player> players, boolean sync) {
        String combined = rawName.replaceAll("\\s+", " ").trim();
        String normalized = normalize(combined);
        String name = null;
        List<Player> participants = new ArrayList<>();
        if (sync) {
            for (String first : players.keySet()) {
                String prefix = normalize(first) + " ";
                if (!normalized.startsWith(prefix)) continue;
                String second = normalized.substring(prefix.length());
                for (String candidate : players.keySet()) {
                    if (normalize(candidate).equals(second)) {
                        name = first.compareTo(candidate) <= 0 ? first + " & " + candidate : candidate + " & " + first;
                        participants.add(players.get(first));
                        participants.add(players.get(candidate));
                        break;
                    }
                }
                if (name != null) break;
            }
        } else {
            for (String candidate : players.keySet()) {
                if (normalize(candidate).equals(normalized)) {
                    name = candidate;
                    participants.add(players.get(candidate));
                    break;
                }
            }
        }
        if (name == null) {
            throw new IllegalStateException(event + "/" + stage + ": athlete not in entry list: " + combined);
        }
        BigDecimal sum = BigDecimal.ZERO;
        for (String score : dives) sum = sum.add(new BigDecimal(score));
        if (sum.compareTo(new BigDecimal(total)) != 0) {
            throw new IllegalStateException(event + "/" + stage + ": sum mismatch " + name + " " + sum + " != " + total);
        }
        if (country == null || !COUNTRIES.containsKey(country)) {
            throw new IllegalStateException(event + "/" + stage + ": invalid country " + country);
        }
        for (Player participant : participants) {
            if (!participant.countryCode.equals(country)) {
                throw new IllegalStateException(event + "/" + stage + ": country mismatch for "
                        + participant.name + ": " + country + " != " + participant.countryCode);
            }
        }
        return new Row(event, stage, rank, name, dives, total);
    }

    private static String content(String line) {
        return line.replaceFirst("^L\\d+@P\\d+:\\s*", "").trim();
    }

    private static String normalize(String name) {
        return Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toUpperCase(java.util.Locale.ROOT);
    }

    private static Map<String, String> countries() {
        Map<String, String> result = new HashMap<>();
        String values = "ARM=Armenia|AUS=Australia|AUT=Austria|BRA=Brazil|CAN=Canada|"
                + "CHI=Chile|CHN=China|COL=Colombia|CRO=Croatia|CUB=Cuba|CZE=Czechia|"
                + "DEN=Denmark|DOM=Dominican Republic|EGY=Egypt|ESP=Spain|FIN=Finland|"
                + "FRA=France|GBR=Great Britain|GEO=Georgia|GER=Germany|GRE=Greece|"
                + "HKG=Hong Kong, China|HUN=Hungary|INA=Indonesia|IND=India|IRL=Ireland|"
                + "ITA=Italy|JAM=Jamaica|JPN=Japan|KAZ=Kazakhstan|KOR=Republic of Korea|"
                + "LAT=Latvia|LTU=Lithuania|MAC=Macao, China|MAS=Malaysia|MEX=Mexico|"
                + "NED=Netherlands|NOR=Norway|NZL=New Zealand|POL=Poland|PRK=DPR Korea|"
                + "PUR=Puerto Rico|ROU=Romania|RSA=South Africa|SGP=Singapore|SRB=Serbia|"
                + "SRI=Sri Lanka|SUI=Switzerland|SWE=Sweden|THA=Thailand|TUR=Türkiye|"
                + "UKR=Ukraine|USA=United States|UZB=Uzbekistan|VEN=Venezuela";
        for (String pair : values.split("\\|")) {
            String[] parts = pair.split("=", 2);
            result.put(parts[0], parts[1]);
        }
        return result;
    }
}
