package com.wjord.service;

import com.wjord.dao.BuyerDao;
import com.wjord.dao.OrderDao;
import com.wjord.dao.ProductDao;
import com.wjord.info.Buyer;
import com.wjord.info.Order;
import com.wjord.info.Product;
import com.wjord.methods.RandomBarcodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    //TODO:使用注解的方式进行校验
    @Autowired
    private Order order;
    @Autowired
    private Product product;
    @Autowired
    private Buyer buyer;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private BuyerDao buyerDao;
    @Autowired
    private ProductDao productDao;

    //校验器
    private boolean isValidPhoneNumber(String phoneNumber) {
        // 使用正则表达式验证手机号码格式
        String regex = "^1[3|4|5|7|8][0-9]\\d{8}$";
        return Pattern.matches(regex, phoneNumber);
    }

    private boolean isValidOrderCode(String orderCode) {
        // 使用正则表达式验证手机号码格式
        String regex = "^[0-9]{12}$";
        return Pattern.matches(regex, orderCode);
    }

    private boolean isValidProductCode(String productCode) {
        // 使用正则表达式验证手机号码格式
        String regex = "^[0-9]{13}$";
        return Pattern.matches(regex, productCode);
    }

    private boolean isValidOrder(int orderAmount, BigDecimal orderPrice) {
        if (orderAmount < 0 && orderAmount != -1) {
            return false;
        }
        return orderPrice != null && orderPrice.compareTo(BigDecimal.ZERO) >= 0;
    }

    /**
     * 添加订单的方法，进行了校验参数，并且校验产品是否存在，买家是否存在
     *
     * @param productName
     * @param buyerPhone
     * @param orderAmount
     * @param orderPrice
     */
    @Override
    public void insertOrder(String productName, String buyerPhone, int orderAmount, BigDecimal orderPrice) {
        if (!isValidPhoneNumber(buyerPhone)) {
            System.out.println("创建失败,请输入正确的手机号码");
            return;
        }
        if (!isValidOrder(orderAmount, orderPrice)) {
            System.out.println("创建失败,请输入正确的订单信息");
            return;
        }
        if (buyerDao.selectBuyerByPhone(buyerPhone) != null) {
            order.setBuyerPhone(buyerPhone);
        } else {
            System.out.println("创建失败，该客户不存在");
            return;
        }
        order.setOrderCode(new RandomBarcodeGenerator().generateRandomBarcode());
        if (productDao.selectProductByName(productName) != null) {
            order.setProductCode(productDao.selectProductByName(productName).getProductCode());
        } else {
            System.out.println("创建失败,该产品不存在");
            return;
        }
        order.setOrderAmount(orderAmount);
        order.setOrderPrice(orderPrice);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        orderDao.insertOrder(order);
        System.out.println("成功创建了一个订单");
    }

    /**
     * 更新订单的方法，校验了各种参数
     *
     * @param orderCode
     * @param productName
     * @param buyerPhone
     * @param orderAmount
     * @param orderPrice
     */
    @Override
    public void updateOrder(String orderCode, String productName, String buyerPhone, int orderAmount, BigDecimal orderPrice) {
        //校验参数格式
        if (!isValidPhoneNumber(buyerPhone)) {
            System.out.println("更新失败,请输入正确的手机号码");
            return;
        }
        if (!isValidOrderCode(orderCode)) {
            System.out.println("更新失败,请输入正确的订单号");
            return;
        }

        if (selectOrder(orderCode) != null) {
            //校验信息
            if (productName != null) {
                //校验更新的产品是否存在
                if (productDao.selectProductByName(productName) != null) {
                    order.setProductCode(productDao.selectProductByName(productName).getProductCode());
                } else {
                    System.out.println("更新失败,该产品不存在");
                    return;
                }
            } else {
                order.setProductCode(orderDao.selectOrderByCode(orderCode).getProductCode());
            }
            if (buyerPhone != null) {
                //校验买家是否存在
                if (buyerDao.selectBuyerByPhone(buyerPhone) != null) {
                    order.setBuyerPhone(buyerDao.selectBuyerByPhone(buyerPhone).getBuyerPhone());
                } else {
                    System.out.println("该客户不存在");
                    return;
                }
            } else {
                order.setBuyerPhone(orderDao.selectOrderByCode(orderCode).getBuyerPhone());
            }
            //校验数量是否合理
            if (orderAmount >= 0) {
                order.setOrderAmount(orderAmount);
            } else {
                order.setOrderAmount(orderDao.selectOrderByCode(orderCode).getOrderAmount());
            }
            //校验价格是否合理
            if (orderPrice != null) {
                order.setOrderPrice(orderPrice);
            } else {
                order.setOrderPrice(orderDao.selectOrderByCode(orderCode).getOrderPrice());
            }
            order.setCreateTime(orderDao.selectOrderByCode(orderCode).getCreateTime());
            order.setUpdateTime(orderDao.selectOrderByCode(orderCode).getUpdateTime());
            orderDao.updateOrderByCode(orderCode, order);
            System.out.println("成功更新了一个订单");
        } else {
            System.out.println("更新失败,该订单不存在");
        }
    }

    /**
     * @param orderCode
     */
    @Override
    public void deleteOrder(String orderCode) {
        if (!isValidOrderCode(orderCode)) {
            System.out.println("删除失败,请输入正确的订单号");
            return;
        }
        if (selectOrder(orderCode) != null) {
            orderDao.deleteOrderByCode(orderCode);
            System.out.println("成功删除了一个订单");
        } else {
            System.out.println("删除失败,该订单不存在");
        }
    }

    /**
     * @param orderCode
     * @return
     */
    @Override
    public Order selectOrder(String orderCode) {
        if (!isValidOrderCode(orderCode)) {
            System.out.println("查询失败,请输入正确的订单号");
            return null;
        }
        Order selected = orderDao.selectOrderByCode(orderCode);
        return selected;
    }

    @Override
    public List<Order> selectAllOrders() {
        List<Order> orders = orderDao.selectAllOrders();
        return orders;
    }

    @Override
    public int selectTotalOrderCount() {
        int selected = orderDao.selectTotalOrderCount();
        return selected;
    }

    @Override
    public int selectTotalOrderCountByProductCode(String productCode) {
        if (!isValidProductCode(productCode)) {
            System.out.println("查询失败,请输入正确的产品号");
            return 0;
        }
        int selected = orderDao.selectTotalOrderCountByProductCode(productCode);
        return selected;
    }

    @Override
    public int selectTotalOrderCountByProductName(String productName) {
        if (productDao.selectProductByName(productName) == null) {
            System.out.println("请输入正确的产品名");
            return 0;
        }
        String productCode = productDao.selectProductByName(productName).getProductCode();
        List<Order> orders = orderDao.selectOrdersByProductCode(productCode);
        return orders.size();
    }

    @Override
    public int selectTotalOrderCountByBuyerPhone(String buyerPhone) {
        if (!isValidPhoneNumber(buyerPhone)) {
            System.out.println("查询失败,请输入正确的手机号码");
            return 0;
        }
        int selected = orderDao.selectTotalOrderCountByBuyerPhone(buyerPhone);
        return selected;
    }

    @Override
    public List<Order> selectOrdersByProductName(String productName) {
        if (productDao.selectProductByName(productName) != null) {
            order.setProductCode(productDao.selectProductByName(productName).getProductCode());
        } else {
            System.out.println("创建失败,该产品不存在");
            return null;
        }
        if (productDao.selectProductByName(productName) == null) {
            System.out.println("请输入正确的产品名");
            return null;
        }
        String productCode = productDao.selectProductByName(productName).getProductCode();
        List<Order> orders = orderDao.selectOrdersByProductCode(productCode);
        return orders;
    }

    @Override
    public List<Order> selectOrdersByProductCode(String productCode) {
        if (!isValidProductCode(productCode)) {
            System.out.println("查询失败,请输入正确的产品号");
            return null;
        }
        List<Order> selected = orderDao.selectOrdersByProductCode(productCode);
        return selected;
    }

    @Override
    public List<Order> selectOrdersByBuyerPhone(String buyerPhone) {
        if (!isValidPhoneNumber(buyerPhone)) {
            System.out.println("查询失败,请输入正确的手机号码");
            return null;
        }
        List<Order> selected = orderDao.selectOrdersByBuyerPhone(buyerPhone);
        return selected;
    }

    @Override
    public List<Order> selectSortedOrderByPrice() {
        List<Order> orders = orderDao.sortOrderByPrice();
        return orders;
    }

    @Override
    public List<Order> selectSortedOrderByUpdateTime() {
        List<Order> orders = orderDao.sortOrderByUpdateTime();
        return orders;
    }


}
