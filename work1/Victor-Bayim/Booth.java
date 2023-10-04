import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        this.melonsInStock += amount;
        System.out.println("Restock successful! Booth now has " + this.melonsInStock + " melons.");
    }

    // 静态方法关闭摊位
    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (!booth.isClosed) {
                booth.isClosed = true;
                System.out.println("Booth: " + booth + " is now closed.");
            } else {
                System.out.println("Booth: " + booth + " is already closed.");
            }
        }
    }

    // 用户互动的主函数
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Booth> boothList = new ArrayList<>();

        while (true) {
            // 用户输入摊位详细信息
            System.out.print("Enter Booth ID: ");
            while (!scanner.hasNextLong()) {
                System.out.println("Invalid input. Please enter a valid Booth ID.");
                scanner.next();
            }
            long id = scanner.nextLong();

            System.out.print("Enter Owner Name: ");
            scanner.nextLine();  // Clear the previous input
            String ownerName = scanner.nextLine();

            System.out.print("Enter number of melons in stock: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number of melons.");
                scanner.next();
            }
            int melonsInStock = scanner.nextInt();

            System.out.print("Is the booth closed? (true/false): ");
            while (!scanner.hasNextBoolean()) {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
                scanner.next();
            }
            boolean isClosed = scanner.nextBoolean();

            // 根据用户输入创建摊位并添加到列表
            Booth booth = new Booth(id, ownerName, melonsInStock, isClosed);
            boothList.add(booth);
            System.out.println("Booth created: " + booth);

            // 询问用户是否继续输入
            System.out.print("\nDo you want to enter another booth? (yes/no): ");
            scanner.nextLine();  // Clear the previous input
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }

        // 测试购买功能
        System.out.print("\nChoose a booth ID to purchase from: ");
        while (!scanner.hasNextLong()) {
            System.out.println("Invalid input. Please enter a valid Booth ID.");
            scanner.next();
        }
        long chosenId = scanner.nextLong();

        System.out.print("Enter number of melons to purchase: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number of melons to purchase.");
            scanner.next();
        }
        int purchaseAmount = scanner.nextInt();
        for (Booth b : boothList) {
            if (b.getId() == chosenId) {
                Booth.purchase(b, purchaseAmount);
                System.out.println(b);  // 显示摊位信息
                break;
            }
        }

        // 测试进货功能
        System.out.print("\nChoose a booth ID to restock: ");
        while (!scanner.hasNextLong()) {
            System.out.println("Invalid input. Please enter a valid Booth ID.");
            scanner.next();
        }
        chosenId = scanner.nextLong();

        System.out.print("Enter number of melons to restock: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number of melons to restock.");
            scanner.next();
        }
        int restockAmount = scanner.nextInt();
        for (Booth b : boothList) {
            if (b.getId() == chosenId) {
                b.restock(restockAmount);
                System.out.println(b);  // 显示摊位信息
                break;
            }
        }

        // 关闭摊位
        System.out.println("\nClosing all booths...");
        Booth[] booths = boothList.toArray(new Booth[0]);
        Booth.closeBooths(booths);

        // 显示所有摊位信息
        System.out.println("\nAll booths:");
        for (Booth b : boothList) {
            System.out.println(b);
        }

        scanner.close();
    }
}