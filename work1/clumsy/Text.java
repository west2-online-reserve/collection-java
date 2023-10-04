public class Text {
    public static void main(String[] args){
        WatermelonBooth sb =new  WatermelonBooth(222,"捷",150,false);
            String w=sb.toString();
            System.out.println(w);
            //买
        System.out.println( sb.purchase(sb,200));//失败
        System.out.println(sb.purchase(sb,-150));//失败
        System.out.println(sb.purchase(sb,150));//成功
        sb.setClose(true);
        System.out.println(sb.purchase(sb,150));//失败
        //进货
        System.out.println( sb.restock(100,sb));//成功
        System.out.println( sb.restock(-100,sb));//失败
        System.out.println( sb.restock(400,sb));//失败
        
        
        //歇业 
        
    }
}
