package Work2complete;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int times = 0;
    private LocalDate now;

    public Customer(String n,int t,LocalDate now){
        this.name = n;
        this.times = t;
        this.now = now;
    }

    public String getName() {
        return name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String toString(){
        return "name is "+name+
                " times is "+times+
                " LocalDate is "+now
                ;
    }


}
