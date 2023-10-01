public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;
    public Booth(long id, String name, int total, boolean isClosed){
        this.id=id;
        this.name=name;
        this.total=total;
        this.isClosed=isClosed;
    }
    public Booth(){

    }
    public long getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public int getTotal(){
        return total;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    @Override
    public String toString() {
        String s="摊号："+getId()+
                "\n摊主姓名："+getName()+
                "\n在售西瓜数："+getTotal()+
                "\n是否休摊整改："+getIsClosed();
        return s;
    }
    public static void purchase(Booth booth, int numsOfpurchases){
        if(numsOfpurchases<0|| booth.getIsClosed()==true||numsOfpurchases> booth.getTotal()){
            System.out.println("购买失败。");
        }else{
            System.out.println("购买成功。");
            booth.setTotal(booth.getTotal()-numsOfpurchases);
        }
    }
    public void restock(int numsOfpurchases){
        if (numsOfpurchases>200||numsOfpurchases<0){
            System.out.println("进货失败。");
        }else{
            total+=numsOfpurchases;
            System.out.println("进货成功");
        }
    }
    public static void closeBooths(Booth[] booths){
        for(int i=0;i<=booths.length-1;i++){
            if(booths[i].isClosed==false){
                booths[i].isClosed=true;
            }else{
                System.out.println(booths[i].toString());
            }
        }
    }
}
