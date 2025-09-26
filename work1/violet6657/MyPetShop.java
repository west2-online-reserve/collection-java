import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MyPetShop implements AnimalShop {
    private static final LocalTime OPEN_TIME = LocalTime.of(9, 0);  // 9:00 开店
    private static final LocalTime CLOSE_TIME = LocalTime.of(23, 0); // 21:00 闭店
    private double money;
    private ArrayList<Animal> animals;
    private ArrayList<Customer> customers;
    public boolean Working;



    public MyPetShop(double money, ArrayList<Animal> animals, ArrayList<Customer> customers) {
        this.money = money;
        this.animals = animals;
        this.customers = customers;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void checkWorking() {
        LocalDateTime now = LocalDateTime.now();  // 获取当前本地时间
        LocalTime currentTime = now.toLocalTime(); // 提取时间部分
        if (currentTime.isBefore(OPEN_TIME)||currentTime.isAfter(CLOSE_TIME)) {
            this.Working = false;
        }else{
            this.Working = true;
        }
    }


    @Override
    public void buyNewAnimal(Animal animal) throws InsufficientBalanceException{
        Scanner in = new Scanner(System.in);
        double purchasePrice = 0;
        String animalClass = "";
        if (animal instanceof Cat) {
            purchasePrice = Cat.CAT_PURCHASE_PRICE;
            animalClass = "猫咪";
        } else if (animal instanceof ChineseDog) {
            purchasePrice = ChineseDog.CHINESEDOG_PURCHASE_PRICE;
            animalClass = "中华田园犬";
        } else if (animal instanceof Chicken) {
            purchasePrice = Chicken.CHICKEN_PURCHASE_PRICE;
            animalClass = "鸡";
        } else {
            System.out.println("进货商暂无此宠物提供");
        }
        System.out.println("您正在买一只" + animalClass + "价格为" + purchasePrice);
        if (money < purchasePrice) {
            System.out.println("您的账户余额不足,请充值");
            throw new InsufficientBalanceException("账户余额错误,您买不起这只动物");
        } else {
            money -= purchasePrice;
            animals.add(animal);
            System.out.println("恭喜您,购买成功,您的余额还剩" + money + "元");
            initialize(animal);
        }
    }

    @Override
    public void tradeCustomer(ArrayList<Customer> customers,ArrayList<Animal> animals) {
        Scanner in = new Scanner(System.in);
        System.out.println("""
                请问客官您要买什么?
                本店有猫,狗,鸡出售
                想进店逛逛请按1
                想退出购买请按2""");
        int choose=in.nextInt();
        switch (choose){
            case 1->{
                if(animals.size()==0){
                    System.out.println("店内没有动物!");
                    throw new AnimalNotFountException("店内没有动物可供售卖");
                }else{
                    sellAnimal(customers,animals);
                }
            }
            case 2->{
                System.out.println("您已退出");}
            default -> {
                tradeCustomer(customers,animals);
            }

        }


    }

    @Override
    public void closeShop() {
        checkTime();
        if (!Working) {
            System.out.println("我们已经闭店了");
            System.exit(-1);
        }else {
            //执行闭店程序
            System.out.println("闭店成功");
            for (int i  = 0; i <customers.size() ; i ++) {
                System.out.println(customers.get(i).toString());
            }
            System.out.println("今日利润为"+(money-10000));
            System.exit(1);
        }

    }

    public void initialize(Animal animal) {
        Scanner in = new Scanner(System.in);
        System.out.println("请初始化您新购买的动物");
        System.out.println("请输入新动物的名字");
        animal.setName(in.nextLine());

        System.out.println("请输入动物的年龄");
        animal.setAge(Integer.parseInt(in.nextLine()));
        while (true) {
            System.out.println("请输入动物的性别(公/母)");
            String gender = in.nextLine();
            if(genderCheck(gender)){
                animal.setGender(gender);
                break;
            }
        }

        System.out.println("请输入动物的体重");
        animal.setWeight(Integer.parseInt(in.nextLine()));
        System.out.println("请输入动物的售价");
        int price = in.nextInt();
        String temp = in.nextLine();
        animal.setPrice(price);

        if (animal instanceof Cat) {
            System.out.println("猫咪初始化完成：" + animal);
        } else if (animal instanceof ChineseDog) {
            while (true){
                System.out.println("请输入是否已打狂犬疫苗（true/false）");
                String rightOrFalse = in.nextLine();
                if(rightOrFalse.equals("true") || rightOrFalse.equals("false")){
                    ((ChineseDog) animal).isVaccineInjected = Boolean.parseBoolean(rightOrFalse);
                    System.out.println("中华田园犬初始化完成：" + animal);
                    break;
                }
            }
        }
        if (animal instanceof Chicken) {
            System.out.println("鸡初始化完成"+ animal);
        }
    }

    public void checkTime(){
        checkWorking();
    }


    public void sellAnimal(ArrayList<Customer> customers,ArrayList<Animal> animals) {
        Scanner in = new Scanner(System.in);
        System.out.println("以下为我店所有宠物名单,请输入想购买的宠物编号,如想退出,请输入0");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("NO." + (i + 1) + " " + animals.get(i).toString());
        }
        int choicePet = in.nextInt();
        in.nextLine();
        if (choicePet == 0) {
            MyPetShopTest.petStart(MyPetShopTest.getMyPetShop(), animals, customers);
        } else if (choicePet > 0 && choicePet <= animals.size()) {
            int index = choicePet - 1;
            Animal soldAnimal = animals.get(index);
            money += soldAnimal.getPrice();
            System.out.println("恭喜您已买到" + soldAnimal.getName());
            animals.remove(index);
            System.out.println("请留下您的个人信息");
            System.out.println("您的名字为?");
            String name = in.nextLine();
            boolean customerExists = false;
            for (Customer customer : customers) {
                if (name.equals(customer.getName())) {
                    customer.setShoppingTimes(customer.getShoppingTimes() + 1);
                    customer.setLatestShoppingTime(LocalTime.now());
                    customerExists = true;
                    System.out.println("欢迎老顾客光临");
                    break;
                }
            }
            if (!customerExists) {
                Customer newCustomer = new Customer(name, 1, LocalTime.now());
                customers.add(newCustomer);
                System.out.println("欢迎新顾客光临");
            }
        } else {
            System.out.println("无效的宠物编号，请重新选择");
            sellAnimal(customers, animals);
        }

    }

    public  boolean genderCheck(String gender) {
        if (gender.equals("公")||gender.equals("母")) {
            return true;
        }else{
            return false;
        }
    }


}
