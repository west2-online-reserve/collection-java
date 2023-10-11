package com.west2.work2;

public class InsufficientBalanceException extends RuntimeException {
    private Animal animal;
    private double balance;

    public InsufficientBalanceException(Animal animal, double balance) {
        this.animal = animal;
        this.balance = balance;
    }

    public Animal getAnimal() {
        return animal;
    }

    public double getBalance() {
        return balance;
    }

    public String toString() {
        return "余额为" + balance + ",而宠物" + animal.getName() + "售价为" + animal.getPrice() + ",购买失败！";
    }
}
