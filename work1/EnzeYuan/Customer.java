package petStore;

import java.time.LocalDate;
import java.time.LocalTime;

public class Customer {
    private String name;
    private int comeTime;
    private LocalDate lastTime;
    private Animal wantBuy;
    private int pay;

    public Customer() {
        this.name = "none";
        this.comeTime = 0;
        this.lastTime = LocalDate.now();
        this.setPay(0);
    }

    public Customer(String name, int comeTime, String lastTime, Animal wantBuy) {
        this.wantBuy = wantBuy;
        this.name = name;
        this.comeTime = comeTime;
        String[] temp = lastTime.split("-");
        this.lastTime = LocalDate.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
        this.setPay(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getComeTime() {
        return comeTime;
    }

    public void setComeTime(int comeTime) {
        this.comeTime = comeTime;
    }

    public LocalDate getLastTime() {
        return lastTime;
    }

    @Override
    public String toString() {
        return (this.name + " has already come to the store "
                + this.comeTime + " times, and the latest cometime is at "
                + this.lastTime + ",and already cost " + this.pay + "$ in our store");
    }

    public Animal getWantBuy() {
        return wantBuy;
    }

    public void setWantBuy(Animal wantBuy) {
        this.wantBuy = wantBuy;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

}
