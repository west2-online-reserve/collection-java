package petStore;

import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    private double cash;
    private ArrayList<Animal> animalList;
    private ArrayList<Customer> customerList;
    private boolean isOpen;

    public MyAnimalShop() {
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.setCash(2000);
        this.setOpen(true);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public ArrayList<Animal> getAnimalList() {
        return this.animalList;
    }

    public ArrayList<Customer> getCustomerList() {
        return this.customerList;
    }


    @Override
    public void service(Customer c) {
        // TODO Auto-generated method stub
        this.customerList.add(c);

        Animal an = c.getWantBuy();

        if (this.animalList.contains(an)) {

            this.animalList.remove(an);
            System.out.println(an.toString());

            String[] arr = an.getClass().getName().split("\\.");
            String name = arr[arr.length - 1];


            if (name.equalsIgnoreCase("Cat")) {
                this.cash += 200;
                c.setPay(c.getPay() + 200);
            } else if (name.equalsIgnoreCase("Rabbit")) {
                this.cash += 50;
                c.setPay(c.getPay() + 50);
            } else {
                this.cash += 100;
                c.setPay(c.getPay() + 100);
            }
        } else {
            throw new AnimalNotFoundException();
        }

    }
}
