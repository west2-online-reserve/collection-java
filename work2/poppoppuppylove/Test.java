public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(400, false);

        Animal dog1 = new ChineseRuralDog("旺财", 3, "公", true);
        Animal cat1 = new Cat("喵喵", 1, "母");
        Animal pig1 = new GuineaPig("猪猪", 1, "母");
        try {
            shop.buyAnimal(dog1);
            shop.buyAnimal(cat1);
            shop.buyAnimal(pig1);
            // 余额不足,抛出异常
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }

        Customer ming = new Customer("小明");
        Customer hong = new Customer("小红");

        shop.welcomeCustomer(hong);
        shop.welcomeCustomer(ming);
        try {
            shop.welcomeCustomer(ming);
        } catch (AnimalNotFoundException e) {
            e.printStackTrace();
        }

        shop.close();
    }
    }
