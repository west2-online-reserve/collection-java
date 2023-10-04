public class WatermelonBooth {
    private long id;//摊号
    private String name;//摊主名
    private int total;//西瓜存货
    private boolean isClosed;//是否营业

    //set

    public void setId(long id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setTotal(int total){
        this.total=total;
    }
    public void setClosed(boolean isClosed){
        this.isClosed=isClosed;
    }

    //get

    public long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public int getTotal(){
        return this.total;
    }
    public boolean getClosed(){
        return this.isClosed;
    }

    //重写toString输出

    public String toString(){
        return "摊号为："+id+"\n"+"摊主名字："+name+"\n"+"西瓜存货："+total+"\n"+"是否营业："+isClosed;
    }

    //构造方法

    public WatermelonBooth(long a,String b,int c,boolean d){
        this.id=a;
        this.name=b;
        this.total=c;
        this.isClosed=d;
    }

    //购买西瓜

    public static String purchase(WatermelonBooth a,int b){
        if (b<=0||a.isClosed||b>a.total){
            return "购买失败，请确认相关信息";
        }
        else {
            a.total-=b;
            return "购买成功，目标摊位仍剩余西瓜："+a.total+"个";
        }
    }

    //进货

    public String restock(int a) {
        if (a < 0 || a > 200) {
            return "进货失败，请检查相关信息";
        } else {
            this.total += a;
            return "进货成功，摊位目前剩余西瓜：" + this.total + "个";
        }
    }

    //停业

    public static void closeBooths(WatermelonBooth[] a){
        for (WatermelonBooth watermelonBooth : a) {
            if (!watermelonBooth.isClosed) {
                watermelonBooth.setClosed(true);
            } else {
                System.out.println(watermelonBooth.toString());
            }
        }
    }
}
