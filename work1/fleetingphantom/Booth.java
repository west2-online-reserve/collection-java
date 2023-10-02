
public class Booth {
	//西瓜摊
    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public long getId() { return id; }

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

    public boolean setisClosed() {
        return isClosed;
    }

    public void getisClosed(boolean closed) {
        isClosed = closed;
    }
    public String toString() {
        return "摊号:" + id + "\n摊主姓名:" + name +"\n在售西瓜数:"+ total +"\n是否休摊整改:" + isClosed ;
    }

    public static void purchase(Booth booth,int purchaseNumber){
        if (booth.isClosed) {
            System.out.println("购买失败，摊位正在休摊整改．");
        }else {
            if (purchaseNumber < 0) {
                System.out.println("购买失败，购买西瓜的数量不能低于0．");
            }
            else if (purchaseNumber > booth.total) {
                System.out.println("购买失败，摊位西瓜数不足．");
            } else {
                System.out.println("购买成功，购买了" + purchaseNumber + "个西瓜．");
                booth.setTotal(booth.getTotal()-purchaseNumber);
            }
        }
    }
    public void restock(int restockNumber){
        if(restockNumber>200){
            System.out.println("进货失败，进货数不能大于200．");
        }
        else if(restockNumber<0){
            System.out.println("进货失败，进货数不能小于0．");
        }else {
            System.out.println("进货成功，进了"+ restockNumber +"个西瓜．");
            setTotal(getTotal()+restockNumber);
        }
    }
    public static void closeBooths(Booth[]booths){
        for (int i =0;i <booths.length;i++) {
            if (!booths[i].isClosed) {
                booths[i].isClosed = true;
            } else {
                System.out.println(booths[i].toString());}

        }
        
    }

}
