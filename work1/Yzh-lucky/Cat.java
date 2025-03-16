package Work1;

public class Cat extends Animal {
    private boolean isVaccineInjected=true;

    public Cat(String name,int age,char sex,double price,double salePrice) {
        super(name,age,sex,price,salePrice);
    }

    public void toString(String name, int age,char sex,double price,boolean isVaccineInjected){
        System.out.print("这只猫的名字: "+name+",年龄: "+age+",性别: "+sex+",价格: "+price);
        if(isVaccineInjected){
            System.out.println(",接种了疫苗");
        }else{
            System.out.println(",没有接种疫苗");
        }
    }

    public void action(){
        System.out.println("猫的叫声是喵喵。");
    }
}
