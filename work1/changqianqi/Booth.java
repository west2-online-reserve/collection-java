public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;
     public Booth(long id, String name, int total, boolean isClosed){
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
    public String toString() {
        return "摊号：" + id +
                "\n摊主姓名：" + name +
                "\n在售西瓜数：" + total +
                "\n是否休摊整改：" + isClosed;
    }
    public static void purchase(Booth a,int restockQuantity)
    {
        if(restockQuantity<=0)
        {
            System.out.println("购买数量必须为正数");
            return;
        }
        if(a.isClosed())
        {
            System.out.println("摊主休摊整改中，无法购买");
            return;
        }
        if(restockQuantity>a.getTotal())
        {
            System.out.println("购买数量大于在售西瓜数，购买失败");
            return;
        }
        a.setTotal(a.getTotal() - restockQuantity);
        System.out.println("成功购买 " +restockQuantity + " 个西瓜");
    }
    public void restock(int restockQuantity) {
        if (restockQuantity <= 0 || restockQuantity > 200) {
            System.out.println("进货数量必须为正数且不超过200");
            return;
        }

        this.total += restockQuantity;
        System.out.println("成功进货 " + restockQuantity + " 个西瓜");
    }
    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.isClosed()) {
                booth.setClosed(true);
                System.out.println("摊号 " + booth.getId() + " 已休摊整改");
            }
        }
    }

}
