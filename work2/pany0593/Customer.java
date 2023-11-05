import java.time.LocalDate;

/**
 * 顾客类
 *
 * @author pany0593
 * @date 2023/10/30
 */
public class Customer {
    private String name;
    private int arriveTimes;
    private LocalDate latestArriveTime;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setArriveTimes(int arriveTimes) {
        this.arriveTimes = arriveTimes;
    }

    public int getArriveTimes() {
        return this.arriveTimes;
    }

    public void setLatestArriveTime(LocalDate latestArriveTime) {
        this.latestArriveTime = latestArriveTime;
    }

    public LocalDate getLatestArriveTime() {
        return this.latestArriveTime;
    }

    Customer(String name, int arriveTimes, LocalDate latestArriveTime) {
        this.name = name;
        this.arriveTimes = arriveTimes;
        this.latestArriveTime = latestArriveTime;
    }

    @Override
    public String toString() {
        return "姓名：" + this.getName() + "\n" +
                "到店次数：" + this.getArriveTimes() + "\n" +
                "最新到店时间：" + this.getLatestArriveTime() + "\n";
    }

}
