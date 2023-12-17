package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    private int ul_id_goods;
    private double price_goods;
    private String name_goods;
    private DateTime gmt_create;
    private DateTime gmt_modified;
}
