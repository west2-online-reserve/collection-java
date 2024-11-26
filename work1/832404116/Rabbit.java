package com.animals;

class Rabbit extends Animals{

    private String name;
    private double cost;

    public Rabbit(String name, int age, String gender, double price, double cost, String name1) {
        super(name, age, gender, price);
        this.cost = cost;
        this.name = name1;
    }
    public Rabbit(String name, int age, String gender, double price) {
        super(name, age, gender, price);
    }


    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return "宠物名："+getName()+" "+"年龄："+getAge()+" "+"性别："+getGender()+"售价："+getPrice();
    }


}
