package com.amiera;


import com.amiera.mapper.objectsMapper;
import com.amiera.objects.orders;
import com.amiera.objects.products;
import com.amiera.utils.ValidationUtils;
import com.amiera.utils.utils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;


public class MyTest {
    //测试查询所有产品
    @Test
    public void testSelectAllProducts() {
        SqlSession sqlSession = null;
        try {
            sqlSession = utils.getSqlSession();
            objectsMapper mapper = sqlSession.getMapper(objectsMapper.class);
            List<com.amiera.objects.products> products = mapper.selectAllProducts();
            for (com.amiera.objects.products product : products) {
                System.out.println(product);
            }
        } catch (Exception e) {
            System.err.println("查询产品列表失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    //测试查询所有订单
    @Test
    public void testSelectAllOrders() {
        SqlSession sqlSession = null;
        try {
            sqlSession = utils.getSqlSession();
            objectsMapper mapper = sqlSession.getMapper(objectsMapper.class);
            List<com.amiera.objects.orders> orders = mapper.selectAllOrders();
            for (com.amiera.objects.orders order : orders) {
                System.out.println(order);
            }
        } catch (Exception e) {
            System.err.println("查询订单列表失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    // 测试完整流程：添加产品然后添加订单（带验证）
    @Test
    public void testCompleteFlowWithValidation() {
        SqlSession sqlSession = utils.getSqlSession();
        objectsMapper mapper = sqlSession.getMapper(objectsMapper.class);

        try {
            // 1. 添加产品
            products product = new products();
            product.setProduct_name("验证测试产品2");
            product.setPrice(120.50); // 正确精度的价格
            mapper.insertProducts(product);
            System.out.println("添加产品成功，产品ID: " + product.getProduct_id());

            // 2. 创建订单
            orders order = new orders();
            order.setOrder_time(new java.util.Date());
            order.setOrder_price(120.50); // 与产品价格匹配
            order.setOrder_information("有效订单信息");
            order.setProduct_id(product.getProduct_id());

            // 3. 查询商品并验证订单信息
            products dbProduct = mapper.selectProductById(order.getProduct_id());
            ValidationUtils.validateOrderInfo(order, dbProduct);

            // 4. 验证通过后插入订单
            mapper.insertOrders(order);
            System.out.println("添加订单成功，订单ID: " + order.getOrder_id());

            // 5. 查询并显示结果
            List<products> productsList = mapper.selectAllProducts();
            List<orders> ordersList = mapper.selectAllOrders();

            System.out.println("\n=== 添加后的产品列表 ===");
            for (products p : productsList) {
                System.out.println(p.getProduct_id() + ": " + p.getProduct_name() + " - " + p.getPrice());
            }

            System.out.println("\n=== 添加后的订单列表 ===");
            for (orders o : ordersList) {
                System.out.println(o.getOrder_id() + ": " + o.getOrder_time() + " - " + o.getOrder_price() + " (产品ID: " + o.getProduct_id() + ")");
            }

            sqlSession.commit();
        } catch (IllegalArgumentException e) {
            System.err.println("验证失败: " + e.getMessage());
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 测试删除产品
    // 增加功能：删除时检查是否有订单依赖该产品
    @Test
    public void testDeleteProduct() {
        SqlSession sqlSession = utils.getSqlSession();
        objectsMapper mapper = sqlSession.getMapper(objectsMapper.class);

        try {
            //先输出列表
            List<products> productsList = mapper.selectAllProducts();
            for (products p : productsList) {
                System.out.println(p.getProduct_id() + ": " + p.getProduct_name() + " - " + p.getPrice());
            }
            // 请根据实际存在的产品ID修改这个值
            int productId = 1; // 这里设置你要删除的产品ID
            System.out.println("要删除的产品id: " + productId);

            // 先验证产品是否存在
            products existingProduct = mapper.selectProductById(productId);
            if (existingProduct == null) {
                System.err.println("产品ID " + productId + " 不存在");
                return;
            }
            // 检查是否有订单依赖该产品，若有则提示用户先删除订单
            orders existingOrder = mapper.selectOrderById(productId);
            if (existingOrder == null) {
                mapper.deleteProducts(productId);
                System.out.println("删除产品成功，产品ID: " + productId);
            } else {
                System.err.println("产品ID " + productId + " 已被订单 " + existingOrder.getOrder_id() + " 使用，无法删除,请先删除对应订单");
                return;
            }

            //查询并显示结果
            List<products> AfterDeleteproductsList = mapper.selectAllProducts();

            System.out.println("\n=== 删除后的产品列表 ===");
            for (products p : AfterDeleteproductsList) {
                System.out.println(p.getProduct_id() + ": " + p.getProduct_name() + " - " + p.getPrice());
            }
            sqlSession.commit();

        } catch (Exception e) {
            System.err.println("删除产品失败: " + e.getMessage());
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 测试删除订单
    @Test
    public void testDeleteOrder() {
        SqlSession sqlSession = utils.getSqlSession();
        objectsMapper mapper = sqlSession.getMapper(objectsMapper.class);

        try {
            //先输出列表
            List<orders> ordersList = mapper.selectAllOrders();
            for (orders o : ordersList) {
                System.out.println(o.getOrder_id() + ": " + o.getOrder_time() + " - " + o.getOrder_price() + " (产品ID: " + o.getProduct_id() + ")");
            }
            // 请根据实际存在的订单ID修改这个值
            int orderId = 3; // 这里设置你要删除的订单ID
            System.out.println("要删除的订单id: " + orderId);

            // 先验证订单是否存在
            orders existingOrder = mapper.selectOrderById(orderId);
            if (existingOrder == null) {
                System.err.println("订单ID " + orderId + " 不存在");
                return;
            }
            mapper.deleteOrders(orderId);
            System.out.println("删除订单成功，订单ID: " + orderId);

            //查询并显示结果
            List<orders> AfterDeleteordersList = mapper.selectAllOrders();

            System.out.println("\n=== 删除后的订单列表 ===");
            for (orders o : AfterDeleteordersList) {
                System.out.println(o.getOrder_id() + ": " + o.getOrder_time() + " - " + o.getOrder_price() + " (产品ID: " + o.getProduct_id() + ")");
            }
            sqlSession.commit();

        } catch (Exception e) {
            System.err.println("删除订单失败: " + e.getMessage());
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 测试更新订单
    @Test
    public void testUpdateOrder() {
        SqlSession sqlSession = utils.getSqlSession();
        objectsMapper mapper = sqlSession.getMapper(objectsMapper.class);

        try {
            //先输出列表
            List<orders> ordersList = mapper.selectAllOrders();
            for (orders o : ordersList) {
                System.out.println(o.getOrder_id() + ": " + o.getOrder_time() + " - " + o.getOrder_price() + " (产品ID: " + o.getProduct_id() + ")");
            }
            // 请根据实际存在的订单ID修改这个值
            int orderId = 1; // 这里设置你要更新的订单ID
            System.out.println("要更新的订单id: " + orderId);

            // 先验证订单是否存在
            orders existingOrder = mapper.selectOrderById(orderId);
            if (existingOrder == null) {
                System.err.println("订单ID " + orderId + " 不存在");
                return;
            }
            // 更新订单信息
            existingOrder.setOrder_price(200.00);
            // 调用ValidationUtils验证订单信息
            ValidationUtils.validateOrderInfo(existingOrder, mapper.selectProductById(existingOrder.getProduct_id()));
            existingOrder.setOrder_information("更新后的订单信息"); // 更新订单信息
            mapper.updateOrders(existingOrder);
            System.out.println("更新订单成功，订单ID: " + orderId);

            //查询并显示结果
            orders updatedOrder = mapper.selectOrderById(orderId);
            System.out.println("\n=== 更新后的订单详情 ===");
            System.out.println(updatedOrder.getOrder_id() + ": " + updatedOrder.getOrder_time() + " - " + updatedOrder.getOrder_price() + " (产品ID: " + updatedOrder.getProduct_id() + ")");
            System.out.println("订单信息: " + updatedOrder.getOrder_information());
            sqlSession.commit();

        } catch (Exception e) {
            System.err.println("更新订单失败: " + e.getMessage());
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 测试更新商品
    @Test
    public void testUpdateProduct() {
        SqlSession sqlSession = utils.getSqlSession();
        objectsMapper mapper = sqlSession.getMapper(objectsMapper.class);

        try {
            //先输出列表
            List<products> productsList = mapper.selectAllProducts();
            for (products p : productsList) {
                System.out.println(p.getProduct_id() + ": " + p.getProduct_name() + " - " + p.getPrice());
            }
            // 请根据实际存在的商品ID修改这个值
            int productId = 3; // 这里设置你要更新的商品ID
            System.out.println("要更新的商品id: " + productId);

            // 先验证商品是否存在
            products existingProduct = mapper.selectProductById(productId);
            if (existingProduct == null) {
                System.err.println("商品ID " + productId + " 不存在");
                return;
            }
            // 更新商品信息
            existingProduct.setPrice(150.00);
            mapper.updateProducts(existingProduct);
            System.out.println("更新商品成功，商品ID: " + productId);

            //查询并显示结果
            products updatedProduct = mapper.selectProductById(productId);
            System.out.println("\n=== 更新后的商品详情 ===");
            System.out.println(updatedProduct.getProduct_id() + ": " + updatedProduct.getProduct_name() + " - " + updatedProduct.getPrice());
            sqlSession.commit();

        } catch (Exception e) {
            System.err.println("更新商品失败: " + e.getMessage());
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
