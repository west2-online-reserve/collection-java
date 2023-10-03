package shop;

/**
 * @author HarveyBlocks
 * @date 2023/08/15 12:20
 **/
public  abstract class Animal {
    protected String name;
    protected int age;
    protected int sex;//"female"==0/"male"==1
    protected double price;//售价

    protected double cost;//成本

    //一个无参构造方法
    public Animal(){}


    //    一个全参构造方法
    public Animal( String name, int age, int sex,double price,double cost) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.cost = cost;
        this.price = price;
    }



    //一个抽象的toString() 方法
    public abstract String toString() ;
    public String sexChange(int sex) {
        switch (sex){
            case 0:
                return "femal";
            case 1:
                return "male";
            default:
                return "unknow";
        }
    }

    public abstract String getName() ;

    public abstract int getPrice() ;
}
