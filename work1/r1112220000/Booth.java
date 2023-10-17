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

    @Override
    public String toString() {
        return "Booth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", isClosed=" + isClosed +
                '}';
    }

    public static void purchase(Booth booth, int quantity) {
        if (booth.getIsClosed() || quantity <= 0 || quantity > booth.getTotal()) {
            System.out.println("购买失败！");
            return;
        }
        booth.setTotal(booth.getTotal() - quantity);
        System.out.println("购买成功,剩余西瓜：" + booth.getTotal());
    }

    public void restock(int quantity) {
        if (quantity > 200 && quantity < 0) {
            System.out.println("进货失败,单次进货量不能超过200个西瓜。");
            return;
        }
        setTotal(getTotal() + quantity);
        System.out.println("进货成功,当前西瓜数量：" + getTotal());
    }

    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.getIsClosed()) {
                booth.setIsClosed(true);
                System.out.println(booth.getId());
            } else {
                System.out.println(booth.getId());
            }
        }
    }
}
