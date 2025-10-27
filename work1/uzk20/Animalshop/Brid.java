package work1.Animalshop;

class Brid extends Animal{
    public Brid(String n,int a,String s){
        super(n,a,s,300,450);
    }

    @Override
    public String toString(){
        return "鸟的名字："+this.name+"\n它的年龄+"+this.age+"\n价格："+this.sellingPrice+"\n";
    }
}
