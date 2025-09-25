package test;

import DAO.OrderDAO;
import informationClass.Order;
import informationClass.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderDAOTest {
    public static void main(String[] args) {
        String code = "1";
        OrderDAO orderDAO = new OrderDAO();
//        ArrayList<Product> products = new ArrayList<Product>();
//        products.add(new Product("1","机械键盘",399.0));
//        products.add(new Product("2","游戏鼠标",199.0));
//        products.add(new Product("3","HDMI高清线",39.0));
//        Order o = new Order("27", LocalDateTime.now(),"user5",products);
        orderDAO.printQueryByPage(1,10);
//        orderDAO.queryOrder(code);
//        orderDAO.queryAllOrder("desc");
//        orderDAO.addOrderDAO(o);
//        orderDAO.updateOrder(o,new Product("1","机械键盘",300.0),true);
//        orderDAO.updateOrder(o,new Product("1","机械键盘",300.0),true);
//        orderDAO.updateOrder(o,new Product("1","机械键盘",300.0),true);
//
//        orderDAO.deleteOrder(o);
//        orderDAO.updateOrder(o,new Product("2","游戏鼠标",199.00),false);
    }
}
