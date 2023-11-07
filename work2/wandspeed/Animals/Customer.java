import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitCount;
    private LocalDate latestVisitDate;

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

    public LocalDate getLatestVisitDate() {
        return latestVisitDate;
    }

    public void setLatestVisitDate(LocalDate latestVisitDate) {
        this.latestVisitDate = latestVisitDate;
    }

    public Customer(String name, int visitCount, LocalDate latestVisitDate) {
        this.name = name;
        this.visitCount = visitCount;
        this.latestVisitDate = latestVisitDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", visitCount=" + visitCount +
                ", latestVisitDate=" + latestVisitDate +
                '}';
    }
}

