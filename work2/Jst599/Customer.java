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
    private String name;
    // 顾客到店次数
    private int frequency;
    // 顾客最新到店时间
    private LocalDate timeNew;

    public Customer() {
    }

    public Customer(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
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
                "frequency =" + (frequency + 1) + "\t" +
                "timeNew =" + timeNew + "\t" +
                "}";
    }

    public void renewCustomer(Customer customer){
        customer.frequency += 1;
        customer.timeNew = LocalDate.now();
    }
}
