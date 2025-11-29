public abstract class Animal {
    //变量:
    //动物名(String)
    //年龄(int)
    //性别
    //价格(double)
    //....
    //方法
    //一个全参构造方法
    //一个抽象的 toString() 方法
    //........
    private String animalName;  // 动物名
    private int age;            // 年龄
    private String sex;         // 性别
    private double price;       // 价格
    //抽象方法
    public abstract String toString();

    // 构造方法
    public Animal(String animalName, int age, String sex, double price) {
        this.animalName = animalName;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }
    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
