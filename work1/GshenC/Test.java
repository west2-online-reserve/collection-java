import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("请输入宠物店的初始余额：");
            double initialBalance = scanner.nextDouble();
            MyAnimalShop animalShop = new MyAnimalShop(initialBalance);
            animalShop.openShop();

            while (true) {
                System.out.println("请选择操作：1. 买入动物  2. 招待顾客  3. 关闭商店  4. 退出");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        System.out.println("请选择动物类型：1. 猫  2. 中华田园犬  3. 猴子");
                        int animalType = scanner.nextInt();
                        scanner.nextLine(); 

                        System.out.println("请输入动物的名字、年龄、性别：");
                        String name = scanner.nextLine();
                        int age = scanner.nextInt();
                        String gender = scanner.next();

                        boolean hasRabiesVaccine = false;
                        if (animalType == 2) { 
                            System.out.println("是否注射狂犬疫苗 (true/false)：");
                            hasRabiesVaccine = scanner.nextBoolean();
                        }

                        try {
                            animalShop.buyAnimal(animalType, name, age, gender, hasRabiesVaccine);
                        } catch (InsufficientBalanceException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("请输入顾客的名字：");
                        String customerName = scanner.nextLine();

                        LocalDate lastVisitDate = null;
                        while (lastVisitDate == null) {
                            System.out.println("请输入到店日期 (格式: YYYY-MM-DD)：");
                            String dateInput = scanner.nextLine();

                            try {
                                lastVisitDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            } catch (DateTimeParseException e) {
                                System.out.println("日期格式错误，请使用 YYYY-MM-DD 格式重新输入。");
                            }
                        }

                        Customer customer = new Customer(customerName, lastVisitDate);
                        try {
                            animalShop.attendCustomer(customer);
                        } catch (AnimalNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3:
                        animalShop.closeShop();
                        return;

                    case 4:
                        System.out.println("退出程序。");
                        return;

                    default:
                        System.out.println("无效的选择。");
                }
            }
        }
    }
}
