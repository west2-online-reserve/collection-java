package Xuxiiii;

public class InsufficientBalanceException extends RuntimeException{
    private String name;
    private double money;
    public InsufficientBalanceException(String name,double money){
        this.name=name;
        this.money=money;
    }
    public String toString(){return "余额不足！无法购买"+name;}
}
