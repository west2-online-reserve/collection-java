package org.learning.order_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer orderId;
    private Double orderAmount;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
