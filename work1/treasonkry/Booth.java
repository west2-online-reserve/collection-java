package Homework;

public class Booth {
    public static void main(String[] args) {


    }


    long id;
    int total;
    String name;
    boolean isClosed;
    //toString
    public String toString(){
        if (this.isClosed==true){
            return "摊号:"+getId()+"\n"+"摊主姓名："+getName()+"\n"+"在售西瓜数："+getTotal()+"\n"+"是否休摊整改："+"是";
        }else {
            return "摊号:"+getId()+"\n"+"摊主姓名："+getName()+"\n"+"在售西瓜数："+getTotal()+"\n"+"是否休摊整改："+"否";
        }

    }
    //构造方法
    public Booth() {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    //get和set方法
    //---------------------------------------------------------------
    public long getId(){
        return  this.id;
    }
    public void setId(long id1){
        this.id = id1;
    }
    public int getTotal(){
        return this.total;
    }
    public void setTotal(int tt){
        this.total = tt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //---------------------------------------------------------------------------------------
    //购买方法
    //-----------------------------------------------------------------------------------
    public static int purchase(Booth id,int buy){
        Booth booth = new Booth();
        int num = booth.total;
        boolean is_closed = booth.isClosed;
        if(is_closed==false && buy<=num&& num >0){
            num-=buy;
            System.out.println("成功购买"+buy+"个西瓜");
        }else {
            System.out.println("购买失败");
        }
        return num;
    }
    //----------------------------------------------------------------------------------------
    //----------------------------------------------------------
    //进货
    public int stock(int add){
        Booth booth = new Booth();
        int t = booth.total;
        if(add>0&&add<=200){
            t+=add;

        }else {
            System.out.println("进货失败");
        }
        return t;
    }
    //----------------------------------------------------------------------------
    //歇业--------------------------------------
    public static  void closeBooth(Booth[] booths){
        for (int i = 0; i < booths.length-1; i++) {
            if(booths[i].isClosed==false){
                booths[i].isClosed = true;
            }else {
                System.out.println(booths[i].id);
                System.out.println(booths[i].name);
                System.out.println(booths[i].isClosed);
                System.out.println(booths[i].total);
            }

        }
    }
}
