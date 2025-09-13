package org.learning.order_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Integer itemId;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Double itemPrice;
    private Double subtotal;
}
