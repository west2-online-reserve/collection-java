public class Test {
    public static void main(String[] args) {
        AnimalShop myanimalshop = new MyAnimalShop(2000);

        Animal cat = new Cat("陈卡特", 15, '公');
        Animal dog = new Zhonghuadog("旺财", 7, '公');

        Customer cus1 = new Customer("小明", 0, new LocalDate(2025, 9, 12));
        myanimalshop.addAnimal(cat);
        myanimalshop.addAnimal(dog);
        myanimalshop.addCustomer(cus1, dog);

        myanimalshop.close();
    }
}
