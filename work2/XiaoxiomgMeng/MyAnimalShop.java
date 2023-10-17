import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

class MyAnimalShop implements AnimalShop{
    boolean open = true;

    final static double PURCHASE_PRICE_MULTIPLIER = 0.5;
    ArrayList<BaseAnimal> animalsList = new ArrayList<BaseAnimal>();

    ArrayList<Customer> customersList = new ArrayList<Customer>();

    double balance = 9999;

    int turnover = 0;

    @Override
    public void buy(BaseAnimal animal) {
        try {
            checkBalance(animal);
        } catch (InsufficientBalanceException e) {
            System.err.println("An error occurred: " + e.getMessage());;
        }
    }

    @Override
    public void close() {
        open = false;
        System.out.println("\n今日到店顾客：");
        int n = customersList.size();
        for (Customer customer : customersList) {
            if (Objects.equals(customer.latest, LocalDate.now())) {
                System.out.println();
                System.out.println(customer);
            }
        }
    }

    @Override
    public void welcome(Customer customer) {
        int operation, buyId;
        if (!customersList.contains(customer)){
            System.out.println("新客到店，欢迎光临！");
            customersList.add(customer);
        }
        customer.comeStore();
        while(true){
            System.out.println("请选择要进行的操作：\n1.购买宠物\t2.离店");
            Scanner sc = new Scanner(System.in);
            operation = sc.nextInt();
            if (operation == 2){
                break;
            } else {
                int sum = animalsList.size();
                for(int i=0; i < sum; ++i){
                    System.out.println("序号为 "+(i+1)+"的动物："+animalsList.get(i));
                }
                System.out.println("请输入您要购买的动物的序号:");
                buyId = sc.nextInt() - 1;
                try {
                    checkAnimal(buyId);
                } catch (AnimalNotFoundException e) {
                    System.err.println("An error occurred: " + e.getMessage());;
                }
            }
            sc = null;
        }
    }

    class InsufficientBalanceException extends RuntimeException {
        public InsufficientBalanceException() {
            super("Insufficient balance.");
        }
    }

    void checkBalance(BaseAnimal animal) throws InsufficientBalanceException {
        if (animal.price*PURCHASE_PRICE_MULTIPLIER>balance){
            throw new InsufficientBalanceException();
        } else {
            balance -= animal.price*PURCHASE_PRICE_MULTIPLIER;
            animalsList.add(animal);
            System.out.println("成功买入："+ animal +"\n");
            System.out.println("现在共有："+animalsList.size()+"只动物");
        }
    }

    class AnimalNotFoundException extends RuntimeException {
        public AnimalNotFoundException() {
            super("Animal not found.");
        }
    }

    void checkAnimal(int id) throws AnimalNotFoundException {
        if (id >= animalsList.size()){
            throw new AnimalNotFoundException();
        } else {
            balance += animalsList.get(id).price;
            turnover += animalsList.get(id).price;
            animalsList.remove(id);
            System.out.println("购买成功！欢迎下次光临");
        }
    }
}