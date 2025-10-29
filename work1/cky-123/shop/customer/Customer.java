package shop.customer;

import java.time.LocalDate;

/**
 * Describe:
 *
 * @author cky
 * @date 2025/10/22
 */
public class Customer {
    private String name;
    private int NumberOfVisits;
    private LocalDate lastVisitDate;


    public Customer(String name, int numberOfVisits, LocalDate lastVisitDate) {
        this.name = name;
        NumberOfVisits = numberOfVisits;
        this.lastVisitDate = lastVisitDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfVisits() {
        return NumberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        NumberOfVisits = numberOfVisits;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + getName() + '\'' +
                ", NumberOfVisits=" + getNumberOfVisits() +
                ", lastVisitDate=" + getLastVisitDate() +
        '}';
    }
}