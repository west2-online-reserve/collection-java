package PetShop;

import java.time.LocalDate;

/**
 * Customer表示光顾宠物店的顾客
 *
 * 该类包含用户的基本信息和操作
 * @author Jst599
 * @date 2023/10/17
 */
public class Customer {
    protected String name;
    //顾客到店次数
    protected int frequency;
    //顾客最新到店时间
    protected LocalDate timeNew;

    public Customer() {
    }

    public Customer(String name, int frequency, LocalDate timeNew) {
        this.name = name;
        this.frequency = frequency;
        this.timeNew = timeNew;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public LocalDate getTimeNew() {
        return timeNew;
    }

    public void setTimeNew(LocalDate timeNew) {
        this.timeNew = timeNew;
    }

    @Override
    public String toString(){
        return "Customer{" + "\t" +
                "name = " + name + "\t" +
                "frequency =" + frequency + "\t" +
                "timeNew =" + timeNew + "\t" +
                "}";
    }
}
