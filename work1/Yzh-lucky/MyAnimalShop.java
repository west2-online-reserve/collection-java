package Work1;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animalsList = new ArrayList<>();
    private List<Customer> customersList=new ArrayList<>();
    private boolean isOpen;//用来判断是否开关门

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException{
        if (balance<animal.price) {
            throw new InsufficientBalanceException("余额不足！！！");
        }
        balance-=animal.price;
        animalsList.add(animal);
    }

    public void showAnimals(){
        System.out.println("店铺里有：");
        for(Animal animal:animalsList){
            System.out.println(animal);
        }
    }
    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        customersList.add(customer);
        Animal soldAnimal=customer.getWantAnimal();
        if (animalsList.isEmpty()){
            throw new AnimalNotFoundException("店内没有所需要的宠物!!!");
        }
        animalsList.remove(soldAnimal);
        balance+=soldAnimal.salePrice;
        System.out.println("出售了"+soldAnimal+"给"+customer);
    }
    @Override
    public void closeDoor() {
        System.out.println("当天光顾的顾客链表:");
        for (Customer customer : customersList){
            System.out.println(customer);
        }
        System.out.println();
        System.out.println("店铺中还剩：");
        for (Animal animal : animalsList){
            System.out.print(animal+" ");
        }
        System.out.println();
        double profit=calculateProfit();
        System.out.println("一天的利润为"+profit);
    }
    private double calculateProfit(){
        return balance;
    }
}
