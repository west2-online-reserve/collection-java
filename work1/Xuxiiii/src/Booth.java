public class Booth {
    private long id;/*西瓜摊编号*/
    private String name;//摊主姓名
    private int total;//在售西瓜的总数
    private boolean isClosed;//是否休摊整改

    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    //get方法
    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getTotal() {
        return total;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    //set方法
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String toString() {
        return "西瓜摊的" + "摊号为" + id +
                "摊主姓名为" + name +
                "在售西瓜总数位" + total +
                "是否在休摊整改" + isClosed;

    }

    //进货
    public void restock(int amount) {
        if (amount <= 0 || amount > 200) {
            System.out.println("进货失败，单次进货量必须在1~200之间。");
            return;
        }
        total += amount;
        System.out.println("进货成功，当前库存量为：" + total);
    }

    //买西瓜
    public static void purchase(Booth booth, int purchaseAmount) {
        if (purchaseAmount <= 0) {
            System.out.println("购买失败，购买数量必须为正数");
        } else if (purchaseAmount > booth.getTotal()) {
            System.out.println("购买失败，摊位西瓜不足");
        } else if (booth.isClosed) {
            System.out.println("购买失败，该摊已经休摊整改");
        } else {
            booth.setTotal(booth.getTotal() - purchaseAmount);
            System.out.println("购买成功");
        }
    }
    public static void closeBooth(Booth[] booths){
        boolean stop=false;
        for(int i=0;i<booths.length;i++){
            if(booths[i].isClosed==false){
                booths[i].setIsClosed(true);
                System.out.println(booths.toString());
            }else {
                System.out.println(booths.toString());
            }
        }
    }

}
