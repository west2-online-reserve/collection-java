package shop;

/**
 * @author HarveyBlocks
 * @date 2023/09/04 10:32
 **/

public class AnimalNotFountException extends RuntimeException{
    public AnimalNotFountException(String message) {
        super(message);
    }
}
