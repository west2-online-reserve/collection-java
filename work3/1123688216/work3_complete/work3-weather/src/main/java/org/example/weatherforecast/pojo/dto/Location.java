package org.example.weatherforecast.pojo.dto;

import lombok.Data;

@Data
public class Location {
    private String name;      // 北京
    private Long id;        // 101010100 (这个最重要，查天气要用)
    private Double lat;       // 纬度
    private Double lon;       // 经度
    private String adm2;      // 城市
    private String adm1;      // 省份
    private String country;   // 国家
    private String tz;        // 时区
    private String utcOffset;
    private String type;
    private String fxLink;    // 天气详情链接
}
