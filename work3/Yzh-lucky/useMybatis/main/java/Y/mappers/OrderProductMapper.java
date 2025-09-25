package Y.mappers;

import Y.pojo.OrderProduct;

import java.util.ArrayList;

public interface OrderProductMapper {
    //查询订单商品信息（根据订单号）
    ArrayList<OrderProduct> getOrderProductById(int id);

    //查询所有订单商品信息
    ArrayList<OrderProduct> getAllOrderProduct();
}
