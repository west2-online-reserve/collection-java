package petShop.customer;

import java.time.LocalDate;

public class Customer {
    private static long lastId=0;
    private long id;
    private String name;
    private int visitCount;
    private LocalDate lastVisitDate;

    {
        synchronized (Customer.class) {
            id=++lastId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public Customer(String name) {
        this.name = name;
        this.visitCount++;
        this.lastVisitDate = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", visitCount=" + visitCount +
                ", lastVisitDate=" + lastVisitDate +
                '}';
    }
}
