package com.west2.work;

public class BoothTest {
    public static void main(String[] args) {
        System.out.println("进行get与set方法测试：");
        Booth homo1 = new Booth(1, "homo1", 10, false);
        System.out.println(homo1.getId());
        System.out.println(homo1.getName());
        System.out.println(homo1.getTotal());
        System.out.println(homo1.isClosed());
        System.out.println("---------------------------------------");//分割线看的更清楚
        System.out.println(homo1.toString());


        homo1.setId(2);
        homo1.setName("Hao2");
        homo1.setTotal(114);
        homo1.setClosed(true);
        System.out.println(homo1.toString());


        System.out.println("进行purchase方法测试：");
        Booth homo2 = new Booth(3,"Seipai",451,false);
        Booth.purchase(homo2,-1);
        Booth.purchase(homo2,23);
        Booth.purchase(homo1,12);
        Booth.purchase(homo2,12);

        System.out.println("\n进行restock方法测试：");
        homo2.restock(homo2,100);
        homo2.restock(homo2,-12);
        homo2.restock(homo2,1145);

        System.out.println("\n最后进行closeBooths方法的测试：");
        Booth [] booth=new Booth[2];
        booth[0]=homo1;
        booth[1]=homo2;
        Booth.closeBooths(booth);


    }
}
