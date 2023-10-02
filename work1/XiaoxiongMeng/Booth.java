public class Booth {
    private long id;  //摊位号
    private String name;  //摊主姓名
    private int total;  //在售西瓜总数
    private boolean isClosed;  //是否关门大吉

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public void getId(){
        System.out.println(this.id);
    }

    public void getIsClosed(){
        System.out.println(this.isClosed);
    }

    public void getName(){
        System.out.println(this.name);
    }

    public void getTotal(){
        System.out.println(this.total);
    }

    @Override
    public String toString() {
        return "Booth{" +
                "摊位号id=" + id +
                ", 摊主姓名name='" + name + '\'' +
                ", 剩余西瓜总数total=" + total +
                ", 是否休摊isClosed=" + isClosed +
                '}';
    }

    public Booth(long id, String name, int total, boolean isClosed){
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    public static void purchase(Booth seller, int number){
        if(number<=0){
            System.out.println("必须购买大于0个！");
        }
        else if(seller.isClosed){
            System.out.println("该卖家已休摊！");
        }else if(seller.total<number){
            System.out.println("卖家不能满足你的需求！");
        }else{
            seller.total = seller.total - number;
            System.out.println("购买成功！");
        }
    }

    public void restock(int number){
        if(number>200){
            System.out.println("太多啦！吃不消啦");
        }else if(number<0){
            System.out.println("你是进货商，不能偷哟！");
        }else{
            this.total += number;
            System.out.println("成功进货");
        }
    }

    public static void closeBooths(Booth[] booths){
        int number = booths.length;
        for(int i=0;i<number;i++){
            if(booths[i].isClosed){
                System.out.println(booths[i].toString());
            }else{
                booths[i].isClosed = true;
            }
        }
    }
}
