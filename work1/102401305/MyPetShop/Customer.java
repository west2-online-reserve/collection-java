package MyPetShop;

import java.time.LocalDate;

public class Customer {

    private String nameCustomer;
    private int frequencyCustomer;
    private LocalDate latestTime;

    Customer(String nameCustomer, int frequencyCustomer, LocalDate latestTime) {
        this.nameCustomer = nameCustomer;
        this.frequencyCustomer = frequencyCustomer;
        this.latestTime = latestTime;
    }

    @Override
    public String toString() {
        return "顾客姓名:"+nameCustomer+"\n到店次数:"+frequencyCustomer+"\n最近一次到店时间:"+latestTime.toString();
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public LocalDate getLatestTime() {
        return latestTime;
    }

    public void updateFrequency() {
        frequencyCustomer++;
    }

    public void updateLatestTime() {
        latestTime = LocalDate.now();
    }

}
