package DataCrawler.Athlete;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import Common.OutputFormatter;

public class AthleteFormatter implements OutputFormatter {


    public String format(String json,String option)
    {
        switch (option) {
            case "":
               return  formatAllPlayers(json);
            default:
                throw new IllegalArgumentException("未知的格式化选项: " + option);
        }
    }

    // Format all players for the "players" command.
    public String formatAllPlayers(String json) {
        if (json == null || json.trim().isEmpty()) {
            return "";
        }
        JsonArray countries = JsonParser.parseString(json).getAsJsonArray();
        StringBuilder sb = new StringBuilder();
        for (JsonElement countryElem : countries) {
            JsonObject countryObj = countryElem.getAsJsonObject();
            String countryName = getAsString(countryObj, "CountryName");
            JsonArray participations = countryObj.getAsJsonArray("Participations");
            if (participations == null) {
                continue;
            }
            for (JsonElement participantElem : participations) {
                JsonObject participant = participantElem.getAsJsonObject();
                String lastName = getAsString(participant, "PreferredLastName");
                String firstName = getAsString(participant, "PreferredFirstName");
                String fullName = (lastName + " " + firstName).trim();
                String gender = mapGender(participant);

                sb.append("Full Name:").append(fullName).append("\n");
                sb.append("Gender:").append(gender).append("\n");
                sb.append("Country:").append(countryName).append("\n");
                sb.append("-----\n");
            }
        }
        return sb.toString();
    }

    private String mapGender(JsonObject participant) {
        if (!participant.has("Gender") || participant.get("Gender").isJsonNull()) {
            return "Unknown";
        }
        int genderCode = participant.get("Gender").getAsInt();
        if (genderCode == 0) {
            return "Male";
        }
        if (genderCode == 1) {
            return "Female";
        }
        return "Unknown";
    }

    private String getAsString(JsonObject obj, String key) {
        if (!obj.has(key) || obj.get(key).isJsonNull()) {
            return "";
        }
        return obj.get(key).getAsString();
    }
}
