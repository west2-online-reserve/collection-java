package Test;

import java.time.LocalDate;

public class Customer {

    protected String name;
    protected int arriveTimes;
    protected LocalDate VisitDate;

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", arriveTimes=" + arriveTimes +
                ", theLatestArriveTime=" + VisitDate +
                '}';
    }

    public Customer() {
    }

    public Customer(String name, int arriveTimes, LocalDate VisitDate) {
        this.name = name;
        this.arriveTimes = 1;  // 就来一次
        this.VisitDate = VisitDate;
    }

    public LocalDate getVisitDate(){
        return VisitDate;
    }
}
