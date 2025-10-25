package work1.Animalshop;

class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException() {
    }

    public InsufficientBalanceException(String error){
        super(error);
    }
}
