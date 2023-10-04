package com.Jay.work1;


public class BoothTest {
    public static void main(String[] args) {
        //例子
        Booth booth1 = new Booth(1, "周杰伦", 100, false);
        Booth booth2 = new Booth(2, "林俊杰", 50, false);

        Booth.purchase(booth1, 80);
        Booth.purchase(booth1, -10);
        Booth.purchase(booth1, 120);
        Booth.purchase(booth1, 50);

        booth1.restock(150);
        booth1.restock(250);

        Booth[] booths = {booth1, booth2};
        Booth.closeBooths(booths);
    }
}
