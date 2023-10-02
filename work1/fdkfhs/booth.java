public class Booth {
    //属性
    private long id;//摊号
    private String name;//
    private int total;//
    private boolean isClosed;//

    //方法
    //上述变量对应的 get 和 set 方法
    public long getId(){
        return id;
    }
    public void setId(long i){
        this.id=i;
    }
    public String getName(){
        return name;
    }
    public void setName(String n){
        this.name=n;
    }
    public int getTotal(){
        return total;
    }
    public void setTotal(int t){
        this.total=t;
    }
    public boolean getIsClosed(){
        return isClosed;
    }
    public void setIsClosed(boolean is){
        this.isClosed=is;
    }
    //一个重写的 toString()方法来输出 该西瓜摊的所有信息
    public String toString(){
        return "摊号是"+id+"/n摊主是"+name+"/n在售西瓜数是"+total+"/n是否休摊整改"+isClosed;
    }
    //一个接受摊号(long)、摊主姓名(name) 、在售西瓜数(int)、是否休摊整改 (boolean)作为参数的构造方法
    public Booth(long id,String name,int total,boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    //一个静态(static)方法 purchase（Booth 商家, int 购买数量）, 向目标摊位卖家 购买指定数量的西瓜。
    // 购买的西瓜数必须为正数；商家不能处于休摊整改状态；购买西瓜数不能大于在售西 瓜数。
    // 出现以上情况视为购买失败，摊主在售西瓜数不能有所变化。 无论交易成功与否，都要输出一定的提示信息
    public static void purchase(Booth booth, int num) {
        if (booth.getTotal() >= num && !booth.isClosed && num >= 0) {
            booth.setTotal(booth.getTotal() - num);
            System.out.println("交易成功！\n");
        } else {
            System.out.print("交易失败！");
            if (booth.isClosed) {
                System.out.print("休摊整改中\n");
            } else if (num < 0) {
                System.out.print("不能买入负数\n");
            } else if (booth.getTotal() < num) {
                System.out.print("西瓜数量不足\n");
            }
        }
    }
    //一个实例方法 restock(int 进货西瓜数),为对应摊位进货，单次进货量不能超过 200，进货失败则输出相应的提示信息。
    public void restock(Booth booth,int num){
        if(num>200){
            System.out.println("进货失败,数量过多");
        } else if(num>=0&&num<=200) {
            System.out.println("进货成功");
        } else if (num<0) {
            System.out.println("进货失败，进货数量不能为负数");
        }
    }
    //一个静态(static)方法 closeBooths（Booth[] booths）让 booths 中所有未被休 业整改的摊位歇业(将 false 变为 true)，
    // 输出已在休业整改的摊位信息（调用实例的 toString()方法）。
    public static void closeBooths(Booth[] booths) {
        boolean titlePrinted = false;
        for (int i = 0; i < booths.length; i++) {
            if (booths[i].getIsClosed() == true) {
                if (titlePrinted == false) {
                    System.out.println("以下为休摊整改的摊位信息：\n");
                    titlePrinted = true;
                }
                System.out.println(booths[i].toString());
            } else {
                booths[i].setIsClosed(true);
            }
        }
    }


}
