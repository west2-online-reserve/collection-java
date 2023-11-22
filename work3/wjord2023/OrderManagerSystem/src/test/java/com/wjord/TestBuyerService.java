package com.wjord;

import com.wjord.config.Config;
import com.wjord.service.BuyerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Component
@SpringJUnitConfig(classes = Config.class)
public class TestBuyerService {
    @Autowired
    private BuyerService buyerService;

    @Test
    public void testInsertBuyer() {
        buyerService.insertBuyer("12345678901", "阿狸", "艾欧尼亚");
        buyerService.insertBuyer("114", "格温", "暗影岛");
        buyerService.insertBuyer("13455678901", "亚索", "艾欧尼亚");
        buyerService.insertBuyer("13455678901", "永恩", "艾欧尼亚");
        buyerService.insertBuyer("15205678902", "永恩", "艾欧尼亚");
        buyerService.insertBuyer("15234356709", "凯特琳", "皮尔特沃夫");
        buyerService.insertBuyer("18896898798", "大头", "皮尔特沃夫");
        buyerService.insertBuyer("15899079800", "盖伦", "德玛西亚");
        buyerService.insertBuyer("15344459810", "盖伦", "德玛西亚");
        buyerService.insertBuyer("15344466666", "拉克丝", "德玛西亚");
    }


    @Test
    public void testUpdateBuyerInfo() {
        buyerService.updateBuyerByPhone("18896898798", "黑默丁格", "皮尔特沃夫");
        buyerService.updateBuyerByPhone("18896898798", null, null);
        buyerService.updateBuyerByPhone(null, "黑默丁格", "皮尔特沃夫");
    }

    @Test
    public void testDeleteBuyerInfo() {
        buyerService.deleteBuyer("13455678901");
        buyerService.deleteBuyer("15205678902");
        buyerService.deleteBuyer("15234356709");
        buyerService.deleteBuyer("18896898798");
    }

    @Test
    public void testSelectBuyerInfo() {
        System.out.println(buyerService.selectBuyerByPhone("13455678901"));
        System.out.println(buyerService.selectBuyerByName("盖伦"));
        System.out.println(buyerService.selectBuyerByName("亚索"));
    }

    @Test
    public void testSelectAllBuyers() {
        System.out.println(buyerService.selectAllBuyers());
    }

    @Test
    public void testSelectTotalBuyerCount() {
        System.out.println(buyerService.selectTotalBuyerCount());
    }

}
