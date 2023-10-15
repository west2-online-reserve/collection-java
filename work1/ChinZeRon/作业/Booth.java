package homework;
public class Booth {
    //Booth类中包含private变量的命名、get/set方法、有参构造器、toString()方法重写
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    //getset1:西瓜摊号的get/set方法
    public long getId() {
        return id;
    }
    public long setId(long a) {
        this.id = a ;
        return a;
    }
    //getset2:西瓜摊名的get/set方法
    public String getName() {
        return name;
    }
    public String setName(String name) {
        this.name = name ;
        return name;
    }
    //getset3:西瓜摊在售西瓜数的get/set方法
    public int getTotal() {
        return total;
    }
    public int setTotal(int a) {
        this.total = a ;
        return a;
    }
    //getset4:西瓜摊的是否休摊整改的get/set方法
    public boolean getIsClosed() {
        return isClosed;
    }
    public boolean setIsClosed(boolean a) {
        this.isClosed = a ;
        return a;
    }
    //有参构造器：
    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    //toString()方法重写
    @Override
    public String toString() {
        return "Booth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", isClosed=" + isClosed +
                '}';
    }
    //restock方法
    public void restock(Booth booth ,int restockTotal){
        if (restockTotal > 200) {
            System.out.println("进货失败！进货量>200!");
        } else if (restockTotal < 0) {
            System.out.println("进货失败！请输入正确的进货数据！");
        } else {booth.setTotal(booth.getTotal() + restockTotal);}
    }
}
