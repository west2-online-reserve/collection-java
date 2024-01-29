package work1;

public class Booth {
    long id;
    String name;
    int total;
    boolean isClosed;

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
        return "Booth{"
                + id +
               "\t" + name +"\t" +
                 total +
                "\t" + isClosed +
                '}';
    }

    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    public static void purchase(Booth booths,int num) {
        if(num<=0) {
            System.out.println("购买失败，购买的西瓜数必须为正数");
            return;
        }
        else if(booths.isClosed) {
            System.out.println("购买失败，商家正在休摊整改");
            return;
        }
        else if(num> booths.total) {
            System.out.println("购买失败，购买的西瓜数不能大于在售西瓜数");
            return;
        }
        else {
            booths.total-=num;
            System.out.println("购买成功");
            return;
        }
    }

    public void restock(int num){
        if(num>200)
            System.out.println("进货失败，进货的数量不能超过200");
        else
            this.total+=num;
        return;
    }

    public static void closeBooths(Booth[] booths){
        for (Booth booth:booths) {
            if(!booth.isClosed)
                booth.isClosed=true;
            System.out.println(booth.toString());
        }
    }

}
