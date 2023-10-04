import java.time.LocalDate;

public class Customer {
    protected String name;
    protected int times;
    protected LocalDate latestArrivedTime;

    public Customer(String name, int times) {
        this.name = name;
        this.times = times;
        this.latestArrivedTime = LocalDate.now();
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
        return "Customer{" +
                "name='" + name + '\'' +
                ", times=" + times +
                ", latestArrivedTime=" + latestArrivedTime +
                '}';
    }
}
