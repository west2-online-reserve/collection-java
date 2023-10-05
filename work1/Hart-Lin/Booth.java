package watermelon;
public class Booth {

    private long id ;
    private String name ;
    private int total ;
    private boolean isClosed ;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName () {
        return name;
    }
    public void setName (String name){
        this.name = name;
    }
    public int getTotal () {
        return total;
    }
    public void setTotal ( int total){
        this.total = total;
    }
    public boolean isClosed () {
        return isClosed;
    }
    public void setClosed ( boolean closed){
        isClosed = closed;
    }

    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    @Override
    public String toString() {
        System.out.println("西瓜摊的摊号为"+getId());
        System.out.println("西瓜摊的摊主姓名为"+getName());
        System.out.println("西瓜摊的在售西瓜数为"+getTotal());
        System.out.println();
        return super.toString();
    }
    public void restock(int purchaseIn){
        if (purchaseIn <= 200){
            this.total +=purchaseIn;
            System.out.println("进货成功");
        }else {
            System.out.println("货源地没有这么多的西瓜");
        }
    }
    public  static void purchase(Booth booth,int quantity){

        if (quantity <=booth.getTotal() && booth.isClosed() != false){
            booth.setTotal(booth.getTotal()-quantity);
            System.out.println("购买成功");
        }else if(quantity <= booth.getTotal() && booth.isClosed() == false){
            System.out.println("购买失败，商家未在营业中");
        }else if(quantity > booth.getTotal() && booth.isClosed() != false){
            System.out.println("购买失败，商家西瓜数量没这么多");
        }else {
            System.out.println("购买失败，商家西瓜没这么多且商家未在营业中");
        }
    }
    public static void closeBooths(Booth[] booths) {
        for (int i = 0; i < booths.length; i++) {
            if (booths[i].isClosed()) {
                System.out.println(booths[i]);
            } else {
                booths[i].setClosed(true);
            }
        }
    }
}

