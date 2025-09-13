package work2;

/**
 * @author Mia
 * @date 2023/11/1
 */
public interface AnimalShop {
      void buy(Animal animal, double buyInPrice);
     void treat(Customer person,String name);
     void close();
}
