package org.example.service;

import org.example.pojo.Commodity;

import java.io.IOException;
import java.util.List;

public interface CommodityService {
    void add(String name,double price) throws IOException;
    List<Commodity> queryAll();
    void deleteById(int id);
    List<Commodity> queryAllShop();
    Commodity queryById(int id);

}
