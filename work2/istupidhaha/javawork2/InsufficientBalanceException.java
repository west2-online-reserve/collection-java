package com.west2.javawork2;

public class InsufficientBalanceException extends RuntimeException{
    //购买时超出余额的动物
    private Animal animal;
    //此时商店余额
    private double balance;

    //获取参数
    public Animal getAnimal() {
        return animal;
    }

    public double getBalance() {
        return balance;
    }

    public InsufficientBalanceException(Animal animal,double balance,String warning){
        super(warning);
        this.animal = animal;
        this.balance = balance;
    }
}
