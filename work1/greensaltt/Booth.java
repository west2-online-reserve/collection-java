package watermellon;

public class Booth {
//======================================
    //构造器
    public Booth() {

    }
    //无参构造器
    public Booth(long id, String name, int total, boolean isclosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
        System.out.println(isClosed);
    }
//======================================
    //定义私有变量
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    //======================================
    //set
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

//======================================
    //get


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public boolean isClosed() {
        return isClosed;
    }

    //======================================
    //重写方法


    @Override
    public String toString() {
        System.out.println("该摊位的编号为："+id);
        System.out.println("该摊位的名字为："+name);
        System.out.println("该摊位的在售西瓜数为："+total);
        System.out.println("是否休摊整改："+isClosed);
        return "摊位"+id+"名字"+name+"总量"+total+"休整"+isClosed;
    }

//======================================
    //静态方法purchase
   public static int purchase(Booth booth, int buy) {
       if (buy<=0){
            System.out.println("购买量必须为正数");
        }else if(buy> booth.total){
            System.out.println("购买量不能大于在售量");
        }else if(booth.isClosed){
            System.out.println("该摊位在休摊整改中");
        }else{
            booth.total=booth.total-buy;
            System.out.println("成功购买"+buy+"个西瓜");
        }
        return booth.total;
    }
//======================================
    //实例方法
    public void restock(int num){
        //对应摊位进货，num进货量
        if(num>200 || num<=0){
            //单次进货量不能超过200
            System.out.println("进货失败");
        }else{
            System.out.println("成功进货"+num+"个西瓜");
            this.total=this.total+num;
        }
    }
//======================================
    //静态方法closeBooths
   public static void closeBooths(Booth[] booths) {
        //让 booths 中所有未被休 业整改的摊位歇业(将 false 变为 true)
        for(Booth booth :booths){
            if(booth.isClosed()==false){
                booth.setClosed(true);
                booth.toString();
            }
        }
    }
}

//====================================
//测试类

public class Application {
    public static void main(String[] args) {
        Booth booth=new Booth();
        booth.setId(100);
        booth.setClosed(false);
        booth.setName("西瓜摊1");
        booth.setTotal(666);
        booth.getId();
        booth.getName();
        booth.getTotal();
        booth.isClosed();
        //=======================
        booth.toString();
        int buy=50;
        booth.purchase(booth,buy);
        booth.restock(buy);
        Booth[] booths={booth};
        booth.closeBooths(booths);

    }
}
