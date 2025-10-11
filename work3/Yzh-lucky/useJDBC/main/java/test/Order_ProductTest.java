package test;

import DAO.Order_ProductDAO;

public class Order_ProductTest {
    public static void main(String[] args) {
        Order_ProductDAO test = new Order_ProductDAO();
        //test.queryAll("asc");
//        test.queryByOrderCode("27", "asc");
        test.printQueryByPage(1, 10);
    }
}
