import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitTimes;
    private LocalDate latestVisitDate;

    public Customer(String name, int visitTimes) {
        this.name = name;
        this.visitTimes = visitTimes;
        this.latestVisitDate =LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(int visitTimes) {
        this.visitTimes = visitTimes;
    }

    public LocalDate getLatestVisitDate() {
        return latestVisitDate;
    }

    public void setLatestVisitDate(LocalDate latestVisitDate) {
        this.latestVisitDate = latestVisitDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", visitTimes=" + visitTimes +
                ", latestVisitDate=" + latestVisitDate +
                '}';
    }
}
