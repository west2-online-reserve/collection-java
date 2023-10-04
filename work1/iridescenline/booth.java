//package work1.iridescenline;

public class booth {

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

    public boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String toString() {

        return "id:" + id + " name:" + name + " total" + total + " isClosed:" + isClosed;
    }

    public booth() {

    }

    public booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    public static void purchase(int num, int total, boolean isClosed) {
        if (num > 0 && num <= total && !isClosed) {
            System.out.println("购买成功");
        } else {
            System.out.println("购买失败");
        }
    }

    public void restock(int getQuantity) {
        if (getQuantity > 200) {
            System.out.println("进货失败");
        } else {
            System.out.println("进货成功");
        }
    }

    public static void closeBooths(booth[] booths) {
        for (booth booth : booths) {
            if (!booth.getIsClosed()) {
                booth.setIsClosed(true);
                System.out.println("已关闭");
            } else {
                System.out.println("已在休业整改");
            }
        }
    }
}
