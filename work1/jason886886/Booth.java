package work1;

public class Booth {
    private long id;//摊号
    private String name;//摊主姓名
    private int total;//在售西瓜数
    private boolean isClosed; //是否休摊整改


    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

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

    public boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isclosed) {
        this.isClosed = isclosed;
    }

    @Override
    public String toString() {
        System.out.println("id:" + this.getId());
        System.out.println("name:" + this.getName());
        System.out.println("total:" + this.getTotal());
        System.out.println("isClosed:" + this.getIsClosed());
        return super.toString();
    }

    public static void purchase(Booth booth,int num) {
        if(booth.getIsClosed()==false&&num>0&&num<=booth.getTotal()){
            System.out.println("购买成功");
            booth.total= booth.total-num;
        }
        else{
            System.out.println("购买失败");
        }
    }
    public void restock(int num){
        if(num>0&&num<=200){
            System.out.println("进货成功");
            this.total+=num;
        }else{
            System.out.println("进货失败");
        }
    }
    public static void closeBooths(Booth[] booths){
        int length = booths.length;
        System.out.println("已休业整改:");
        for(int time=0;time<length;time++){
            if(booths[time].getIsClosed()==true){
                booths[time].toString();
            }else{
                booths[time].setIsClosed(true);
            }
        }

    }

    public Booth() {

    }
}
