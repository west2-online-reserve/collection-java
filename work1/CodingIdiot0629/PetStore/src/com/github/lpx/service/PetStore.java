package com.github.lpx.service;

import com.github.lpx.model.pet.Animal;

public interface PetStore {
    public void openStore();

    public void closeStore();

    public boolean buyPet();

    public void showAllPet();

    public void sellPet(String name, double discount);

    public void serviceToCustomer();

    public void addBalance(double money);

    public void removeBalance(double money);
}
