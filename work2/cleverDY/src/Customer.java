import java.time.LocalDate;

class Customer {
    public int times;
    public LocalDate latestArrivedTime;
    // 成员变量
    String name;
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
