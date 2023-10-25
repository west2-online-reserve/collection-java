package AnimalShop;

import java.time.LocalDate;

public class Customer {
    protected String name;
    protected int times;
    protected LocalDate lastestTimeArrived;

    public Customer(String name, int times) {
        this.name = name;
        this.times = times;
        this.lastestTimeArrived = LocalDate.now();
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

    public LocalDate getLastestTimeArrived() {
        return lastestTimeArrived;
    }

    public void setLastestTimeArrived(LocalDate lastestTimeArrived) {
        this.lastestTimeArrived = lastestTimeArrived;
    }

    @Override
    public String toString() {
        return "Customer{" + "name=" + name + " ,times=" + times + " ,lastedTimeArrived=" + lastestTimeArrived;
    }
}
