package com.huayu.work02;

public class InsufficientBalanceException extends RuntimeException {

//这是购买时钱不够的异常，我在test方法中注释了一个价格很大的宠物来专门检验这个异常
    public InsufficientBalanceException() {

    }

    @Override
    public String toString() {
        return "余额不足";
    }
}
