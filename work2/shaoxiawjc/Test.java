package shaoxiawjc;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop me = new MyAnimalShop(10000);
        MyAnimalShop mine2 = new MyAnimalShop(66666666);

        Animal dog = new Dog("中国狗",111,"男",100);
        Animal cat = new Cat("Tom",666,"公",200);
        Animal dog2 = new Dog("战狼",9999,"雄",99999);
        Animal cat2 = new Cat("加菲",1,"man",9.9);

        Customer c1 = new Customer("张三",0, LocalDate.now());
        Customer c2 = new Customer("李四",1,LocalDate.now());
        Customer c3 = new Customer("王五",3,LocalDate.now());

        me.treat(c1,1);
        me.add(dog);             //len = 1
        me.treat(c2,1);       //c2 买第一只 len = 0
        me.add(dog2);           //战狼买不起，len = 0
        me.add(cat);        // 小猫买得起 len = 1
        me.add(cat2);           //加一个加菲 len = 2
        me.treat(c3,2);
        me.close();

        mine2.add(dog2);
        mine2.add(dog);
        mine2.treat(c3,2);
        mine2.treat(c1,1);
        mine2.close();
    }
}
