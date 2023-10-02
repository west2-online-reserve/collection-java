import javax.lang.model.element.Name;

public class Booth {
    private long id;
    private String name;
    int total;
    boolean isClosed;

    public Booth() {
    }

    ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id=id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total=total;
    }

    public boolean getisClosed() {
        return isClosed;
    }

    public void setisClosed(boolean isClosed) {
        this.isClosed=isClosed;
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
 * 一个重写的 toString()方法来输出 该西瓜摊的所有信息（要有一定的格式
 * 一个接受摊号(long)、摊主姓名(name) 、在售西瓜数(int)、是否休摊整改 (boolean)作为参数的构造方法
 * 一个静态(static)方法 purchase（Booth 商家, int 购买数量）, 向目标摊位卖家 购买指定数量的西瓜。 购买的西瓜数必须为正数；商家不能处于休摊整改状态；购买西瓜数不能大于在售西 瓜数。出现以上情况视为购买失败，摊主在售西瓜数不能有所变化。 无论交易成功与否，都要输出一定的提示信息
 * 一个实例方法 restock(int 进货西瓜数),为对应摊位进货，单次进货量不能超过 200，进货失败则输出相应的提示信息。
 * 一个静态(static)方法 closeBooths（Booth[] booths）让 booths 中所有未被休 业整改的摊位歇业(将 false 变为 true)，输出已在休业整改的摊位信息（调用实例的 toString()方法）。*/
