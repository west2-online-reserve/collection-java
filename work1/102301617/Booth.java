public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

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
        return "摊号: " + id + "\n摊主姓名: " + name + "\n在售西瓜数: " + total;
    }

    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    public static void purchase(Booth booth, int quantity) {
        if (quantity > 0 && !booth.isClosed && quantity <= booth.getTotal()) {
            booth.setTotal(booth.getTotal() - quantity);
            System.out.println("购买成功！已从摊位 " + booth.getId() + " 购买了 " + quantity + "个西瓜。");
        } else {
            System.out.println("购买失败！请检查购买数量是否合法或摊位是否处于休摊整改状态。");
        }
    }
    public void restock(int quantity) {
        if (quantity <= 200&&quantity>=0) {
            this.setTotal(this.getTotal() + quantity);
            System.out.println("进货成功！已为摊位 " + this.getId() + " 进货了 " + quantity + "个西瓜。");
        } else {
            System.out.println("进货失败！单次进货量不能超过200且不能小于零");
        }
    }
    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.isClosed) {
                booth.isClosed = true;
                System.out.println(booth.toString());
            }
        }
    }
}
