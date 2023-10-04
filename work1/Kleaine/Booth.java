package com.Jay.work1;

public class Booth {
    private long id; //摊号
    private String name; //摊主姓名
    private int total; //在售数量
    private boolean isClosed; //是否在接受整改

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

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public static void purchase(Booth seller, int quantity) {
        if (quantity <= 0 || seller.isClosed() || quantity > seller.getTotal()) {
            System.out.println("购买的西瓜数必须为正数!");
            return;
        }
        seller.setTotal(seller.getTotal() - quantity);
        System.out.println("成功从摊位 " + seller.getId() + " 购买了 " + quantity + " 个西瓜");
    }

    public void restock(int quantity) {
        if (quantity <= 0) {
            System.out.println("进货数量必须为正数");
            return;
        }
        if (quantity > 200) {
            System.out.println("进货失败,单次进货量不能超过200");
            return;
        }
        this.total += quantity;
        System.out.println("成功为摊位 " + this.getId() + " 进货了 " + quantity + " 个西瓜");
    }

    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.isClosed()) {
                booth.setClosed(true);
                System.out.println("摊位 " + booth.getId() + " 已休业整改");
                System.out.println(booth.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "摊位信息：" +
                "摊号=" + id +
                ", 摊主姓名='" + name + '\'' +
                ", 在售西瓜数=" + total +
                ", 是否休摊整改=" + isClosed;
    }
}
