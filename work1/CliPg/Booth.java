package com.PeanutJava.task1;

    //创建对象
public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    //构造方法
    //空参
    public Booth() {
    }
    //有参
    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    //Set/Get
    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getTotal() {
        return total;
    }


    public void setTotal(int total) {
        this.total = total;
    }


    public boolean isIsClosed() {
        return isClosed;
    }


    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String toString() {
        return "WatermelonBooth{id = " + id + ", name = " + name + ", total = " + total + ", isClosed = " + isClosed + "}";
    }

    //进货
    public void restock(int ResNums){
        if(ResNums<=200&&ResNum>=0){
            this.total+=ResNums;
            System.out.println("进货成功");
        }else{
            System.out.println("进货失败，单次进货量要在[0，200]间");
        }
    }

               //向目标摊位购买指定数量西瓜
    public static void purchase(Booth booth,int PurNums) {
            if (PurNums > 0 && !booth.isIsClosed() && PurNums <= booth.getTotal()) {
                int remain = booth.getTotal() - PurNums;
                booth.setTotal(remain);
                System.out.println("购买成功");
            } else {
                System.out.println("购买失败");
            }
        }

        //让booths中所有未被休业整改的摊位歇业，并输出已在休业整改的摊位信息
    public static void closeBooths(Booth []booths) {
            for (int i = 0; i < booths.length; i++) {
                if (!booths[i].isIsClosed()) {
                    booths[i].setIsClosed(true);
                } else {
                    System.out.println(booths[i].toString());
                }
            }

        
    }


}
