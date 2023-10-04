package watermelon;
public class test2 {

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
    @Override
    public String toString() {
        System.out.println("西瓜摊的摊号为"+getId());
        System.out.println("西瓜摊的摊主姓名为"+getName());
        System.out.println("西瓜摊的在售西瓜数为"+getTotal());
        System.out.println();
        return null;
    }
    public static void restock(int purchase_in){
        if (purchase_in <= 200){
            System.out.println("进货成功");
        }else {
            System.out.println("货源地没有这么多的西瓜");
        }
    }
    public static void purchase(int booth){
        test2 instance = new test2();
        if (booth <= instance.getTotal() && instance.isClosed() != false){
            System.out.println("购买成功");
        }else if(booth <= instance.getTotal() && instance.isClosed() == false){
            System.out.println("购买失败，商家未在营业中");
        }else if(booth > instance.getTotal() && instance.isClosed() != false){
            System.out.println("购买失败，商家西瓜数量没这么多");
        }else {
            System.out.println("购买失败，商家西瓜没这么多且商家未在营业中");
        }
    }
    public static void closeBooth(){
        test2 instance = new test2();
        if(instance.isClosed()== false){
            System.out.println(instance.toString());
        }else {
            instance.setClosed(false);
            instance.isClosed();
        }
    }
}

