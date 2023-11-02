package work2;

public class AnimalNotFoundException extends RuntimeException {
    String buyer;

    public AnimalNotFoundException(Customer customer) {
        this.buyer = customer.getName();
    }

    @Override
    public String toString() {
        return "抱歉" + buyer + ",真的没动物了。。";
    }
}
