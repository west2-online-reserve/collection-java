package learn_java1;

import java.time.LocalDate;

public interface AnimalShop {
    void CloseShop(LocalDate date);//歇业

    void entertainCustomer(Customer customer);//招待客户

    void purchaseAnimal(Animal animal);//买入动物
}
