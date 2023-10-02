public class BoothTest {
    public static void main(String[] args) {
        Booth boothWatermelon = new Booth(0, "Woooooooooooooooter Melon!", 5, false);
        Booth boothMuskmelon = new Booth(0, "Maaaaaaaaaaaaaaaask Melon!", 55555, true);
        Booth boothPumpkin = new Booth(0, "Pump Kinnnnnnnnnnnng!", 23333, false);

        Booth.purchase(boothWatermelon, 10); //Output: Purchase failed: Count is out of bound!
        Booth.purchase(boothWatermelon, 5); //Output: Purchase successfully!
        Booth.purchase(boothWatermelon, -10); //Output: Purchase failed: Count is out of bound!

        Booth.purchase(boothMuskmelon, 5); //Output: Purchase failed: Booth is closed!

        boothMuskmelon.restock(10); //Operation succeeded without output.
        boothMuskmelon.restock(2000); //Output: Restock failed: Count is out of bound!


        Booth.closeBooths(new Booth[]{boothWatermelon, boothMuskmelon, boothPumpkin});
        //Output:
        //Id: 0; Name: Woooooooooooooooter Melon!; Total: 0; IsClosed: true
        //Id: 0; Name: Pump Kinnnnnnnnnnnng!; Total: 23333; IsClosed: true

    }
}
