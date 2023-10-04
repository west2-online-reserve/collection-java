public class InsufficientBalanceException extends RuntimeException {
      public  InsufficientBalanceException(String tip){
          super(tip);
      }
}