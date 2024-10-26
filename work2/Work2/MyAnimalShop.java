package Work2;
import java.util.ArrayList;

public  class MyAnimalShop implements AnimalShop {
    private double remaining;
    private boolean dobusiness;
    private double profit = 0;
    ArrayList<Animal> animal = new ArrayList<Animal>();
    ArrayList<Customer> customer = new ArrayList<Customer>();
    private ArrayList<Animal> animalsArrayList;
    private ArrayList<Customer> costomersArrayList;
    private boolean close;

    public ArrayList<Animal> getAnimal() {
        return animal;
    }

    public ArrayList<Customer> getCustomer() {
        return customer;
    }
    @Override
    public void buy(Animal animal) throws InsufficientBalanceException {
        if (remaining >= animal.price) {
            remaining -= animal.price;
            profit -= animal.price;
            animalsArrayList.add(animal);
        } else {
            throw new InsufficientBalanceException("余额不足");

        }


    }

    @Override
    public void recepte(Customer customer) throws AnimalNotFoundException {
        if (!close) {

            if (animalsArrayList.isEmpty()) {
                throw new AnimalNotFoundException("店内没有动物可供出售");
            }
            else {animalsArrayList.forEach(System.out::println);
                Animal animalToSell = animalsArrayList.get(0);
                remaining+=animalToSell.price;
                profit+=animalToSell.price;
                costomersArrayList.add(customer);
                System.out.println(animal);
                animalsArrayList.remove(animal);
            }
//这里有错误，应该是先让顾客挑选，再判断有没有相应的宠物
        }
        else {
            System.out.println("已歇业");

        }


    }
    @Override
    public  void close() {
        if (close) {
            System.out.println("今日顾客列表:");
            costomersArrayList.forEach(System.out::println);
            System.out.println("今日利润"+profit);

        } else {
            System.out.println("继续营业");
        }

    }




}




