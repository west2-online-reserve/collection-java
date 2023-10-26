public abstract class Animal {
    protected String name;
    protected int age;
    protected char sex;
    protected double price;
    public Animal(double price,char sex,int age,String name){
        this.price = price;
        this.sex = sex;
        this.age = age;
        this.name = name;
    }


    public abstract String toString();

    public char getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
