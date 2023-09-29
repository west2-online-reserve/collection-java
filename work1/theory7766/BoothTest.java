package com.itheima.demo1;

public class BoothTest {
    public static void main(String[] args) {
        //测试全参构造函数
        Booth a=new Booth(1000,"张三",20,false);
        //测试toString构造函数
        System.out.println(a.toString());
        Booth b=new Booth(1001,"李四",100,true);
        //西瓜数为负数
        Booth.purchase(a,-1);
        //大于在售西瓜数
        Booth.purchase(a,25);
        //休业整改西瓜摊
        Booth.purchase(b,10);
        //成功购买
        Booth.purchase(a,2);
        System.out.println(a.toString()+'\n');
        //测试restock
        a.restock(-1);
        a.restock(201);
        a.restock(200);
        System.out.println(a.toString()+'\n');
        //测试closeBooths
        Booth c=new Booth(1002,"顾言",50,true);
        Booth []booths={a,b,c};
        Booth.closeBooths(booths);

    }
}
