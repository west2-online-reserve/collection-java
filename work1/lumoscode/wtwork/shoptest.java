package com.dong.wtwork;

public class shoptest {
    public static void main(String[] args) {
        WatermelonBooth booth1=new WatermelonBooth(1,"Lumos",100,false);

        booth1.restock(100);
        WatermelonBooth.purchase(booth1,90);
        WatermelonBooth.closeBooths(new WatermelonBooth[]{booth1});
    }
}
