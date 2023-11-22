package com.wjord.service;

import com.wjord.info.Buyer;

import java.util.List;

public interface BuyerService {
    void insertBuyer(String buyerPhone, String buyerName, String buyerAddress);

    void updateBuyerByPhone(String buyerPhone, String buyerName, String buyerAddress);

    void deleteBuyer(String buyerPhone);

    Buyer selectBuyerByPhone(String buyerPhone);

    Buyer selectBuyerByName(String buyerName);

    List<Buyer> selectAllBuyers();

    int selectTotalBuyerCount();

    int selectBuyerCountByName(String buyerName);
}
