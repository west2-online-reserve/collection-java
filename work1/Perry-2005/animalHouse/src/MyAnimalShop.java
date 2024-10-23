import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    private double remainingMoney;
    private ArrayList<Animal> animalList;
    private ArrayList<Customer> customerList;
    private boolean working;
    public MyAnimalShop() {}

    public MyAnimalShop(double remainingMoney, ArrayList<Animal> animals, ArrayList<Customer> customers, boolean working) {
        this.remainingMoney = remainingMoney;
        this.animalList = animals;
        this.customerList = customers;
        this.working = working;
    }
    public double getRemainingMoney() {
        return remainingMoney;
    }
    public void setRemainingMoney(double remainingMoney) {
        this.remainingMoney = remainingMoney;
    }
    public ArrayList<Animal> getAnimals() {
        return animalList;
    }
    public void setAnimals(ArrayList<Animal> animals) {
        this.animalList = animals;
    }
    public ArrayList<Customer> getCustomers() {
        return customerList;
    }
    public void setCustomers(ArrayList<Customer> customers) {
        this.customerList = customers;
    }
    public boolean isWorking() {
        return working;
    }
    public void setWorking(boolean working) {
        this.working = working;
    }


    @Override
    public void buyAnimal(Animal animal) throws AnimalNotFountException,InsufficientBalanceException {
        try {
            if (animal.getPrice() <= remainingMoney) {
                this.animalList.add(animal);
                this.remainingMoney = this.remainingMoney - animal.getPrice();
            } else {
                throw new InsufficientBalanceException();
            }
        }catch (IllegalArgumentException e){
            System.out.println("我们没有钱了");
            e.printStackTrace();
            System.exit(0);
        }

    }//购入动物,判断余额

    @Override
    public void attendCustmer(Customer customer) throws AnimalNotFountException {

        this.customerList.add(customer);
        //第一步,录入客户
        if (this.animalList.size() > 0) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入你的选择 我们只有小狗,小猫,小蛇");
            String customerWant = sc.next();
            for (int i = 0; i < this.animalList.size(); i++) {
                if (i < this.animalList.size() - 1) {
                    if (this.animalList.get(i).getName().equals(customerWant)) {
                        System.out.println("谢谢惠顾");
                        this.remainingMoney = remainingMoney + animalList.get(i).getPrice();
                        System.out.println("宠物" + this.animalList.get(i).getName().toString() + "已出库");
                        System.out.println(this.animalList.get(i));
                        this.animalList.remove(i);
                        //遍历最后一个之前,如果有相应的宠物,则输出
                        break;
                    }
                } else {
                    if (this.animalList.get(i).getName().equals(customerWant)) {
                        System.out.println("谢谢惠顾");
                        this.remainingMoney = remainingMoney + animalList.get(i).getPrice();
                        System.out.println("宠物" + this.animalList.get(i).getName().toString() + "已出库");
                        System.out.println(this.animalList.get(i));
                        this.animalList.remove(i);
                        //最后一个宠物有则输出,无则报错
                    } else {
                        throw new AnimalNotFountException();
                    }
                }
                //AnimalNotFountException e)
                //e.printStackTrace();
            }
        }   //将客户所想与宠物清单对比,判断是否有该动物,有则购买余额增加
        }

        @Override
        public void closeShop () {
            System.out.println("我们歇业了");

        }
    }



