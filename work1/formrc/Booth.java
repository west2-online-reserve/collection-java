package java_work;

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

    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public boolean getClosed() {
        return isClosed;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public static void purchase(Booth stand, int num) {
        if (num <= 0) {
            System.out.println("购买的西瓜数必须为正数");
            return;
        }
        else if (stand.getClosed()) {
            System.out.println("商家处于休摊整改状态,无法购买");
            return;
        }
        else if (num > stand.getTotal()) {
            System.out.println("购买的西瓜数不能大于在售西瓜数");
            return;
        }
        stand.setTotal(stand.getTotal() - num);
        System.out.println("成功从" + stand.getName() + "处购买了" + num + "个西瓜");
    }

    public void restock(int num) {
        if (num <= 0) {
            System.out.println("进货数量必须为正数");
            return;
        }
        if (num > 200) {
            System.out.println("单次进货量不能超过200");
            return;
        }
        this.total += num;
        System.out.println(this.name + "的西瓜摊成功进货了" + num + "个西瓜");
    }

    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.getClosed()) {
                booth.setClosed(true);
                System.out.println(booth.getName() + "的摊位" + "已在休业整改。");
            }
        }
    }

    public String toString() {
        return "摊号：" + id + "\n" +
                "摊主姓名：" + name + "\n" +
                "在售西瓜数：" + total + "\n" +
                "是否休摊整改：" + isClosed;
    }
}
