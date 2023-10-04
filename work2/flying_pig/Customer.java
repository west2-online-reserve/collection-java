import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Customer {
    private String name;
    private int count;
    private LocalDate arriveDay;
    @Override
    public String toString(){
        String result="name:"+name+" "+"count:"+count+" "+"arriveTime:"+ arriveDay.toString();
        return result;
    }

    public Customer(String name, int count, LocalDate arriveDay) {
        this.name = name;
        this.count = count;
        this.arriveDay = arriveDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDate getArriveDay() {
        return arriveDay;
    }

    public void setArriveDay(LocalDate arriveDay) {
        this.arriveDay = arriveDay;
    }
}
