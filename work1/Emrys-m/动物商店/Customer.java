package HomeWork;

import java.time.LocalDate;

public class Customer {
    public String name;
    public int num;
    LocalDate localDate;

    public Customer(String name) {
        this.name = name;
        num = 1;
        localDate = LocalDate.now();
    }

    @Override
    public String toString() {
        String str1 = "Customer's name:" + this.name;
        String str2 = "number of store visits:" + this.num;
        String str3 = "latest store visit time:" + localDate.getYear() + "年" + localDate.getMonthValue() + "月" + localDate.getDayOfMonth() + "日";
        return str1 + "\n" + str2 + "\n" + str3;
    }

    public void getNewDate() {
        localDate = LocalDate.now();

    }
}
