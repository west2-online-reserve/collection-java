package homework.work2;
public class AnimalNotFountException extends RuntimeException{
    public AnimalNotFountException(String message) {
        super("店内没有该动物！");
    }
}
