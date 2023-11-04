import java.util.List;

public interface AnimalShop {
    public abstract void buyNewAnimal(Animal A);
    public abstract void entertainCustomer(Customer C, Animal A);
    public abstract void outOfBusiness(List<Customer> customers);
}
