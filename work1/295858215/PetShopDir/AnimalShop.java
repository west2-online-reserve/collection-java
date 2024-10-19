//先设为抽象类等下再具体写
public interface AnimalShop {
    // 接口中的方法默认是 public abstract，不需要额外声明为 abstract
    void shopClose();

    void buyAnimal(Animal animal) throws InsufficientBalanceException;

    void treatCustomer(Customer customer) throws AnimalNotFoundException;
}

