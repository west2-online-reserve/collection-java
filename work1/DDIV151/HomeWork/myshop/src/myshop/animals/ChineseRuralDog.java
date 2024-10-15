package myshop.animals;

import java.util.Scanner;

public class ChineseRuralDog extends Animal {
    //是否注射狂犬病疫苗
    boolean isVaccineInjected;

    public ChineseRuralDog(String name, int age, String sex, double price, boolean isVaccineInjected) {
        this.setName(name);
        this.setAge(age);
        this.setSex(sex);
        this.setPrice(price);
        this.isVaccineInjected = isVaccineInjected;
    }

    public ChineseRuralDog() {
    }

    @Override
    public String toString() {
        String ivi;
        if (isVaccineInjected) {
            ivi = "检疫合格";
        } else {
            ivi = "需要疫苗";
        }
        return "中华田园犬\t" + getName() + "\t" + getAge() + "岁\t" + getSex() + "\t" + ivi + "\t" + getPrice();
    }

    public ChineseRuralDog dogBuying() {
        Scanner scanner = new Scanner(System.in);
        ChineseRuralDog dog = new ChineseRuralDog();
        System.out.println("接下来将开始录入动物的信息");
        System.out.println("请输入动物的名字:");
        String name = scanner.nextLine();
        dog.setName(name);
        System.out.println("请选择动物的性别\n1:雄\n2:雌\n用数字输入进行选择(如性别未知请随意输入除1,2以外的字符)");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                sex = "雄";
                break;
            case 2:
                sex = "雌";
                break;
            default:
                sex = "未知";
        }
        dog.setSex(sex);
        System.out.println("请输入动物的年龄:");
        int age = scanner.nextInt();
        scanner.nextLine();
        dog.setAge(age);
        System.out.println("请输入动物的售价:");
        double price = scanner.nextDouble();
        dog.setPrice(price);
        System.out.println("动物打过疫苗吗?\n1: 是\n2: 否");
        isVaccineInjected = scanner.nextInt() == 1;
        scanner.nextLine();
        return dog;
    }
}
