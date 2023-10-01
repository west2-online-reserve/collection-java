package Watermelon;

public class Method {
    public static void purchase(Booth booth, int quantity) {
        if (quantity <= 0) {
            System.out.println("购买的西瓜数量必须为正数！");
            return;
        }
        if (booth.isClosed()) {
            System.out.println("该摊位已经休摊！");
            return;
        }
        if (quantity > booth.getTotal()) {
            System.out.println("购买的西瓜数量不能大于在售西瓜数量！");
            return;

        }
        booth.setTotal(booth.getTotal() - quantity);
        System.out.println("购买成功,摊主是：" + booth.getName() + "，剩余在售西瓜数：" + booth.getTotal());

    }
    public void restock(int quantity){
        if (quantity>200){
            System.out.println("进货失败，单次进货量不能超过 200");
        }else {


        }
    }
    public static void closeBooths(Booth[] booths){
        for (Booth booth:booths){
            if (!booth.isClosed()) {
                booth.setClosed(true);
                booth.toString();
            }
        }
    }
}
