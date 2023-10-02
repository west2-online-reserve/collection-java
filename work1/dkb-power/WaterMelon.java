//设计一个西瓜摊类
//私有变量：
//摊号 long id
//摊主姓名 String name
//在售西瓜数 int total
//是否休摊整改 boolean isClosed
//方法：
// 上述变量对应的 get 和 set 方法
// 1.一个重写的 toString()方法来输出 该西瓜摊的所有信息（要有一定的格式
// 2.一个接受摊号(long)、摊主姓名(name) 、在售西瓜数(int)、是否休摊整改 (boolean)作为参数的构造方法
// 3.一个静态(static)方法 purchase（Booth 商家, int 购买数量）, 向目标摊位卖家 购买指定数量的西瓜。
//        购买的西瓜数必须为正数；商家不能处于休摊整改状态；购买西瓜数不能大于在售西 瓜数。出现以上情况视为购买失败，
//        摊主在售西瓜数不能有所变化。 无论交易成功与否，都要输出一定的提示信息
// 4.一个实例方法 restock(int 进货西瓜数),为对应摊位进货，单次进货量不能超过 200，进货失败则输出相应的提示信息。
// 5,一个静态(static)方法 closeBooths（Booth[] booths）让 booths 中所有未被休
//        业整改的摊位歇业(将 false 变为 true)，输出已在休业整改的摊位信息（调用实例的 toString()方法）

public class WaterMelon {
    public static void main(String[] args) {

    }
}

class stall {
    long id;
    String name;
    int total;
    boolean isClosed;

    public stall(long id, String name, int total, boolean isClosed) {
        this.setId(id);
        this.setName(name);
        this.setTotal(total);
        this.setClosed(isClosed);
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

    public static void purchase(stall booth, int quantity) {
        if (quantity <= 0) {
            System.out.println("购买的西瓜必须为正数");
        } else if (booth.isClosed() == true) {
            System.out.println("商家正在休摊整改");
        } else if (quantity > booth.total) {
            System.out.println("你购买的数量太多了");
        } else {
            System.out.println("成功购买" + quantity + "个西瓜，但不影响在售西瓜数量");
        }
    }

    public void restock(int sum) {
        if (sum <= 0 || sum >= 200) {
            System.out.println("进货失败，进货量应该在（0-200）之间");
        } else {
            total += sum;
            System.out.println("当下有" + total + "个西瓜");
        }
    }

    public String toString() {
        return "摊号=" + id + " "
                + "摊主名字为" + name + " "
                + "在售西瓜数" + total + " "
                + "是否休摊整改" + isClosed;
    }
    public static void closeBooths(stall[] booths) {
        for(int i = 0;i< booths.length;i++) {
            if(booths[i].isClosed==false) {
                booths[i].isClosed=true;
            }else {
                System.out.println(booths[i].toString());
            }
        }
    }
}
