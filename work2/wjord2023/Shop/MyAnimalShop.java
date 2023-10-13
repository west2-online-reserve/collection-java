package Shop;

import Animal.*;
import MyException.AnimalNotFountException;

import java.time.LocalDateTime;
import java.util.*;

public class MyAnimalShop implements AnimalShop {

    Map<String, ThreeInfo> listOfCustomer = new HashMap<>();
    //用linkedlist我不知道怎么删除特定元素，最后想了想决定用字典
    private double overage;
    private LinkedList listOfAnimal = new LinkedList();
    private boolean isOpen;
    private boolean isCustomerArrive;

    public MyAnimalShop(double overage, LinkedList listOfAnimal, HashMap listOfCustomer, boolean isOpen) {
        this.overage = overage;
        this.listOfAnimal = listOfAnimal;
        this.listOfCustomer = listOfCustomer;
        this.isOpen = isOpen;
    }

    public MyAnimalShop() {
    }

    public static String defineName(String string) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要给该" + string + "取的名字：");
        String name = scanner.nextLine();
        return name;
    }

    public static int defineAge(String string) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("请输入你要购买的" + string + "的年龄：");
            int age = scanner.nextInt();
            return age;
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    public static int reDefineAge(String string) {
        int age = defineAge(string);
        while (age < 0 || age >= 10) {
            System.out.println("你输入的不是有效年龄");
            age = defineAge(string);
        }
        return age;
    }

    //保证年龄合理
    public static String defineGender(String string) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要购买的" + string + "的性别(boy或girl)：");
        String str = scanner.nextLine();
        if (str.equals("boy") || str.equals("girl")) {
            return str;
        } else {
            return "null";
        }
    }

    public static String reDefineGender(String string) {
        String str = defineGender(string);
        while (str == "null") {
            System.out.println("请输入正确的性别：");
            str = defineGender(string);
        }
        return str;
    }

    public static String defineDistinctiveFeature(String string) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要购买的" + string + "需有的特点：");
        String feature = scanner.nextLine();
        return feature;
    }

    public boolean isCustomerArrive() {
        return isCustomerArrive;
    }
    //声明和get，set

    public void setCustomerArrive(boolean customerArrive) {
        isCustomerArrive = customerArrive;
    }

    public boolean isOpen() {
        return isOpen;
    }
    //构造器

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public LinkedList getListOfAnimal() {
        return listOfAnimal;
    }

    public void setListOfAnimal(LinkedList listOfAnimal) {
        this.listOfAnimal = listOfAnimal;
    }

    public Map<String, ThreeInfo> getListOfCustomer() {
        return listOfCustomer;
    }

    public void setListOfCustomer(Map<String, ThreeInfo> listOfCustomer) {
        this.listOfCustomer = listOfCustomer;
    }

//保证性别合理

    public double getOverage() {
        return overage;
    }

    public void setOverage(double overage) {
        this.overage = overage;
    }

    //为避免重复造轮子，我先把要重复的部分定义成了方法,虽然还要重复写方法名，但我想不出可以用一个方法解决的办法了
    //我想让商家可以自行购买宠物的类型（假设这个市场足够大可以满足商家的所有需求）
    @Override
    public void purchase() {
        String nameToPurchase;
        int ageToPurchase;
        String genderToPurchase;
        String distinctiveFeatureToPurchase;
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n以下为宠物类别：" +
                "\n1.中华田园犬" +
                "\n2.猫猫" +
                "\n3.鹦鹉" +
                "\n4.兔子" +
                "\n请输入你要购买的宠物类别(输入序号）或输入N退出：");
        String specie = scanner.nextLine();
        //确定购买类型
        if (specie.equals("1")) {
            nameToPurchase = defineName("中华田园犬");
            ageToPurchase = reDefineAge("中华田园犬");
            //defineAge将报错捕捉reDefine方法再次执行保证用户输入的为整数，且不会因为任何原因导致程序不能进行
            genderToPurchase = reDefineGender("中华田园犬");
            distinctiveFeatureToPurchase = defineDistinctiveFeature("中华田园犬");
            ChinesePastoralDogs chinesePastoralDogs = new ChinesePastoralDogs(nameToPurchase, ageToPurchase, genderToPurchase, distinctiveFeatureToPurchase);
            listOfAnimal.add(chinesePastoralDogs);
            if (getOverage() - chinesePastoralDogs.getPrice() >= 0) {
                setOverage(getOverage() - chinesePastoralDogs.getPrice());
                System.out.println("购买成功,你还有资金" + getOverage() + "元");
            } else {
                try {
                    throw new MyException.InsufficientBalanceException("你已经没有资金来购买宠物了！");

                } catch (MyException.InsufficientBalanceException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (specie.equals("2")) {
            nameToPurchase = defineName("猫猫");
            ageToPurchase = reDefineAge("猫猫");
            genderToPurchase = reDefineGender("猫猫");
            distinctiveFeatureToPurchase = defineDistinctiveFeature("猫猫");
            Cat cat = new Cat(nameToPurchase, ageToPurchase, genderToPurchase, distinctiveFeatureToPurchase);
            listOfAnimal.add(cat);
            if (getOverage() - cat.getPrice() >= 0) {
                setOverage(getOverage() - cat.getPrice());
                System.out.println("购买成功,你还有资金" + getOverage() + "元");
            } else {
                try {
                    throw new MyException.InsufficientBalanceException("你已经没有资金来购买宠物了！");

                } catch (MyException.InsufficientBalanceException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (specie.equals("3")) {
            nameToPurchase = defineName("鹦鹉");
            ageToPurchase = reDefineAge("鹦鹉");
            genderToPurchase = reDefineGender("鹦鹉");
            distinctiveFeatureToPurchase = defineDistinctiveFeature("鹦鹉");
            Parrot parrot = new Parrot(nameToPurchase, ageToPurchase, genderToPurchase, distinctiveFeatureToPurchase);
            listOfAnimal.add(parrot);
            if (getOverage() - parrot.getPrice() >= 0) {
                setOverage(getOverage() - parrot.getPrice());
                System.out.println("购买成功,你还有资金" + getOverage() + "元");
            } else {
                try {
                    throw new MyException.InsufficientBalanceException("你已经没有资金来购买宠物了！");

                } catch (MyException.InsufficientBalanceException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (specie.equals("4")) {
            nameToPurchase = defineName("兔子");
            ageToPurchase = reDefineAge("兔子");
            genderToPurchase = reDefineGender("兔子");
            distinctiveFeatureToPurchase = defineDistinctiveFeature("兔子");
            Rabbit rabbit = new Rabbit(nameToPurchase, ageToPurchase, genderToPurchase, distinctiveFeatureToPurchase);
            listOfAnimal.add(rabbit);
            if (getOverage() - rabbit.getPrice() >= 0) {
                setOverage(getOverage() - rabbit.getPrice());
                System.out.println("购买成功,你还有资金" + getOverage() + "元");
            } else {
                try {
                    throw new MyException.InsufficientBalanceException("你已经没有资金来购买宠物了！");

                } catch (MyException.InsufficientBalanceException e) {
                    System.out.println(e.getMessage());
                }
            }
            //这块代码很重复，懒得优化了，将就看吧

        } else if (specie.equals("N")) {
            System.out.println("你已经取消购买");
        } else {
            System.out.println("\n你输入的信息有误，购买失败！");
            Scanner scanner1 = new Scanner(System.in);
            purchase();
        }
    }

    @Override
    public void serveCustomer() {
        if (isCustomerArrive) {
            try {
                if (listOfAnimal.isEmpty()) {
                    throw new AnimalNotFountException("你的商店已经没有宠物可以销售了！");
                }
                System.out.println("你说：欢迎光临，请问有需要什么吗？");
                Random random = new Random();
                int r = random.nextInt(listOfAnimal.size() + 1);
                if (r == listOfAnimal.size()) {
                    System.out.println("你说：抱歉未能有你喜欢的宠物，\n感谢惠顾，欢迎下次光临！");
                } else {
                    Animals animals = (Animals) listOfAnimal.get(r);
                    listOfAnimal.remove(r);
                    if (animals instanceof ChinesePastoralDogs chinesePastoralDogs) {
                        Random random1 = new Random();
                        int a = random1.nextInt(5);
                        if (a <= 3) {
                            System.out.println("\n顾客选择注射疫苗");
                            chinesePastoralDogs.setVaccineInjected(true);
                            //如需注射疫苗要赚25元
                            chinesePastoralDogs.setPrice(chinesePastoralDogs.getPrice() * 150 / 100 + 25);
                            setOverage(getOverage() + chinesePastoralDogs.getPrice());
                            System.out.println("\n你说：你已经成功购买了" + chinesePastoralDogs + "欢迎下次光临");
                        } else {
                            System.out.println("\n顾客选择不注射疫苗");
                            chinesePastoralDogs.setPrice(chinesePastoralDogs.getPrice() * 150 / 100);
                            setOverage(getOverage() + chinesePastoralDogs.getPrice());
                            System.out.println("\n你说：你已经成功购买了" + chinesePastoralDogs + "欢迎下次光临");
                        }

                    } else {
                        animals.setPrice(animals.getPrice() * 150 / 100);
                        setOverage(getOverage() + animals.getPrice());
                        System.out.println("\n你说：你已经成功购买了" + animals + "欢迎下次光临");
                    }
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("\n请记录该顾客的姓名：");
                    String name = scanner.nextLine();
                    if (listOfCustomer.containsKey(name)) {
                        ThreeInfo info = listOfCustomer.get(name);
                        int currentValue = info.getInfo2();
                        int updatedValue = currentValue + 1;
                        LocalDateTime currentTime = LocalDateTime.now();
                        listOfCustomer.put(name, new ThreeInfo(name, updatedValue, currentTime));
                        //先将最后一个移除再加避免出现重复顾客信息
                    } else {
                        LocalDateTime currentTime = LocalDateTime.now();
                        listOfCustomer.put(name, new ThreeInfo(name, 1, currentTime));
                    }

                }
            } catch (AnimalNotFountException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("暂无顾客");
        }
    }

    @Override
    public void closeShop() {
        System.out.println("你的商店已关闭！");
        setOpen(false);
    }

    @Override
    public void openShop() {
        System.out.println("你的商店以开张营业！");
        setOpen(true);
    }


    @Override
    public void basicPage() {
        if (isOpen) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n现在店铺状态为开张，你可以进行以下操作：" +
                    "\n1.查看你的资金" +
                    "\n2.查看你店里有的宠物列表" +
                    "\n3.查看顾客纪念列表" +
                    "\n4.接待顾客" +
                    "\n5.关店" +
                    "\n请输入你要执行的序号：");
            String str = scanner.nextLine();
            if (str.equals("1")) {
                System.out.println("你的资金还有" + getOverage() + "元");
            } else if (str.equals("2")) {
                System.out.println(listOfAnimal);
            } else if (str.equals("3")) {
                System.out.println(listOfCustomer.toString());
            } else if (str.equals("4")) {
                serveCustomer();
            } else if (str.equals("5")) {
                closeShop();
            } else {
                System.out.println("请输入正确的序号");
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n现在店铺状态为打烊，现在你可以进行以下操作：" +
                    "\n1.购买宠物" +
                    "\n2.开张" +
                    "\n3.查看你的资金" +
                    "\n4.查看你店里有的宠物列表" +
                    "\n5.查看顾客纪念列表");
            String str = scanner.nextLine();
            if (str.equals("1")) {
                try {
                    purchase();
                } catch (MyException.InsufficientBalanceException e) {
                    System.out.println(e.getMessage());
                }
            } else if (str.equals("2")) {
                openShop();
            } else if (str.equals("3")) {
                System.out.println("你的资金还有" + getOverage() + "元");
            } else if (str.equals("4")) {
                System.out.println(listOfAnimal);
            } else if (str.equals("5")) {
                System.out.println(listOfCustomer.toString());
            } else {
                System.out.println("请输入正确的序号");
            }
        }

    }

}
