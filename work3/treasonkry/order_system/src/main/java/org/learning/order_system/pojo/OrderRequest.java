package org.learning.order_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderItemRequest> orderItemRequests;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemRequest {

        private Integer productId;
        private Integer quantity;
    }
}
