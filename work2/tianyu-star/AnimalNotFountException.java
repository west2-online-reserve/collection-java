public class AnimalNotFountException extends RuntimeException{
    public AnimalNotFountException(){

    }
    public  AnimalNotFountException(String tip){
        super(tip);
    }
}
