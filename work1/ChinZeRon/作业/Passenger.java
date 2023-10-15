package homework;
public class Passenger {
    public static void main(String[] args) {
        Passenger passenger = new Passenger();
        Booth booth = new Booth(102301438, "陈泽荣", 200, false);
        Booth booth1 = new Booth(1, "陈", 20, false);
        Booth booth2 = new Booth(2, "泽", 20, true);
        Booth booth3 = new Booth(3, "荣", 20, true);
        Booth nums1[] = new Booth[]{booth,booth1,booth2,booth3};//测试数据，创建数组
        booth.restock(booth,200);//西瓜摊进货
        passenger.purchase(booth, 20);//顾客购买
        passenger.closebooths(nums1);//全部休业整顿
    }
    //purchase方法
    public static void purchase(Booth name, int purchaseTotal) {
        if (purchaseTotal <= 0) {
            System.out.println("交易失败！请输入正确的购买数量！");
        } else {
            if (name.getIsClosed() == true) {
                System.out.println("交易失败！店铺正处于休业整顿！");
            } else {
                if (purchaseTotal > name.getTotal()) {
                    System.out.println("交易失败！购买数量大于店铺库存！");
                    System.out.println("店铺剩余的西瓜数量为：" + name.getTotal());
                } else {
                    name.setTotal(name.getTotal() - purchaseTotal);
                    System.out.println("恭喜您！您的交易成功！");
                    System.out.println("店铺剩余的西瓜数量为：" + name.getTotal());
                }
            }
        }
    }
    //closebooths方法
    public static void closebooths(Booth[] booths){
        for (int i = 0; i < booths.length; i++) {
            if(booths[i].getIsClosed() == false){
                booths[i].setIsClosed(true);
            }else {
                String string = booths[i].toString();
                System.out.println(string);
            }
        }
    }
}


