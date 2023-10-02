public class Booth{
    //私有变量
    private long id;
    private String name;
    private int total;
    private boolean isClosed;
    //set方法
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
    //get方法


    public long getId() {
        return id;
    }
    public String getName(){
        return  name;
    }

    public int getTotal() {
        return total;
    }

    public boolean isClosed() {
        return isClosed;
    }
    //展示西瓜摊信息
    public String toString(){
        return ("摊号：" + id + "\n摊主姓名：" + name + "\n在售西瓜数：" + total + "\n是否休摊整改:"+isClosed);
    }
    //构造方法
    public Booth(long id,String name,int total,boolean isClosed){
        this.id=id;
        this.name=name;
        this.total=total;
        this.isClosed=isClosed;
    }
    //购买西瓜情况
    public static void purchase(Booth id,int num){
        int numafter=id.getTotal()-num;
        if(num<=0|num>id.getTotal()){
            System.out.println("购买数量不合法，购买失败");
        } else if (id.isClosed()==true) {
            System.out.println("休摊中，无法购买");
        }else{
            System.out.println("\n购买成功，剩余西瓜数："+numafter);
        }

    }
    //进货情况
    public void restock(Booth id,int plusnum){
        int numafter=id.getTotal()+plusnum;
        if(plusnum<=0|plusnum>200){
            System.out.println("进货不合法,进货数量应在0到200之间");
        }else {
            System.out.println("进货成功，现在西瓜数量为："+numafter);
        }
    }
    public static void closeBooth(Booth[] booths){
        boolean stop=false;
        for(int i=0;i<booths.length;i++){
            if(booths[i].isClosed==false){
                booths[i].setClosed(true);
                System.out.println(booths[i].toString());
            }else {
                System.out.println(booths[i].toString());
            }
        }
    }
}