package work1;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int NumbersOfVisit;
    private LocalDate lastVisitDate;

    public Customer(String name) {
        this.name = name;
        this.NumbersOfVisit = 1;
        this.lastVisitDate = LocalDate.now();
    }

    @Override
    public String toString(){
        return "work1.Customer{" +
                "Name='" + name + '\'' +
                ", Numbers of visit=" + NumbersOfVisit +
                ", Time to shop='" + lastVisitDate + '\'' +
                '}';
    }

}
