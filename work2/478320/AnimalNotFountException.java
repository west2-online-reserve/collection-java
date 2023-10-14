package com.huayu.work02;

public class AnimalNotFountException extends RuntimeException {
//这是找不到动物的运行时异常，在没有动物时会抛出return的内容，我在text中也注释了它检验的代码
    public AnimalNotFountException() {

    }

    @Override
    public String toString() {
        return "宠物店内没有宠物";
    }
}
