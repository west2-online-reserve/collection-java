package Y.mappers;

import Y.pojo.Order;
import Y.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface OrderMapper {
    //查询所有订单信息(排序后)
    ArrayList<Order> selectAll(String orderBy);

    //根据订单号查询订单信息
    Order selectById(int id);

    //分页查询
    ArrayList<Order> selectByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize, @Param("orderBy") String orderBy);

    //增加订单信息
    int insertOrder(Order order);

    //修改订单信息
    int deleteById(int id);

    //改变订单信息(增加商品或者删除商品)
    int updateOrder(@Param("code")int code,@Param("product") Product product,@Param("dateTime") LocalDateTime dateTime,@Param("operator") boolean operator);
}
