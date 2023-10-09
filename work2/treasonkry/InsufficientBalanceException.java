public class InsufficientBalanceException extends RuntimeException{
    double money;
    public InsufficientBalanceException(double money){
        this.money = money;
    }

    @Override
    public String toString() {
        return "InsufficientBalanceException";
    }
}
