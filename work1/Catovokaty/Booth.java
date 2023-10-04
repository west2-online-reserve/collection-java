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

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public static void purchase(Booth booth, int purchaseQuantity) {
        if (purchaseQuantity <= 0) {
            System.out.println("购买数量必须为正数。购买失败。");
            return;
        }

        if (booth.isClosed()) {
            System.out.println("摊位处于休摊整改状态。购买失败。");
            return;
        }

        if (purchaseQuantity > booth.getTotal()) {
            System.out.println("购买数量大于在售西瓜数。购买失败。");
            return;
        }

        booth.setTotal(booth.getTotal() - purchaseQuantity);
        System.out.println("成功购买了" + purchaseQuantity + "个西瓜。");
    }

    public void restock(int restockQuantity) {
        if (restockQuantity > 200) {
            System.out.println("进货量不能超过200。进货失败。");
            return;
        }
    if(restockQuantity<0) {
            System.out.println("进货量不能为负数，进货失败。")；}
                else if(0<restockQuantity<200){
        System.out.println("成功进货了" + restockQuantity + "个西瓜。");
        total += restockQuantity;
        
    }

    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.isClosed()) {
                booth.setClosed(true);
                System.out.println("摊位 " + booth.getId() + " 已休业整改。");
            }
        }
    }

    @Override
    public String toString() {
        return "摊位信息：" +
                "\n摊号: " + id +
                "\n摊主姓名: " + name +
                "\n在售西瓜数: " + total +
                "\n是否休摊整改: " + isClosed;
    }
}
