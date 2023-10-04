import java.util.Arrays;

public class Booth {
    //私有变量
    private Long id;
    private String name;
    private int total;
    private boolean isClosed;
    //上述变量对应的 get 和 set 方法
    public long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(){
        this.name=name;
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

    public void setIsClosed(boolean isClosed) {
        this.isClosed=isClosed;
    }
    //重写toString


    @Override
    public String toString() {
        return "watermelonStall[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", isClosed=" + isClosed +
                ']';
    }
    //带参构造方法
    public Booth(Long id,String name,int total,boolean isClosed){
        this.id=id;
        this.name=name;
        this.total=total;
        this.isClosed=isClosed;
    }
    //购买西瓜
    public static void purchase(Booth booth,int purNum){
        if(purNum<0){
            System.out.println("购买失败，购买数量错误");
        } else if (booth.isClosed) {
            System.out.println("购买失败，该摊已休摊");
        } else if (purNum> booth.total) {
            System.out.println("购买失败，该摊在售西瓜数不足");
        }else {
            booth.setTotal(booth.getTotal()-purNum);
            System.out.println("购买成功");
        }
    }
    //实例方法进货
    public void restock(Booth booth,int resNum){
        if (resNum>200){
            System.out.println("进货失败，单次进货量不能超过 200");
        } else if (resNum<0) {
            System.out.println("进货失败，单次进货量不能少于0");
        } else{
            booth.setTotal(booth.getTotal()+resNum);
            System.out.println("进货成功");
        }
    }
    //摊位歇业
    public static void closeBooths(Booth[] booths){
        for (int i = 0; i < booths.length; i++) {
            if (!booths[i].getIsClosed()) {
                booths[i].setIsClosed(true);
            }else {
            System.out.println(booths[i].toString());
            }
        }
    }
}
