package core;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class CoreModuleTest {
    @Test
    void testDisplayAllPlayersInfo() throws IOException {
        String input = "input.txt";
        String output = "output.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(input))) {
            // 示例数据，需求修改
            String content= "player\n" +
                    "Players\n" +
                    "resultwomen 1m springboard\n" +
                    "result women 10m springboard\n" +
                    "result sss\n" +
                    "result detail\n" +
                    "result women 1m springboard details\n" +
                    "result men 10m     synchronised\n" +
                    "players" ;
            bw.write(content);
            // 关闭流
        } // try-with-resources 会自动关闭资源
    }

    @Test//测试输出决赛结果
    void testDisplayResults() {
        CoreModule coreModule = new CoreModule();
        coreModule.displayResults("women 1m springboard");
    }

    @Test
//测试输出详细信息
    public  void testDisplayDetailedResults(){
        CoreModule coreModule = new CoreModule();
        coreModule.displayDetailedResults("women 10m platform");

    }
}
