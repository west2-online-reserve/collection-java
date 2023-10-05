package demo1;

public class Booth {
    //私有变量
    private long id;
    private String name;
    private int total;
    private boolean isClosed;
    //对应的get和set方法
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
    public  String toString(){
        return "摊号："+this.id+",  摊主姓名:"+this.name+",  剩余西瓜数量："+this.total+",  摊位是否在修改："+this.isClosed;
    }
    public void Booth(long id,String name,int total,boolean isClosed){
        this.isClosed=isClosed;
        this.name=name;
        this.id=id;
        this.total=total;
    }
    public static void purchase(Booth name,int amount){
        if (amount<0){
            System.out.println("购买失败,无法出售负数西瓜");
        }else
            if (name.isClosed){
                 System.out.println("商家正在修正，无法出售");

            }else
                 if (amount>name.total){
                     System.out.println("商家剩余西瓜数不足，购买失败");

                }else {
                     name.total-=amount;
                     System.out.println("购买成功");
                    }
    }
    public void restock(Booth name,int comeAmount){
        if (comeAmount<=200&&comeAmount>0){
            name.total+=comeAmount;
        }else {
            System.out.println("进货失败，进货量应不超过200且要大于0");
        }
    }
    public static void closeBooths(Booth[] booths){
        for (int i=0;i<booths.length;i++){
            if (!booths[i].isClosed){
                booths[i].isClosed=true;
            }else booths[i].toString();
        }
    }
}
