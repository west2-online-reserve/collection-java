package PetShop.model;

public class ShopClosedException extends RuntimeException {
    public ShopClosedException(String message) {
        super(message);
    }
}
