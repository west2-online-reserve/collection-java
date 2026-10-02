package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class JoinCountry {

    @JsonProperty("CountryName")
    private String CountryName;

    @JsonProperty("CountryCode")
    private String CountryCode;

    @JsonProperty("Participations")
    private List<Participations> Participations;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Participations{

        @JsonProperty("Gender")
        private String Gender;

        @JsonProperty("PreferredLastName")
        private String PreferredLastName;

        @JsonProperty("PreferredFirstName")
        private String PreferredFirstName;

        @JsonProperty("NAT")
        private String NAT;

    }

}
