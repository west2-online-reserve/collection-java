
    public class Application {
        public static void main(String[] args) {
            Booth booth1=new Booth(12345,"Lili",150,false);
            booth1.purchase(booth1,150);//购买成功
            booth1.purchase(booth1,-20);//购买失败，因为购买数量小于0
            booth1.purchase(booth1,200);//购买失败，因为购买西瓜数大于在售西瓜数
            booth1.setIsClosed(true);//购买失败，因为处于休摊状态
            System.out.println(booth1.getTotal());//0

            Booth booth2=new Booth(23456,"Jack",160,true);
            booth2.restock(booth2,170);//进货成功
            booth2.restock(booth2,300);//进货失败
            booth2.restock(booth2,-50);//进货失败
            System.out.println(booth2.getTotal());//330

            Booth[] booths ={booth1,booth2};
            booth1.closeBooths(booths);//输出booth1，booth2的信息
        }
    }












