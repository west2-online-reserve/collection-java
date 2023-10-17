import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class Customer {
    protected String name;
    protected int visitTimes;
    protected LocalDate latestVisitDate;

    public Customer(String name){
        this.setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitTimes() {
        return this.visitTimes;
    }

    public void setVisitTimes(int visitTimes) {
        this.visitTimes = visitTimes;
    }

    public LocalDate getLatestVisitDate() {
        return this.latestVisitDate;
    }

    public void setLatestVisitDate(LocalDate latestVisitDate) {
        this.latestVisitDate = latestVisitDate;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", " +
                "Visit Times: " + this.getVisitTimes() + ", " +
                "Latest Visit Date: " + this.getLatestVisitDate() ;
    }
}
