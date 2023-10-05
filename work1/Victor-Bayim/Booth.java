public class Booth {
    // 私有变量
    private long id;
    private String ownerName;
    private int melonsInStock;
    private boolean isClosed;

    // 构造函数
    public Booth(long id, String ownerName, int melonsInStock, boolean isClosed) {
        this.id = id;
        this.ownerName = ownerName;
        this.melonsInStock = melonsInStock;
        this.isClosed = isClosed;
    }

    // getter 和 setter 方法
    public long getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getMelonsInStock() {
        return melonsInStock;
    }

    public boolean isClosed() {
        return isClosed;
    }

    // 重写 toString 方法
    @Override
    public String toString() {
        return "Booth ID: " + id + ", Owner Name: " + ownerName + ", Melons in Stock: " + melonsInStock + ", Is Closed: " + isClosed;
    }

    // 静态方法购买西瓜
    public static void purchase(Booth seller, int amount) {
        if (amount <= 0) {
            System.out.println("Purchase failed: Amount must be positive.");
            return;
        }

        if (seller.isClosed) {
            System.out.println("Purchase failed: The booth is currently closed.");
            return;
        }

        if (amount > seller.melonsInStock) {
            System.out.println("Purchase failed: Desired amount exceeds available stock.");
            return;
        }

        seller.melonsInStock -= amount;
        System.out.println("Purchase successful! Booth now has " + seller.melonsInStock + " melons left.");
    }

    // 进货方法
    public void restock(int amount) {
        if (amount > 200) {
            System.out.println("Restock failed: Can't restock more than 200 melons at once.");
            return;
        }
        if (amount < 0) {
            System.out.println("Restock failed: Can't restock a negative amount of melons.");
            return;
        }

        this.melonsInStock += amount;
        System.out.println("Restock successful! Booth now has " + this.melonsInStock + " melons.");
    }

    // 静态方法关闭摊位
    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.isClosed) {
                booth.isClosed = true;
                System.out.println(booth); // 使用toString()直接输出摊位信息
            } else {
                System.out.println(booth + " (Already closed)"); // 如果摊位已关闭，再加上一个提示
            }
        }
    }


}