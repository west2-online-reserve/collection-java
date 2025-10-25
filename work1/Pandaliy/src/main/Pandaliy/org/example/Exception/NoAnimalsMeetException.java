package org.example.Exception;

public class NoAnimalsMeetException extends RuntimeException {
    public NoAnimalsMeetException() {
        super("当前宠物店没有该客户想要的宠物。\n");
    }
}
