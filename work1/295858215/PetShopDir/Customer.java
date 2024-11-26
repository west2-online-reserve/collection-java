import java.time.LocalDate;
import java.time.LocalTime;
class Customer {
    private String name;
    private int enterTimes;
    private LocalDate localDate;
    public Customer(){

    }
    public Customer(int enterTimes,String name,LocalDate localDate) {
        this.enterTimes = enterTimes;
        this.localDate = localDate;
        this.name = name;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Customer{" +
                "enterTimes=" + enterTimes +
                ", name='" + name + '\'' +
                ", localDate=" + localDate +
                '}';
    }

    public int getEnterTimes() {
        return enterTimes;
    }

    public void setEnterTimes(int enterTimes) {
        this.enterTimes = enterTimes;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
