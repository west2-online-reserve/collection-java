package com.diving;

import com.diving.model.Player;
import com.diving.service.QueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueryServiceTest {

    private QueryService queryService;

    @BeforeEach
    void setUp() {
        List<Player> mockData = new ArrayList<>();
        // 假设选手数据
        mockData.add(new Player("CHEN Yiwen", "Female", "China"));
        mockData.add(new Player("CHANG Yani", "Female", "China"));
        
        // 假设成绩记录
        List<Double> scores1 = List.of(72.00, 74.80, 75.60);
        mockData.add(new Player("women 3m springboard", 1, "CHEN Yiwen", scores1, 374.40));

        List<Double> scores2 = List.of(71.40, 72.00, 73.50);
        mockData.add(new Player("women 3m springboard", 2, "CHANG Yani", scores2, 373.05));

        queryService = new QueryService(mockData);
    }

    @Test
    @DisplayName("测试获取所有选手列表")
    void testGetAllPlayers() {
        List<Player> players = queryService.getAllPlayers();
        
        // 断言1：总共 4 条数据，过滤掉成绩后，应该只剩 2 个选手
        assertEquals(2, players.size());
    }

    @Test
    @DisplayName("测试按项目查询成绩，并验证排序逻辑")
    void testGetResultsByProject() {
        //这里输入大写，测试是否能忽略大小写匹配
        List<Player> results = queryService.getResultsByProject("WOMEN 3M SPRINGBOARD");

        // 断言2：应该查出 2 条成绩记录
        assertEquals(2, results.size());
        
        // 断言3：核心逻辑验证 排序后的第一个人，必须是第一名（陈艺文）
        assertEquals("CHEN Yiwen", results.get(0).getName());
        assertEquals(1, results.get(0).getRank());
    }

    @Test
    @DisplayName("测试查询不存在的项目")
    void testGetResultsByProject_NotFound() {
        List<Player> results = queryService.getResultsByProject("unknown project");
        
        // 断言4：查询不存在的数据，应该返回空列表
        assertTrue(results.isEmpty());
    }
}