package JDBC.orderManagemaentSystem;

import java.sql.SQLException;
import java.util.Scanner;
/**
 * 测试类，订单管理系统实例化，运行
 *
 * @Author 31445
 * @Date 2023/11/23
 */
public class Text {
    public static void main(String[] args) throws SQLException {
        ManagementSystem m=new ManagementSystem();
        Scanner sc=new Scanner(System.in);
        m.addProduct("A5笔记本",29);
        m.deleteProdect(31);
        m.updateProdect(1,"Java秘籍",49);
        m.addOrder(1,69);
        m.deleteOrder(2);
        m.updateOrder(1,3,159);
        m.viewProdect();
        m.viweOrder();
        //排序需要输入进行
        m.rankOrder(sc);
    }
}
