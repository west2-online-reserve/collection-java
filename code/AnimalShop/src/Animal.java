public abstract class Animal {
    //宠物的一些基本属性
    private String name;
    private int age;
    private String sex;
    private double sellingPrice;
    private double costPprice;

    //构造方法
    public Animal(String name,int age, String sex, double sellingPrice,double costPrice) {
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.sellingPrice=sellingPrice;
        this.costPprice=costPrice;
    }

    //一个抽象的tostring方法
    public abstract String toString();

    //set方法
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public void setCostPprice(double costPprice) {
        this.costPprice = costPprice;
    }

    //get方法
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getSex() {
        return sex;
    }
    public double getSellingPrice() {
        return sellingPrice;
    }
    public double getCostPprice() {
        return costPprice;
    }
}
