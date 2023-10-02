public class Booth {
    Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    private long id;
    private String name;
    private int total;// 在售西瓜数
    private boolean isClosed;// 是否在接受整改

    // 各变量的get方法
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    // 各变量的set方法

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String toString() {
        return "ID:" + id + " Name:" + name + " Total:" + total + " isClosed:" + isClosed;
    }

    public static void purchase(Booth booth, int saleNum) {
        if (booth.isClosed) {
            System.out.println("商家正在整改，暂时不能购买");
            return;// 终止函数
        } else if (saleNum <= 0) {
            System.out.println("购买数量必须为正数");
            return;
        } else if (saleNum > booth.total) {
            System.out.println("购买数量大于库存");
            return;
        } else {
            booth.total -= saleNum;
            System.out.println("购买成功");
        }
    }

    public void restore(int restoreNum) {
        if (restoreNum > 200 || restoreNum < 0) {
            System.out.println("单次进货量需大于0且不能超过200");
            return;
        } else if (isClosed) {
            System.out.println("商家已歇业");
        } else {
            this.total += restoreNum;
            System.out.println("您已成功进货" + restoreNum + "西瓜，目前库存为" + this.getTotal());
        }
    }

    static public void closeBooths(Booth[] booths) {
        for (int i = 0; i != booths.length; ++i) {
            if (!booths[i].isClosed) {
                booths[i].isClosed = true;
            } else {
                System.out.println(booths[i]);
            }
        }
    }

}
