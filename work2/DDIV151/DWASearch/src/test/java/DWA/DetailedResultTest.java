package DWA;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DetailedResultTest {

    @Test()
    void testToString() {
        //测试抛出异常
        try {
            DetailedResult detailedResult = new DetailedResult(new Result(), new ArrayList<Result>());
            DetailedResult de = new DetailedResult(new Result(), null);
        }catch (Exception e) {
            Assert.assertEquals("比赛列表不应为空",e.getMessage());
        }
        //测试输出
        DetailedResult detailedResult = new DetailedResult(new Result(), new ArrayList<>());
    }
}
