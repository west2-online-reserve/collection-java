package org.learning.order_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer productId;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private Integer isDeleted;
}
