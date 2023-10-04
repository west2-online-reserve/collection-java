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
        System.out.println(id);
        System.out.println(name);
        System.out.println(total);
        System.out.println(isClosed);
        return super.toString();
    }

//======================================
    //静态方法purchase
    public static int purchase(Booth id, int buy){
        if(buy>0 && new Booth().isClosed){
            //购买数大于0且西瓜摊未收摊
            if(buy < id.getTotal()){
                System.out.println("交易成功");
            }else{
                //购买数大于出售量报错
                System.out.println("交易失败" );
            }
        }else{
            System.out.println("交易失败");
        }
        return id.getTotal();
    }
//======================================
    //实例方法
    public void restock(int num){
        //对应摊位进货，num进货量
        if(num>200){
            //单次进货量不能超过200
            System.out.println("进货失败");
        }
    }
//======================================
    //静态方法closeBooths
    public static void closeBooths(Booth booths) {
        //让 booths 中所有未被休 业整改的摊位歇业(将 false 变为 true)
        if (booths.isClosed==false) {
            booths.isClosed=true;
        }
        System.out.println(booths.toString());
    }
}
