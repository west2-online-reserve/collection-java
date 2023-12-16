package HomeworkWall;

import java.util.Scanner;

public class Watermelon {
    private long id;//摊号
    private String name;//姓名
    private int total;//在售西瓜
    private boolean isClosed;//是否休摊
    public Watermelon(long id,String name,int total,boolean isClosed){
        this.id=id;
        this.name=name;
        this.total=total;
        this.isClosed=isClosed;
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
        return "Watermelon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", isClosed=" + isClosed +
                '}';
    }
    Scanner scanner=new Scanner(System.in);
    //想X号摊位购买西瓜
    public static void purchase(Watermelon watermelon,int num){
        if (num<=0) {
            System.out.println("在"+watermelon.getName()+"购买失败，购买数量有误");
        } else if (num>watermelon.total) {
            System.out.println("在"+watermelon.getName()+"购买失败，西瓜数量不足");
        }else {
            System.out.println("在"+watermelon.getName()+"购买成功，购买了"+num+"个西瓜");
            watermelon.total-=num;
        }
    }
    //进货
    public void add(Watermelon watermelon,int num2){
        if (num2>200||num2<=0){
            System.out.println(watermelon.getName()+"进货失败");
        }else {
            total+=num2;
            System.out.println(watermelon.getName()+"进货成功,进了"+num2+"个西瓜");
        }
    }
    //判断西瓜摊是否休摊,输出西瓜摊信息
    public static void closeBooth(Watermelon []watermelon){
        for (int i=0;i< watermelon.length;i++){
            if(!watermelon[i].isClosed){
                watermelon[i].setClosed(true);
            }
        }
    }
}
