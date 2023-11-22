package com.wjord.dao;

import com.wjord.info.Buyer;

import java.util.List;

public interface BuyerDao {
    void insertBuyer(Buyer buyer);

    void deleteBuyerByPhone(String buyerPhone);

    void updateBuyerByPhone(String buyerPhone, Buyer buyer);

    void updateBuyerByName(String buyerName, Buyer buyer);

    Buyer selectBuyerByName(String buyerName);

    Buyer selectBuyerByPhone(String buyerPhone);

    List<Buyer> selectAllBuyers();

    int selectTotalBuyerCount();

    int selectBuyerCountByName(String buyerName);
}
