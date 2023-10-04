package homework;

public class Booth {
    private String name;
    private int total;
    private long id;
    private boolean isClosed;
    public String getName(){
        return this.name;
    }
    public long getId(){
        return this.id;
    }
    public int getTotal(){
        return this.total;
    }
    public boolean getIsClosed(){
        return this.isClosed;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public Booth(int total,String name,long id,boolean isClosed) {
        this.total = total;
        this.name = name;
        this.id=id;
        this.isClosed=isClosed;
    }

    @Override
    public String toString() {
        return "西瓜摊"  + id + "号"+" " +"姓名" +name +" "+ "数量为" + total + " "+ "是否整改" + isClosed;
    }

    public static void purchase(Booth booth ,int nums){
        if (nums<=0||nums>booth.total||booth.isClosed) {
            System.out.println("交易失败");
        } else {booth.total -= nums;
        System.out.println("交易成功");}
        return;
    }
    public void restock(int quantity) {
        if (quantity<=0||quantity>200) {
            System.out.println("进货失败");
            return;
        } else {total += quantity;
        System.out.println("现有" + quantity + "个西瓜");}
    }
    public static void closeBooths(Booth[] Booths) {
        for(int i=0;i<Booths.length;i++) {
            if (Booths[i].getIsClosed()) {
                System.out.println(Booths[i]);
            } else {
                Booths[i].setIsClosed(true);}
        }
    }



}

