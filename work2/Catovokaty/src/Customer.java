import java.time.LocalDate;

public class Customer {
    private String name;
    private int time;
    private LocalDate arriveTime;

    public Customer(String name, int time, LocalDate arriveTime) {
        this.name = name;
        this.time = time;
        this.arriveTime = arriveTime;
    }

    public String getName() {
        return name;
    }

    public int time() {
        return time;
    }

    public void settime(int time) {
        this.time = time;
    }

    public void setarriveTime(LocalDate arriveTime) {
        this.arriveTime = arriveTime;
    }

    public LocalDate getarriveTime() {
        return arriveTime;
    }

    @Override
    public String toString() {
        return "name is" + name + "time is" + time + "arriveTime is" + arriveTime;
    }
}
