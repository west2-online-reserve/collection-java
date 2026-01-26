package DataCrawler.Result;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import  Common.OutputFormatter;

public class ResultFormatter implements OutputFormatter{

    private static final String PHASE_PRELIM = "Preliminaries";
    private static final String PHASE_SEMI = "Semifinals";
    private static final String PHASE_FINAL = "Finals";



    public String format(String json,String option){
        switch (option){
            case "detail":
                return formatDetailedResults(json);
            case "":
                return formatFinalResults(json);
            default:
                throw new IllegalArgumentException("未知的格式化选项: " + option);
        }
    }

    public String formatFinalResults(String eventJson) {
        JsonObject root = JsonParser.parseString(eventJson).getAsJsonObject();
        JsonObject finalHeat = findHeatByPhase(root, PHASE_FINAL);
        if (finalHeat == null) {
            return "";
        }
        JsonArray results = finalHeat.getAsJsonArray("Results");
        if (results == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (JsonElement resultElem : results) {
            JsonObject result = resultElem.getAsJsonObject();
            String fullName = buildFullName(result);
            String rank = getAsString(result, "Rank");
            String score = buildScore(result);

            sb.append("Full Name:").append(fullName).append("\n");
            sb.append("Rank:").append(rank).append("\n");
            sb.append("Score:").append(score).append("\n");
            sb.append("-----\n");
        }
        return sb.toString();
    }

    // Format detailed results for requirement 1.3.
    public String formatDetailedResults(String eventJson) {
        JsonObject root = JsonParser.parseString(eventJson).getAsJsonObject();
        JsonArray heats = root.getAsJsonArray("Heats");
        if (heats == null) {
            return "";
        }

        Map<String, PhaseData> phaseMap = new HashMap<>();
        for (JsonElement heatElem : heats) {
            JsonObject heat = heatElem.getAsJsonObject();
            String phaseName = getAsString(heat, "PhaseName");
            if (!isTargetPhase(phaseName)) {
                continue;
            }
            PhaseData phaseData = phaseMap.computeIfAbsent(phaseName, k -> new PhaseData());
            JsonArray results = heat.getAsJsonArray("Results");
            if (results == null) {
                continue;
            }
            for (JsonElement resultElem : results) {
                JsonObject result = resultElem.getAsJsonObject();
                String key = buildCompetitorKey(result);
                if (key.isEmpty()) {
                    continue;
                }
                if (!phaseData.order.contains(key)) {
                    phaseData.order.add(key);
                }
                phaseData.nameByKey.putIfAbsent(key, buildFullName(result));
                phaseData.rankByKey.put(key, getAsString(result, "Rank"));
                phaseData.scoreByKey.put(key, buildScore(result));
            }
        }

        List<String> outputOrder = new ArrayList<>();
        Map<String, String> nameByKey = new HashMap<>();
        appendPhaseOrder(PHASE_PRELIM, phaseMap, outputOrder, nameByKey);
        appendPhaseOrder(PHASE_SEMI, phaseMap, outputOrder, nameByKey);
        appendPhaseOrder(PHASE_FINAL, phaseMap, outputOrder, nameByKey);

        StringBuilder sb = new StringBuilder();
        for (String key : outputOrder) {
            String fullName = nameByKey.getOrDefault(key, "");
            String prelimRank = getRank(phaseMap.get(PHASE_PRELIM), key);
            String semiRank = getRank(phaseMap.get(PHASE_SEMI), key);
            String finalRank = getRank(phaseMap.get(PHASE_FINAL), key);

            String prelimScore = getScore(phaseMap.get(PHASE_PRELIM), key);
            String semiScore = getScore(phaseMap.get(PHASE_SEMI), key);
            String finalScore = getScore(phaseMap.get(PHASE_FINAL), key);

            sb.append("Full Name:").append(fullName).append("\n");
            sb.append("Rank:").append(prelimRank)
                    .append(" | ").append(semiRank)
                    .append(" | ").append(finalRank).append("\n");
            sb.append("Preliminary Score:").append(prelimScore).append("\n");
            sb.append("Semifinal Score:").append(semiScore).append("\n");
            sb.append("Final Score:").append(finalScore).append("\n");
            sb.append("-----\n");
        }
        return sb.toString();
    }

    private JsonObject findHeatByPhase(JsonObject root, String phaseName) {
        JsonArray heats = root.getAsJsonArray("Heats");
        if (heats == null) {
            return null;
        }
        for (JsonElement heatElem : heats) {
            JsonObject heat = heatElem.getAsJsonObject();
            String name = getAsString(heat, "PhaseName");
            if (phaseName.equalsIgnoreCase(name)) {
                return heat;
            }
        }
        return null;
    }

    private boolean isTargetPhase(String phaseName) {
        return PHASE_PRELIM.equalsIgnoreCase(phaseName)
                || PHASE_SEMI.equalsIgnoreCase(phaseName)
                || PHASE_FINAL.equalsIgnoreCase(phaseName);
    }

    private String getRank(PhaseData data, String key) {
        if (data == null) {
            return "*";
        }
        String rank = data.rankByKey.get(key);
        return rank == null || rank.isEmpty() ? "*" : rank;
    }

    private String getScore(PhaseData data, String key) {
        if (data == null) {
            return "*";
        }
        String score = data.scoreByKey.get(key);
        return score == null || score.isEmpty() ? "*" : score;
    }

    private void appendPhaseOrder(String phase,
                                  Map<String, PhaseData> phaseMap,
                                  List<String> outputOrder,
                                  Map<String, String> nameByKey) {
        PhaseData data = phaseMap.get(phase);
        if (data == null) {
            return;
        }
        for (String key : data.order) {
            if (!nameByKey.containsKey(key) || nameByKey.get(key).isEmpty()) {
                String name = data.nameByKey.getOrDefault(key, "");
                nameByKey.put(key, name);
            }
            if (!outputOrder.contains(key)) {
                outputOrder.add(key);
            }
        }
    }

    private String buildScore(JsonObject result) {
        JsonArray dives = result.getAsJsonArray("Dives");
        if (dives == null || dives.size() == 0) {
            return "*";
        }

        List<DiveItem> items = new ArrayList<>();
        for (JsonElement diveElem : dives) {
            JsonObject dive = diveElem.getAsJsonObject();
            int order = getAsInt(dive, "DiveOrder", 0);
            String points = getAsString(dive, "DivePoints");
            items.add(new DiveItem(order, points, getAsString(dive, "TotalPoints")));
        }

        items.sort((a, b) -> Integer.compare(a.order, b.order));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            if (i > 0) {
                sb.append(" + ");
            }
            sb.append(items.get(i).points);
        }

        String total = getAsString(result, "TotalPoints");
        if (total.isEmpty() && !items.isEmpty()) {
            total = items.get(items.size() - 1).totalPoints;
        }
        if (sb.length() == 0 || total.isEmpty()) {
            return "*";
        }
        return sb.append(" = ").append(total).toString();
    }

    private String buildFullName(JsonObject result) {
        JsonElement competitorsElem = result.get("Competitors");
        if (competitorsElem != null && competitorsElem.isJsonArray()) {
            JsonArray competitors = competitorsElem.getAsJsonArray();
            if (competitors.size() > 0) {
            List<NameItem> items = new ArrayList<>();
            for (JsonElement compElem : competitors) {
                JsonObject comp = compElem.getAsJsonObject();
                String last = getAsString(comp, "LastName");
                String first = getAsString(comp, "FirstName");
                String display = (last + " " + first).trim();
                if (display.isEmpty()) {
                    display = getAsString(comp, "FullName");
                }
                if (!display.isEmpty()) {
                    items.add(new NameItem(last, display));
                }
            }
            if (!items.isEmpty()) {
                items.sort((a, b) -> {
                    String aKey = a.lastName.isEmpty() ? a.display : a.lastName;
                    String bKey = b.lastName.isEmpty() ? b.display : b.lastName;
                    return aKey.compareToIgnoreCase(bKey);
                });
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < items.size(); i++) {
                    if (i > 0) {
                        sb.append(" & ");
                    }
                    sb.append(items.get(i).display);
                }
                return sb.toString();
            }
        }
        }

        String fullName = getAsString(result, "FullName");
        if (!fullName.isEmpty()) {
            return fullName;
        }
        String last = getAsString(result, "LastName");
        String first = getAsString(result, "FirstName");
        return (last + " " + first).trim();
    }

    private String buildCompetitorKey(JsonObject result) {
        String personId = getAsString(result, "PersonId");
        if (!personId.isEmpty()) {
            return personId;
        }
        JsonElement competitorsElem = result.get("Competitors");
        if (competitorsElem != null && competitorsElem.isJsonArray()) {
            JsonArray competitors = competitorsElem.getAsJsonArray();
            if (competitors.size() > 0) {
            List<String> parts = new ArrayList<>();
            for (JsonElement compElem : competitors) {
                JsonObject comp = compElem.getAsJsonObject();
                String compId = getAsString(comp, "PersonId");
                if (!compId.isEmpty()) {
                    parts.add(compId);
                } else {
                    String last = getAsString(comp, "LastName");
                    String first = getAsString(comp, "FirstName");
                    String name = (last + " " + first).trim();
                    if (name.isEmpty()) {
                        name = getAsString(comp, "FullName");
                    }
                    if (!name.isEmpty()) {
                        parts.add(name);
                    }
                }
            }
            if (!parts.isEmpty()) {
                parts.sort(String.CASE_INSENSITIVE_ORDER);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < parts.size(); i++) {
                    if (i > 0) {
                        sb.append("|");
                    }
                    sb.append(parts.get(i));
                }
                return sb.toString();
            }
        }
        }
        String fullName = getAsString(result, "FullName");
        if (!fullName.isEmpty()) {
            return fullName;
        }
        String last = getAsString(result, "LastName");
        String first = getAsString(result, "FirstName");
        return (last + " " + first).trim();
    }

    private String getAsString(JsonObject obj, String key) {
        if (!obj.has(key) || obj.get(key).isJsonNull()) {
            return "";
        }
        return obj.get(key).getAsString();
    }

    private int getAsInt(JsonObject obj, String key, int defaultValue) {
        if (!obj.has(key) || obj.get(key).isJsonNull()) {
            return defaultValue;
        }
        return obj.get(key).getAsInt();
    }

    private static class PhaseData {
        private final List<String> order = new ArrayList<>();
        private final Map<String, String> nameByKey = new HashMap<>();
        private final Map<String, String> rankByKey = new HashMap<>();
        private final Map<String, String> scoreByKey = new HashMap<>();
    }

    private static class DiveItem {
        private final int order;
        private final String points;
        private final String totalPoints;

        private DiveItem(int order, String points, String totalPoints) {
            this.order = order;
            this.points = points == null ? "" : points;
            this.totalPoints = totalPoints == null ? "" : totalPoints;
        }
    }

    private static class NameItem {
        private final String lastName;
        private final String display;

        private NameItem(String lastName, String display) {
            this.lastName = lastName == null ? "" : lastName;
            this.display = display == null ? "" : display;
        }
    }
}
