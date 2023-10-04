public class test
{
    public static void main(String[] args)
    {
        Booth person1 = new Booth(10086,"原神高手",100,true);

        Booth[] booths=new Booth[5];
        booths[0]=new Booth(123456789,"12345",20,false);
        booths[1]=new Booth(1001,"abcde",0,true);
        booths[2]=new Booth(222200133,"!@#$%",999,false);
        booths[3]=new Booth(4090,"姓名",500,true);
        booths[4]=new Booth(99999,"小帅",1024,false);

        System.out.println("**********************");
        System.out.println(person1);
        System.out.println("**********************");

        //各变量对应的set和get方法测试
        person1.setId(1);
        System.out.println("当前摊号为："+person1.getId());
        person1.setName("coke老师");
        System.out.println("摊主姓名为："+person1.getName());
        person1.setTotal(200);
        System.out.println("当前在售西瓜数："+person1.getTotal());
        person1.setClosed(false);
        System.out.println("是否休摊整改："+person1.isClosed());
        System.out.println("**********************");

        //purchase方法测试
        //正常购买
        Booth.purchase(booths[0],20);
        //缺货情况
        Booth.purchase(booths[0],20);
        //休摊情况
        Booth.purchase(booths[1],1);
        //买0个
        Booth.purchase(booths[2],0);
        //买负数个
        Booth.purchase(booths[2],-999);
        Booth.purchase(booths[3],-9);
        //正常购买
        Booth.purchase(booths[3],99);
        Booth.purchase(booths[3],400);
        Booth.purchase(booths[4],528);
        Booth.purchase(booths[4],400);
        System.out.println("**********************");

        //restock方法测试
        //正常测试
        booths[0].restock(100);
        booths[0].restock(200);
        booths[1].restock(1);

        //非正常
        booths[0].restock(201);
        booths[0].restock(-1);
        booths[0].restock(0);
        booths[0].restock(999);
        booths[0].restock(-999);
        System.out.println("**********************");


        //closeBooths方法测试
        Booth.closeBooths(booths);
        System.out.println("**********************");
        Booth.closeBooths(booths);

    }
}
