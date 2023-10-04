package com.sty;

import java.util.ArrayList;
import java.util.List;

public class test
{
    public static void main(String[] args) {
        Booth booth1 = new Booth(1001,"西瓜摊1号",500,false);
        Booth booth2 = new Booth(1002,"西瓜摊2号",100,false);
        Booth booth3 = new Booth(1003,"西瓜摊3号",90000,false);
        Booth booth4 = new Booth(1004,"西瓜摊4号",10,true);
        Booth booth5 = new Booth(1005,"西瓜摊5号",0,true);
        Booth booth6 = new Booth(1006,"西瓜摊6号",1090,true);

        ArrayList<Booth> boothArrayList = new ArrayList<>();
        boothArrayList.add(booth1);
        boothArrayList.add(booth2);
        boothArrayList.add(booth3);
        boothArrayList.add(booth4);
        boothArrayList.add(booth5);
        boothArrayList.add(booth6);
        //购买操作
        Booth.purchase(booth1,100);
        System.out.println("--------------------------------------");

        //购买数目小于0
        Booth.purchase(booth1,-1);
        System.out.println("--------------------------------------");
        //购买的数目大于西瓜数目
        Booth.purchase(booth1,200000);
        System.out.println("--------------------------------------");

        //进货
        booth1.restock(100);
        System.out.println("--------------------------------------");
        //进货超过200
        booth1.restock(300);
        System.out.println("--------------------------------------");
        //进货小于0
        booth1.restock(-9);
        System.out.println("--------------------------------------");
        //关闭店铺，打印信息
        Booth.closeBooths(boothArrayList);
        System.out.println("--------------------------------------");
    }
}
