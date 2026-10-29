package com.example.taskdive;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;          // 用来将相应属性连接到相应键
import com.fasterxml.jackson.annotation.JsonAlias;            // 不同文件对同一对象的命名不同，需要这个包来实现

// 运动员属性方法
@JsonIgnoreProperties(ignoreUnknown = true)
public class Athlete {
    private static final Map<String,String> CountryMap = Map.ofEntries(
            Map.entry("COL", "Colombia"),
            Map.entry("MEX", "Mexico"),
            Map.entry("USA", "United States of America"),
            Map.entry("ESP", "Spain"),
            Map.entry ("SUI", "Switzerland"),
            Map.entry("CHN", "People's Republic of China"),
            Map.entry("PRK", "Democratic People's Republic of Korea"),
            Map.entry("JPN", "Japan"),
            Map.entry("CAN", "Canada"),
            Map.entry( "RUS", "Russia"),
            Map.entry( "FRA", "France"),
            Map.entry("GER", "Germany"),
            Map.entry("GBR", "Great Britain"),
            Map.entry("ITA", "Italy"),
            Map.entry("UKR", "Ukraine"),
            Map.entry("AUS", "Australia")
    );

    @JsonProperty("CountryName")
    private String CountryName;

    @JsonProperty("CountryCode")
    @JsonAlias({"NAT", "Nationality"})
    private String CountryCode;

    @JsonProperty("PreferredLastName")
    @JsonAlias("LastName")
    private String PreferredLastName;

    @JsonProperty("PreferredFirstName")
    @JsonAlias("FirstName")
    private String PreferredFirstName;

    private String FullName = PreferredFirstName + " " + CountryName;

    @JsonProperty("Gender")
    private int Gender;

    public Athlete(){}
    public String getCountryName() { return CountryName; }
    public void setCountryName(String countryName) { this.CountryName = countryName; }

    public String getCountryNameFromCode(String countryCode){
        return CountryMap.get(countryCode);
    }

    public String getCountryCode() { return CountryCode; }
    public void setCountryCode(String countryCode) { this.CountryCode = countryCode; }

    public String getPreferredLastName() { return PreferredLastName; }
    public void setPreferredLastName(String preferredLastName) { this.PreferredLastName = preferredLastName; }

    public String getPreferredFirstName() { return PreferredFirstName; }
    public void setPreferredFirstName(String preferredFirstName) { this.PreferredFirstName = preferredFirstName; }

    public int getGender() { return Gender; }
    public void setGender(int gender) { this.Gender = gender; }

    public String getFullName() { return FullName; }
    public void setFullName(String fullName) { this.FullName = fullName; }



    @Override
    public String toString() {
        String sex = (Gender == 0) ? "Male" : "Female";
        CountryName = (CountryName == null) ? CountryMap.get(CountryCode) : CountryName;
        return "Full Name:" + PreferredLastName + " " + PreferredFirstName +
                "\nGender:" + sex + "\nCountry:" + CountryName + "\nCountryCode:" + CountryCode + "\n-----\n";
    }
}
