public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClose;

    @Override
    public String toString() {
        return "Booth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", isClose=" + isClose +
                '}';
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

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean close) {
        isClose = close;
    }

    public Booth(long id, String name, int total, boolean isClose) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClose = isClose;
    }

    public Booth() {
    }

    public static void purchase(Booth b, int number) {
        if (number > 0 && number < b.total && !b.isClose) {
            b.total -= number;
        } else {
            System.out.print("购买失败；");
            if (number < 0 && number < b.total && !b.isClose) {
                System.out.println("你输入的购买数有问题");
            } else if (number > 0 && number > b.total && !b.isClose) {
                System.out.println("店家西瓜数不足");
            } else if (number > 0 && number < b.total && b.isClose) {
                System.out.println("店家已关门！");
            }
        }
    }

    public void restock(int number){
        if(number < 200 && number >=0){
            this.total += number;
            System.out.println("进货成功");
        }else {
            System.out.print("进货失败；");
            if(number > 200 ){
                System.out.println("进货数量过多");
            } else if (number < 0) {
                System.out.println("进货数不能小于0");
            }
        }
    }

    public static void closeBooths(Booth[] booths){
        for (int i = 0; i < booths.length; i++) {
            if(booths[i].isClose == false){
                booths[i].isClose = true;
            }
            System.out.println(booths[i].toString());
        }

    }
}