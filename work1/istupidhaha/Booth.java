package com.west2.work;

public class Booth {
    //私有变量
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    //上述变量对应的 get 和 set 方法

    public long getId() {
        return id;
    }

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

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    @Override
    public String toString() {
        return "WaterMelonStall's id:" + id + "\nowner's name:" + name + "\nSold in watermelon:" + total + "\nWhether it is currently closed:" + isClosed + " \n";
    }

    //构造方法
    //芝士空参的
    public Booth() {
    }

    //芝士有参的
    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    public static void purchase(Booth booth  , int outnum) {
        if (outnum > 0 && booth.isClosed == false && outnum <= booth.total){
            System.out.println("西瓜数量足够，购买" + outnum +"个西瓜成功");
            booth.total -= outnum;
        }else if (outnum < 0){
            System.out.println("购买量不可以为负数，购买失败");
        } else if (outnum > booth.total ) {
            System.out.println("购买量大于西瓜总数，购买失败");
        } else if (booth.isClosed()) {
            System.out.println("该西瓜摊已休摊整改，购买失败");
        }

    }


    public void restock(Booth booth,int innum) {
        if (innum > 0 && innum <=200) {
            booth.total += innum;
            System.out.println("进货" + innum + "个西瓜成功");
        }else if (innum < 0){
            System.out.println("进货量为负数，进货失败");
        }else {
            System.out.println("进货量大于200，进货失败");
        }
    }

    public static void closeBooths(Booth[] booths) {
        int x =booths.length;
        for (int i = 0; i < booths.length; i++) {
            if (booths[i].isClosed==false){
                booths[i].isClosed=true;
                System.out.println("已成功关闭该摊位");
                System.out.println(booths[i].toString());
            }else {
                System.out.println("该摊位已休业整改");
                System.out.println(booths[i].toString());
            }
        }
    }









}
