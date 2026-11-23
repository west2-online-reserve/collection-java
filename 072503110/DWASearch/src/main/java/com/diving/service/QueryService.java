package com.diving.service;

import com.diving.model.Player;

import java.util.ArrayList;
import java.util.List;


public class QueryService {

    private final List<Player> allData;

    public QueryService(List<Player> allData) {
        this.allData = allData;
    }

    // 获取所有选手
    public List<Player> getAllPlayers() {
        List<Player> resultList = new ArrayList<>();
        for (Player p : allData) {
            if ("player".equals(p.getType())) {
                resultList.add(p);
            }
        }
        return resultList;
    }

    // 按项目获取成绩
    public List<Player> getResultsByProject(String projectName) {
        List<Player> resultList = new ArrayList<>();
        
        // 1. 先过滤出需要的项目
        for (Player p : allData) {
            if ("result".equals(p.getType())) {
                if (p.getEvent().equalsIgnoreCase(projectName)) {
                    resultList.add(p);
                }
            }
        }
        
        // 2. 对挑出来的结果进行（冒泡排序）（按 rank 升序）
        for (int i = 0; i < resultList.size() - 1; i++) {
            for (int j = 0; j < resultList.size() - 1 - i; j++) {
                
                Player p1 = resultList.get(j);     
                Player p2 = resultList.get(j + 1); 
                
                
                if (p1.getRank() > p2.getRank()) {
                    resultList.set(j, p2);    
                    resultList.set(j + 1, p1); 
                }
            }
        }
        
        return resultList;
    }
}