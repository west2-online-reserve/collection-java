package west_2_work_JST;

public class test {
    public static void main(String[] args) {
        Booth booth = new Booth(1, "JST",400,false);
        System.out.println(booth.getId());
        booth.setId(2);
        System.out.println(booth.getId());
        System.out.println(booth.getName());
        Booth.purchase(booth,200);
        booth.restock(400);
        Booth.closeBooths(booth);
    }

}    
