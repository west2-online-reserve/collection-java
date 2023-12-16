import java.time.LocalDate;

public class Customer {
    //姓名
    private String customerName;
    //访问次数
    private int times;
    //时间
    private LocalDate date;


    Customer(String customerName) {
        this.customerName = customerName;
        this.times = 1;
        this.date = LocalDate.now();
    }


    public String getCustomerName() {
        return customerName;
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
        return "客户名字：" + customerName + "\n到店次数:" + times + "\n最新到店时间:" + date;
    }
}
