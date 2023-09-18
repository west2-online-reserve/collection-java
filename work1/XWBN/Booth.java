package XWB;

public class Booth{
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public Booth(long id,String name,int total,boolean isClosed){
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    public long getID(){
        return this.id;
    }
    public void setID(long id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getTotal(){
        return this.total;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public boolean getIsClosed(){
        return this.isClosed;
    }
    public void setIsClosed(boolean isClosed)
    {
        this.isClosed = isClosed;
    }

    public String toString(){
        return getClass().getName() + '\n' + "[ID: "+getID() + "]" + '\n'+"[Name: "+getName()+"]"+'\n'+"[Total:"+getTotal()+"]"+'\n'+"[IsClosed:"+getIsClosed()+"]" + '\n';
    }

    public static void purchase(Booth a,int num){
        if(num<=0){
            System.out.println("购买西瓜的数量错误（必须大于零）----交易失败！！！");
            return;
        }else if(a.isClosed == true){
            System.out.println("商家处于休业整顿中（无法购买）----交易失败！！！");
            return;
        }else if(num>a.total){
            System.out.println("商家["+a.id+"]所售西瓜数不足以购买----交易失败！！！");
            return;
        }else{
            a.total -=num;
            System.out.println("交易成功！！！\n");
            return;
        }
    }

    public void restock(int num){
        if(num>200){
            System.out.println("摊位["+this.id+"]进货失败！！！（进货数量需小于等于200）");
        }else if(num<0){
            System.out.println("摊位["+this.id+"]进货失败！！！（进货数量需大于等于0)");
        } else{
            this.total+=num;
            this.toString();
        }
    }

    public static void closeBooths(Booth[] booths){
        int num = 0;
        for(Booth booth:booths){
            if(!booth.isClosed){
                booth.setIsClosed(true);
            }else{
                System.out.println( booth.toString());
                num++;
            }
        }
        System.out.println("以上为已在休业的摊位信息(摊位数："+num+")");
    }


}
