package org.example.weatherforecast.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class City {
    private Long id;
    private String name;
    private double lat;
    private double lon;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateTime;
    private List<WeatherTable> weatherTable;

}
