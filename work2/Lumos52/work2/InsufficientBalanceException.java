package com.west2.work2;

/**
 * InsufficientBalanceException类用于处理宠物店余额不足的异常
 */
public class InsufficientBalanceException extends Throwable {
    /**
     * 余额
     */
    private double balance;

    public InsufficientBalanceException(double balance) {
        this.balance = balance;
    }

    public String toString() {
        return "余额不足警告！！！，您的余额只剩" + balance + "不足以购买新的动物。";
    }
}
