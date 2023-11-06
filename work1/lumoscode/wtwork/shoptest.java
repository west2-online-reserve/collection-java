package com.dong.wtwork;

public class shoptest {
    public static void main(String[] args) {
        WatermelonBooth booth1=new WatermelonBooth(1,"Lumos",100,false);
        WatermelonBooth booth2=new WatermelonBooth(2,"kevin",150,true);
        WatermelonBooth booth3=new WatermelonBooth(3,"david",30,false);
        WatermelonBooth booth4=new WatermelonBooth(4,"ilsa",70,false);
        booth1.restock(100);
        booth2.restock(150);
        booth3.restock(500);
        booth4.restock(200);
        WatermelonBooth.purchase(booth1,90);
        WatermelonBooth.purchase(booth2,35);
        WatermelonBooth.purchase(booth3,60);
        WatermelonBooth.purchase(booth4,599);
        WatermelonBooth.closeBooths(new WatermelonBooth[]{booth1,booth3});
    }
}
