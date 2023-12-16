import java.time.LocalDate;

/**
 * Customer类中存放顾客的信息
 *
 * @author AGoodYear
 * @date 2023/11/4
 */
public class Customer {
    private String name;
    private int arrivalTime;
    private LocalDate latestArrival;
    public Customer(String name) {
        this.name = name;
        arrivalTime = 0;
    }

    public void setLatestArrival(LocalDate latestArrival) {

        this.latestArrival = latestArrival;
    }

    public void plusArrivalTime() {
        arrivalTime++;
    }

    public String getName() {
        return name;
    }

    public LocalDate getLatestArrival() {
        return latestArrival;
    }

    @Override
    public String toString() {
        String temp;
        temp = "顾客姓名：" + name + "\n到店次数" + arrivalTime + "\n最新到店：" + latestArrival;
        return temp;
    }
}
