import HomeWork.Animal;
import HomeWork.Customer;

public interface AnimalShop {
    public abstract void buyNewAnimal(Animal a);
    public abstract void entertainCustomer(Customer c);
    public abstract void shutdown();
}
