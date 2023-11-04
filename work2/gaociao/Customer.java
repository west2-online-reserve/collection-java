import java.time.LocalDate;

public class Customer {
    protected String name;
    protected int arrivalTimes;
    protected LocalDate lastArriveTime;

    public Customer() {
    }

    public Customer(String name, int arrivalTimes) {
        this.name = name;
        this.arrivalTimes = arrivalTimes;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getArrivalTimes() {
        return arrivalTimes;
    }


    public void setArrivalTimes(int arrivalTimes) {
        this.arrivalTimes = arrivalTimes;
    }

    public LocalDate getLastArriveTime() {
        return lastArriveTime;
    }


    public void setLastArriveTime(LocalDate lastArriveTime) {
        this.lastArriveTime = lastArriveTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", arrivalTimes=" + arrivalTimes +
                ", lastArriveTime=" + lastArriveTime +
                '}';
    }
}
