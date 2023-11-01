public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(){
        
    }
      public  InsufficientBalanceException(String tip){
          super(tip);
      }
}