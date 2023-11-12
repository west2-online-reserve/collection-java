package collection

import java.time.LocalDate;

public class Customer {
    private String name;
    private int times;//到店次数
    private LocalDate lastTime;//最近到店时间
    private LocalDate getLastTime() {
        return lastTime;
    }
    public void setLastTime(LocalDate lastTime) {
        this.lastTime = lastTime;
    }
    public void setTimes(int times) {
        this.times = times;
    }
    @Override
    public String toString(){
        return "Customer name: "+this.name+"  到店次数:"+this.times+"  最新到店时间:"+lastTime;
    }

    public Customer(String name, int times) {
        this.name = name;
        this.times = times;
        this.lastTime = LocalDate.now();
    }
}
