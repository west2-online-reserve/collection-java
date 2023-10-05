public class BoothTest {
    public static void main(String[] args) {
        Booth booth1=new Booth(1,"XiaoHong",300,false);
        Booth booth2=new Booth();
        booth2.setId(2);
        booth2.setName("XiaoWang");
        booth2.setTotal(1000);
        booth2.setIsClosed(true);
        booth1.restock(100);
        System.out.println(booth1.getId());
        Booth.purchase(booth1,100);
        Booth[] booths = {booth1,booth2};
        Booth.closeBooths(booths);
    }
}
