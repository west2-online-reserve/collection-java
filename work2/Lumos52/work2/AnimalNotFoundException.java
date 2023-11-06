package com.west2.work2;

/**
 * AnimalNotFoundException类用于处理没有动物的异常
 */
public class AnimalNotFoundException extends RuntimeException {
    private String customer;

    public AnimalNotFoundException(String customer) {
        this.customer = customer;
    }

    public String toString() {
        return "不好意思顾客，小动物没有货了！";
    }
}
