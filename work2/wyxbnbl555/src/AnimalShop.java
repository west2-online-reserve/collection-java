import java.util.List;

public interface AnimalShop {
    public void buyNewAnimal(Animal A);
    public void entertainCustomer(Customer C);
    public void outOfBusiness(List<Customer> customers);
}
