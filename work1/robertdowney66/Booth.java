package com.west2.check01;

public class Booth {
   private long id;
   private String name;
   private  int total ;
   private  boolean isClosed;

    public  long getId() {
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
      return "\t 摊主姓名:"+this.getName()+"\t 是否休整整改:"+this.isClosed()+"\t 摊号:"+this.getId()
              +"\t 在售西瓜数:"+this.getTotal();
    }

    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }



    public static void purchase(Booth a,int b){
        if (a.isClosed==true||b<=0||b>a.total){
            System.out.println("购买失败");
        }else{
            System.out.println("交易成功");
            a.total-=b;
        }

    }
    public void restock(int b){
        if (b>200){
            System.out.println("进货失败");
        }else{
            total+=b;
            System.out.println("进货成功");
        }

    }
    public static void closeBooths(Booth[] booths){
        for (int i = 0; i < booths.length; i++) {
            if (booths[i].isClosed==false){
                booths[i].isClosed=true;

            }else{
                System.out.println(booths[i].toString());
            }
        }

    }
}
