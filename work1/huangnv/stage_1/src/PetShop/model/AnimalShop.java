package PetShop.model;

public interface AnimalShop {
    public void buyAnimal(Animal animal);
    public void treatCustomer(Customer customer,Animal animal);
    public void open();
    public void close();
}
