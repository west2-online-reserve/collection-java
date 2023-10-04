package com.jyy.exam;


public class BoothTest {
    public static void main(String[] args) {
        Booth booth1 = new Booth(123, "jyy", 200, false);
        Booth booth2 = new Booth(321, "yyj", 222, false);
        System.out.println(booth1.toString());
        System.out.println(booth2.toString());

        //购买
        Booth.purchase(booth1,100);
        Booth.purchase(booth1,-100);
        Booth.purchase(booth1,300);
        booth1.setIsClosed(true);

        //进货
        booth2.restock(100);
        booth2.restock(300);
        booth2.restock(-100);

        Booth[] booths = {booth1,booth2};

        //整改后
        for (Booth booth : booths) {
            Booth.closeBooths(booths);
            System.out.println(booth);
        }
    }
}
