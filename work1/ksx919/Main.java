import java.time.LocalDate;

class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(1000.0);
        Cat a=new Cat("CuteCat",1,"Male");
        Cat c=new Cat("PrettyCat",2,"Female");
        Cat f=new Cat("ACat",2,"Female");
        Cat g=new Cat("BCat",2,"male");
        GardenDog b=new GardenDog("CoolDog", 3, "Male", true);
        GardenDog d=new GardenDog("UglyDog", 4, "Male", true);
        GardenDog e=new GardenDog("CleverDog", 2, "Female", true);
        shop.buyAnimal(a);
        shop.buyAnimal(b);
        shop.buyAnimal(c);
        shop.buyAnimal(d);
        shop.buyAnimal(e);
        shop.buyAnimal(f);
//        shop.buyAnimal(g);//添加购买g时抛出错误，金额不足购买。
        Customer customer1 = new Customer("Handsome Boy", LocalDate.now());
        Customer customer2 = new Customer("Pretty Girl",LocalDate.now());
        shop.serveCustomer(customer1,b);
        shop.serveCustomer(customer2,c);
        customer1.getLastdate();
        shop.closeShop();
    }
}