package org.example.weatherforecast.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class WeatherTable {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fxDate;       // 预报日期，格式：2026-01-13
    @JsonFormat(pattern = "HH:mm")
    private LocalTime sunrise;      // 日出时间
    @JsonFormat(pattern = "HH:mm")
    private LocalTime sunset;       // 日落时间
    private Integer tempMax;      // 当天最高温度
    private Integer tempMin;      // 当天最低温度
    private Integer humidity;
    private Long cityId;
}
