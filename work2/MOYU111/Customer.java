import java.time.LocalDate;

public class Customer {
    private String name;
    private int times = 0;
    private LocalDate now;

    public Customer(String n,int t,LocalDate now){
        this.name = n;
        this.times = t;
        this.now = now;
    }

    public String getName() {
        return name;
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

    public String toString(){
        return "名字是："+name+
                "\n到店次数为："+times+
                "\n最近到店日期为："+now+
                "\n"
                ;
    }


}
