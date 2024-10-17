package base;

import exception.InsufficientBalanceException;
import pojo.Customer;

public interface AnimalShop {
    //买入新动物的功能
    void buy(AbstractAnimal pet) throws InsufficientBalanceException;

    //招待客户
    void welcomeClients(Customer customer);

    //歇业
    void close();
}
