package com.haha.Controller;

import com.haha.Connection.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;

public class Select {
    //从数据库中获取数据
    public static Object[][] getGoods(String sql) throws SQLException {
        ResultSet resultSet= DbConnection.query(sql);
        ArrayList<com.haha.Goods.Goods> list=new ArrayList<com.haha.Goods.Goods>();
        try{
            while(resultSet.next()){
                com.haha.Goods.Goods goods=new com.haha.Goods.Goods();
                goods.setGoodsID( resultSet.getInt(1));
                goods.setGoodsName(resultSet.getString(2));
                goods.setPrice(resultSet.getDouble(3));
                goods.setRestNum(resultSet.getInt(4));
                list.add(goods);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        Object[][] object =new Object[list.size()][4];
        for(int i=0;i<list.size();i++){
            object[i][0]=list.get(i).getGoodsID();
            object[i][1]=list.get(i).getGoodsName();
            object[i][2]=list.get(i).getPrice();
            object[i][3]=list.get(i).getRestNum();
        }
        return object;
    }

    public static Object[][] getOrders(String sql) throws SQLException {
        ResultSet resultSet= DbConnection.query(sql);
        ArrayList<com.haha.Order.Order> list=new ArrayList<com.haha.Order.Order>();
        try{
            while(resultSet.next()){
                LocalDateTime localDateTime=LocalDateTime.parse(resultSet.getString(2));
                com.haha.Order.Order order=new com.haha.Order.Order();
                order.setOrderID(resultSet.getInt(1));
                order.setOrderTime(localDateTime);
                order.setGoodsName(resultSet.getString(3));
                order.setNum(resultSet.getInt(5));
                order.setProfit(resultSet.getInt(5)*resultSet.getDouble(4));
                order.setManager((resultSet.getString(6)));
                list.add(order);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        Object[][] object =new Object[list.size()][6];
        for(int i=0;i<list.size();i++){
            object[i][0]=list.get(i).getOrderID();
            object[i][1]=list.get(i).getOrderTime();
            object[i][2]=list.get(i).getGoodsName();
            object[i][3]=list.get(i).getNum();
            object[i][4]=list.get(i).getProfit();
            object[i][5]=list.get(i).getManager();
        }
        return object;
    }
}
