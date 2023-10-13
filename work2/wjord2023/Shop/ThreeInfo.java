package Shop;

import java.time.LocalDateTime;

public class ThreeInfo {
    private final String info1;
    private final int info2;
    private final LocalDateTime info3;

    public ThreeInfo(String info1, int info2, LocalDateTime info3) {
        this.info1 = info1;
        this.info2 = info2;
        this.info3 = info3;
    }

    public String getInfo1() {
        return info1;
    }

    public int getInfo2() {
        return info2;
    }

    public LocalDateTime getInfo3() {
        return info3;
    }

    @Override
    public String toString() {
        return "\n顾客姓名为：" + info1 +
                "\n到店次数为：" + info2 +
                "\n最新到店时间为：" + info3 + "\n";
    }
}
