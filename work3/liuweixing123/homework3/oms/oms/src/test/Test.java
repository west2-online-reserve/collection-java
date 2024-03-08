package test;

import util.JDBC;

import java.io.ObjectStreamException;
import java.sql.Connection;
import java.sql.Timestamp;

public class Test {
    public static void main(String[] args) throws Exception{


        //增
        //JDBC.addOrder(2,new Timestamp(System.currentTimeMillis()),-123);
        //JDBC.addProduct("香蕉",10);

        //删
        //JDBC.deleteProduct(2);
        //JDBC.deleteOrder(2);

        //改
        //String sql ="update product set product_name=?,product_price=? where product_id=? ";
        //Object[] args1 ={"火龙果",30,1};
        //JDBC.update(sql,args1);

        //String sql ="update `order` set product_id =?,order_price =? where order_id =?";
        //JDBC.update(sql,1,120,3);

        //查
        //String sql ="select product_name,product_price from `product`where product_id>? order by product_price desc";
        //JDBC.query(sql,1);


    }
}
