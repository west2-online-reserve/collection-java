import java.time.LocalDate;

public class Customer extends AbstractAnimal {


    public Customer(String customerName, int numberOfVisits, LocalDate localVisit) {
        this.customerName = customerName;
        this.numberOfVisits = numberOfVisits;
        this.localVisit = localVisit;
    }

    /* 顾客名字(String)
        到店次数(int)
        最新到店时间(LocalDate类)
        方法
        重写(@Override) toString() 方法, 要求按一定格式输出客户的所有信息*/
    private String customerName;
    private int numberOfVisits;
    private LocalDate localVisit;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public LocalDate getLocalVisit() {
        return localVisit;
    }

    public void setLocalVisit(LocalDate localVisit) {
        this.localVisit = localVisit;
    }

    //顾客当天买的宠物


    @Override
    public String toString() {
        String s = " customerName: " + getCustomerName();
        s = s + " numberOfVisits: " + getNumberOfVisits();
        s = s + " localVisit: " + getLocalVisit();
        return s;
    }
}
