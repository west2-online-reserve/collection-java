package com.west2.work2;

public class InsufficientBalanceException extends RuntimeException {
    /**
     * 价格超出余额的宠物
     */
    private Animal animal;
    /**
     * 商店余额
     */
    private double balance;

    public InsufficientBalanceException(Animal animal, double balance) {
        this.animal = animal;
        this.balance = balance;
    }

    /**
     * 获取价格超出余额的宠物
     *
     * @return 价格超出余额的宠物
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * 获取商店余额
     *
     * @return 商店余额
     */
    public double getBalance() {
        return balance;
    }

}
