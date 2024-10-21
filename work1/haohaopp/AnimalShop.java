package chongwudian;

import java.time.LocalDate;

public interface AnimalShop {
    public abstract void buyAnimal(Animal a);

    public  abstract void  comeCustomer(Customer c);

    public  abstract  void closed(LocalDate date);
}
