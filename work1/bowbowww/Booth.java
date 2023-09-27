package work1;

public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;
    public Booth(){
    }

    public Booth(long id,String name,int total,boolean isClosed){
        this.id =  id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    //上述变量对应的 get 和 set 方法

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getTotal(){
        return total;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public boolean getIsClosed(){
        return isClosed;
    }
    public void setIsClosed(boolean isClosed){
        this.isClosed = isClosed;
    }

    //一个重写的 toString()方法来输出 该西瓜摊的所有信息（要有一定的格式\
    public  String toString(){
        return "The booth id: "+getId()+"\n"+"the name of booth owner: "+getName()+"\n"+"the number of watermelons on sale: "+getTotal()+"\n"+"Whether to stop amortization rectification:  "+getIsClosed();
    }

    //一个静态(static)方法 purchase（Booth 商家, int 购买数量）, 向目标摊位卖家 购买指定数量的西瓜。 购买的西瓜数必须为正数；商家不能处于休摊整改状态；购买西瓜数不能大于在售西 瓜数。出现以上情况视为购买失败，摊主在售西瓜数不能有所变化。 无论交易成功与否，都要输出一定的提示信息
    public static  void purchase(Booth booth, int buy){
        long id = booth.getId();
        String name = booth.getName();
        int total = booth.getTotal();
        boolean isClosed = booth.getIsClosed();
        if(!isClosed){
            System.out.println("The business cannot be in a state of rectification");
        }else if(total<=0){
            System.out.println("The number of watermelons purchased must be positive");
        }else if(buy>total){
            System.out.println("The number of watermelons purchased cannot be greater than the number of watermelons on sale");
        }else{
            System.out.println("Successful purchase");
            booth.setTotal(total-buy);
        }
    }

    //一个实例方法 restock(int 进货西瓜数),为对应摊位进货，单次进货量不能超过 200，进货失败则输出相应的提示信息。
    public void restock(int stock){
        if(stock>200){
            System.out.println("Failed to stock");
        }else{
            System.out.println("Successful stock");
        }
    }

    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (booth.getIsClosed()) {
                System.out.println(booth.toString());
            } else {
                booth.setIsClosed(true);
            }
        }
    }
}
