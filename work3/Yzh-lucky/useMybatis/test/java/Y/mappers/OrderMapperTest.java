package Y.mappers;

import Y.pojo.Order;
import Y.pojo.Product;
import Y.utils.Update;
import Y.utils.mybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderMapperTest {
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        ArrayList<Order> orders = new ArrayList<>();
        orders = orderMapper.selectAll("desc");
        for (Order order : orders) {
            System.out.println(order);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Order order = orderMapper.selectById(6);
        System.out.println(order);
        sqlSession.close();
    }

    @Test
    public void testSelectByPage() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        ArrayList<Order> orders = new ArrayList<>();
        orders = orderMapper.selectByPage(0, 5, "desc");
        for (Order order : orders) {
            System.out.println(order);
        }
        sqlSession.close();
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Update update = sqlSession.getMapper(Update.class);
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product(1, "机械键盘", 399.0));
        products.add(new Product(2, "游戏鼠标", 199.0));
        products.add(new Product(2, "游戏鼠标", 199.0));
        Order order = new Order( 36, "Tom", products, LocalDateTime.now());
        int i = orderMapper.insertOrder(order);
        int j = update.insertToOrderProduct(order);
        System.out.println(i);
        System.out.println(j);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        int i = orderMapper.deleteById(27);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Update update = sqlSession.getMapper(Update.class);
        Product product = new Product(1, "机械键盘", 399.0);
        boolean operator = false;
        int[] i = new int[3];
        i[0] = orderMapper.updateOrder(36,product, LocalDateTime.now(),operator);
        if (operator){
            i[1] = update.insertNewToOrderProduct(36,product);
        } else {
            i[1] = update.deleteToOrderProduct(36,product);
        }
        i[2] = update.updateToOrderProduct(36);
        System.out.println(i[0]);
        System.out.println(i[1]);
        System.out.println(i[2]);
        sqlSession.commit();
        sqlSession.close();
    }
}
