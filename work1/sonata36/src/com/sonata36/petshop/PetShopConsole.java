package com.sonata36.petshop;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** 供用户手动测试宠物店业务的命令行入口。 */
public final class PetShopConsole {
    private final Scanner scanner;
    private final Map<String, Customer> knownCustomers;
    private MyAnimalShop shop;
    private LocalDate businessDate;

    private PetShopConsole(Scanner scanner) {
        this.scanner = scanner;
        this.knownCustomers = new LinkedHashMap<>();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            new PetShopConsole(scanner).run();
        }
    }

    private void run() {
        System.out.println("欢迎来到宠物店手动测试台！");
        try {
            openNewShop();
            while (true) {
                printMenu();
                String choice = readLine("请选择操作：");
                if (!shop.isOpen() && ("1".equals(choice) || "2".equals(choice)
                        || "4".equals(choice))) {
                    System.out.println("操作失败：宠物店已经歇业");
                    continue;
                }
                try {
                    switch (choice) {
                        case "1":
                            buyAnimal();
                            break;
                        case "2":
                            serveCustomer();
                            break;
                        case "3":
                            showStatus();
                            break;
                        case "4":
                            shop.close();
                            break;
                        case "5":
                            openNextDay();
                            break;
                        case "0":
                            System.out.println("测试结束。");
                            return;
                        default:
                            System.out.println("无效选项，请输入 0～5。");
                    }
                } catch (InsufficientBalanceException | AnimalNotFoundException
                        | IllegalStateException exception) {
                    System.out.println("操作失败：" + exception.getMessage());
                }
            }
        } catch (NoSuchElementException exception) {
            System.out.println("输入已结束，测试退出。");
        }
    }

    private void openNewShop() {
        double balance = readNonNegativeDouble("请输入开店余额：");
        businessDate = LocalDate.now();
        shop = new MyAnimalShop(balance, List.of(), List.of(), businessClock());
        knownCustomers.clear();
        System.out.printf(Locale.ROOT, "新店已开张，余额：%.2f 元%n", balance);
    }

    private void openNextDay() {
        if (shop.isOpen()) {
            System.out.println("操作失败：请先歇业，再开始下一营业日");
            return;
        }
        double balance = shop.getBalance();
        List<Animal> animals = shop.getAnimals();
        List<Customer> customers = shop.getCustomers();
        businessDate = businessDate.plusDays(1);
        shop = new MyAnimalShop(balance, animals, customers, businessClock());
        System.out.printf(Locale.ROOT, "下一营业日（%s）已开张，继承余额：%.2f 元，库存：%d 只%n",
                businessDate, balance, animals.size());
    }

    private Clock businessClock() {
        ZoneId zone = ZoneId.systemDefault();
        return Clock.fixed(businessDate.atStartOfDay(zone).toInstant(), zone);
    }

    private void buyAnimal() {
        System.out.println("动物种类：1. 中华田园犬（100 元）  2. 猫（200 元）  3. 兔子（80 元）");
        String type = readAnimalType();
        String name = readNonBlank("动物名字：");
        int age = readNonNegativeInt("动物年龄：");
        String sex = readSex();
        Animal animal;
        switch (type) {
            case "1":
                boolean vaccineInjected = readYesNo("是否已注射狂犬病疫苗？(y/n)：");
                animal = new ChineseRuralDog(name, age, sex, vaccineInjected);
                break;
            case "2":
                animal = new Cat(name, age, sex);
                break;
            default:
                animal = new Rabbit(name, age, sex);
        }
        double costPrice = readCostPrice(animal.getPrice());
        shop.buyAnimal(animal, costPrice);
        System.out.println("买入成功：" + animal);
        System.out.printf(Locale.ROOT, "成本价：%.2f 元，售价：%.2f 元，预计售出利润：%.2f 元%n",
                costPrice, animal.getPrice(), animal.getPrice() - costPrice);
    }

    private void serveCustomer() {
        String name = readNonBlank("顾客名字：");
        Customer customer = knownCustomers.computeIfAbsent(name, Customer::new);
        List<Animal> animals = shop.getAnimals();
        if (animals.isEmpty()) {
            shop.serveCustomer(customer);
            return;
        }
        System.out.println("可选宠物：");
        for (int index = 0; index < animals.size(); index++) {
            System.out.println((index + 1) + ". " + animals.get(index));
        }
        int choice = readAnimalChoice(animals.size());
        shop.serveCustomer(customer, choice - 1);
        System.out.println("顾客到店次数：" + customer.getVisitCount());
    }

    private void showStatus() {
        System.out.println("营业日期：" + businessDate);
        System.out.println("营业状态：" + (shop.isOpen() ? "营业中" : "已歇业"));
        System.out.printf(Locale.ROOT, "余额：%.2f 元%n", shop.getBalance());
        System.out.printf(Locale.ROOT, "今日利润：%.2f 元%n",
                shop.getProfit(businessDate));
        List<Animal> animals = shop.getAnimals();
        System.out.println("库存（按入库顺序）：" + animals.size() + " 只");
        for (int index = 0; index < animals.size(); index++) {
            System.out.println((index + 1) + ". " + animals.get(index));
        }
        System.out.println("已认识顾客：" + knownCustomers.size() + " 位");
        for (Customer customer : knownCustomers.values()) {
            System.out.println(customer);
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. 买入动物  2. 招待顾客  3. 查看状态");
        System.out.println("4. 歇业      5. 次日开店  0. 退出测试");
    }

    private String readAnimalType() {
        while (true) {
            String type = readLine("请选择动物种类 (1/2/3)：");
            if ("1".equals(type) || "2".equals(type) || "3".equals(type)) {
                return type;
            }
            System.out.println("请输入 1、2 或 3。");
        }
    }

    private String readSex() {
        System.out.println("动物性别：1. 公  2. 母");
        while (true) {
            String choice = readLine("请选择动物性别 (1/2)：");
            if ("1".equals(choice)) {
                return "公";
            }
            if ("2".equals(choice)) {
                return "母";
            }
            System.out.println("请输入 1 或 2。");
        }
    }

    private int readAnimalChoice(int animalCount) {
        while (true) {
            int choice = readNonNegativeInt("请选择要购买的宠物编号：");
            if (choice >= 1 && choice <= animalCount) {
                return choice;
            }
            System.out.println("请输入列表中的宠物编号（1～" + animalCount + "）。");
        }
    }

    private String readNonBlank(String prompt) {
        while (true) {
            String value = readLine(prompt);
            if (!value.isBlank()) {
                return value;
            }
            System.out.println("内容不能为空，请重新输入。");
        }
    }

    private int readNonNegativeInt(String prompt) {
        while (true) {
            try {
                int value = Integer.parseInt(readLine(prompt));
                if (value >= 0) {
                    return value;
                }
            } catch (NumberFormatException exception) {
                // 输入格式不正确时统一提示并重试。
            }
            System.out.println("请输入非负整数。");
        }
    }

    private double readNonNegativeDouble(String prompt) {
        while (true) {
            try {
                double value = Double.parseDouble(readLine(prompt));
                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }
            } catch (NumberFormatException exception) {
                // 输入格式不正确时统一提示并重试。
            }
            System.out.println("请输入非负的有限数字。");
        }
    }

    private double readCostPrice(double sellingPrice) {
        while (true) {
            double costPrice = readNonNegativeDouble(String.format(Locale.ROOT,
                    "请输入成本价（须低于售价 %.2f 元）：", sellingPrice));
            if (BigDecimal.valueOf(costPrice).scale() > 2) {
                System.out.println("成本价最多保留两位小数。");
                continue;
            }
            if (costPrice < sellingPrice) {
                return costPrice;
            }
            System.out.println("成本价必须低于售价，才能保证售出有利润。");
        }
    }

    private boolean readYesNo(String prompt) {
        while (true) {
            String answer = readLine(prompt);
            if ("y".equalsIgnoreCase(answer) || "是".equals(answer)) {
                return true;
            }
            if ("n".equalsIgnoreCase(answer) || "否".equals(answer)) {
                return false;
            }
            System.out.println("请输入 y/n 或 是/否。");
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            throw new NoSuchElementException("没有更多输入");
        }
        return scanner.nextLine().trim();
    }
}
