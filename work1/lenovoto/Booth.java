package homework;

public class Booth {
    public Booth() {
    }

    public Booth(String name, int total, long id, boolean isClosed) {
        this.name = name;
        this.total = total;
        this.id = id;
        this.isClosed = isClosed;
    }

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



    @Override
    public String toString() {
        System.out.println("Booth{" +
                "name='" + name + '\'' +
                ", total=" + total +
                ", id=" + id +
                ", isClosed=" + isClosed +
                '}');
        return super.toString();
    }

   
    public static void purchase(Booth booth ,int nums){
        if (nums<=0||nums>booth.total||booth.isClosed) {
            System.out.println("交易失败");
        } else {booth.total -= nums;
            System.out.println("交易成功");
            booth.total-=nums;
        }
        return;
    }
    
    public void restock(int quantity) {
        if (quantity<=0||quantity>200) {
            System.out.println("进货失败");
            return;
        } else {total += quantity;
            System.out.println("现有" + quantity + "个西瓜");
        }
    }
   
    public static void closeBooths(Booth[] Booths) {
        for(Booth booths:Booths) {
            if (booths.getIsClosed()) {
                booths.toString();
            } else {
                booths.setIsClosed(true);
                booths.toString();
            }
        }
    }



}
/*package homework;

public class Application {
    public static void main(String[] args) {
        Booth booth=new Booth();
        booth.setId(100);
        booth.setIsClosed(false);
        booth.setName("西瓜摊1");
        booth.setTotal(666);
        booth.getId();
        booth.getName();
        booth.getTotal();
        booth.getIsClosed();
        //=======================
        booth.toString();
        int buy=50;
        booth.purchase(booth,buy);
        booth.restock(buy);
        Booth[] booths={booth};
        booth.closeBooths(booths);

    }
}*/


/*Booth{name='西瓜摊1', total=666, id=100, isClosed=false}
交易成功
现有50个西瓜
Booth{name='西瓜摊1', total=616, id=100, isClosed=true}*/




