package com.huayu.work02;

import java.time.LocalDate;
import java.util.ArrayList;


public class MyAnimalShop implements AnimalShop {

    double storeBalance;
    ArrayList<Animal> animalsList = new ArrayList<Animal>();
    ArrayList<Customer> customersList = new ArrayList<Customer>();
    ArrayList<Double> soldOnClosedDays = new ArrayList<Double>();
    ArrayList<Double> importOnClosedDays = new ArrayList<Double>();
    LocalDate closingTime;
    boolean businessInProgress;

    @Override
    public void purchaseNewAnimal(Animal animal, LocalDate importTime) {
        if (storeBalance < animal.animalImportPrice) {
            try {
                throw new InsufficientBalanceException();
            } catch (InsufficientBalanceException e) {
                System.out.println(e);
            }
        }
        storeBalance = storeBalance - animal.animalImportPrice;
        animalsList.add(animal);
        if (importTime.equals(closingTime)) {
            importOnClosedDays.add(animal.animalImportPrice);
        }
    }

    @Override
    public void hospitalizingCustomers(Customer customer, int whichOneToPurchase, Animal animal) throws IndexOutOfBoundsException {
        if (animalsList.size() == 0) {
            try {
                throw new AnimalNotFountException();
            } catch (AnimalNotFountException e) {
                System.out.println(e);
            }
        }
        if (animalsList.size() <= 0) {
            throw new IndexOutOfBoundsException();
        }
        if (animalsList.size() > 0) {
            System.out.println(animal.toString());
            storeBalance = storeBalance + animal.animalPrice;
            animalsList.remove(whichOneToPurchase - 1);
        }
        customersList.add(customer);
        if (customer.latestArrivalTime.equals(closingTime) && animalsList.size() > 0) {
            soldOnClosedDays.add(animal.animalPrice);
        }
    }

    @Override
    public void closureOfBusiness(Customer... customer) {
        for (int i = 0; i < customer.length; i++) {
            if (customer[i].latestArrivalTime.equals(closingTime)) {
                System.out.println(customer[i].toString());
            }

        }
        double revenue = 0;
        for (int j = 0; j < importOnClosedDays.size(); j++) {
            revenue = revenue + importOnClosedDays.get(j);
        }
        double expenditure = 0;
        for (int j = 0; j < soldOnClosedDays.size(); j++) {
            expenditure = expenditure + soldOnClosedDays.get(j);

        }
        double profit = expenditure - revenue;
        System.out.println(profit);

    }
}
