package work1;

public class Test {
    public static void main(String[] args) {

        Booth booth1=new Booth(1,"张三",20,false);
        Booth booth2=new Booth(2,"李四",50,false);
        Booth booth3=new Booth(3,"王五",70,true);
        Booth booth4=new Booth(4,"赵六",10,false);

        System.out.println(booth1.toString());
        System.out.println(booth2.toString());
        System.out.println(booth3.toString());
        System.out.println(booth4.toString());


        Booth.purchase(booth1,200);
        Booth.purchase(booth1,-1);
        Booth.purchase(booth1,10);

        booth2.restock(250);
        booth2.restock(50);

        Booth[] booths={booth1,booth2,booth3,booth4};
        Booth.closeBooths(booths);

    }
}
