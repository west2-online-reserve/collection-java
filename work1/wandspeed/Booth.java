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

    public Booth() {
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

    public boolean GetisClosed() {
        return isClosed;

    }

    public void setIsClosed(boolean isClosed) {

        this.isClosed = isClosed;
    }
    public static void purchase(Booth booth, int purchaseQuantity) {
        if (purchaseQuantity <= 0) {
            System.out.println("购买的西瓜数量必须为正数!");
            return;
        }

        if (booth.GetisClosed()) {
            System.out.println("摊主处于休摊整改状态，购买失败!");
            return;
        }

        if (purchaseQuantity > booth.getTotal()) {
            System.out.println("购买的西瓜数量超过了在售西瓜数，购买失败!");
            return;
        }

        booth.setTotal(booth.getTotal() - purchaseQuantity);
        System.out.println("成功购买" + purchaseQuantity + "个西瓜!");
    }

    public void restock(int restockQuantity) {
        if (restockQuantity <= 0) {
            System.out.println("进货的西瓜数量必须为正数!");
            return;
        }

        if (restockQuantity > 200) {
            System.out.println("单次进货量不能超过200个，进货失败!");
            return;
        }

        this.total += restockQuantity;
        System.out.println("成功进货" + restockQuantity + "个西瓜!");
    }

    public static void closeBooths(Booth[] booths) {
        int len=booths.length;
        for (int i=0;i<len;i++) {
            if (!booths[i].GetisClosed()) {
                booths[i].setIsClosed(true);
                System.out.println(booths[i].toString());
            }
        }
    }

    @Override
    public String toString() {
        return "摊号: " + id +
                ", 摊主姓名: " + name +
                ", 在售西瓜数: " + total +
                ", 是否休摊整改: " + isClosed;
    }

}
