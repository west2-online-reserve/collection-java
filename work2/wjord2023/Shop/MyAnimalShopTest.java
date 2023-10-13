package Shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MyAnimalShopTest {
    //我的main程序是可以运行的，所以这个test没有太认真写，有点问题，主要看main吧
    private MyAnimalShop myAnimalShop;

    @BeforeEach
    void setUp() {
        // 初始化测试之前的环境
        LinkedList listOfAnimal = new LinkedList();
        Map<String, ThreeInfo> listOfCustomer = new HashMap<>();
        myAnimalShop = new MyAnimalShop(1000, listOfAnimal, (HashMap) listOfCustomer, true);
    }

    @Test
    void purchaseChinesePastoralDogs() {
        // 模拟输入
        String input = "1\n小花\n3\nboy\n傻\nN\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        // 调用方法
        myAnimalShop.purchase();
        assertEquals(900, myAnimalShop.getOverage());
        assertEquals(1, myAnimalShop.getListOfAnimal().size());
    }

    @Test
    void serveCustomer() {
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        myAnimalShop.serveCustomer();

    }

}
