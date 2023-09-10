package Work2complete;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop me = new MyAnimalShop(10000);
        Animal dog = new Dog("中国狗",111,"男",100);
        Animal cat = new Cat("Tom",666,"公",9999);
        Customer c1 = new Customer("张三",0, LocalDate.now());
        Customer c2 = new Customer("李四",1,LocalDate.now());
        me.treat(c1);
        me.add(dog);
        me.treat(c2);
        me.shopping(1,dog.price);
        me.close();
    }
}
