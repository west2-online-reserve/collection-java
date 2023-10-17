package homework;
public class Booth {
    //Booth类中包含private变量的命名、get/set方法、有参构造器、toString()方法重写、restock方法、purchase方法
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    //getset1:西瓜摊号的get/set方法
    public long getId() {
        return id;
    }
    public long setId(long a) {
        this.id = a ;
        return a;
    }
    //getset2:西瓜摊名的get/set方法
    public String getName() {
        return name;
    }
    public String setName(String name) {
        this.name = name ;
        return name;
    }
    //getset3:西瓜摊在售西瓜数的get/set方法
    public int getTotal() {
        return total;
    }
    public int setTotal(int a) {
        this.total = a ;
        return a;
    }
    //getset4:西瓜摊的是否休摊整改的get/set方法
    public boolean getIsClosed() {
        return isClosed;
    }
    public boolean setIsClosed(boolean a) {
        this.isClosed = a ;
        return a;
    }
    //有参构造器：
    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    //toString()方法重写
    @Override
    public String toString() {
        return "Booth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", isClosed=" + isClosed +
                '}';
    }
    //restock方法
    public void restock(Booth booth ,int restockTotal){
        if (restockTotal > 200) {
            System.out.println("进货失败！进货量>200!");
        } else if (restockTotal < 0) {
            System.out.println("进货失败！请输入正确的进货数据！");
        } else {booth.setTotal(booth.getTotal() + restockTotal);}
    }
    //closebooths方法
    public static void closebooths(Booth[] booths){
        for (int i = 0; i < booths.length; i++) {
            if(booths[i].getIsClosed() == false){
                booths[i].setIsClosed(true);
            }else {
                String string = booths[i].toString();
                System.out.println(string);
            }
        }
    }
    //purchase方法
    public static void purchase(Booth name, int purchaseTotal) {
        if (purchaseTotal <= 0) {
            System.out.println("交易失败！请输入正确的购买数量！");
        } else {
            if (name.getIsClosed() == true) {
                System.out.println("交易失败！店铺正处于休业整顿！");
            } else {
                if (purchaseTotal > name.getTotal()) {
                    System.out.println("交易失败！购买数量大于店铺库存！");
                    System.out.println("店铺剩余的西瓜数量为：" + name.getTotal());
                } else {
                    name.setTotal(name.getTotal() - purchaseTotal);
                    System.out.println("恭喜您！您的交易成功！");
                    System.out.println("店铺剩余的西瓜数量为：" + name.getTotal());
                }
            }
        }
    }
}
