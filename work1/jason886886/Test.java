package work1;

public class Test {
    public static void main(String[] args) {
        Booth booth1 = new Booth(123, "mark", 23, false);
        System.out.println(booth1.toString());

        Booth booth2 = new Booth();
        booth2.setId(124);
        booth2.setName("jack");
        booth2.setTotal(201);
        booth2.setIsClosed(true);

        Booth.purchase(booth1,40);

        booth2.restock(300);

        Booth.closeBooths(new Booth[]{booth1, booth2});

    }
}
