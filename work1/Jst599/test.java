package west_2_work_JST;

public class test {
    public static void main(String[] args) {
        Booth booth1 = new Booth(1, "JST",400,false);
        Booth booth2 = new Booth(2,"qjc",500,true);
        System.out.println(booth1.getId());
        booth1.setId(2);
        System.out.println(booth1.getId());
        System.out.println(booth1.getName());
        Booth.purchase(booth1,200);
        booth1.restock(booth1,400);
        booth2.restock(booth2,900);
        Booth[] booths = {booth1,booth2};
        Booth.closeBooths(booths);
    }

}
