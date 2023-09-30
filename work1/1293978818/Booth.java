public class Booth{
    //私有变量
    private long id;
    private String name;
    private int tota;
    private boolean isClosed;

    //构造方法
    public Booth(long id,String name,int tota,boolean isClosed){
        this.id = id;
        this.name = name;
        this.tota = tota;
        this.isClosed = isClosed;
    }

    //上述私有变量的get方法
    public long getid(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int gettota(){
        return tota;
    }
    public boolean getisClosed(){
        return isClosed;
    }

    //上述变量的set方法
    public void setid(long id){
        this.id = id;
    }
    public void setname(String name){
        this.name = name;
    }
    public void settota(int tota){
        this.tota = tota;
    }
    public void setisClosed(boolean isClosed){
        this.isClosed = isClosed;
    }

    //重写的toString方法
    public String toString(){
        return("该商家的摊号为" + id + "摊主姓名为" + name + "在售西瓜数为" + tota + "休摊情况为" + isClosed);
    }

    
    //购买西瓜
    public static void purchase(Booth booth,int purchasenum){
        if(purchasenum < 0){
            System.out.println("购买失败，购买数量错误");
        }else if(purchasenum > booth.gettota()){
            System.out.println("购买失败，西瓜摊在售西瓜数不足");
        }else if(booth.isClosed){
            System.out.println("购买失败，该摊已休摊");
        }else{
            booth.settota(booth.gettota() - purchasenum);
            System.out.println("购买成功");
        }
    }
    
        //进西瓜
    public void restock(Booth booth,int restocknum){
        if (restocknum > 200){
                System.out.println("进货失败,数量超过上限");
        }else if(restocknum < 0){
            System.out.println("进货数量不能小于0，进货失败");
        }else{
            booth.settota(booth.gettota() + restocknum);
            System.out.println("进货成功");
        }
    }
    
        //歇业
    public static void closeBooth(Booth[] booths){
        for (int i = 0; i < booths.length; i++) {
            if (!booths[i].getisClosed()){
                booths[i].setisClosed(true); 
            }else{
                System.out.println(booth[i].toString());
            }
            
        }
            
    }
    
}
