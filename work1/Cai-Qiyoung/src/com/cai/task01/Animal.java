package com.cai.task01;

public abstract class Animal {
    private String name;
    private int age;
    private String gender;
    private double price;
    private String kind;

    abstract void eat();
    abstract void makeSound();

    @Override
    public abstract String toString();

    public Animal() {
    }

    public Animal(String name, int age, String gender, double price, String kind) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.kind = kind;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public static class Dog extends Animal{
        boolean isVaccineInjected;

        public Dog(String name, int age, String gender, String kind, boolean isVaccineInjected) {
            super(name, age, gender, 100, kind);
            this.isVaccineInjected = isVaccineInjected;
        }

        public void makeSound(){
            System.out.println(getName() + ": 汪！！汪！！汪！！");
        }
        public void eat(){
            System.out.println(getName() + ": 啃骨头ing");
        }

        @Override
        public String toString() {
            return "Dog{Name:"+getName()+" Age:"+getAge()+" Gender:"+getGender()+" Price:"+getPrice()+" Kind:"+getKind()+"}";
        }

    }

    public static class Cat extends Animal{
        public Cat(String name, int age, String gender, String kind) {
            super(name, age, gender, 200, kind);
        }

        public void makeSound(){
            System.out.println(getName() + ": 喵呜～～～");
        }
        public void eat(){
            System.out.println(getName() + ": 吃小鱼干ing");
        }
        @Override
        public String toString() {
            return "Cat{Name:"+getName()+" Age:"+getAge()+" Gender:"+getGender()+" Price:"+getPrice()+" Kind:"+getKind()+"}";
        }
    }
}
