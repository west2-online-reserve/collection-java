package myshop;

import myshop.animals.Cat;
import myshop.animals.ChineseRuralDog;
import myshop.customer.Custom;
import myshop.ecxeption.AnimalNotFountException;
import myshop.ecxeption.InsufficientBalanceException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    Scanner scanner = new Scanner(System.in);
    //店的余额
    double money;
    //利润
    double earnMoney = 0;
    //是否营业
    boolean ifOpen = false;
    ArrayList animals = new ArrayList();
    ArrayList customs = new ArrayList();


    public MyAnimalShop(ArrayList customs, ArrayList animals, double money) {
        this.customs = customs;
        this.animals = animals;
        this.money = money;
    }

    public MyAnimalShop() {
    }

    public void open() {
        ifOpen = true;
        System.out.println("Welcome to My Animal Shop!");
        System.out.println("现在有动物: ");
        printAnimals(animals);
        System.out.println();
        System.out.println("现在有余额: " + getMoney() + "元");
    }

    public ArrayList getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList animals) {
        this.animals = animals;
    }

    public ArrayList getCustoms() {
        return customs;
    }

    public void setCustoms(ArrayList customs) {
        this.customs = customs;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public void buyAnimal(MyAnimalShop myAnimalShop) {
        System.out.println("买入一只动物\n这是一只什么动物?");
        System.out.println("1:这是猫\n2:这是狗");
        if (scanner.hasNext()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    if (money < 200) {
                        throw new InsufficientBalanceException("余额不足无法购入猫猫");
                    } else {
                        Cat cat = new Cat();
                        cat = cat.catBuying();
                        animals.add(cat);//在动物列表添加
                        money -= 200;
                        earnMoney -= 200;
                        System.out.println("添加成功\n" + cat + "\n当前余额: " + money);
                    }
                    break;
                case 2:
                    if (money < 100) {
                        throw new InsufficientBalanceException("余额不足无法购入狗狗");
                    } else {
                        ChineseRuralDog dog = new ChineseRuralDog();
                        animals.add(dog.dogBuying());
                        money -= 100;
                        earnMoney -= 100;
                        System.out.println("添加成功\n" + dog + "\n当前余额: " + money);
                    }
                    break;
            }
        }
    }

    @Override
    public void serveCustomer(MyAnimalShop myAnimalShop) {
        whileServe:
        while (true) {
            if (ifOpen) {
                System.out.println("请输入客户姓名: ");
                String customerName;
                if (scanner.hasNext()) {
                    customerName = scanner.nextLine();
                    Custom custom = new Custom(customerName, 1, LocalDate.now());
                    //判断是不是老顾客,是就读取并自动更新数据,否则新增顾客
                    if (custom.checkCustom(customs, customerName)) {
                        System.out.println("这是一位老顾客,已录入信息");
                        custom = (Custom) customs.get(custom.getCustomIndex(customs, customerName));
                        custom.setDate(LocalDate.now());
                        custom.setTimes(custom.getTimes() + 1);
                        customs.set(custom.getCustomIndex(customs, customerName), custom);
                        System.out.println("客户信息录入成功\n" + "欢迎" + custom.getName());
                        System.out.println("选择要购买的宠物:");
                    } else {
                        customs.add(custom);
                        System.out.println("客户信息录入成功\n" + "欢迎" + custom.getName());
                        System.out.println("选择要购买的宠物:");
                    }

                    //运行到这,客户识别成功,准备卖宠物
                    //首先判断有没有动物
                    if (animals.isEmpty()) {
                        throw new AnimalNotFountException("没有动物可卖");
                    } else {
                        //以表格形式展现一下动物,并接收客户需求
                        printAnimals(animals);
                        System.out.println("请输入购买的动物编号:");
                        int animalIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        //客户乱输入,来找茬了
                        if (animalIndex < 0 || animalIndex > animals.size()) {
                            throw new AnimalNotFountException("顾客买的动物没在卖");
                        } else {
                            //获取卖的动物的品种,获取对应对象
                            if (animals.get(animalIndex) instanceof Cat) {
                                Cat saledCat = (Cat) animals.get(animalIndex);
                                //记录收入,下面同理
                                money += saledCat.getPrice();
                                earnMoney = saledCat.getPrice() - 200;
                            } else {
                                ChineseRuralDog saledDog = (ChineseRuralDog) animals.get(animalIndex);
                                money += saledDog.getPrice();
                                earnMoney = saledDog.getPrice() - 100;
                            }
                            //给出购买结果,从动物列表删除动物
                            System.out.println("顾客购买了\n" + animals.remove(animalIndex));
                        }
                    }
                    break whileServe;
                }
            } else {
                System.out.println("还没开店呢,请先开店");
                System.out.println("要开店吗?\n1: 开店\n2: 保持关店");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        ifOpen = true;
                        continue whileServe;
                    case 2:
                        ifOpen = false;
                        break whileServe;
                    default:
                        break whileServe;
                }
            }
        }
    }

    public void printAnimals(ArrayList animals) {
        System.out.println("动物种类\t动物名字\t动物年龄\t动物性别\t疫苗情况\t售价");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println(i + 1 + " " + animals.get(i).toString());
        }
    }

    public void printCustoms(ArrayList customs) {
        System.out.println("顾客编号\t顾客姓名\t光顾次数\t光顾日期");
        for (int i = 0; i < customs.size(); i++) {
            System.out.println(i + 1 + "\t" + customs.get(i).toString());
        }
    }

    @Override
    public void close(MyAnimalShop myAnimalShop) {
        LocalDateTime now = LocalDateTime.now();
        //LocalDate判断
        if (now.getHour() < 9 || now.getHour() > 18) {
            System.out.println("关店");
            double firstMoney = money - earnMoney;
            System.out.println("你往店里投入了" + firstMoney + "\n");
            System.out.println("有这些顾客来过:\n");
            printCustoms(customs);
            System.out.println();
            System.out.println("最后店里还有 " + getMoney() + " 元");
            if (earnMoney < 0) {
                System.out.println("可惜了,你亏了 " + -earnMoney + " 元");
            } else if (earnMoney == 0) {
                System.out.println("不赚不亏?\n(其实亏了,房租水电结一下)");
            } else {
                System.out.println("今天赚了 " + earnMoney + " 元!");
            }
            System.out.println("再见");
            System.exit(0);
        } else {
            System.out.println("还早呢,18点下班后再关店吧!");
        }

    }
}
