package org.example.weatherforecast.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeoApi {
    private String code;           // 对应 JSON 中的 "code"
    private List<Location> location; // 对应 JSON 中的 "location" 数组
}
