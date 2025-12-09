package cn.github.lpx.model;
import com.fasterxml.jackson.annotation.JsonProperty;
/*
    athletes.json解析的目标类
 */
public class Athlete {
    @JsonProperty("CountryName")
    private String CountryName;
    @JsonProperty("Participations")
    private Participation[] Participations;

    public String getCountryName() {
        return CountryName;
    }
    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public Participation[] getParticipations() {
        return Participations;
    }
    public void setParticipations(Participation[] participations) {
        Participations = participations;
    }

    public static class Participation {
        @JsonProperty("PreferredLastName")
        private String PreferredLastName;
        @JsonProperty("PreferredFirstName")
        private String PreferredFirstName;
        @JsonProperty("Gender")
        private int Gender;

        public int getGender() {
            return Gender;
        }
        public void setGender(int gender) {
            Gender = gender;
        }

        public String getPreferredLastName() {
            return PreferredLastName;
        }
        public void setPreferredLastName(String preferredLastName) {
            PreferredLastName = preferredLastName;
        }

        public String getPreferredFirstName() {
            return PreferredFirstName;
        }
        public void setPreferredFirstName(String preferredFirstName) {
            PreferredFirstName = preferredFirstName;
        }
    }
}
