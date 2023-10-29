import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop{
    private double money;
    private ArrayList<Animal> animalArrayList;
    private ArrayList<Customer> customerArrayList;
    private boolean isClosed;
    private double earn = 0;

    MyAnimalShop(){};

    MyAnimalShop(double money){
        this.money=money;
        this.animalArrayList = new ArrayList<Animal>();
        this.customerArrayList = new ArrayList<Customer>();
        this.isClosed=false;
    }

    public void showMoney() {
        System.out.println("宠物店的余额为："+money);
    }

    public void showAnimal(){
        System.out.println("宠物店现有的宠物有:\n");
        for(int i=0;i<animalArrayList.size();i++){
            System.out.println("序号:"+i);
            System.out.println(animalArrayList.get(i).toString());
            System.out.println();
        }
    }

    public void sendAnimal(){
        System.out.print("客户要购买的动物序号为：");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        System.out.println("卖出的动物为:\n"+animalArrayList.get(n).toString());
        money+=animalArrayList.get(n).price;
        earn+=animalArrayList.get(n).price;
        animalArrayList.remove(n);

    }

    public void showCustomer(){
        System.out.println("宠物店的客户有：\n");
        for (int i=0;i<customerArrayList.size();i++){
            System.out.println(customerArrayList.get(i).toString());
            System.out.println();
        }

    }

    @Override
    public void buyAnimal(Animal animal) {

        try {
            //没钱报错
            if (animal.price > money) {
                throw new InsufficientBalanceException("宠物店余额不足");
            }
            //添加动物
            else {
                animalArrayList.add(animal);
                money -= animal.getPrice();
            }

        }catch (InsufficientBalanceException exception){
            System.out.println(exception.toString());
        }

    }

    @Override
    public void entertainCustomer(Customer customer) {
        //添加客户信息
        if(customerArrayList.isEmpty()){
            customerArrayList.add(customer);
        }else {
            boolean flag = false;
            for (int i = 0; i < customerArrayList.size(); i++) {
                if (customer.getName() == customerArrayList.get(i).getName()) {
                    customerArrayList.get(i).addTimes();
                    customerArrayList.get(i).setDate();
                    flag = true;
                    break;
                }
            }
            if(!flag){
                customerArrayList.add(customer);
            }
        }

        try {
            //没动物报错
            if(animalArrayList.isEmpty()){
                throw new AnimalNotFoundException("店内没有动物了");
            } else{

                showAnimal();
                sendAnimal();
            }
        }catch (AnimalNotFoundException exception){
            System.out.println(exception.toString());
        }

    }

    @Override
    public void close() {
        this.isClosed = true;

        // 输出当天光顾的客户的列表信息
        for (int i = 0; i < customerArrayList.size(); i++) {
            if(Objects.equals(customerArrayList.get(i).getDate(), LocalDate.now())) {
                System.out.println("今日光顾的顾客：\n"+customerArrayList.get(i).toString());
            }
        }

        //计算当天利润
        System.out.println("日期:"+LocalDate.now()+"的利润为:"+earn);
        earn = 0;
    }



}
