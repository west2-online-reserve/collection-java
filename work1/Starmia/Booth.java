package com.jyy.exam;

public class Booth {
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
    public boolean getIsClosed() {
        return isClosed;
    }
    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    //重写toString()
    public String toString() {
        return "这个西瓜摊摊号为"+id+"，摊主姓名为"+name+"，在售西瓜数为"+total+"，是否休摊整改为"+isClosed;
    }

    //购买西瓜
    public static void purchase(Booth business, int nums) {
        if (nums <= 0) {
            System.out.println("购买的西瓜数必须为正数，购买失败！");
        }else if (business.isClosed) {
            System.out.println("商家正在休摊整改，购买失败！");
        }else if (nums > business.total) {
            System.out.println("购买西瓜数不能大于在售西瓜数，购买失败！");
        }else {
            business.total -= nums;
            System.out.println("购买成功！");
        }
    }

    //为摊位进货
    public void restock(int nums) {
        if (nums > 200) {
            System.out.println("单次进货量不得超过200，进货失败！");
        }else if (nums <= 0) {
            System.out.println("单次进货量必须为正数，进货失败！");
        }else {
            total += nums;
            System.out.println("进货成功！");
        }
    }

    //让所有未被休摊整改的西瓜摊休业
    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.isClosed) {
                booth.isClosed = true;
            }
        }
    }
}
