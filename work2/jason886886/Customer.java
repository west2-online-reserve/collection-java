package work2;

import java.time.LocalDate;

/**
 * @author jason
 */
public class Customer {
    private String name;
    private int frequencyOfVisitingShop;
    private LocalDate lastTimeOfVisitingShop;

    public Customer(String name) {
        this.name = name;
        this.frequencyOfVisitingShop = 0;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLastTimeOfVisitingShop() {
        return lastTimeOfVisitingShop;
    }

    public void setLastTimeOfVisitingShop(LocalDate lastTimeOfVisitingShop) {
        this.lastTimeOfVisitingShop = lastTimeOfVisitingShop;
    }

    public int getFrequencyOfVisitingShop() {
        return frequencyOfVisitingShop;
    }

    public void setFrequencyOfVisitingShop(int frequencyOfVisitingShop) {
        this.frequencyOfVisitingShop = frequencyOfVisitingShop;
    }

    @Override
    public String toString() {
        return "顾客名字:" + this.name + "\n到店次数:" + this.frequencyOfVisitingShop + "\n最新到店时间:" + this.lastTimeOfVisitingShop + "\n";
    }
}
