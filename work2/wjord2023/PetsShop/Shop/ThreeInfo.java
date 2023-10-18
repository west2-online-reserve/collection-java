package Shop;

import java.time.LocalDateTime;

public class ThreeInfo {
    // 这个包提供一个public的方法以储存3个变量使得字典可以储存3个value
    private final String infoName;
    private final int infoTimeArrival;
    private final LocalDateTime infoLatestTime;

    public ThreeInfo(String infoName, int infoTimeArrival, LocalDateTime infoLatestTime) {
        this.infoName = infoName;
        this.infoTimeArrival = infoTimeArrival;
        this.infoLatestTime = infoLatestTime;
    }

    public String getInfoName() {
        return infoName;
    }

    public int getInfoTimeArrival() {
        return infoTimeArrival;
    }

    public LocalDateTime getInfo3() {
        return infoLatestTime;
    }

    @Override
    public String toString() {
        return "\n顾客姓名为：" + infoName +
                "\n到店次数为：" + infoTimeArrival +
                "\n最新到店时间为：" + infoLatestTime + "\n";
    }
}
