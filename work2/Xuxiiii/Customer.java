package Xuxiiii;
import java.time.LocalDate;

public class Customer {
    private String name;
    // 顾客名字
    private int times;
    // 顾客到店次数
    private LocalDate now;
    // 顾客最新到店时间
    public Customer(String name, int times, LocalDate now){
        this.name=name;
        this.times=times;
        this.now=now;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }
    public void setTimes(int times) {
        this.times = times;
    }

    public LocalDate getNow() {
        return now;
    }
    public void setNow(LocalDate now) {
        this.now = now;
    }
    @Override
    public String toString(){
        return "name is "+name+
                " times is "+times+
                " now is "+now;
    }
}
