public class Booth {
private long id;
private String name;
private int total;
private boolean isClosed;
    //私有变量
    public void setId(long id ){this.id=id;}
    public long getId(){return id;}//摊号的set与get
    public void setName(String name){this.name=name;}
    public String getName(){return name;}//摊主姓名的set与get

    public void setTotal(int total){this.total=total;}
    public int getTotal(){return total;}//在售西瓜数的set与get

    public void setisClosed(boolean isclosed) {this.isClosed = isclosed;}

    public boolean getisClosed() {return isClosed;}//是否休摊整改的set与get
    @Override
    public String toString() {
        return ("该摊的id是" + getId() +
                "\n该摊摊主的name是" + getName() +
                "\n该摊的在售西瓜数" + getTotal() +
                "\n休摊状态" + getisClosed()
        );
    }//信息公示
    public Booth(long id,String name,int total,boolean isClosed){
    this.id=id;
    this.name=name;
    this.total=total;
    this.isClosed=isClosed;
    }//构造方法


    public static void purchase(Booth shangjia,int shuliang){
        if(shuliang<=0||shuliang> shangjia.getTotal()|| shangjia.getisClosed()){
            System.out.println("购买失败");
        }else {
            shangjia.setTotal(shangjia.getTotal() - shuliang);
            System.out.println("购买成功" + "剩余量：" + shangjia.getTotal());
        }
    }//购买
    public void restock(Booth shangjia,int buyin){
        if (buyin<=200&&buyin>=0){
            shangjia.setTotal(shangjia.getTotal()+buyin);
            System.out.println("进货成功");
        }else{
            System.out.println("进货失败");
        }
    }//进货
    public static void closeBooths(Booth[] Booths){
       for(int i=0;i< Booths.length;i++){
           if (Booths[i].getisClosed()==true){System.out.println(Booths[i]);}
           if (Booths[i].getisClosed()==false){Booths[i].setisClosed(true);}
       }
    }//全体修整
}