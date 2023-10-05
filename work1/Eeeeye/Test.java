import org.junit.Test;
class Boothtest{
    public static void main(String[] args) {
        Booth booth = new Booth(1, "张三", 10, false);
        
        long id = booth.getId();
        System.out.println("摊号: " + id);
        
        booth.setName("李四");
        String name = booth.getName();
        System.out.println("摊主姓名: " + name);
        
        
        int total = booth.getTotal();
        System.out.println("在售西瓜数: " + total);

      
        booth.setClosed(true);
        boolean isClosed = booth.isClosed();
        System.out.println("是否休摊整改: " + isClosed);

      
        System.out.println(booth.toString());

   
        Booth.purchase(booth, 3);

       
        booth.restock(100);

        
        Booth[] booths = new Booth[2];
        booths[0] = new Booth(2, "王五", 20, false);
        booths[1] = new Booth(3, "赵六", 15, false);


        Booth.closeBooths(booths);
    }
}