import java.time.LocalDate;

public class test {
    public static void main(String[] args) {
        MyAnimalShop me = new MyAnimalShop(10000);
        MyAnimalShop my = new MyAnimalShop(2000000);

        animal d1 = new Dog("中华田园犬",2,"雄",100);
        animal c1 = new Cat("Tom",84,"公",200);
        animal d2 = new Dog("泰迪",3,"雄",10001);
        animal c2 = new Cat("Garfield",45,"公",9.9);

        Customer customer1 = new Customer("张三",1, LocalDate.now());
        Customer customer2 = new Customer("李四",2,LocalDate.now());
        Customer customer3 = new Customer("王五",3,LocalDate.now());

        me.treat(customer1,1);

        me.add(d1);
        me.treat(customer2,1);

        me.add(d2);
        me.add(c1);
        me.add(c2);
        me.treat(customer3,1);
        me.close();

        my.add(d2);
        my.add(d1);
        my.treat(customer3,2);
        my.treat(customer1,1);
        my.close();
    }
}
