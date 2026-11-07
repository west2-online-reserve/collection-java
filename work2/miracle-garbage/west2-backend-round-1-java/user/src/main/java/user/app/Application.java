package user.app;

import core.data.JsonData;
import core.lib.ReadData;

public class Application {
    // 初始化程序
    private JsonData data;

    public Application() {
        this.data = new JsonData();
    }

    public void loadData() {
        this.data = ReadData.readData();
    }

    public JsonData getData() {
        return data;
    }
}