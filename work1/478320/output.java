package com.huayu.work;



public class WorkApplication {
    public static void main(String[] args) {
        Booth booth1 = new Booth(1L, "huayu", 50, false);
        Booth booth2 = new Booth(2L, "lianai", 30, false);
        Booth booth3 = new Booth(3L, "yusiheng", 45, false);
        System.out.println(booth1.toString());
        System.out.println(booth2.toString());
        System.out.println(booth3.toString());
        Booth.purchase(booth1,70);
        Booth.purchase(booth2,2);
        Booth.purchase(booth3,12);
        booth1.restock(500);
        booth2.restock(40);
        booth3.restock(30);
        Booth[] booths={booth1,booth2,booth3};

        Booth.closeBooths(booths);


    }
}
