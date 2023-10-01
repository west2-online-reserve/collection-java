public class test {
        public static void main(String[] args) {
            Booth b1 = new Booth(001, "Mike", 200, false);
            Booth b2 = new Booth(002, "David", 2002, true);
            Booth b3 = new Booth(003, "Lucy", 2300, false);
            Booth b4 = new Booth(004, "Alice", 2040, true);
            Booth b5 = new Booth(005, "Hellen", 2800, false);
            Booth[] booths = {b1, b2, b3, b4, b5,};

            System.out.println(b1);
            System.out.println("-----------------------------------");
            b1.purchase(b1,100);
            b2.purchase(b2,300);
            b3.purchase(b3,-300);
            b4.purchase(b4,300);
            System.out.println("-----------------------------------");
            b3.restock(b3,30);
            b3.restock(b3,-30);
            b3.restock(b3,2000);
            System.out.println("-----------------------------------");
        Booth.closeBooths(booths);
        }
    }
