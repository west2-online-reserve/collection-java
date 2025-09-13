package text1;

public class WatermelonBooth {
    private long id;
    private String name;
    private int totalWatermelons;
    private boolean isClosed;

    public WatermelonBooth(long id, String name, int tota, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.totalWatermelons = tota;
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

    public int getTotalWatermelons() {
        return totalWatermelons;
    }

    public void setTotalWatermelons(int tota) {
        this.totalWatermelons = tota;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    @Override
    public String toString() {
        return "摊号：" + id +
                "\n摊主姓名：" + name +
                "\n在售西瓜数：" + totalWatermelons +
                "\n是否休摊整改：" + (isClosed ? "是" : "否");
    }

    public static void purchase(WatermelonBooth booth, int quantity) {


        if(quantity <=0)

        {
            System.out.println("购买失败：购买的西瓜数必须为正数。");
            return;
        }
        if(booth.isClosed())

        {
            System.out.println(booth.getName() + "购买失败：商家处于休摊整改状态。");
            return;
        }
        if(quantity >booth.getTotalWatermelons())

        {
            System.out.println("购买失败：购买的西瓜数超过在售西瓜数。");
            return;
        }

        booth.setTotalWatermelons(booth.getTotalWatermelons()-quantity);
        System.out.println(booth.getName()+"成功出售了 "+quantity +" 个西瓜.");
    }

    // restock method
    public void restock(int quantity) {
        if (quantity <= 0||quantity > 200) {
            System.out.println("进货失败：单次进货量必须为正数且不超过200。");
            return;
        }


        totalWatermelons += quantity;
        System.out.println(name + "成功进货了 " + quantity + " 个西瓜.");
    }

    // Static closeBooths method
    public static void closeBooths(WatermelonBooth[] booths) {
        for (WatermelonBooth booth : booths) {
            if (!booth.isClosed()) {
                booth.setClosed(true);
                System.out.println(booth.toString());
            }
        }
    }
}
