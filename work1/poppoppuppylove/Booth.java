class Booth {
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

    @Override
    public String toString() {
        return "摊号:" + id + ", 摊主:" + name + ", 在售西瓜数:" + total + ", 休摊状态:" + isClosed;
    }

    public static void purchase(Booth booth, int amount) {
        if (amount <= 0 || booth.isClosed() || amount > booth.getTotal()) {
            System.out.println("购买西瓜失败");
        } else {
            booth.setTotal(booth.getTotal() - amount);
            System.out.println("购买西瓜" + amount + "成功");
        }
    }

    public void restock(int amount) {
        if (amount <= 0 || amount > 200) {
            System.out.println("进货失败,进货量必须大于0且在200内");
        } else {
            setTotal(getTotal() + amount);
            System.out.println("进货" + amount + "成功");
        }
    }

    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.isClosed()) {
                booth.setClosed(true);
                System.out.println(booth);
            }
        }
    }
}

