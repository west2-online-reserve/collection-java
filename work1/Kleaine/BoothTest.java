package com.Jay.work1;


public class BoothTest {
    public static void main(String[] args) {
        Booth booth1 = new Booth(1, "周杰伦", 100, false);
        Booth booth2 = new Booth(2, "林俊杰", 50, false);

        Booth.purchase(booth1, 80);
        booth1.restock(50);
        Booth.purchase(booth2, 100);

        Booth[] booths = {booth1, booth2};
        Booth.closeBooths(booths);
    }
}