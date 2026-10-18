package oop.PetShop;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int count = 1;
    LocalDate  newDate;
    public Customer(){}
    public Customer(String name){
        this.name = name;
        newDate = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public LocalDate getNewDate() {
        return newDate;
    }

    public void setNewDate(LocalDate newDate) {
        this.newDate = newDate;
    }
    public void increaseCount(){
        this.count++;
    }
    @Override
    public String toString() {
        return name+" "+count+" "+newDate;
    }
}
