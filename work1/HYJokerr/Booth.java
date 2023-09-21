public class Booth {
    private long id;
    private String name;
    private int tota;
    private boolean isClosed;

    public long getId() {
        return id;
    }
    public void setId(long n){
        id=n;
    }

    public String getName(){
        return name;
    }
    public void setName(String s){
        name=s;
    }

    public int getTota(){
        return tota;
    }
    public void setTota(int n){
        tota=n;
    }

    public boolean getIsClosed(){
        return isClosed;
    }
    public void setIsClosed(boolean b){
        isClosed=b;
    }

    public String toString(){
        return "西瓜摊的摊号:"+id+"\n摊主的名字:"+name+"\n在售西瓜数:"+tota+"\n当前是否休摊:"+isClosed+"\n";
    }

    public Booth(long i,String n,int t,boolean c){
        id=i;
        name=n;
        tota=t;
        isClosed=c;
    }

    public static void purchase(Booth business, int num){
        if (num<=0){
            System.out.println("购买的西瓜数量应为正数!");
            return;
        }
        if(business.isClosed){
            System.out.println("商家处于休摊整改状态！");
            return;
        }
        if(num>business.tota){
            System.out.println("你买的瓜太多了！商家店也卖你得了。");
            return;
        }
        System.out.println("购买成功!");
        business.tota-=num;
    }

    public void restock(int num){
        if(num>200){
            System.out.println("单次进货量不能超过200!");
            return;
        }
        if(num<=0){
            System.out.println("单次进货量应为正数!");
            return;
        }
        tota+=num;
    }

    public static void closeBooths(Booth[] booths){
        int n=booths.length;
        for(int i=0;i<n;i++){
            if(!booths[i].isClosed){
                booths[i].isClosed=true;
            }
            else{
                System.out.println("该摊位已在休业整改:\n");
                System.out.println(booths[i].toString());
            }
        }
    }

    public static void main(String[] args) {


    }
}
