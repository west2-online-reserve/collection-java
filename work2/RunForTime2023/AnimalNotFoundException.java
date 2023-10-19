public class AnimalNotFoundException extends RuntimeException {
    AnimalNotFoundException() {
        super("Here is no animals to buy!");
    }
}
