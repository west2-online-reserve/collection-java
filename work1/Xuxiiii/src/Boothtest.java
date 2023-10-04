public class Boothtest {
    public static void main(String[] args) {
        Booth booth1 = new Booth(1, "yuanshen", 100, true);
        Booth booth2 = new Booth(114, "dio", 115, false);
        Booth booth3 = new Booth(7859, "Xuxiiii", 188, true);
        Booth[] booths = {booth1, booth2, booth3,};


        for (Booth b : booths) {
            System.out.println(b);


            Booth.purchase(booth1, 20);
            Booth.purchase(booth2, 80);
            Booth.purchase(booth3, 80);


            booth1.restock(50);
            booth2.restock(300);
            booth3.restock(70);


            Booth.closeBooth(new Booth[]{booth1, booth2, booth3,});


            for (Booth c : booths) {
                System.out.println(c);
            }
        }
    }
}
