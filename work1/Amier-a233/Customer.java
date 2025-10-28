import java.time.LocalDate;

public class Customer {
    /*成员变量:
    顾客名字(String)
    到店次数(int)
    最新到店时间(LocalDate 类)
    方法
    重写(@Override) toString() 方法, 要求按一定格式输出客户的所有信息*/
    // 成员变量
    private String customerName;  // 顾客名字
    private int visitCount;       // 到店次数
    private LocalDate latestVisitDate;  // 最新到店时间

    // 构造方法
    public Customer(String customerName, int visitCount, LocalDate latestVisitDate) {
        this.customerName = customerName;
        this.visitCount = visitCount;
        this.latestVisitDate = latestVisitDate;
    }

    // 重写toString()方法，按一定格式输出客户的所有信息
    @Override
    public String toString() {
        return "客户信息{" +
                "姓名='" + customerName + '\'' +
                ", 到店次数=" + visitCount +
                ", 最新到店时间=" + latestVisitDate +
                "}";
    }

    // getter和setter方法（符合阿里巴巴开发规范）
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public LocalDate getLatestVisitDate() {
        return latestVisitDate;
    }

    public void setLatestVisitDate(LocalDate latestVisitDate) {
        this.latestVisitDate = latestVisitDate;
    }
}
