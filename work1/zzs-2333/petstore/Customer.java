package com.petstore;

import java.time.LocalDate;

public class Customer {
    private String customerName;
    private int Count;
    private LocalDate lastVisit;

    public Customer(String name) {
        customerName = name;
        Count = 1;//初次到店
        lastVisit = LocalDate.now();//设为当前日期
    }

    public void updateVisit(){
        Count++;
        lastVisit = LocalDate.now();//更新到店时间
    }
    @Override
    public String toString(){
        return String.format("顾客名字: %s, 到店次数: %d, 最新到店时间: %s",
                customerName,Count,lastVisit);
    }
}
class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}



