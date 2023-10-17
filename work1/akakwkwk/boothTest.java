package com.li.work;

public class boothTest {
    public static void main(String[] args) {
        Booth booth=new Booth(666,"福大陈冠希",50,false);
        System.out.println(booth);
        Booth.purchase(booth,20);
        System.out.println(booth);
        Booth.purchase(booth,80);
        System.out.println(booth);
        Booth.purchase(booth,-20);
        System.out.println(booth);
        booth = new Booth(666, "福大陈冠希",50, true );
        System.out.println(booth);
        booth.restock(150);
        System.out.println(booth);
        booth.restock(-14);
        System.out.println(booth);

    }
}
