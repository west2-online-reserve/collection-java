package HOMEWORK;

public class WaterMelonbooth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;


    public long getId()
    {return id;}
    public void setId(long id)
    {this.id = id;}
    public String getName()
    {return name;}
    public void setName(String name)
    {this.name = name;}
    public  int getTotal()
    {return total;}
    public  boolean isClosed()
    {return isClosed;}
    public void setClosed(boolean closed)
    {isClosed = closed;}


    @Override
    public String toString() {
        return "西瓜摊：" + '{' + "id=" + id + ", name='" + name + '\'' + ", total=" + total + ", isClosed=" + isClosed +
                '}';
    }
//带参构造
    public WaterMelonbooth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total=  total;
        this.isClosed = isClosed;}
//购买方法
    public void purchase(int quantity) {

        if (quantity <= 0||quantity>200) {
            System.out.println("购买失败：购买数量为正数!");
            return;
        }
        if (isClosed) {
            System.out.println("购买失败：商家处于休摊整改状态!");
            return;
        }
        if (quantity > total) {
            System.out.println("购买失败：购买西瓜数不能大于在售西瓜数!");
            return;
        }
       total-= quantity;
        System.out.println("购买成功：购买了"+ quantity + "个西瓜!");
    }
//进货方法
    public void restock(int RestockQuantity) {
        if (RestockQuantity <= 0 || RestockQuantity > 200) {
            System.out.println("进货失败：进货量不能超过200！");
            return;}
        this.total += RestockQuantity;
        System.out.println("进货了 " + RestockQuantity + "个西瓜! ");
    }

//休业整改
    public static void closeBooths(WaterMelonbooth[] booths) {
        for (int i = 0; i < booths.length; ++i) {
            if (booths[i].isClosed()) {
                System.out.println("休业整改的摊位：" + booths[i].toString());
            } else {
                booths[i].setClosed(true);
            }
        }
    }
}





