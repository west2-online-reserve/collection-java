
public abstract class animal {
     private String name;
     private int age;
     private String sex;
     private double price;

    public animal(String n, int a, String s, double p) {
        this.name = n;
        this.age = a;
        this.sex = s;
        this.price = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public  String toString()
    { return "名字是：" + name +
            "\n年龄是：" + age +
            "\n性别是：" + sex +
            "\n价格是：" + price+
            "\n";}

}

