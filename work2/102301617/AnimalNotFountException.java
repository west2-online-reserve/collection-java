
public class AnimalNotFountException extends RuntimeException{
    @Override
    public String toString() {
        return "AnimalNotFountException{"+"没有动物了"+'}';
    }
}