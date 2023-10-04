public class  WatermelonBooth {
    private long id;
    private String name;
    private int total;
    private boolean isClose;//构造私有变量

    // 构造方法
    public WatermelonBooth(long id, String name, int total, boolean isClose) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClose = isClose;
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

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean close) {
        isClose = close;
    }

    @Override
    public String toString() {
        return "WatermelonStand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", isClose=" + isClose +
                '}';
    }

    public static String purchase( WatermelonBooth seller, int quantity) {

        if (quantity <= 0) {
            return "数量必须是整数，购买失败";
        }
        if (seller .isClose()) {
            return "不能处于休息状态，购买失败";
        }
        if (quantity > seller.getTotal()) {
            return "购买数量不能大于总数，购买失败";
        }

        seller.setTotal( seller.getTotal() - quantity);
        return "购买成功，买了"+quantity+"个西瓜";
    }

    public String restock(int input, WatermelonBooth s){

        if(input>200){
            return "进货失败，数量不能超过200";
        }
        if(input<=0){
            return "进货失败，进货必须是正数";
        }
        s.setTotal(s.getTotal()+input);
        return "进货"+input+"个西瓜";
    }
    public static void closeBooths(WatermelonBooth[] booths){
        for ( int x=0;x<=booths.length;x++){
            if (booths[x].isClose()==false) {
                booths[x].setClose(true);
                System.out.println(booths[x].toString());
            }
            else {
                System.out.println(booths[x].toString());
            }
        }

    }
}



