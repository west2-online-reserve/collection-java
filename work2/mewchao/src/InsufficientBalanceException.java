//余额不足异常
public class InsufficientBalanceException extends BaseException{
    private double balanced;
    private double price;

    public InsufficientBalanceException(double balanced,double price){
        this.balanced=balanced;
        this.price=price;
    }
    double getPrice(){
        return this.price;
    }
    double getBalanced(){
        return this.balanced;
    }
}
