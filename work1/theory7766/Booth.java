package com.itheima.demo1;

public class Booth{
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }


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

    public boolean isIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String toString() {
        String s1;
        if (this.isClosed){
            s1="该西瓜摊已休摊整改";
        }else {
            s1="该西瓜摊正在营业";
        }
        return "摊号：" + id + "\n摊主名字：" + name + "\n在售西瓜数：" + total + '\n' +s1+'\n';
    }
    //向目标摊位卖家购买指定数量的西瓜
    public static void purchase(Booth busniess,int number){
        if (number<=0){
            System.out.println("西瓜数量必须为正数！");
        }else if (busniess.isClosed){
            System.out.println(busniess.name+"家的西瓜摊今天休摊整改！");
        }else if (number>busniess.total){
            System.out.println("在售西瓜数小于所需西瓜！");
        }else {
            System.out.println("您已经成功购买"+number+"个西瓜！");
            busniess.total-=number;
        }
    }
    //进货实例函数
    public void restock(int number){
        if (number<=0||number>200){
            System.out.println("西瓜数量错误，进货失败！");
        }else {
            this.total+=number;
        }
    }
    public static void closeBooths (Booth[] booths){
        for (int i = 0; i < booths.length; i++) {
            if (booths[i].isClosed) {
                System.out.println(booths[i].toString());
            } else {
                booths[i].isClosed = true;
            }
        }
    }

}
