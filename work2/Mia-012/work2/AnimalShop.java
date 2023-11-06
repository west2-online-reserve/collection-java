package work2;

import java.time.LocalDate;

public interface AnimalShop {
     void buy(
             Animal animal,
             double buyInPrice
     );
     void treat(Customer person,String name);
     void close();
}
