import java.time.LocalDate;

public class Customer {
    private String name;
    private int times;
    private LocalDate time;
    public Customer(){
    }
    public Customer(String name,int times,LocalDate time){
        this.name = name;
        this.times = times;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    @Override
    public String toString(){
        return "Customer{" +
                "name:" + name +
                ",times:" + times +
                ",time:" + time+
                "}";
    }
}
