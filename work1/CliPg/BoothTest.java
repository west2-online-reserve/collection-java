package com.PeanutJava.task1;


public class BoothTest {
    public static void main(String[] args) {
        Booth b1 = new Booth(1,"KevinDurant", 200, true);
        Booth b2 = new Booth(34,"PaulGeorge", 2002, true);
        Booth b3 = new Booth(90,"LeBronJames", 2300, false);
        Booth b4 = new Booth(121,"KyrieIrving", 2040, true);
        Booth b5 = new Booth(291,"LukaMagic", 2800, false);
        Booth b6 = new Booth(321,"RusellWestbrook", 2002, true);
        Booth[] booths = {b1, b2, b3, b4, b5, b6};

        System.out.println(b1);
        b1.purchase(b1,300);
        b3.purchase(b3,300);
        b1.purchase(b1,-300);
        b2.purchase(b2,300);
        b3.restock(30);
        b3.restock(-30);
        b3.restock(2000);
        for (Booth booth : booths) {
            booth.closeBooths(booths);
            System.out.println(booth);
        }
    }


}
