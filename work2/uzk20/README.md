# DWASearch 项目说明文档

本项目的数据爬取以及处理流程主要参照了学长的代码https://github.com/LunaY77/DWASearch/tree/main?tab=readme-ov-file
用IDEA中的Maven项目实现了功能，虽然到头来pom文件内依然有无法识别插件版本的问题，但是单元测试以及主程序的JAR压缩却都正常实现了


## 1. 接口模块的设计与实现过程

### 1.1 指令解析模块
项目通过`Command`类实现指令解析功能，支持两种核心指令类型：
- `players`：查询运动员信息
- `result [项目名] [detail]`：查询比赛结果，支持详情查询

解析流程：
1. trim处理输入指令，去除首尾空格
2. 匹配指令类型，区分`players`指令和`result`指令
3. 对`result`指令进行参数解析，提取项目名和是否需要详情标识
4. 验证指令合法性，对无效指令返回错误信息（"Error"或"N/A"）

### 1.2 文件操作模块
通过`Utility`类实现文件读取功能，主要负责：
- 读取JSON格式的数据文件
- 处理文件读取异常，抛出运行时异常并附带错误信息
- 使用类加载器获取资源文件，确保在不同环境下的兼容性

### 1.3 主程序入口
`DWASearch`类作为程序入口，实现：
- 接收命令行参数（输入文件路径和输出文件路径）
- 读取输入文件中的指令并逐条处理
- 调用核心模块处理指令并收集结果
- 将最终结果写入输出文件

## 2. 核心类实现

### 2.1 Command类
```java
public class Command {
    // 指令类型枚举：运动员查询、结果查询、无效指令
    public enum CmdType { PLAYERS, RESULT, INVALID }
    
    private CmdType cmdType;       // 指令类型
    private String eventName;      // 项目名称（仅result指令）
    private boolean isDetail;      // 是否需要详情（仅result指令）
    private boolean isLegal;       // 指令是否合法
    private String errorMsg;       // 错误信息（非法指令时）

    // 静态解析方法，将输入字符串转换为Command对象
    public static Command parseCommand(String line) {
        // 解析逻辑实现...
    }
    
    // getter方法...
}
```

### 2.2 DWASearch类
```java
public class DWASearch {
    public static void main(String[] args) {
        // 参数校验
        if (args.length < 2) {
            System.err.println("请传入输入文件和输出文件路径");
            return;
        }

        // 初始化核心模块
        CoreModule coreModule = new CoreModule();
        StringBuilder finalOutput = new StringBuilder();

        // 读取输入文件并处理指令
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                Command cmd = Command.parseCommand(line);
                String cmdOutput = coreModule.processCommand(cmd);
                finalOutput.append(cmdOutput);
            }
            
            // 写入输出文件
            try (FileWriter fw = new FileWriter(args[1], false)) {
                fw.write(finalOutput.toString());
            }
            System.out.println("成功处理" + args[1]);
        } catch (IOException e) {
            System.out.println("失败" + e.getMessage());
        }
    }
}
```

### 2.3 Athlete类
用于表示运动员信息：
```java
// 单人项目运动员类
public class Athlete {
    private String fullName;  // 全名（姓氏 + 名字）
    private String gender;    // 性别
    private String country;   // 国家
    
    // 构造方法，拼接全名并转换性别标识
    public Athlete(String firstname, String lastname, int gender, String country) {
        this.fullName = lastname + " " + firstname;
        this.gender = gender == 1 ? Constants.GENDER_MALE : Constants.GENDER_FEMALE;
        this.country = country;
    }
}
```

### 2.4 Utility类
提供文件读取工具方法：
```java
public class Utility {
    // 读取JSON文件内容并返回字符串
    public static String readJsonFile(String fileName) {
        StringBuilder jsonContent = new StringBuilder();
        try (InputStream in = Utility.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            if (in == null) {
                throw new RuntimeException("没找到" + fileName + "文件");
            }
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("读取文件失败：" + fileName, e);
        }
        return jsonContent.toString();
    }
}
```

## 3. 单元测试的大体结构

项目使用JUnit 5进行单元测试，测试类为`DWASearchTest`，主要包含以下测试场景：

### 3.1 主程序功能测试
- `testMain_Success`：测试正常处理指令的场景，使用临时目录验证输入输出文件处理
- `testMain_InsufficientArgs`：测试参数不足的场景，验证错误提示信息
- `testMain_FileNotFound`：测试文件读取失败的场景（输入文件不存在）

### 3.2 综合指令测试
- `testMain1`：通过关键词匹配验证多种指令的处理结果，包括：
  - 错误信息（"Error"）出现次数
  - 无效项目（"N/A"）出现次数
  - 国家信息（"Country:"）出现次数
  - 特定得分字段出现次数验证

### 3.3 测试辅助方法
- `readFileToString`：读取文件内容为字符串
- `countStringOccurrences`：统计字符串在文本中出现的次数
- 自定义`NoExitSecurityManager`：捕获程序退出指令（用于测试异常场景）

### 测试资源
测试使用的输入文件位于`src/test/resources/input.txt`，包含多种合法和非法指令，用于全面验证程序处理能力。

## 备注
项目已实现基础功能，能够正确处理样例输入并输出预期结果。JAR包已构建完成，单元测试覆盖主要场景。