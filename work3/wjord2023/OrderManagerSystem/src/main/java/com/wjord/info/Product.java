package com.wjord.info;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class Product {
    // 验证条形码格式
    @Pattern(regexp = "^[0-9]{13}$", message = "产品号格式错误")
    private String productCode;
    private String productName;
    @Min(value = 0, message = "价格不能为负数")
    private BigDecimal productPrice;
    @Min(value = 0, message = "库存不能为负数")
    private int productStock;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    @Override
    public String toString() {
        return "Product{" + "productCode='" + productCode + '\'' + ", productName='" + productName + '\'' + ", productPrice=" + productPrice + ", productStock=" + productStock + ", createTime=" + createTime + ", updateTime=" + updateTime + '}';
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
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
