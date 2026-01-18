package org.example.weatherforecast.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Daily {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fxDate;       // 预报日期，格式：2026-01-13
    @JsonFormat(pattern = "HH:mm")
    private LocalTime sunrise;      // 日出时间
    @JsonFormat(pattern = "HH:mm")
    private LocalTime sunset;       // 日落时间
    private Integer tempMax;      // 当天最高温度
    private Integer tempMin;      // 当天最低温度
    private String textDay;      // 白天天气状况文字
    private String textNight;    // 晚上天气状况文字
    private String iconDay;      // 白天天气图标代码
    private String iconNight;    // 晚上天气图标代码
    private String windDirDay;   // 白天风向
    private String windScaleDay; // 白天风力等级
    private Integer humidity;     // 相对湿度，百分比数值
    private String precip;       // 当天总降水量
    private String uvIndex;      // 紫外线指数
    private String pressure;     // 大气压强
}
