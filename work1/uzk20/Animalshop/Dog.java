package work1.Animalshop;

class Dog extends Animal{
    boolean isVaccineInjected;
    public Dog(String n,int a,String s,boolean v){
        super(n,a,s,100,150);
        isVaccineInjected=v;
    }
    @Override
    public String toString(){
        return "狗的名字："+this.name+"\n它的年龄+"+this.age+"\n价格："+this.sellingPrice+"\n疫苗接种情况："+this.isVaccineInjected+"\n";
    }
}
