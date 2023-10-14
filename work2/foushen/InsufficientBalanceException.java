public class InsufficientBalanceException extends RuntimeException {
    double rest;//记录还差多少钱
    String message;//异常信息

    InsufficientBalanceException(double rest, String message) {
        this.rest = rest;
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    public double getRestMoney() {
        return rest;
    }
}
