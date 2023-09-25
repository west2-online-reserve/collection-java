package watermelon;

public class test {
    public static void main(String[] args) {
    	 Stand a= new Stand(1,"张三",100,false);
         Stand b= new Stand(2,"李四",300,true);
         Stand c= new Stand(3,"王五",500,false);

         a.purchase(200);
         a.restock(100);
         a.setIsclosed(false);
         b.purchase(400);
         b.restock(200);
         b.setIsclosed(false);
         c.purchase(600);
         c.restock(300);
         c.setIsclosed(false);
         a.closeStands(new Stand[]{a,b,c});
     }
    


 }