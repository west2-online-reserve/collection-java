import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

class MyAnimalShop implements AnimalShop{
    final static double PURCHASE_PRICE_MULTIPLIER = 0.5;

    ArrayList<BaseAnimal> animalsList = new ArrayList<>();

    ArrayList<Customer> customersList = new ArrayList<>();

    private double balance = 9999;

    private int turnover = 0;

    private final Scanner sc = new Scanner(System.in);

    @Override
    public void buy(BaseAnimal animal) {
        try {
            checkBalance(animal);
        } catch (InsufficientBalanceException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        System.out.println("\n今日到店顾客：");
        for (Customer customer : customersList) {
            if (Objects.equals(customer.getLatest(), LocalDate.now())) {
                System.out.println();
                System.out.println(customer);
            }
        }
        System.out.println();
        System.out.println("今日营业额为："+turnover);
        sc.close();
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
                    System.err.println("An error occurred: " + e.getMessage());
                }
            }
        }

    }

    class InsufficientBalanceException extends RuntimeException {
        public InsufficientBalanceException() {
            super("Insufficient balance.");
        }
    }

    void checkBalance(BaseAnimal animal) throws InsufficientBalanceException {
        if (animal.getPrice()*PURCHASE_PRICE_MULTIPLIER>balance){
            throw new InsufficientBalanceException();
        } else {
            balance -= animal.getPrice()*PURCHASE_PRICE_MULTIPLIER;
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
            balance += animalsList.get(id).getPrice();
            turnover += animalsList.get(id).getPrice();
            animalsList.remove(id);
            System.out.println("购买成功！欢迎下次光临");
        }
    }
}
