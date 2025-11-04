// PetShopUI.java
package shop;

import java.util.Scanner;

public class PetShopUI {
    private MyAnimalShop shop;
    private Scanner scanner;

    public PetShopUI() {
        // 初始化宠物店，假设初始资金为2000元
        this.shop = new MyAnimalShop(2000.0);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("===== 欢迎来到我的宠物店 =====");

        while (true) {
            showMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    buyAnimal();
                    break;
                case 2:
                    serveCustomer();
                    break;
                case 3:
                    closeShop();
                    System.out.println("回车进入下一天");
                    scanner.nextLine();
                    shop.setOpen(true);
                    break;
                case 4:
                    showShopInfo();
                    break;
                default:
                    System.out.println("无效选项，请重新选择！");
            }
        }
    }

    private void showMenu() {
        int today = shop.getCurrentdayDay();
        System.out.println("========== 第" + today + "天 =========");
        System.out.println("\n========== 菜单 ==========");
        System.out.println("1. 买入动物");
        System.out.println("2. 招待客户");
        System.out.println("3. 歇业");
        System.out.println("4. 查看店铺信息");
        System.out.println("========================");
        System.out.print("请选择操作: ");
    }

    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void buyAnimal() {
        System.out.println("\n===== 买入动物 =====");
        System.out.println("1. 中华田园犬 (¥100)");
        System.out.println("2. 猫猫 (¥200)");
        System.out.println("3. 兔子 (¥150)");
        System.out.print("请选择要买入的动物类型: ");

        try {
            int type = Integer.parseInt(scanner.nextLine());
            System.out.print("请输入动物名字: ");
            String name = scanner.nextLine();
            System.out.print("请输入动物年龄: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("请输入动物性别(male/female): ");
            String gender = scanner.nextLine();

            // 性别验证
            validateGender(gender);
            validateage(age);

            Animal animal = null;
            switch (type) {
                case 1:
                    System.out.print("是否已注射疫苗(true/false): ");
                    boolean isVaccinated = Boolean.parseBoolean(scanner.nextLine());
                    animal = new ChineseRuralDog(name, age, gender, isVaccinated);
                    break;
                case 2:
                    animal = new Cat(name, age, gender);
                    break;
                case 3:
                    animal = new Rabbit(name, age, gender);
                    break;
                default:
                    System.out.println("无效的动物类型！");
                    return;
            }

            shop.buyAnimal(animal);
        } catch (NumberFormatException e) {
            System.out.println("输入格式错误！");
        } catch (InsufficientBalanceException e) {
            System.out.println("购买失败: " + e.getMessage());
        } catch (GenderException e) {
            System.out.println("性别输入错误: " + e.getMessage());
        } catch (AgeException e) {
            System.out.println("年龄输入错误: " + e.getMessage());
        }
    }

    // 添加性别验证方法
    private void validateGender(String gender) throws GenderException {
        if (!gender.equals("male") && !gender.equals("female")) {
            throw new GenderException("性别只能是'male'或'female'");
        }
    }
   // 添加年龄验证方法
    private void validateage(int age) throws AgeException {
        if (age < 0) {
            throw new AgeException("年龄不能小于0");
        }
        if (age > 40) {
            throw new AgeException("年龄不能大于40");
        }
    }

    private void serveCustomer() {
        System.out.println("\n===== 招待客户 =====");
        System.out.println("1. 手动输入客户姓名");
        System.out.println("2. 随机生成一个客户");
        System.out.print("请选择: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            Customer customer;

            if (choice == 1) {
                System.out.print("请输入客户姓名: ");
                String name = scanner.nextLine();
                customer = new Customer(name,shop);
            } else {
                customer = Customer.getRandomCustomer(shop);
                System.out.println("随机生成的客户: " + customer.toString());
            }

            shop.serveCustomer(customer);
        } catch (NumberFormatException e) {
            System.out.println("输入格式错误！");
        } catch (AnimalNotFoundException e) {
            System.out.println("招待失败: " + e.getMessage());
        }
    }

    private void closeShop() {
        System.out.println("\n===== 歇业 =====");
        shop.closeShop();
        System.out.println("感谢使用宠物店管理系统！");
    }

    private void showShopInfo() {
        System.out.println("\n===== 店铺信息 =====");
        System.out.println("当前余额: ¥" + shop.getBalance());
        System.out.println("动物库存数量: " + shop.getAnimalCount());
        System.out.println("累计客户数: " + shop.getCustomerCount());
        //顾客列表
        System.out.println("顾客列表:" + shop.getCustomers());


    }
}
