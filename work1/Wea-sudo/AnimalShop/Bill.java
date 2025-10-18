package example;

import java.time.LocalDate;

public class Bill {
    private Customer customer;
    private Animal animal;
    private int price;
    private LocalDate localDate;

    public Bill(Customer customer, Animal animal, int price, LocalDate localDate) {
        this.customer = customer;
        this.animal = animal;
        this.price = price;
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "顾客姓名:" + customer.getName() +
                ", 购买动物:" + animal.toString() +
                ", 购买价格:" + price +
                ", 购买日期:" + localDate;
    }



}
