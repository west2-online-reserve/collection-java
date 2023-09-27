public class watermalontest {
    public static void main(String[] args) {
        Stand x= new Stand(1,"张三",200,false);
        Stand y= new Stand(2,"李四",300,true);
        //接下来开始测试
        Stand.purchase(y,500);//购买量过多
        Stand.purchase(y,-2);//购买量为负
        Stand.purchase(y, 50);//成功购买
        System.out.println(y);
        x.restock(200);//成功进货
        x.restock(-20);//进货量为负
        System.out.println(x);
        Stand.closeStands(new Stand[]{x,y});//歇业整顿
    }
}
