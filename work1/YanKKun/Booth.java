public class Booth {
   private long id;
   private String name;
   private int total;
   private boolean isClosed;



    public long getId() {
        return this.id;
    }
    public void setId(long id){
    this.id=id;
    }
    public String getName(){
    return this.name;
    }
    public void setName(String name){
    this.name=name;
    }
    public int getTotal(){
        return this.total;
    }
    public void setTotal(int total){
    this.total=total;
    }
    public boolean getisClosed(){
        return this.isClosed;
    }
    public void setClosed(boolean isClosed){
    this.isClosed=isClosed;
    }

    @Override
    public String toString() {
        return "Booth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", isClosed=" + isClosed +
                '}';
    }



    public  Booth (long id,String name,int total,boolean isClosed){
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    public static void purchase(Booth business, int need){
    boolean pass = true;
    if (need < 0){
        pass=false;
        System.out.println("购买西瓜数需为正数");
    }
    if(business.isClosed){
        pass = false;
        System.out.println("商家处于休摊整改状态");
    }
    if(need > business.total){
        pass = false;
        System.out.println("购买西瓜数不能大于在售西瓜数");
    }
    if (pass==true){
        business.total=business.total-need;
        System.out.println("购买成功！");
    }

    }
    public void restock(int add){
        boolean pass = true;
        if (add < 0){
            pass = false;
            System.out.println("单次进货量不能小于0");
        }
        if (add > 200){
            pass = false;
            System.out.println("单次进货量不能大于200");
        }
        if(pass){
            this.total=(this.total+add);
            System.out.println("进货成功 ");
        }
    }
    public static void closeBooths(Booth[] booths){
        int len = booths.length;
        for (int i = 0; i < len ; i++) {
            if(booths[i].getisClosed() == false){
                booths[i].setClosed(true);
            }else {
                System.out.println(booths[i]);
            }

        }

    }





}
