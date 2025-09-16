import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 宠物店测试类
 *
 * @author LYanl7
 * @since 2025-9-14
 */
class AnimalShopTests {

    @Test
    void purchaseAnimalTest() {
        MilkDragon milkDragon = new MilkDragon("真奶龙", 1000, Animal.Gender.MALE);
        RuralDog ruralDog = new RuralDog("旺财", 1, Animal.Gender.MALE, true);
        MyAnimalShop myAnimalShop = new MyAnimalShop(114514.0, new ArrayList<>(), new ArrayList<>(), true);
        myAnimalShop.purchaseAnimal(milkDragon);
        assertThrows(InsufficientBalanceException.class, () -> myAnimalShop.purchaseAnimal(ruralDog));
    }

    @Test
    void purchaseAnimalTest2() {
        MilkDragon milkDragon = new MilkDragon("真奶龙", 1000, Animal.Gender.MALE);
        RuralDog ruralDog = new RuralDog("旺财", 1, Animal.Gender.MALE, true);
        Cat cat = new Cat("咪咪", 2, Animal.Gender.MALE);
        MyAnimalShop myAnimalShop = new MyAnimalShop(1145140.0, new ArrayList<>(), new ArrayList<>(), true);
        myAnimalShop.purchaseAnimal(milkDragon);
        myAnimalShop.purchaseAnimal(ruralDog);
        myAnimalShop.purchaseAnimal(cat);
    }

    @Test
    void entertainCustomerTest() {
        MilkDragon milkDragon = new MilkDragon("真奶龙", 1000, Animal.Gender.MALE);
        MyAnimalShop myAnimalShop = new MyAnimalShop(1145140.0, new ArrayList<>(), new ArrayList<>(), true);
        myAnimalShop.purchaseAnimal(milkDragon);
        myAnimalShop.purchaseAnimal(milkDragon);

        Customer customer = new Customer("小明", 0, null);
        myAnimalShop.entertainCustomer(customer);
        myAnimalShop.entertainCustomer(customer);
        assertEquals(customer.getLastVisitDate(), LocalDate.now());
        assertEquals(2, customer.getFrequency());
        assertEquals(1, myAnimalShop.getCustomers().size());
        assertThrows(AnimalNotFoundException.class, () -> myAnimalShop.entertainCustomer(customer));
        assertEquals(3, customer.getFrequency());
    }

    @Test
    void combinationalTest() {
        MilkDragon milkDragon = new MilkDragon("真奶龙", 1000, Animal.Gender.MALE);
        RuralDog ruralDog = new RuralDog("旺财", 1, Animal.Gender.MALE, true);
        Cat cat = new Cat("咪咪", 2, Animal.Gender.MALE);
        List<Animal> animals = new ArrayList<>();
        animals.add(milkDragon);
        animals.add(ruralDog);
        animals.add(cat);
        List<Customer> customers = new ArrayList<>();
        MyAnimalShop myAnimalShop = new MyAnimalShop(0.0, animals, customers, true);
        Customer customer1 = new Customer("小明", 0, null);
        Customer customer2 = new Customer("小红", 0, null);
        myAnimalShop.entertainCustomer(customer1);
        myAnimalShop.entertainCustomer(customer2);
        myAnimalShop.closeShop();
        assertEquals(2, myAnimalShop.getCustomers().size());
        assertEquals(114614.0, myAnimalShop.getBalance());
        assertEquals(1, myAnimalShop.getAnimals().size());
        assertThrows(RuntimeException.class, () -> myAnimalShop.entertainCustomer(customer2));
    }
}
