package com.wjord.info;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class Order {
    @Pattern(regexp = "^[0-9]{13}$", message = "订单号格式错误")
    private String orderCode;

    @Pattern(regexp = "^[0-9]{13}$", message = "产品号格式错误")
    private String productCode;

    @Pattern(regexp = "1[3-9]\\\\d{9}$", message = "请输入正确的手机号码")
    private String buyerPhone;
    @Min(value = 0, message = "数量不能为负数")
    private int orderAmount;
    @Min(value = 0, message = "价格不能为负数")
    private BigDecimal orderPrice;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /*
    TODO:我希望可以再打印订单信息的同时打印出买家信息和产品信息,原先想法是改tostring
     但是如果我在这里注入BuyerService和ProductService会报错，可能后面再controller层进行处理应该比较容易
     */
    @Override
    public String toString() {
        return "Order{" + "orderCode='" + orderCode + '\'' + ", productCode='" + productCode + '\'' + ", buyerPhone='" + buyerPhone + '\'' + ", orderAmount=" + orderAmount + ", orderPrice=" + orderPrice + ", createTime=" + createTime + ", updateTime=" + updateTime + '}';
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }


    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
