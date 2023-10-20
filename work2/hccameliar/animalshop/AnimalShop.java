package animalshop;

/**
 * @author 102301412
 */
public interface AnimalShop {

    void purchase(BaseAnimal animal);

    void treatCustomer(Customer customer, String petName);

    public abstract void close();

}


