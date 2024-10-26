/*顾客类*/

import java.time.LocalDate;

public class Customer {
    protected String name;
    protected int times;
    protected LocalDate visitTime;
    protected boolean isVisit;

    public Customer(String name, int times, LocalDate visitTime) {
        this.name = name;
        this.times = times;
        this.visitTime = visitTime;
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

    public LocalDate getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDate visitTime) {
        this.visitTime = visitTime;
    }

    @Override
    public String toString() {
        return "顾客的名字是：" + this.name + "\n" +
                "顾客的到店次数：" + this.times + "\n" +
                "顾客的最新到店时间：" + this.visitTime + "\n";
    }
}
