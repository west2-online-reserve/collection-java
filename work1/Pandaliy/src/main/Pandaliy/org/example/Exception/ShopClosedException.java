package org.example.Exception;

public class ShopClosedException extends RuntimeException {
    public ShopClosedException() {
        super("商店已歇业！\n");
    }
}
