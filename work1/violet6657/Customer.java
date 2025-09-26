import java.time.LocalDate;
import java.time.LocalTime;

public class Customer {
    private String name;
    private int shoppingTimes;
    private LocalTime latestShoppingTime;

    public Customer() {
    }
    public Customer(String name, int shoppingTimes, LocalTime latestShoppingTime) {
        this.name = name;
        this.shoppingTimes = shoppingTimes;
        this.latestShoppingTime = latestShoppingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShoppingTimes() {
        return shoppingTimes;
    }

    public void setShoppingTimes(int shoppingTimes) {
        this.shoppingTimes = shoppingTimes;
    }

    public LocalTime getLatestShoppingDate() {
        return latestShoppingTime;
    }

    public void setLatestShoppingTime(LocalTime latestShoppingDate) {
        this.latestShoppingTime = latestShoppingDate;
    }

    public String toString() {
        return "Customer{名字为 = " + name + ", 到店次数 = " + shoppingTimes + ", 最近一次到店时间为 = " + latestShoppingTime + "}";
    }
}
