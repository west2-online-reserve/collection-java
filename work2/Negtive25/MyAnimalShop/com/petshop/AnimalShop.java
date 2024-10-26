package com.petshop;

public interface AnimalShop {
    public void purchaseAnimal(Animal animal,double costOfPurchase);
    public void sellAnimal(String animalSpecies);
    public void showAnimalList();
    public void entertainCustomer(Customer customer);
    public void showCustomerList();
    public void openShop();
    public void closeShop();
    public void whetherIsOpen();
}
