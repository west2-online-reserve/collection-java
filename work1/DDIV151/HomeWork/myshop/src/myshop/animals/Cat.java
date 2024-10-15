package myshop.animals;

public class Cat extends Animal {
    public Cat() {
    }

    public Cat(String name, int age, String sex, double price) {
        this.setName(name);
        this.setAge(age);
        this.setSex(sex);
        this.setPrice(price);
    }

    @Override
    public String toString() {
        return "猫\t" + getName() + "\t" + getAge() + "岁\t" + getSex() + "\t\t//\t\t" + getPrice() + "元";
    }

    public Cat catBuying() {
        Cat cat = new Cat();
        System.out.println("接下来将开始录入动物的信息");
        System.out.println("请输入动物的名字:");
        if (scanner.hasNext()) {
            String name = scanner.nextLine();
            cat.setName(name);
            System.out.println("请选择动物的性别\n1:雄\n2:雌\n用数字输入进行选择(如性别未知请随意输入除1,2以外的字符)");
            if (scanner.hasNext()) {
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
            }
            cat.setSex(sex);
            System.out.println("请输入动物的年龄:");
            int age = scanner.nextInt();
            scanner.nextLine();
            cat.setAge(age);
            System.out.println("请输入动物的售价:");
            double price = scanner.nextDouble();
            scanner.nextLine();
            cat.setPrice(price);
            return cat;
        }
        return cat;
    }
}
