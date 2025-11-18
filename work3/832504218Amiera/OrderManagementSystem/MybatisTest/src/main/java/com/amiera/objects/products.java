package com.amiera.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class products {
    private double price;
    private int product_id;
    private String product_name;
}
