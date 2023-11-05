public class Test {
	public static void main(String[] args) {
		Dog dog1 = new Dog("大黄",4,"male",true);
		Dog dog2 = new Dog("小黄",2,"male",true);
		Dog dog3 = new Dog("小白",2,"female",true);
		Cat cat1 = new Cat("挖挖",3,"female");
		Bird bird1 = new Bird("大鸟",2,"male");
		Bird bird2 = new Bird("小鸟",2,"female");
		Customer c1 = new Customer("A",1);
		Customer c2 = new Customer("B",0);
		Customer c3 = new Customer("C",2);
		MyAnimalShop myShop = new MyAnimalShop(1000,true);
		MyAnimalShop closedShop = new MyAnimalShop(0,false);
		closedShop.entertainCustomer(c1);
		myShop.buyAnimal(dog1);
		myShop.buyAnimal(dog2);
		myShop.buyAnimal(dog3);
		myShop.buyAnimal(cat1);
		myShop.buyAnimal(bird1);
		myShop.buyAnimal(bird2);
		myShop.entertainCustomer(c1);
		myShop.entertainCustomer(c2);
		myShop.entertainCustomer(c3);
		myShop.entertainCustomer(c1);
		myShop.entertainCustomer(c1);
		myShop.entertainCustomer(c1);
		myShop.close();
	}
}
