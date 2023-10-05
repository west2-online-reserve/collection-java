public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;
    public  Booth(){ }
    public Booth(long id, String name, int total, boolean isClosed){
       this.name = name;
        this.id= id;
        this.total = total;
        this.isClosed = isClosed;
    }
    //get获取数据
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getTotal(){
        return total;
    }
    public boolean getIsClosed() {
        return isClosed;
    }
    //set给数据设置值
    public void setId(long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public void setIsClosed(boolean isClosed){
        this.isClosed = isClosed;
    }
    public String toString(){
        return id+" "+name+" "+total+" "+isClosed;
    }
    public static void purchase(Booth booth,int num){
        if (num<=0 || booth.isClosed || num>booth.total ){
            System.out.println("购买失败");
        }else {
            booth.total -= num;
            System.out.println("购买成功");
        }
    }

    public void restock(int amount){
        if (amount > 200 || amount < 0){
            System.out.println("进货超过200，进货失败");
        }else {
            total += amount;
            System.out.println("进货成功");
        }
    }
    public static void closeBooths(Booth[] booths){
        for (Booth booth : booths) {
            if(booth.isClosed == false){
                booth.isClosed = true;
            }
            booth.toString();
        }
    }
}

