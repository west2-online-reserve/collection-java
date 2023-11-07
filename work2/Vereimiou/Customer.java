import java.time.LocalDate;

public class Customer {
    /**
     * 顾客名字
     */
    private String name;
    /**
     * 顾客到店次数
     */
    private int arrivedTimes = 0;
    /**
     * 顾客上次到店时间
     */
    private LocalDate lastVisitedTime;

    public Customer(String name, int arrivedTimes, LocalDate lastVisitedTime) {
        this.name = name;
        this.arrivedTimes = arrivedTimes;
        this.lastVisitedTime = lastVisitedTime;
    }

    public String getName() {
        return name;
    }

    public int getArrivedTimes() {
        return arrivedTimes;
    }

    public LocalDate getLastVisitedTime() {
        return lastVisitedTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastVisitedTime(LocalDate lastVisitedTime) {
        this.lastVisitedTime = lastVisitedTime;
    }

    public void addArrivedTimes() {
        this.arrivedTimes++;
    }

    @Override
    public String toString() {
        return "Customer {" +
                "\nname: " + '"' + name + '"' +
                "\narrivedTimes: " + arrivedTimes +
                "\nlastVisitedTime: " + lastVisitedTime +
                "\n}";
    }
}
