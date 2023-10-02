
public class Test {
    public static void main(String[] args) {
    stall booth01 = new stall(1, "小王", 100, false);
    stall booth02 = new stall(2, "中王", 200, true);
    stall booth03 = new stall(3, "大王", 300, false);
    stall booth04 = new stall(4, "超大王", 400, false);
    stall booth05 = new stall(5, "超级大王", 500, true);
    stall booth06 = new stall(6, "老王", 600, false);
        System.out.println("======一下是booth01的信息======");
        System.out.println(booth01);
        booth01.restock(150);
        stall.purchase(booth01, 50);
        System.out.println("======一下是booth02的信息======");
        System.out.println(booth02);
        booth02.restock(-100);
        stall.purchase(booth02,0);
        System.out.println("======一下是booth03的信息======");
        System.out.println(booth03);
        booth03.restock(300);
        stall.purchase(booth03,300);
        System.out.println("======一下是booth04的信息======");
        System.out.println(booth04);
        booth04.restock(200);
        booth04.purchase(booth04,700);
        System.out.println("======一下是booth05的信息======");
        System.out.println(booth05);
        booth05.restock(100);
        booth05.purchase(booth05,-1);
        System.out.println("======一下是booth06的信息======");
        System.out.println(booth06);
        booth06.restock(1);
        booth06.purchase(booth06,200);
        stall[] booths={booth01,booth02,booth03,booth04,booth05,booth06};
        stall.closeBooths(booths);
        System.out.println("=======全体关店整改后=====");
        System.out.println(booth01);
        System.out.println(booth02);
        System.out.println(booth03);
        System.out.println(booth04);
        System.out.println(booth05);
        System.out.println(booth06);
}
}
