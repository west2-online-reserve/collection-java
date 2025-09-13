package work1;


import java.time.LocalTime;

public class Customer {

    private String customername;
    private int count=0;
   private LocalTime time;//最新到店时间

    public Customer(String customername) {
        this.customername = customername;
    }

    public Customer(String customername, int count, LocalTime time) {
        this.customername = customername;
        this.count = count;
        this.time = time;
    }

    public String getCustomername() {

        return customername;
    }

    public void setCustomername(String customername) {

        this.customername = customername;
    }

    public int getCount() {

        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "顾客名字:" + customername  + ",到店次数:" + count + ",最新到店时间:"+time;
    }
}
