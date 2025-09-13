package LoveAndPetShop;

/**
 * 动物类的创建
 *
 * @author xumostar
 * @date 2024/10/26
 */

abstract class Animal{
    //构造函数
    public Animal (String name,int age,String sex,double price,double sellPrice){
        this.animalName=name;
        this.animalAge=age;
        this.animalSex=sex;
        this.animalPrice=price;
        this.sellPrice=sellPrice;
    }
    
    //抽象的toString
    public abstract String toString();

    //获取动物名
    public String getName(){
        return animalName;
    }

    //获取年龄
    public int getAge(){
        return animalAge;
    }

    //获取性别
    public String getSex(){
        return animalSex;
    }

    //获取买入的价钱
    public double getPrice(){
        return animalPrice;
    }

    //获取卖出的价钱
    public double getSellPrice(){
        return sellPrice;
    }
    
    protected String animalName;  
    protected int animalAge;      
    protected String animalSex;   
    protected double animalPrice;  
    protected double sellPrice;
}