package com.diheima.demo1.xiguatan;

public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public Booth() {
        this.id = -1;
        this.name = "wu";
        this.total = 0;
        this.isClosed = false;
    }

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

    public String toString() {
        return "Booth{id = " + id + ", name = " + name + ", total = " + total + ", isClosed = " + isClosed + "}";
    }

    public static void purchase(Booth booth, int purchaseQuantity) {
        if (purchaseQuantity <= 0 || booth.getIsClosed() || purchaseQuantity > booth.getTotal()) {
            System.out.println("购买失败！");
            return;
        }

        booth.setTotal(booth.getTotal() - purchaseQuantity);
        System.out.println("成功购买 " + purchaseQuantity + " 个西瓜。");
    }

    public void restock(int restockQuantity) {
        if (restockQuantity>200) System.out.println("进货失败！");
        else {
            this.total +=restockQuantity;
            System.out.println("该摊位成功进货"+restockQuantity+"个西瓜");
        }
    }

    public static void closeBooths (Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.getIsClosed()) {
                booth.setIsClosed(true);
                System.out.println(booth.toString());
            }
        }
    }
}
