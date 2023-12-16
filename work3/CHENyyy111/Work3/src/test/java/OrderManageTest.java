import com.chenyyy111.dao.OrderMapper;
import com.chenyyy111.dao.ProductMapper;
import com.chenyyy111.pojo.Order;
import com.chenyyy111.pojo.Product;
import com.chenyyy111.utils.MybatisUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderManageTest {
    @Test
    public void testGetProductsList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        List<Product> productsList = productMapper.getProductsList();
        for (Product product : productsList){
            System.out.println(product);
        }
        sqlSession.close();
    }
    @Test
    public void testGetProductById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product productById = productMapper.getProductById(1);
        System.out.println(productById);
        sqlSession.close();
    }
    @Test
    public void testAddProduct(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try{
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            int i = productMapper.addProduct(new Product(3, "CCC", 6));
            if(i > 0){
                System.out.println("插入成功！");
            }
        }catch (Exception e) {
            System.out.println("插入失败！");
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
    @Test
    public void testUpdateProduct(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        int i = productMapper.updateProduct(new Product(3,"DDD", 2));
        if(i > 0){
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testDeleteProduct(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        int i = productMapper.deleteProduct(1);
        if(i > 0){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
        sqlSession.commit();
        sqlSession.close();
    }













    @Test
    public void testGetOrderList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> allOrders = orderMapper.getOrdersList();
        for (Order order : allOrders){
            System.out.println(order);
        }
        sqlSession.close();
    }
    @Test
    public void testGetOrderById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Order orderById = orderMapper.getOrderById(1);
        System.out.println(orderById);
        sqlSession.close();
    }
    @Test
    public void testUpdateOrder(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Order order = orderMapper.getOrderById(4);
        try{
            List<Product> productsList = order.getProducts();
            order.setProducts(productsList);
            order.calculateOrderPrice();
            int i = orderMapper.updateOrder(order);
            if(i > 0) {
                System.out.println("修改成功！");
            }
        }catch (Exception e){
            //删除失败，即order表中该数据对应null，即该订单没有商品，订单不成立，删除
            System.out.println("修改失败！");
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
    @Test
    public void testDeleteOrder(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        int i = orderMapper.deleteOrder(1);
        if(i > 0){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testAddOrderProduct(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        //创建订单和商品对象
        Order order = new Order(5, LocalDateTime.now(), 0.0);
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product = productMapper.getProductById(1);
        System.out.println(order);
        System.out.println(product);

        //添加商品到订单的商品列表
        order.setProducts(Arrays.asList(product));

        //插入订单
        order.calculateOrderPrice();
        orderMapper.addOrder(order);

        //获取订单和商品的ID
        int orderId = order.getOrderId();
        int productId = product.getProductId();

//      //添加订单和商品的关系
        orderMapper.addOrderProduct(orderId, productId);

        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testAddOrder() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try{
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            int i = orderMapper.addOrder(new Order(6,LocalDateTime.now(), 70));
            if(i > 0){
                System.out.println("插入成功！");
            }
        }catch (Exception e){
            System.out.println("插入失败！");
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }



}
