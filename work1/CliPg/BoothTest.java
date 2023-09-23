package com.PeanutJava.task1;


public class BoothTest {
    public static void main(String[] args) {
        //摊位1
        Booth booth1 = new Booth(1,"PG",100,false);
        //摊位2
        Booth booth2 = new Booth(2,"KL",200,true);
        //摊位数组
        Booth []booths={booth1,booth2};

        booth1.purchase(booth1,50);
        booth2.restock(200);
        System.out.println(booth1.toString());
        booth2.closeBooths(booths);
    }


}
