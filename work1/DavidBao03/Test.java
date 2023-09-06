public class Test {
    public static void main(String[] args) {
        Booth b1 = new Booth(1,"David", 200, true);
        Booth b2 = new Booth(34,"Dav", 2002, true);
        Booth b3 = new Booth(90,"Daid", 2300, false);
        Booth b4 = new Booth(121,"avid", 2040, true);
        Booth b5 = new Booth(291,"aavid", 2800, false);
        Booth b6 = new Booth(321,"vid", 2002, true);
        Booth[] booths = {b1, b2, b3, b4, b5, b6};

        System.out.println(b1);
        System.out.println("-----------------------------------");
        purchase(b1, 300);
        purchase(b3, 300);
        purchase(b1, -300);
        purchase(b2, 300);
        System.out.println("-----------------------------------");
        b3.restock(30);
        b3.restock(-30);
        b3.restock(2000);
        System.out.println("-----------------------------------");
        closeBooths(booths);
    }

    public static void purchase(Booth seller, int num){
        if(!seller.isClosed()){
            System.out.println("商家已打烊，购买失败。");
            return;
        }

        if(num < 0){
            System.out.println("购买数必须大于0，购买失败。");
            return;
        }

        if(num > seller.getTota()){
            System.out.println("购买数不能超过商家所持有的西瓜数，购买失败。");
            return;
        }

        System.out.println("购买成功。");
    }

    public static void closeBooths(Booth[] booths){
        for (Booth booth : booths) {
            if(booth.isClosed()) booth.setClosed(false);
            else System.out.println(booth);
        }
    }
}
