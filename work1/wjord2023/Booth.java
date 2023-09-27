public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    public long getId() {
        return id;
    }
    //get,set方法
    public String toString(){
        return "摊号：" + id + ",摊主名称：" + name + ",在售西瓜数：" + total + ",是否休业整改：" + isClosed;
    //toString()方法
    }
    public static void purchase(Booth shop,int buy){
        if(shop.getIsClosed() || buy >= shop.getTotal() || buy<=0 ){
            System.out.println("购买失败");
        }else{
            int i = shop.getTotal() - buy;
            shop.setTotal(i);
            System.out.println("购买成功，该摊还剩余"+i+"个西瓜");
        }
    }
    //购买西瓜


    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    public  Booth(){

    }
    //构造器
    public void restock(Booth booth,int input){
        if(input >200 || input <=0){
            System.out.println("进货失败");
        }else{
            int i = booth.getTotal() +input;
            booth.setTotal(i);
            System.out.println("进货后该商家的数据为："+booth.toString());
        }
    }
    //进货西瓜
    public static void closeBooths(Booth[] booths){
        for (int i = 0; i < booths.length; i++) {
            if(booths[i].isClosed){
                continue;
            }else{
                booths[i].setIsClosed(true);
                System.out.println(booths[i].toString());
            }
        }
    }
    //休业整改
}

