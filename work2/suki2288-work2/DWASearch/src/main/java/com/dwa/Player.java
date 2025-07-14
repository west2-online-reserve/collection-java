package com.dwa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    private String countryName;
    private int gender; // 0: Male, 1: Female
    private String preferredLastName;
    private String preferredFirstName;
}