package work1;

public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

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

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    @Override
    public String toString() {
        return  id +
                "\t" + name +
                "\t\t" + total +
                "\t\t" + isClosed;
    }

    public static void purchase(Booth booth,int out) {
        if (out > 0 && out <= booth.getTotal() && !booth.isClosed()) {
            int num = booth.getTotal() - out;
            booth.setTotal(num);
            System.out.println("已成功购买" + out +"个西瓜");
        } else if (out < 0){
            System.out.println("购买量不可为负数,购买失败");
        } else if (out > booth.getTotal()){
            System.out.println("购买量大于总数,购买失败");
        } else if (booth.isClosed()){
            System.out.println("该摊位已休业整改,购买失败");
        }

    }

    public void restock(Booth booth,int in) {
        if (in > 0 && in <= 200) {
            int num = booth.getTotal() + in;
            booth.setTotal(num);
            System.out.println("已成功进货" + in + "个西瓜");
        } else if (in > 200){
            System.out.println("进货量不可超过200,进货失败");
        } else {
            System.out.println("进货量必须为正数,进货失败");
        }
    }

    public static void closeBooths(Booth[] booths) {
        for (int i = 0; i < booths.length; i++) {
            if (booths[i].isClosed()) {
                System.out.println(booths[i]);
            } else {
                booths[i].setClosed(true);
            }

        }
    }
}
