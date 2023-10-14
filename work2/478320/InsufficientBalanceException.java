package com.huayu.work02;

/**
 * InsufficientBalanceException类表示余额不足自定义异常
 *
 * 该自定义异常在余额不足时将被抛出
 * @author yusiheng
 * @date 2023/10/04
 */
public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException() {

    }

    @Override
    public String toString() {
        return "余额不足";
    }
}
