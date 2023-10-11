package com.diheima.demo1.xiguatan;

import java.util.Scanner;

public class BoothTest {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);;
        int purchaseQuantity;//购买数目
        int restockQuantity;//进货数
        //建立一新的西瓜摊类
        Booth bo= new Booth(1,"大大大大西瓜摊",110,false);
        Booth bo2= new Booth(2,"大大大西瓜摊",100,false);
        Booth bo3= new Booth(3,"大大西瓜摊",90,false);
        Booth bo4= new Booth(4,"大西瓜摊",10,false);

        System.out.println(bo.toString());
        //测试get和 toString()
        bo.getId();
        bo.getName();
        bo.getIsClosed();
        bo.getTotal();
        //测试get和 toString()
        bo.setIsClosed(true);
        bo.setId(555);
        bo.setName("瓜奇异");
        bo.setTotal(555);
        System.out.println(bo.toString());
        //测试purchase
        bo.setIsClosed(false);
        System.out.println(bo.toString());
        purchaseQuantity=sc.nextInt();
        bo.purchase(bo,purchaseQuantity);
        //测试restock
        restockQuantity=sc.nextInt();
        bo.restock(restockQuantity);
        System.out.println(bo.toString());
        //测试 closeBooths
        System.out.println("测试 closeBooths后");
        bo.closeBooths (new Booth[]{bo,bo2,bo3,bo4});
    }
}
