package animalshop;

import java.time.LocalDate;

/**
 * 客户类
 */
public class Customer {

    // 定义成员变量：姓名、到店次数、最新到店时间
    public String customerName;
    public int times;
    public LocalDate newestTime;

    /**
     *
     * @return 顾客信息
     */
    @Override
    public String toString() {
        System.out.println("顾客的姓名为："+customerName);
        System.out.println("该顾客的到店次数为："+times);
        System.out.println("该顾客的最新到店时间为："+newestTime);
        return "Name"+customerName+"times"+times+"newestTime"+newestTime;
    }


}
