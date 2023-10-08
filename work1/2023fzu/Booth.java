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
    public boolean getIsClosed() {
        return isClosed;
    }
    public void setIsClosed(boolean closed) {
        isClosed = closed;
    }
    public Booth(long id, String name, int total, boolean isClosed){
        this.id=id;
        this.name=name;
        this.total=total;
        this.isClosed=isClosed;
    }
    public Booth(){
        this(0,"Booth",2000,false);
    }

    @Override
    public String toString() {
        return "Booth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", isClosed=" + isClosed +
                '}';
    }

    public static void purchase(Booth booth, int number) {
        if(booth.getIsClosed()){
            System.out.println("店家已歇业,交易失败");
        }else if(number<=0){
            System.out.println("购买的西瓜数必须为正数,交易失败");
        }else if(booth.getTotal()<number){
            System.out.println("购买西瓜数不能大于在售西瓜数,交易失败");
        }else{
        booth.setTotal(booth.getTotal()-number);
        System.out.println("交易成功");
        }
    }
    public void restock(int number) {
        if(number>200){
            System.out.println("单次进货量不能超过 200,进货失败");
        } else if (number<0) {
            System.out.println("单次进货量不能低于 0,进货失败");
        }else{
            int total=getTotal();
            setTotal(total+number);
            System.out.println("进货成功 ");
        }

    }
    public static void  closeBooths(Booth[] booths) {
        for (Booth booth:booths
             ) {
            boolean isClosed=booth.getIsClosed();
            if(isClosed){
                System.out.println(booth.toString());
            }else{
                booth.setIsClosed(true);
            }
        }
    }
}
