import java.time.LocalDate;

public class Customer {
    private String name;
    private int times;
    private LocalDate latestArrivedTime;

    public Customer(String name, int times) {
        this.name = name;
        this.times = times;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public LocalDate getLatestArrivedTime() {
        return latestArrivedTime;
    }

    public void setLatestArrivedTime(LocalDate latestArrivedTime) {
        this.latestArrivedTime = latestArrivedTime;
    }

    @Override
    public String toString() {
        return "顾客" + name + " 光临了" + times +
                "次 上次光临时间是" + latestArrivedTime;
    }
}


