package Instance.Customers;

import java.time.LocalDate;

public class Customer {
    String name;
    public int visitNumber;
    LocalDate finalVisitDate;
    public double spend;

    public Customer(String name, int visitNumber) {
        this.name = name;
        this.visitNumber = visitNumber;
    }

    @Override
    public String toString() {
        return "姓名："+name+","+
                "总访问次数："+visitNumber+","+"最后访问时间："+finalVisitDate.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFinalVisitDate() {
        return finalVisitDate;
    }

    public void setFinalVisitDate(LocalDate finalVisitDate) {
        this.finalVisitDate = finalVisitDate;
    }
}
