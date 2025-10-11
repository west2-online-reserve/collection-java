package Y.mappers;

import Y.pojo.OrderProduct;
import Y.utils.mybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrderProductMapperTest {
    @Test
    public void testGetOrderProductById() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        OrderProductMapper orderProductMapper = sqlSession.getMapper(OrderProductMapper.class);
        ArrayList<OrderProduct> orderProducts = orderProductMapper.getOrderProductById(26);
        for (OrderProduct orderProduct : orderProducts) {
            System.out.println(orderProduct);
        }
        sqlSession.close();
    }

    @Test
    public void testGetAllOrderProduct() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        OrderProductMapper orderProductMapper = sqlSession.getMapper(OrderProductMapper.class);
        ArrayList<OrderProduct> orderProducts = orderProductMapper.getAllOrderProduct();
        for (OrderProduct orderProduct : orderProducts) {
            System.out.println(orderProduct);
        }
        sqlSession.close();
    }
}
