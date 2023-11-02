package src;

public  abstract class AbstractAnimal {


    /* 1. 一个Animal动物类 (抽象类 abstract )

    变量:
    动物名(String)
    年龄(int)
    性别
    价格(double)
    ....
    方法
    一个全参构造方法
    一个抽象的toString() 方法*/
    protected String animalName;
    protected int animalAge;
    protected   String animalGender;
    protected   double purchasingPrice;
    protected   double sellingPrice;

    public double getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(double purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }




    public AbstractAnimal() {

    }
    public AbstractAnimal(String animalName, int animalAge, String animalGender, double purchasingPrice, double sellingPrice) {
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.animalGender = animalGender;
        this.purchasingPrice = purchasingPrice;
        this.sellingPrice = sellingPrice;
    }


    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(int animalAge) {
        this.animalAge = animalAge;
    }

    public String isAnimalGender() {
        return animalGender;
    }

    public void setAnimalGender(String animalGender) {
        this.animalGender = animalGender;
    }



      public abstract String toString ();

}
