package com.amiera.utils;

import com.amiera.objects.orders;
import com.amiera.objects.products;

//这是一个订单价格检查工具类，用于验证订单信息是否符合要求
public class ValidationUtils {

    // 验证订单信息
    public static boolean validateOrderInfo(orders order, products product) {
        // 验证商品是否存在
        if (product == null) {
            throw new IllegalArgumentException("商品不存在，product_id: " + order.getProduct_id());
        }

        // 验证价格
        if (order.getOrder_price() <= 0) {
            throw new IllegalArgumentException("订单价格必须大于0: " + order.getOrder_price());
        }

        // 验证价格精度（假设保留两位小数）
        if (!validatePricePrecision(order.getOrder_price())) {
            throw new IllegalArgumentException("订单价格精度错误: " + order.getOrder_price());
        }

        // 验证订单信息
        if (order.getOrder_information() == null || order.getOrder_information().trim().isEmpty()) {
            throw new IllegalArgumentException("订单信息不能为空");
        }

        // 验证订单信息长度
        if (order.getOrder_information().length() > 100) {
            throw new IllegalArgumentException("订单信息过长，最大100字符");
        }

        // 验证订单价格与商品价格是否匹配（可选）
        if (order.getOrder_price() != product.getPrice()) {
            throw new IllegalArgumentException("订单价格与商品价格不匹配");
        }

        return true;
    }

    // 验证价格精度
    private static boolean validatePricePrecision(double price) {
        // 检查是否有超过两位小数
        String priceStr = String.valueOf(price);
        int dotIndex = priceStr.indexOf('.');
        if (dotIndex != -1 && priceStr.length() - dotIndex - 1 > 2) {
            return false;
        }
        return true;
    }
}
