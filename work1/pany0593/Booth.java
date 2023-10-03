import javax.lang.model.element.Name;

public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;
    public Booth() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean getisClosed() {
        return isClosed;
    }

    public void setisClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public Booth(long id,String name,int total,boolean isClosed)
    {
        this.id=id;
        this.name=name;
        this.total=total;
        this.isClosed=isClosed;
    }
    public String toString()
    {
        String tmp="摊主姓名："+this.name+"\n"+"摊号："
                +this.id+"\n"+"在售西瓜数："+this.total+"\n"+"是否休摊整改：";
        if(this.isClosed)
            tmp+="是";
        else
            tmp+="否";
        return tmp;
    }
    public static void purchase(Booth booth,int num)
    {
        if(num<0||booth.isClosed||num>booth.total)
            System.out.println("购买失败");
        else{
            booth.total-=num;
            System.out.println("购买成功");
        }
    }
    public void restock(int num)
    {
        if(num>200||num<0)
            System.out.println("进货失败");
        else {
            this.total+=num;
            System.out.println("进货成功");
        }
    }
    public static void closeBooths(Booth...booths)
    {
        for (Booth ele:booths)
        {
            ele.isClosed=true;
            System.out.println(ele.toString());
        }
    }
}

/*设计一个西瓜摊类
 * 私有变量：
 * 摊号 long id√
 * 摊主姓名 String name√
 * 在售西瓜数 int total√
 * 是否休摊整改 boolean isClosed√
 * 方法：
 * 上述变量对应的 get 和 set 方法√
 *
 * 一个重写的 toString()方法来输出 该西瓜摊的所有信息（要有一定的格式√
 *
 * 一个接受摊号(long)、摊主姓名(name) 、在售西瓜数(int)、是否休摊整改√
 * (boolean)作为参数的构造方法
 *
 * 一个静态(static)方法purchase（Booth 商家, int 购买数量,向目标摊位卖家购买指定数量的西瓜。√
 * 购买的西瓜数必须为正数；√
 * 商家不能处于休摊整改状态；√
 * 购买西瓜数不能大于在售西瓜数。√
 * 出现以上情况视为购买失败，摊主在售西瓜数不能有所变化。√
 * 无论交易成功与否，都要输出一定的提示信息√
 *
 * 一个实例方法 restock(int 进货西瓜数),为对应摊位进货，单次进货量不能超过 200，√
 * 进货失败则输出相应的提示信息。√
 *
 * 一个静态(static)方法 closeBooths（Booth[] booths）√
 * 让 booths 中所有未被休 业整改的摊位歇业(将 false 变为 true)，√
 * 输出已在休业整改的摊位信息（调用实例的 toString()方法）。*/
