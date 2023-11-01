public class InsufficientBalanceException extends RuntimeException{
    private double money;
    public InsufficientBalanceException(double money){
        this.money = money;
    }

    @Override
    public String toString() {
        return "InsufficientBalanceException: money="+this.money;
    }
}
