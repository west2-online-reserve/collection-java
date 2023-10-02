package work1;

public class Operation {
    public static void purchase(Booth booth,int out) {
        if (out > 0 && out <= booth.getTotal() && !booth.isClosed()) {
            int num = booth.getTotal() - out;
            booth.setTotal(num);
            System.out.println("已成功购买" + out +"个西瓜");
        } else if (out < 0){
            System.out.println("购买量不可为负数,购买失败");
        } else if (out > booth.getTotal()){
            System.out.println("购买量大于总数,购买失败");
        } else if (booth.isClosed()){
            System.out.println("该摊位已休业整改,购买失败");
        }

    }

    public void restock(Booth booth,int in) {
        if (in > 0 && in <= 200) {
            int num = booth.getTotal() + in;
            booth.setTotal(num);
            System.out.println("已成功进货" + in + "个西瓜");
        } else if (in > 200){
            System.out.println("进货量不可超过200,进货失败");
        } else {
            System.out.println("进货量必须为正数,进货失败");
        }
    }

    public static void closeBooths(Booth[] booths) {
        for (int i = 0; i < booths.length; i++) {
            if (booths[i].isClosed()) {
                System.out.println(booths[i]);
            } else {
                booths[i].setClosed(false);
            }

        }
    }
}
