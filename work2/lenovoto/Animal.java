package homework.work2;

public abstract class Animal {
    //Animal抽象类

    /*
     变量:
    动物名(String)
    年龄(int)
    性别
    价格(double)*/

    protected String petName;
    protected int petAge;
    protected String petSex;
    protected double petPrice;
    protected double petImport;
    double x;

    public Animal() {
    }



    //全参构造方法
    public Animal(String petName, int petAge, String petSex, double petPrice,double petImport) {
        this.petName = petName;
        this.petAge = petAge;
        this.petSex = petSex;
        this.petPrice = petPrice;
        this.petImport = petImport;
    }

    abstract public String toString();
}

