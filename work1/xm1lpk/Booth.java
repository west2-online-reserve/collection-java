public class Booth {
    private long id;//摊号
    private int total;//在售西瓜数
    private String name;//摊主姓名
    private boolean isClosed;
    //创建私有变量
    public Booth(long id, String name, int tota, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    //构造函数
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
        return "摊号是" + id +
                "\n摊主姓名是" + name +
                "\n在售西瓜数是" + total +
                "\n休摊整改情况" + (isClosed ? "是" : "否");
    }
    public static void purchase(Booth booth, int num) {

        if(booth.isClosed()) {
            System.out.println(booth.getName() + "false:处于休摊整改状态。");
            return;
        }
        if(num <=0) {
            System.out.println("false:西瓜数必须为正。");
            return;
        }
        if(num >booth.getTotal()) {
            System.out.println("false：购买西瓜超过在售西瓜。");
            return;
        }
        booth.setTotal(booth.getTotal()-num);
        System.out.println(booth.getName()+"出售 "+num +" 个西瓜.");
    }
    public void restock(int num) {
        if (num <= 0||num > 200) {
            System.out.println("false：单次进货量必须为正数且不超过200。");
            return;
        }
        this.total += num;
        System.out.println(name + "成功进货了 " + num + " 个西瓜.");
    }
    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.isClosed()) {
                booth.setClosed(true);
                System.out.println(booth.toString());
            }
        }
    }
}
