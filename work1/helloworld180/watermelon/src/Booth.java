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

    public static void main(String[] args) {
        Booth[] booths = new Booth[10];
        booths[0] = new Booth(1001,"小明",200,false);
        booths[1] = new Booth(1002,"小红",100,true);
        booths[2] = new Booth(1003,"小王",150,false);
        System.out.println(booths[0].toString());
        System.out.println(booths[1].toString());
        System.out.println(booths[2].toString());

        Booth d = new Booth();
        d.setId(1004);
        d.setName("小绿");
        d.setTotal(250);
        d.setIsClosed(true);
        System.out.println(d.toString());

        purchase(booths[0],201);
        purchase(booths[0],190);
        booths[0].restock(201);

        closeBooths(booths);
    }
}

