package com.diving.service;

import com.diving.model.Player;
import java.util.List;


//数据抓取服务接口
public interface DataFetchService {
    
    //选手列表
    List<Player> fetchData();
}