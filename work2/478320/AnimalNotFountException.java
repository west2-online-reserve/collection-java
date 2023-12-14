package com.huayu.work02;

/**
 * AnimalNotFountException类表示宠物店没有宠物异常
 *
 * 该自定义异常在宠物店无宠物时会抛出
 * @author yusiheng
 * @date 2023/10/04
 */
public class AnimalNotFountException extends RuntimeException {

    public AnimalNotFountException() {

    }

    @Override
    public String toString() {
        return "宠物店内没有宠物";
    }
}
