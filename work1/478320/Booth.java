package com.huayu.work;


public class Booth {
    @Override
    public String toString() {
        return "Booth{" +
                "boothNumber=" + boothNumber +
                ", name='" + name + '\'' +
                ", saleNumber=" + saleNumber +
                ", rectification=" + rectification +
                '}';
    }

    private long boothNumber;

    public long getBoothNumber() {
        return boothNumber;
    }

    public void setBoothNumber(long boothNumber) {
        this.boothNumber = boothNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }

    public boolean isRectification() {
        return rectification;
    }

    public void setRectification(boolean rectification) {
        this.rectification = rectification;
    }

    private String name;
    private  int saleNumber;
    private boolean rectification;

    public Booth(long boothNumber,String name,int saleNumber,boolean rectification){
        this.boothNumber=boothNumber;
        this.name=name;
        this.saleNumber=saleNumber;
        this.rectification=rectification;

    }
    public static void purchase(com.huayu.work.Booth name, int num){
        if (num<=0){
            System.out.println("购买数值出错购买失败");
            return;
        }
        else if (name.isRectification()==true){
            System.out.println("休摊整改购买失败");
            return;
        }
        else if(num> name.saleNumber){
            System.out.println("购买数量过多，购买失败");
            return;
        }
        else{
            name.saleNumber= name.saleNumber-num;
            System.out.println("购买成功");
            return;
        }

    }
    public void restock(int inport){
        if (inport<=200 && inport>0){
            saleNumber=saleNumber+inport;
            System.out.println("进货成功");
        }
        else {
            System.out.println("进货失败");
        }

    }
    public static void closeBooths(com.huayu.work.Booth[]booths){

        for (com.huayu.work.Booth booth:booths){

            if (booth.isRectification()==false) {
                booth.setRectification(true);
                System.out.println(booth.toString());

            }

        }}
}
