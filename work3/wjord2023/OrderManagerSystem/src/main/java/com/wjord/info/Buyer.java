package com.wjord.info;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Buyer {
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{8}$", message = "请输入正确的手机号码")
    @NotBlank(message = "手机号不能为空")
    private String buyerPhone;
    private String buyerName;
    private String buyerAddress;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /*
    保密措施，保密买家姓名
     */
    public static String encryptName(String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            if (i >= 1 && i <= 2) {
                sb.append('*');
            } else {
                sb.append(name.charAt(i));
            }
        }
        return sb.toString();
    }

    /*
    保密措施，保密买家电话
     */
    public static String encryptPhone(String phone) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < phone.length(); i++) {
            if (i >= 3 && i <= 6) {
                sb.append('*');
            } else {
                sb.append(phone.charAt(i));
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Buyer{" + "buyerPhone='" + encryptPhone(getBuyerPhone()) + '\'' + ", buyerName='" + encryptName(getBuyerName()) + '\'' + ", buyerAddress='" + buyerAddress + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime + '}';
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
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
