package com.animals;

public interface AnimalsShop {
    public void buyAnimals(Animals animals);
    public <customer> void careCustomer(customer customer);
    public void close(boolean a);

}