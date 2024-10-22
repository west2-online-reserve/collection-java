import java.time.LocalDate;

public class Customer {
    protected String name; //姓名
    protected int times;  //到店次数
    protected LocalDate recentDate = LocalDate.now(); //最近到店日期
    protected Animal PurchasedAnimal;

    public Customer(String name, int times, LocalDate recentDate) {
        this.name = name;
        this.times = times;
        this.recentDate = recentDate;
    }

    public Customer(String name) {
        this.name = name;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setRecentDate(LocalDate recentDate) {
        this.recentDate = recentDate;
    }

    public String getName() {
        return name;
    }

    public int getTimes() {
        return times;
    }

    public LocalDate getRecentDate() {
        return recentDate;
    }


    public Animal getPurchasedAnimal() {
        return PurchasedAnimal;
    }

    public void setPurchasedAnimal(Animal purchasedAnimal) {
        PurchasedAnimal = purchasedAnimal;
    }

    @Override
    public String toString() {
        return "Customer" + "\n" +
                "-----------" + "\n" +
                "name: " + name + "\n" +
                "times: " + times + "\n" +
                "recentDate: " + recentDate + "\n";
    }
}
