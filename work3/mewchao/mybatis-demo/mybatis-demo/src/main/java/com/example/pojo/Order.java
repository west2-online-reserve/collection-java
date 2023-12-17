package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id_order;
    private int nums_good;
    private double price;

    private DateTime time_order;

    private DateTime gmt_create;

    private DateTime gmt_modified;
}
