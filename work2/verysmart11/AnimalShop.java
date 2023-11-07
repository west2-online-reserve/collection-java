package work2;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public interface AnimalShop {
    void buyAnimal(Animal a);

    void treatCustomer(Customer c, Scanner sc);//传sc，让treatCustomer从Test获得输入，然后可以在Test中sc.close

    void close(LocalTime time) throws ParseException;
}
