public class Booth {

    private long id;
    private String name;
    private int total;
    private boolean isClosed;

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

    public boolean getisClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    @Override
    public String toString() {
        return "waterMelon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", isClosed=" + isClosed +
                '}';
    }

    public Booth(long id, String name, int total, boolean isClosed){
        this.id=id;
        this.name=name;
        this.total=total;
        this.isClosed=isClosed;
    }
    public static void purchase(Booth man,int purchase){
        if(purchase<=0){
            System.out.println("购买数量需大于零");
            return;
        }
        if( man.getisClosed()==false){
            System.out.println("已收摊");
            return;
        }
        if(purchase> man.getTotal()){
            System.out.println("存货不足");
            return;
        }
        man.total-=purchase;
        System.out.println("交易成功");
    }
    public void restock(int getmelon){
        if(getmelon>200){
            System.out.println("进货过多,进货失败");
            return;
        }
        if(getmelon<0){
            System.out.println("进货书不可小于0");
            return;
        }
        this.total+=getmelon;
        System.out.println("进货成功");
    }
    public static void closeBooth(Booth[] booths){
        for(int i=0;i< booths.length;i++){
            if(booths[i].getisClosed()==false){
                booths[i].setClosed(true);
            }
            else{
                System.out.println(booths[i].toString());
            }
        }
    }
}