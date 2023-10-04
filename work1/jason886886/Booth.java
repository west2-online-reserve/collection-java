package work1;

public class Booth {
    private long id;//摊号
    private String name;//摊主姓名
    private int total;//在售西瓜数
    private boolean isClosed; //是否休摊整改

    public static void main(String[] args) {
        Booth booth1 = new Booth(123, "mark", 23, false);
        booth1.toString();

        Booth booth2 = new Booth();
        booth2.setId(124);
        booth2.setName("jack");
        booth2.setTotal(201);
        booth2.setIsClosed(true);

        purchase(booth1,40);

        booth2.restock(300);

        closeBooths(new Booth[]{booth1, booth2});

    }

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
        for(int time1=0;time1<length;time1++){
            if(booths[time1].getIsClosed()==true){
                booths[time1].toString();
            }
        }
        for( int time2=0;time2<length;time2++){
            if(booths[time2].getIsClosed()==false){
                booths[time2].setIsClosed(true);
            }
        }
    }

    public Booth() {

    }
}
