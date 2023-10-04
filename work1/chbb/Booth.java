package watermelon;
//西瓜滩类
public class Booth {
    private long id;           //摊号
    private String name;    //名字
    private int total;         //西瓜数
    private boolean isClosed;  //是否休整

    //对应的get和set
    //摊号
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //商家姓名
    public String getName() {
        return this.name;
    }
    public void setName() {
        this.name = name;
    }

    //在售西瓜数
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }

    //是否休整
    public boolean isClosed() {
        return isClosed;
    }
    public void setIsClosed(boolean closed) {
        isClosed = closed;
    }

    //重写object中的toString方法，西瓜类是object的子类
    @Override
    public String toString() {
        return String.format("%d%n %s%n %d%n %b%n", id, name, total, isClosed);
        //format同printf
    }

    //参数的构造方法
    public Booth(int id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    /*
    一个静态(static)方法 purchase（Booth 商家, int 购买数量）向目标摊位卖家 购买指定数量的西瓜。
     购买的西瓜数必须为正数；商家不能处于休摊整改状态；购买西瓜数不能大于在售西 瓜数。出现以上情况视为购
     买失败，摊主在售西瓜数不能有所变化。 无论交易成功与否，都要输出一定的提示信息
    */
    public static int purchase(Booth booth,int nums){
        if(nums<1){

            System.out.println("购买失败！");  //西瓜必须为正数
        }else if(booth.isClosed){

            System.out.println("西瓜摊已收摊！");
        }else if(nums>booth.total){

            System.out.println("西瓜库存不足！");
        }else{

            System.out.println("购买成功！");
        }

        return booth.total;
    }

    //一个实例方法 restock(int 进货西瓜数),为对应摊位进货，单次进货量不能超过 200，进货失败则输出相应的提示信息。
    public int restock(int restockNumber){

        if (restockNumber<=200){

            total+=restockNumber;
        }else{

            System.out.println("单次进货量超过200，进货失败！");
        }

        return total;
    }
    /*
    一个静态(static)方法 closeBooths（Booth[] booths）让 booths 中所有未
    被休 业整改的摊位歇业(将 false 变为 true)，输出已在休业整改的摊位信息（调用实例的 toString()方法）。
     */
    public static void closeBooths(Booth[] booths){
        for (Booth booth: booths){

            if(booth.isClosed){
                System.out.println(booth.toString());

            }else{
                booth.isClosed= true;

            }
        }
    }
}