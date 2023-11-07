import java.time.LocalDate;

class Customer {
    public int times;
    public LocalDate latestArrivedTime;
    String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 成员变量

    private int visitCount;
    private LocalDate latestVisit;

    // 构造方法
    public Customer(String name, int visitCount, LocalDate latestVisit) {
        this.name = name;
        this.visitCount = visitCount;
        this.latestVisit = latestVisit;
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "Customer: " + name + ", Visit Count: " + visitCount + ", Latest Visit: " + latestVisit.toString();
    }

}
