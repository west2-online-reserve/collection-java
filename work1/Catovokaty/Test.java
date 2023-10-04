
public class Test {
    public static void main(String[] args) {
        Booth boothA = new Booth(1, "摊位A", 100, false);
        Booth boothB = new Booth(2, "摊位B", 150, false);

        System.out.println("初始摊位信息:");
        System.out.println(boothA.toString());
        System.out.println(boothB.toString());

        Booth.purchase(boothA, 20);
        Booth.purchase(boothB, 30);

        System.out.println("购买后摊位信息:");
        System.out.println(boothA.toString());
        System.out.println(boothB.toString());

        boothA.restock(50);
        boothB.restock(80);

        System.out.println("进货后摊位信息:");
        System.out.println(boothA.toString());
        System.out.println(boothB);

        Booth[] booths = {boothA, boothB};
        Booth.closeBooths(booths);

        System.out.println("休业整改后摊位信息:");
        System.out.println(boothA.toString());
        System.out.println(boothB.toString());
    }
}