package myshop.customer;

import java.time.LocalDate;
import java.util.ArrayList;

public class Custom {
    private String name;
    private int times;
    private LocalDate date;

    public Custom() {
        times = 1;
        date = LocalDate.now();
    }

    public Custom(String name, int times, LocalDate date) {
        this.name = name;
        this.times = times;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\t" + getName() + "\t" + getTimes() + "\t" + getDate();
    }


    public boolean checkCustom(ArrayList<Custom> customs, String name) {
        return getCustomIndex(customs, name) >= 0;
    }

    public int getCustomIndex(ArrayList<Custom> customs, String name) {
        for (int i = 0; i < customs.size(); i++) {
            if (customs.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

}
