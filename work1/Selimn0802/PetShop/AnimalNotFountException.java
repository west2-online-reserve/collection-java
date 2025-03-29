package PetShop;
//动物未找到异常
public class AnimalNotFountException extends RuntimeException {
    public AnimalNotFountException(String message) {
        super(message);
    }
}
