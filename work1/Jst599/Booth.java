package west_2_work_JST;

public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getTotal(){
        return total;
    }

    public void setTotal(int total){
        this.total = total;
    }

    public boolean getClosed(){   
        return isClosed;
    }

    public void setClosed(boolean isClosed){
        this.isClosed = isClosed;
    }

    public String toString(){
        return "摊号是"+id+"摊主是"+name+"在售西瓜数是"+total+"是否休摊整改"+isClosed;
    }

    public Booth(long id,String name,int total,boolean isClosed){
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    public static void purchase(Booth booth,int num){
        if (num>0&&booth.isClosed==false&&num< booth.total){
            System.out.println("交易成功");
            booth.total -= num;
        }else {
            System.out.println("交易失败");
        }
    }

    public void restock(Booth booth,int input){
        if (input > 200||input <0){
            System.out.println("进货失败");
        }else {
            booth.total += input;
        }
    }

    public static void closeBooths(Booth[] booths){

        for (int i = 0;i<booths.length;i++){
            //boolean flag = true;
            if (booths[i].isClosed == false){
                booths[i].isClosed = true;
            }else {
                System.out.println(booths[i].toString());
            }
        }
    }
}
