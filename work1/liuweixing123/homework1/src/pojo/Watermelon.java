package pojo;

public class Watermelon {
    private long id;//西瓜摊号
    private String name;//摊主名字
    private int total;//在售西瓜数
    private boolean isClosed;//是否休摊整改

    @Override//重写方法
    public String  toString() {
        if(isClosed)
          System.out.println("西瓜摊号为"+id+" 摊主名字为 "+name+" 在售西瓜数为 "+total+" 需要休摊整改");
        else
            System.out.println("西瓜摊号为"+id+" 摊主名字为 "+name+" 在售西瓜数为 "+total+" 不需要休摊整改");
        return null;
    }


//一个接受摊号(long)、摊主姓名(name) 、在售西瓜数(int)、是否休摊整改 (boolean)作为参数的构造方法
    public Watermelon(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    public Watermelon() {
    }

    //静态方法purchase
    public static void purchase(int id,int quantity){
        for(int i=0;i<Db.arry.length;i++){
            if(Db.arry[i].getId()==id){
                if(Db.arry[i].getTotal()<quantity) {
                    System.out.println("购买失败,您所购买的西瓜数超过在售西瓜数");return;
                }else if(Db.arry[i].isClosed()){
                    System.out.println("购买失败,此摊号正在休整中！");return;
                }else if(quantity<=0){
                    System.out.println("您要购买的西瓜数量<=0，购买失败！");return ;
                }
                else{
                    System.out.println("购买成功");
                    Db.arry[i].total-=quantity;
                    return;
                }
            }
        }
        System.out.println("购买失败，未找到相应摊号");
    }

    public void  restock(int cnt){  //实例进货方法
        if(cnt>200)
            System.out.println("一次性进货太多！！进货失败");
        else if(cnt<0)
            System.out.println("进货数小于0,进货失败");
        else {
            System.out.println("进货成功");total+=cnt;
        }
    }

    public static void  closeBooths(Watermelon []arry){//一个静态(static)方法 closeBooths
        for(int i=0;i< arry.length;i++){
            if(arry[i].isClosed)
                arry[i].toString();
            else
                arry[i].setClosed(true);
        }
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
