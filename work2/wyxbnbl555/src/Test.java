import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop();
        ArrayList<Customer> customerList = new ArrayList<>();
        List<Animal> animalList = new ArrayList<>();
        while (shop.isBusiness()){
            System.out.println("1.购买动物");
            System.out.println("2.设置开业成本");
            System.out.println("3.关门");
            System.out.println("4.招待顾客");
            Scanner sc = new Scanner(System.in);
            String s = sc.next();
//            Cat cat1 = new Cat();
//            cat1.setName("小白");
//            cat1.setAge(2);
//            cat1.setGender("男");
            switch (s){
                case "1":
                    System.out.println("要添加什么动物");
                    System.out.println("1.猫");
                    System.out.println("2.中华田园犬");
                    System.out.println("3.哈士奇");
                    Scanner sc1 = new Scanner(System.in);
                    String e = sc1.nextLine();
                    switch(e){
                        case"1":
                            Cat cat1 = new Cat();
                            System.out.println("请输入猫猫的姓名");
                            String catName = sc1.nextLine();
                            cat1.setName(catName);
                            System.out.println("请输入猫猫的年龄");
                            int catAge = sc1.nextInt();
                            cat1.setAge(catAge);
                            System.out.println("请输入猫猫的性别");
                            String catGender = sc1.next();
                            cat1.setGender(catGender);
                            shop.buyNewAnimal(cat1);
                            break;
                        case"2":
                            ChinesePastoralDog chinesePastoralDog = new ChinesePastoralDog();
                            System.out.println("请输入狗狗的姓名");
                            String name = sc1.nextLine();
                            chinesePastoralDog.setName(name);
                            System.out.println("请输入狗狗的年龄");
                            int age = sc1.nextInt();
                            chinesePastoralDog.setAge(age);
                            System.out.println("请输入狗狗的性别");
                            String gender = sc1.next();
                            chinesePastoralDog.setGender(gender);
                            System.out.println("是否注射狂犬疫苗");
                            boolean a = sc1.nextBoolean();
                            chinesePastoralDog.setVaccineInjected(a);
                            shop.buyNewAnimal(chinesePastoralDog);
                            break;
                        case"3":
                            Husky husky = new Husky();
                            System.out.println("请输入哈士奇的姓名");
                            String huskyName = sc1.nextLine();
                            husky.setName(huskyName);
                            System.out.println("请输入哈士奇的年龄");
                            int huskyAge = sc1.nextInt();
                            husky.setAge(huskyAge);
                            System.out.println("请输入哈士奇的性别");
                            String huskyGender = sc1.next();
                            husky.setGender(huskyGender);
                            shop.buyNewAnimal(husky);
                            break;
                        default:
                            System.out.println("没有你要的动物");
                            continue;

                    }
                    break;
                case "2":
                    System.out.println("请输入开业成本");
                    double balance = sc.nextDouble();
                    shop.setBalance(balance);
                    break;
                case "3":
                    shop.outOfBusiness(customerList);
                    break;
                case "4":
                    Customer cust = new Customer();
                    System.out.println("请输入顾客姓名");
                    String name = sc.next();
                    cust.setName(name);
                    System.out.println("请输入访问次数");
                    int visitTime = sc.nextInt();
                    cust.setVisitNumber(visitTime);
                    /*System.out.println("请输入最后访问时间");
                    int year = sc.nextInt();
                    int mouth = sc.nextInt();
                    int day = sc.nextInt();
                    cust.setLastTime(LocalDate.of(year, mouth, day));*/
                    customerList.add(cust);
                    ArrayList<Animal> a = shop.a;
                    for (int i = 0; i < a.size(); i++) {
                        System.out.println( (i + 1) + a.get(i).toString());
                    }
                    System.out.println("要买什么动物");
                    String r = sc.next();
                    if((Integer.parseInt(r) - 1)<a.size()){
                        Animal animal = a.get(Integer.parseInt(r) - 1);
                        shop.entertainCustomer(cust,animal);
                        a.remove(animal);
                    }else{
                        System.out.println("没有这个动物");
                        continue;
                    }

                    break;


                default:
                    System.out.println("没有这个选项");
                    continue;
            }
        }
/*        if(shop.isBusiness()==true) {
            shop.setBalance(2000);
            double startbalance = shop.getBalance();
            ArrayList<Animal> a = new ArrayList<>();
            ArrayList<Customer> c = new ArrayList<>();
            Cat cat = new Cat();
            cat.setName("小白");
            cat.setAge(2);
            cat.setGender("男");
            shop.buyNewAnimal(cat);

            Customer cust = new Customer();
            cust.setName("李四");
            cust.setVisitNumber(3);
            cust.setLastTime(LocalDate.of(2023, 12, 2));
            shop.entertainCustomer(cust, cat);


            shop.outOfBusiness(c);
            double endbalance = shop.getBalance();
            System.out.println(endbalance - startbalance);
        }else{
            System.out.println("本店已经歇业");
        }*/

    }
}
/*class test{
    public static void main(String[] args) {
        Customer customer1 = new Customer();
        for (int i = 0; i < 1000000; i++) {

        }
        Customer customer2 = new Customer();
        System.out.println(customer1.getLastTime().isBefore(customer2.getLastTime()));
    }
}*/
