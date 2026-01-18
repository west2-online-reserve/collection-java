package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.CommodityMapper;
import org.example.pojo.Commodity;
import org.example.pojo.MyOrder;
import org.example.service.MyOrderService;
import org.example.service.impl.CommodityServiceImpl;
import org.example.service.impl.MyOrderServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        //增加商品
        CommodityServiceImpl commodityService = new CommodityServiceImpl();
        MyOrderServiceImpl myOrderService = new MyOrderServiceImpl();
        System.out.println("请输入想进入的功能序号：1.添加功能 2.删除功能 3.修改功能 4.查询功能");
        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();
        int model=0;
        switch (key) {
            case 1:
                System.out.println("输入1，进入创建订单功能；输入2，进入新增商品功能");
                model =sc.nextInt();
                if(model==1){
                    myOrderService.add();
                }else if(model==2){
                    System.out.println("请输入商品的名称和价格，格式为‘商品,价格’");
                    String input = sc.next();
                    String name = input.split(",")[0];
                    double price = Double.parseDouble(input.split(",")[1]);
                    commodityService.add(name, price);
                }else{
                    System.out.println("输入格式有误");
                }
                break;
            case 2:
                System.out.println("输入1，进入删除订单功能；输入2，进入删除商品功能");
                model =sc.nextInt();
                if(model==1){
                    myOrderService.queryAll();
                    System.out.println("请输入您想要删除的订单id");
                    int id = sc.nextInt();
                    myOrderService.deleteById(id);
                }else if(model==2){
                    commodityService.queryAllShop();
                    System.out.println("请输入您想要删除的商品id");
                    int id = sc.nextInt();
                    commodityService.deleteById(id);
                }else{
                    System.out.println("输入格式有误");
                }
                break;
            case 3:
                System.out.println("输入1，进入修改订单功能；输入2，进入修改商品功能");
                model =sc.nextInt();
                if(model==1){
                    myOrderService.queryAll();
                    System.out.println("请输入您想要修改的订单id");
                    int id = sc.nextInt();
                    myOrderService.deleteById(id);
                    System.out.println("请重新选择您要购买的商品");
                    myOrderService.add();
                }else if(model==2){
                    //因为我订单的列表是通过查询商品在数据库中保留的信息得来的，因此不能直接删除商品，新增status来表示在售和下架
                    commodityService.queryAllShop();
                    System.out.println("请输入您想要修改的商品id");
                    int id = sc.nextInt();
                    System.out.println("请输入新的商品名称和价格，格式为‘商品,价格’");
                    String input = sc.next();
                    String name = input.split(",")[0];
                    double price = Double.parseDouble(input.split(",")[1]);
                    commodityService.deleteById(id);
                    commodityService.add(name,price);
                }else{
                    System.out.println("输入格式有误");
                }
                break;
            case 4:
                System.out.println("输入1，查询所有订单；输入2，查询所有商品");
                model =sc.nextInt();
                if(model==1){
                    System.out.println("查询所有订单中...");
                    myOrderService.queryAll();
                    System.out.println("查询完成");
                }else if(model==2){
                    System.out.println("查询所有商品中...");
                    commodityService.queryAll();
                    System.out.println("查询完成");
                }else{
                    System.out.println("输入格式有误");
                }
                break;
            default:
                System.out.println("请输入正确的操作");
        }
    }
}