public class WatermelonBooth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public WatermelonBooth(long id, String name, int total, boolean isClosed) {
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

    public static void purchase(WatermelonBooth booth, int quantity) {
        if (quantity <= 0) {
            System.out.println("购买失败：购买的西瓜数必须为正数。");
            return;
        }
        if (booth.isClosed()) {
            System.out.println("购买失败：摊位处于休摊整改状态。");
            return;
        }
        if (quantity > booth.getTotal()) {
            System.out.println("购买失败：购买西瓜数不能大于在售西瓜数。");
            return;
        }
        booth.setTotal(booth.getTotal() - quantity);
        System.out.println("购买成功：成功购买" + quantity + "个西瓜。");
    }

    public void restock(int quantity) {
        if (quantity <= 0 || quantity > 200) {
            System.out.println("进货失败：单次进货量必须在1到200之间。");
            return;
        }
        this.total += quantity;
        System.out.println("进货成功：成功进货" + quantity + "个西瓜。");
    }

    public static void closeBooths(WatermelonBooth[] booths) {
        for (WatermelonBooth booth : booths) {
            if (!booth.isClosed()) {
                booth.setClosed(true);
                System.out.println("摊位" + booth.getId() + "已休业整改：" + booth.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "摊号: " + id +
                "\n摊主姓名: " + name +
                "\n在售西瓜数: " + total +
                "\n是否休摊整改: " + isClosed;
    }
    public static void main(String[] args) {
        WatermelonBooth booth1 = new WatermelonBooth(1, "张三", 100, false);
        WatermelonBooth booth2 = new WatermelonBooth(2, "李四", 50, false);

        System.out.println(booth1.toString());
        System.out.println(booth2.toString());

        WatermelonBooth.purchase(booth1, 30);
        WatermelonBooth.purchase(booth2, 60); // 这次购买将失败

        booth1.restock(50);
        booth2.restock(300); // 这次进货将失败

        WatermelonBooth.closeBooths(new WatermelonBooth[]{booth1, booth2});
    }
}
