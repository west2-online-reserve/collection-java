package animal;

import java.time.LocalDate;

public class Customer {

    protected String name;protected int times;protected LocalDate dd;


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




    public Customer(String name, int times,LocalDate dd) {
        this.name = name;
        this.times = times;
        this.dd=dd;

    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", times=" + times +

                '}';

    }

    public LocalDate getDd() {

        return dd;
    }
}
