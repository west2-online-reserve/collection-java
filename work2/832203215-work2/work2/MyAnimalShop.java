package work2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    private double money;
    private ArrayList<Animal> animals;
    private ArrayList<Customer> customers;
    private boolean isOpen;

    public MyAnimalShop() {
    }

    public MyAnimalShop(double money, ArrayList<Animal> animals, ArrayList<Customer> customers) {
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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void buyAnimal(Animal a) {
        double price = a.price;
        String name = a.name;
        String gender = a.gender;
        int age = a.age;
        if (money < price) {
            throw new InsufficientBalanceException("店铺余额不足，仅剩" + money + "元" + "，宠物为" + price + "元，还差" + (price - money) + "元");
        } else {
            animals.add(a);
            money -= price;
            System.out.println("购买成功");
        }
    }

    @Override
    public Animal entertainCustomer(Customer customer) {
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入客人的姓名");
        String name = sc.next();
        customer.setName(name);
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(name)) {
                customer = customers.get(i);
                customers.add(customer);
                Customer customer1 = customers.get(i);
                customers.remove(customer1);
                flag = true;
            }
        }
        int count = customer.getCount();
        customer.setCount(++count);
        System.out.println("客人的到店次数" + customer.getCount());
        System.out.println("请输入客人的到店时间");
        String time = sc.next();
        customer.setTime(LocalDate.parse(time));
        if(customer.getTime().getDayOfMonth()==15||customer.getTime().getDayOfMonth()==16){
            System.out.println("抱歉，店铺在月中15，16号不营业");
        }else{
            if(!flag) customers.add(customer);
            System.out.println("请输入顾客想要购买的动物名字，年龄，性别（如果是狗狗请输入是否打疫苗）");
            System.out.println("请输入动物类型");
            String w = sc.next();
            Animal a;
            switch (w) {
                case "狗狗" -> {
                    a = new Dog();
                    Dog d = (Dog) a;
                    a = selectAnimal(d, w);
                    sellAnimal(a);
                    return a;
                }
                case "猫猫" -> {
                    a = new Cat();
                    a = selectAnimal(a, w);
                    sellAnimal(a);
                    return a;
                }
                case "兔兔" -> {
                    a = new Rabbit();
                    a = selectAnimal(a, w);
                    sellAnimal(a);
                    return a;
                }
                default -> {
                    a = new Dog(" ", 0, " ", 1, false);
                    sellAnimal(a);
                    return a;
                }
            }
        }
        return new Dog();
    }

    @Override
    public void close(LocalDate time, ArrayList<Customer> customers, ArrayList<Animal> Animal, MyAnimalShop shop) {
        if(time.getDayOfMonth()==15||time.getDayOfMonth()==16){
            System.out.println("抱歉，店铺在月中15，16号不营业");
        }else{
            int total = 0, start = 0, end = 0;
            for (int i = 0; i < customers.size(); i++) {
                Customer c = customers.get(i);
                if (time.isEqual(c.getTime())) {
                    System.out.println(c);
                    total++;
                    end = i;
                }
            }
            int sum = 0;
            for (int i = start; i < end; i++) {
                Customer c = customers.get(i);
                Animal aa = shop.entertainCustomer(c);
                shop.setMoney((double) (shop.getMoney()) - aa.price);
            }
            System.out.println("当天盈利" + shop.getMoney() + "元");
        }
    }

    public Animal sellAnimal(Animal a) {
        String s = "";
        String s1 = a.name;
        boolean flag = true;
        for (int i = 0; i < animals.size(); i++) {
            s = animals.get(i).getName();
            if (s.equals(s1)) flag = false;
        }
        if (flag) {
            try {
                throw new AnimalNotFountException();
            } catch (AnimalNotFountException e) {
                String ss = "店内没有该动物 " + s1;
                e.printStackTrace();
                System.out.println(ss);
            }

        } else {
            System.out.println("成功购买 " + a.toString());
            animals.remove(a);
            money += a.price;
        }
        return a;
    }

    public Animal selectAnimal(Animal a, String w) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入" + w + "的名字");
        String n = sc.next();
        a.setName(n);
        System.out.println("请输入" + w + "的年龄");
        int age = sc.nextInt();
        a.setAge(age);
        System.out.println("请输入" + w + "的性别");
        String gender = sc.next();
        a.setGender(gender);
        if (a instanceof Dog d) {
            System.out.println("请输入狗狗的疫苗情况");
            String q = sc.next();
            boolean is = false;
            if (q.equals("是")) is = true;
            d.setVaccineInjected(is);
            return d;
        }
        return a;
    }
}
