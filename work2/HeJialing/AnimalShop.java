import java.time.LocalDate;
import java.util.ArrayList;

public interface AnimalShop {
    /* 买入新动物(需要的参数自己决定)
     招待客户(Customer)
     歇业()
     */
    void buyNewAnimals(AbstractAnimal animal);



    void shutdown(LocalDate localDate, ArrayList listOfCustomer);

    void serveCustomer(Customer customer,LocalDate localDate, ArrayList listOfAnimal, AbstractAnimal animal);
}
