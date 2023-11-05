public interface AnimalShop {
    public void buyNewAnimal(Animal animal);

    public void serveNewCustomer(Customer customer);

    public void sellAnimal(Customer customer,Animal animal);

    public void close();

    public void open();
}
