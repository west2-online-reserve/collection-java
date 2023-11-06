import java.time.LocalDate;
import java.util.Date;

public class Customer {
    private String name;
    private int visitNumber;
    private LocalDate lastTime;

    public Customer(){

    }

    public Customer(String name, int visitNumber) {
        this.name = name;
        this.visitNumber = visitNumber;
        this.lastTime = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(int visitNumber) {
        this.visitNumber = visitNumber;
    }

    public LocalDate getLastTime() {
        return this.lastTime;
    }

    public void setLastTime(LocalDate lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", visitNumber=" + visitNumber +
                ", lastTime=" + lastTime +
                '}';
    }
}
