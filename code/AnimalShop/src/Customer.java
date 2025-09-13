import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitTimes;
    private LocalDate latestVisitDate;
    private String wantToBuy;

    public Customer(String name,int visitTimes,String wantToBuy) {
        this.name = name;
        this.visitTimes = visitTimes;
        this.wantToBuy = wantToBuy;
    }

    public String getName() {
        return name;
    }

    public int getVisitTimes() {
        return visitTimes;
    }

    public LocalDate getLatestVisitDate() {
        return latestVisitDate.now();
    }

    public String toString() {
       return "Customer " + name + " is " + visitTimes + " visits in " + getLatestVisitDate();
    }

    public String getWantToBuy() {
        return wantToBuy;
    }


}
