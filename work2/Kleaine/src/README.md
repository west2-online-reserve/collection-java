
##  项目简介

DWASearch 是一个比赛数据查询系统，通过解析 JSON 文件输出选手列表及比赛成绩信息。

支持指令：

```
players
result men 1m springboard
result men 1m springboard detail
```

程序读取 `athletes.json` 和对应比赛 JSON 文件，将结果输出到 `output.txt`。

---

##  系统架构

项目分为三个主要模块：

| 模块           | 作用         |
| ------------ | ---------- |
| `CoreModule` |  API   |
| `Lib`        | JSON 解析、工具类|
| `DWASearch`  | 命令行入口，输入输出处理 |


---

## 核心模块接口设计与实现

### CoreModule 对外 API

```java
public String getAllPlayersInfo();
public String getEventResult(String command);
```

### players 指令处理流程

1. 加载 athletes.json
2. 解析所有选手
3. 排序（国籍 + 姓名）
4. 拼接输出

### result 指令流程（含 detail）

1. 解析指令（标准化空格、识别 detail、校验赛事名）
2. 加载 <event>.json 并解析 Heats / Results
3. 按 TotalPoints 从高到低排序
4. 拼接输出 Full Name / Rank / Country / TotalPoints
   若包含 detail，则追加 DiveOrder / DiveNo / DD / DivePoints


---

##  性能优化设计
* 性能浪费：I/O流的重复启动
* 优化重点：**JSON 文件仅加载一次，避免重复 I/O**

### 优化前问题

* 每次 `players` 或 `result` 调用都重新读取文件
* 浪费 I/O，测试运行慢

### 优化后策略：使用静态缓存

```java
private static List<Lib.Player> cachedPlayers;

if (cachedPlayers == null) {
    cachedPlayers = Lib.loadPlayersFromJson("data/athletes.json");
}
```

##  单元测试

核心测试文件：`DWASearchTest.java`

### 测试：`部分内容`

```java
package com.jay.work2;

import org.junit.Test;
import static org.junit.Assert.*;

public class DWASearchTest {
    CoreModule core = new CoreModule();

    // 测试用例1：合法 players 指令
    @Test
    public void testValidPlayersCommand() {
        String playersOutput = core.getAllPlayersInfo();

        assertNotNull(playersOutput);
        assertTrue(playersOutput.contains("Full Name:"));
        assertTrue(playersOutput.contains("Gender:"));
        assertTrue(playersOutput.contains("Country:"));
    }

    // 测试用例2：result 指令带多空格（预期返回N/A）
    @Test
    public void testResultCommandWithMultipleSpaces() {
        String command = "result men 10m     synchronised";
        String output = core.getEventResult(command);
        assertEquals("N/A", output);
    }

    // 测试用例3：result 指令合法带 detail（预期包含 dive 字段）
    @Test
    public void testResultCommandWithValidDetail() {
        String command = "result men 1m springboard detail";

        String output = core.getEventResult(command);
        System.out.println("=== RESULT OUTPUT ===");
        System.out.println(output);

        assertNotEquals("N/A", output);
        assertTrue(output.contains("Full Name:"));
        assertTrue(output.contains("Rank:"));
        assertTrue(output.contains("Total Points:"));
        assertTrue(output.contains("----- Dive Details -----"));
    }

    // 测试用例4：result 指令非法后缀（不是 detail）
    @Test
    public void testResultCommandWithInvalidSuffix() {
        String command = "result men 1m springboard details";
        String output = core.getEventResult(command);
        assertEquals("N/A", output);
    }

    // 测试用例5：result 指令项目非法
    @Test
    public void testResultCommandWithInvalidEvent() {
        String command = "result men 2m springboard";
        String output = core.getEventResult(command);
        assertEquals("N/A", output);
    }
}

```

覆盖内容：

* players 正常输出验证
* result 各种合法与非法输入
* detail 字段检查
* 空指令、大小写、未知命令
* 文件不存在

---
## 异常处理机制

| 异常情况      | 处理方法      | 返回值       |
| --------- | --------- | --------- |
| 文件不存在     | try/catch | `"N/A"`   |
| JSON 格式错误 | 捕获异常      | `"N/A"`   |
| 指令含大写     | 主程序输出     | `"Error"` |
| 参数不完整     | 校验失败      | `"N/A"`   |
| 赛事名非法     | 不读取文件     | `"N/A"`   |

---

