/**
 * @author AGoodYear
 */
public class AnimalNotFountException extends RuntimeException{
    public AnimalNotFountException() {
    }
    public void printError() {
        System.out.println("店内没有可供出售的动物！");
    }
}
