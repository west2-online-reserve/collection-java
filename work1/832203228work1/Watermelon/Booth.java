package Watermelon;

public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public Booth() {
    }

    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    /**
     * 获取
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 设置
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 获取
     * @return isClosed
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * 设置
     * @param isClosed
     */
    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String toString() {
        if(isClosed ){return "Booth{摊号" + id + ", 摊主姓名是 " + name + ", 在售西瓜数为" + total + ", 关门了"+ "}";}
        else{return "Booth{摊号" + id + ", 摊主姓名是 " + name + ", 在售西瓜数为" + total + " 开门" + "}";}
    }

    public static void purchase(Booth booth, int quantity) {
        if (quantity <= 0) {
            System.out.println("购买的西瓜数量必须为正数！");
            return;
        }
        if (booth.isClosed()) {
            System.out.println("该摊位已经休摊！");
            return;
        }
        if (quantity > booth.getTotal()) {
            System.out.println("购买的西瓜数量不能大于在售西瓜数量！");
            return;

        }
        booth.setTotal(booth.getTotal() - quantity);
        System.out.println("购买成功,摊主是：" + booth.getName() + "，剩余在售西瓜数：" + booth.getTotal());

    }
    public void restock(int quantity){
        if (quantity>200){
            System.out.println("进货失败，单次进货量不能超过 200");
        }else {
            this.total+=quantity;
            System.out.println("摊位" + this.id + "进货了" + quantity + "个西瓜。");


        }
    }
    public static void closeBooths(Booth[] booths){
        for (Booth booth:booths){
            if (!booth.isClosed()) {
                booth.setClosed(true);
                booth.toString();
            }
        }
    }


}
