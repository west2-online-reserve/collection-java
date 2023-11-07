public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException() {
        super("Here is no animals to buy!");
    }
}
