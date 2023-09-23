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

    public static void purchase(Booth booth, int quantity) {
        if (quantity > 0 && !booth.isClosed && quantity <= booth.total) {
            booth.setTotal(booth.getTotal() - quantity);
            System.out.println("成功购买 " + quantity + " 只西瓜");
        } else {
            System.out.println("购买失败，请检查购买数量、摊位营业状态和库存数量");
        }
    }

    public void restock(int quantity) {
        if (quantity > 0 && quantity <= 200) {
            this.total += quantity;
            System.out.println("成功进货 " + quantity + " 只西瓜");
        } else {
            System.out.println("进货失败，请检查进货数量");
        }
    }

    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.isClosed) {
                booth.setClosed(true);
                System.out.println(booth.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "摊号：" + id +
                "\n摊主姓名：" + name +
                "\n在售西瓜数：" + total +
                "\n是否休摊整改：" + isClosed;
    }
}