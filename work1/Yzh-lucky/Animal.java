package Work1;

public abstract class Animal {
        protected String name;
        protected int age;
        protected char sex;
        protected double price;
        protected double salePrice;
        //全参构造方法
        public Animal(String name,int age,char sex,double price,double salePrice) {
            this.name = name;
            this.age = age;
            this.sex=sex;
            this.price=price;
            this.salePrice=salePrice;
        }
        //抽象类
        public abstract void toString(String name, int age,char sex,double price,boolean isVaccineInjected);
        public abstract void action();
}
