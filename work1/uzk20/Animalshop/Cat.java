package work1.Animalshop;

class Cat extends Animal{
    public Cat(String n, int a, String s){
        super(n, a, s, 200, 300);
    }

    @Override
    public String toString(){
        return "猫的名字："+this.name+"\n它的年龄+"+this.age+"\n价格："+this.sellingPrice+"\n";
    }
}
