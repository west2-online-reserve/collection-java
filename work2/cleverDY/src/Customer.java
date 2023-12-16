import java.time.LocalDate;

public class Customer {
    private int visitCount;
    private LocalDate latestArrivedTime;
    private String name;
    private LocalDate latestVisit;
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
    // 构造方法
    public Customer(String name, int visitCount, LocalDate latestVisit) {
        this.name = name;
        this.visitCount = visitCount;
        this.latestVisit = latestVisit;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public LocalDate getLatestVisit() { return latestVisit; }

    public void setLatestVisit(LocalDate latestVisit) {
        this.latestVisit = latestVisit;
    }
    // 重写toString方法
    @Override
    public String toString() {
        return "Customer: " + name + ", Visit Count: " + visitCount + ", Latest Visit: " + latestVisit.toString();
    }

}
