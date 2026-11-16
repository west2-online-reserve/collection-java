package com.example.taskdive;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;


public class AthleteSearch {
    public static void SearchFor()
    {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, String> fileMap = DataUtil.readFiles("Athlete_Contest/Athlete");
            for (Map.Entry<String, String> entry : fileMap.entrySet()) {
                String fileContent = entry.getValue();
                List<Country> countries = OrderClass.CountryOrder(mapper.readValue(
                        fileContent,
                        new TypeReference<>() {
                        }
                ));

                Event event = new Event();
                System.out.println("--------" + event + "--------");
                for (Country country : countries) {
                    System.out.println("=== 国家: " + country.getCountryName() + " ===");
                    if (country.getParticipations() != null) {
                        country.setParticipations(OrderClass.AthleteOrder(country.getParticipations()));
                        for (Athlete athlete : country.getParticipations()) {
                            athlete.setCountryName(country.getCountryName());
                            athlete.setCountryCode(country.getCountryCode());
                            OutputClass.OutputAthlete(athlete);
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            System.err.println("文件不存在或已损坏");
            e.printStackTrace();
        }
    }
}
