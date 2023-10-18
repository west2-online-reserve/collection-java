import java.time.LocalDate;

public class Customer{
    protected String customerName;
    protected int frequency;
    protected LocalDate arrivingTime;

@Override
        public String toString(){
        return "\ncustomerName:"+ customerName +" Frequency:"+ frequency +" ArrivingTime:"+ arrivingTime;
    }

    public Customer(String customername, int frequency, LocalDate arrivingTime) {
        this.customerName = customername;
        this.frequency = frequency;
        this.arrivingTime = arrivingTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public LocalDate getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(LocalDate ArrivingTime) {
        this.arrivingTime = ArrivingTime;
    }
}