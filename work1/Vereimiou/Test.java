public class Test {
    public static void main(String[] args) {
        Booth booth1=new Booth(101,"张三",20,false);
        Booth booth2=new Booth(102,"李四",31,false);
        Booth booth3=new Booth(103,"王五",342,true);
        System.out.println(booth1.toString());
        booth1.setId(111);
        booth1.setClosed(true);
        System.out.println(booth1.getId());
        Booth []booths={booth1,booth2,booth3};
        for(Booth booth:booths){
            System.out.println(booth.toString());
        }
        booth1.restock(201);
        booth1.restock(-121);
        Booth.purchase(booth2,2200);
        Booth.purchase(booth2,-32);
        Booth.purchase(booth2,1);
        System.out.println(booth2.toString());
        Booth.closeBooths(booths);
        Booth.purchase(booth3,2);
    }
}
