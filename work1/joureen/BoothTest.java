public class BoothTest {
    public static void main(String[] args) {
        Booth booth1=new Booth(1,"Hong",300,false);
        Booth booth2=new Booth();
        booth2.setId(2);
        booth2.setName("Wang");
        booth2.setTotal(1000);
        booth2.setIsClosed(true);
        booth1.restock(100);
        booth2.restock(-10);
        booth2.restock(1000);
        Booth.purchase(booth1,100);
        Booth.purchase(booth1,500);
        Booth.purchase(booth1,-100);
        Booth.purchase(booth2,100);
        System.out.println(booth1);
        System.out.println("店铺信息：id="+booth1.getId()+",name="+booth1.getName()+",total="+booth1.getTotal()+",IsClosed="+booth1.getIsClosed());
        Booth[] booths = {booth1,booth2};
        Booth.closeBooths(booths);
    }
}
