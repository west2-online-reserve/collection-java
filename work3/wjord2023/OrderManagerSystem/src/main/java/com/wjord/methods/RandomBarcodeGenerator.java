package com.wjord.methods;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Description 生成随机条形码
 * @Author WJORD
 */

public class RandomBarcodeGenerator {

    public String generateRandomBarcode() {
        Random random = new Random();
        StringBuilder barcode = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            barcode.append(random.nextInt(10));
        }
        return barcode.toString();
    }
}
