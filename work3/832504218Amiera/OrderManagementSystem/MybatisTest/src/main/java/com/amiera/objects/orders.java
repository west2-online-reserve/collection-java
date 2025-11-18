package com.amiera.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class orders {
    private int order_id;
    private java.util.Date order_time;
    private double order_price;
    private String order_information;
    //将order与product关联起来
    private int product_id;
}
