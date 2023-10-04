package com.li.work;

public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    @Override
    public String toString() {
        return "摊号: " + id + "\n摊主姓名: " + name + "\n在售西瓜数: " + total + "\n是否休摊整改" + isClosed;
    }
    //一个重写的 toString()方法来输出 该西瓜摊的所有信息

    public long getId() {
        return id;
    }

    public void setId(long boothId) {
        this.id = boothId;
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

    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    //一个接受摊号(long)、摊主姓名(name) 、在售西瓜数(int)、是否休摊整改 (boolean)作为参数的构造方法

    public static void purchase(Booth booth, int num) {
        if (num <= 0 || num > booth.total || booth.isClosed) {
            System.out.println("购买失败");
        } else booth.total -= num;
        System.out.println("购买成功");
        return;
    }

    public int restock(int restockNumber) {
        if (restockNumber > 200 || restockNumber < 0) {
            System.out.println("进货失败");
        } else total += restockNumber;
        return total;
    }

    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.isClosed()) {
                booth.setClosed(true);
                System.out.println("摊位 " + booth.getId() + " 已休业整改。");
                System.out.println(booth.toString());
            }
        }
    }
}

