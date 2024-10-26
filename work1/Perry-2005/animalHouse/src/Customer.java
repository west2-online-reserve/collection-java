import java.time.LocalDate;

public class Customer {
    private String name;
    private int count;
    private LocalDate lastTime;
    public Customer() {}
    public Customer(String name, int count, LocalDate lastTime) {
        this.name = name;
        this.count = count;
        this.lastTime = lastTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public LocalDate getLastTime() {
        return lastTime;
    }
    public void setLastTime(LocalDate lastTime) {
        this.lastTime = lastTime;
    }
    @Override
    public String toString() {
        return "Customer [name=" + name + ", count=" + count + ", lastTime=" + lastTime + "]";
    }
}
