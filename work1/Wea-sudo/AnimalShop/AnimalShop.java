package example;

public interface AnimalShop {
    public abstract void buyAnimal(Animal animal);
    public abstract void SolicitCustomer(Customer customer, String className, int price, LocalDate localDate);
    public abstract void close();
}
