package Shop;

import Animal.*;
import MyException.AnimalNotFountException;
import MyException.InsufficientBalanceException;

import java.time.LocalDateTime;
import java.util.*;

public class MyAnimalShop implements AnimalShop {
    /* 我之前有用scanner.close但是会莫名出现回车后输入不了的情况，当时想了好久也解决不了于是我把代码发给了chatGPT，它让我把scanner.close
       删了，我试了一下发现果真可以了，我也没有搞明白为什么不能输入scanner.close，希望大佬能帮我找到原因*/
    Map<String, ThreeInfo> listOfCustomer = new HashMap<>();
    // 用LinkedList我不知道怎么删除特定元素，最后想了想决定用字典
    private double overage;
    private LinkedList<Object> listOfAnimal = new LinkedList<Object>();
    private boolean isOpen;
    private boolean isCustomerArrive;

    public MyAnimalShop(double overage, LinkedList<Object> listOfAnimal, HashMap<String, ThreeInfo> listOfCustomer, boolean isOpen) {
        this.overage = overage;
        this.listOfAnimal = listOfAnimal;
        this.listOfCustomer = listOfCustomer;
        this.isOpen = isOpen;
    }

    public MyAnimalShop() {
    }
    // 构造器

    // 以下为serveCustomer中要使用的方法
    // 为避免代码重复，我先把要重复的部分定义成了方法,虽然还要重复写方法名，但我想不出可以用一个方法解决的办法了
    // 我想让商家可以自行购买宠物的类型（假设这个市场足够大可以满足商家的所有需求）
    public static String defineName(String string) {
        String name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要给该" + string + "取的名字：");
        name = scanner.nextLine();
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
    // 使得商家可以自己决定宠物年龄

    public static int reDefineAge(String string) {
        int age = defineAge(string);
        while (age < 0 || age >= 10) {
            System.out.println("你输入的不是有效年龄");
            age = defineAge(string);
        }
        return age;
    }
    // 保证年龄合理

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
    // 使得商家可以自己决定宠物性别

    public static String reDefineGender(String string) {
        String str = defineGender(string);
        while ("null".equals(str)) {
            System.out.println("请输入正确的性别：");
            str = defineGender(string);
        }
        return str;
    }
    // 保证性别合理

    public static String defineDistinctiveFeature(String string) {
        String feature;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要购买的" + string + "需有的特点：");
        feature = scanner.nextLine();
        return feature;
    }
    // 使得商家可以决定宠物特征

    public boolean isCustomerArrive() {
        return isCustomerArrive;
    }

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

    public double getOverage() {
        return overage;
    }

    public void setOverage(double overage) {
        this.overage = overage;
    }
    // get，set


    // 谢谢大佬指导，以下为大佬的代码
    public void purchasePet(Animals pet) {
        double price = pet.getPrice();
        listOfAnimal.add(pet);

        if (getOverage() - price >= 0) {
            setOverage(getOverage() - price);
            System.out.println("购买成功，你还有资金" + getOverage() + "元");
        } else {
            try {
                throw new InsufficientBalanceException("你已经没有资金来购买宠物了！");
            } catch (InsufficientBalanceException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void purchase() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n以下为宠物类别：" +
                "\n1.中华田园犬" +
                "\n2.猫猫" +
                "\n3.鹦鹉" +
                "\n4.兔子" +
                "\n请输入你要购买的宠物类别(输入序号）或输入N退出：");
        String specie = scanner.nextLine();

        if (!specie.equals("N")) {
            String nameToPurchase = defineName(specie);
            int ageToPurchase = reDefineAge(specie);
            String genderToPurchase = reDefineGender(specie);
            String distinctiveFeatureToPurchase = defineDistinctiveFeature(specie);

            switch (specie) {
                case "1":
                    purchasePet(new ChinesePastoralDogs(nameToPurchase, ageToPurchase, genderToPurchase, distinctiveFeatureToPurchase));
                    break;
                case "2":
                    purchasePet(new Cat(nameToPurchase, ageToPurchase, genderToPurchase, distinctiveFeatureToPurchase));
                    break;
                case "3":
                    purchasePet(new Parrot(nameToPurchase, ageToPurchase, genderToPurchase, distinctiveFeatureToPurchase));
                    break;
                case "4":
                    purchasePet(new Rabbit(nameToPurchase, ageToPurchase, genderToPurchase, distinctiveFeatureToPurchase));
                    break;
                default:
                    System.out.println("\n你输入的信息有误，购买失败！");
                    purchase();
                    break;
            }
        } else {
            System.out.println("你已经取消购买");
        }

    }

    // 以下为为简化serveCustomer新增的方法
    public void writeCustomerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n请记录该顾客的姓名：");
        String name = scanner.nextLine();
        if (listOfCustomer.containsKey(name)) {
            ThreeInfo info = listOfCustomer.get(name);
            int currentValue = info.getInfoTimeArrival();
            int updatedValue = currentValue + 1;
            LocalDateTime currentTime = LocalDateTime.now();
            listOfCustomer.put(name, new ThreeInfo(name, updatedValue, currentTime));
            //先将最后一个移除再加避免出现重复顾客信息
        } else {
            LocalDateTime currentTime = LocalDateTime.now();
            listOfCustomer.put(name, new ThreeInfo(name, 1, currentTime));
        }
    }
    // 记录顾客信息

    public void sellDog(ChinesePastoralDogs chinesePastoralDogs) {
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
                        sellDog(chinesePastoralDogs);
                    } else {
                        animals.setPrice(animals.getPrice() * 150 / 100);
                        setOverage(getOverage() + animals.getPrice());
                        System.out.println("\n你说：你已经成功购买了" + animals + "欢迎下次光临");
                    }
                    writeCustomerName();
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
            switch (str) {
                case "1":
                    System.out.println("\n你的资金还有" + getOverage() + "元");
                    break;
                case "2":
                    System.out.println(listOfAnimal);
                    break;
                case "3":
                    System.out.println(listOfCustomer.values());
                    break;
                case "4":
                    serveCustomer();
                    break;
                case "5":
                    closeShop();
                    break;
                default:
                    System.out.println("\n请输入正确的序号");
                    break;
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
            switch (str) {
                case ("1"):
                    try {
                        purchase();
                    } catch (MyException.InsufficientBalanceException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case ("2"):
                    openShop();
                    break;
                case ("3"):
                    System.out.println("你的资金还有" + getOverage() + "元");
                    break;
                case ("4"):
                    System.out.println(listOfAnimal);
                    break;
                case ("5"):
                    System.out.println(listOfCustomer.toString());
                    break;
                default:
                    System.out.println("请输入正确的序号");
                    break;
            }
        }

    }

}
