import java.time.LocalDate;

public class Customer{
    protected String Customername;
    protected int Frequency;
    protected LocalDate ArrivingTime;
    public String toString(){
        return "\nCustomername:"+Customername+" Frequency:"+Frequency+" ArrivingTime:"+ArrivingTime;
    }

    public Customer(String customername, int frequency, LocalDate arrivingTime) {
        this.Customername = customername;
        this.Frequency = frequency;
        this.ArrivingTime = arrivingTime;
    }

    public String getCustomername() {
        return Customername;
    }

    public void setCustomername(String customername) {
        Customername = customername;
    }

    public int getFrequency() {
        return Frequency;
    }

    public void setFrequency(int Frequency) {
        this.Frequency = Frequency;
    }

    public LocalDate getArrivingTime() {
        return ArrivingTime;
    }

    public void setArrivingTime(LocalDate ArrivingTime) {
        this.ArrivingTime = ArrivingTime;
    }
}