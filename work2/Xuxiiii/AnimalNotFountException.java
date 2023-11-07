package Xuxiiii;

public class AnimalNotFountException extends RuntimeException{
    private int a;
    private Customer b;
    public AnimalNotFountException(int a, Customer b){
        this.b=b;
    }
    @Override
    public String toString() {
        return "抱歉"+b.getName()+"没动物了";
    }
}
