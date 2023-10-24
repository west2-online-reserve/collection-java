import java.time.LocalDate;
import java.time.LocalTime;

public class Customer {
    // 客户名字
    private String name;
    // 访问次数
    private int times;
    // 最新到店时间
    private LocalDate date;


    Customer(String name){
        this.name = name;
        times=1;
        date=LocalDate.now();

    }

    public String getName() {
        return name;
    }

    public void addTimes() {
        this.times += 1;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate() {
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return "客户名字："+name+"\n到店次数:"+times+"\n最新到店时间:"+date;
    }


}
