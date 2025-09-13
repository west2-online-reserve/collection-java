# 二轮考核
## 1.模块接口的设计与实现  
**因为29号晚上和30号下午有考试和作业，只搭建了大致的框架，测试还没有完全实现，会努力在1号晚上10点前完善好**  

1.1代码组织  
类:  
DWASearch: 主类，负责程序入口和文件处理。  
Core: 核心逻辑类，包含处理命令的逻辑和选手、结果数据模型。  
Core.Player: 内部类，代表选手数据模型。  
Core.Result: 内部类，代表比赛结果数据模型。  
函数:  
DWASearch.main: 程序入口点，处理命令行参数，读取输入文件，并调用Core类的方法。  
Core.outputPlayersInfo: 输出选手信息。    
Core.outputResult: 输出比赛结果。  
Core.outputDetailedResult: 输出详细比赛结果。  
Core.processCommand: 处理输入命令。  
Core.isValidEvent: 验证事件名称是否有效。  
## 2.性能改进  
## 3.单元测试  
    testOutputPlayersInfo
目的: 测试outputPlayersInfo方法是否能够正确输出非空选手列表的信息。  
方法: 创建一个包含两个选手的列表，并使用ByteArrayOutputStream和BufferedWriter来捕获输出。然后验证输出中是否包含预期的选手信息。  
预期结果: 输出应该包含选手的姓名、性别和国家。  

    testOutputPlayersInfoEmptyList  
目的: 测试outputPlayersInfo方法在处理空列表时的行为。  
方法: 传入一个空的选手列表，并验证输出是否为空。  
预期结果: 应该没有输出。  

    testOutputResult    
目的: 测试outputResult方法是否能够正确输出特定事件的结果。  
方法: 调用outputResult方法并验证输出中是否包含选手信息。  
预期结果: 输出应该包含选手的姓名、排名和得分。  

    testOutputResultEventNotFound
目的: 测试processCommand方法在接收到无效事件名称时的行为。    
方法: 使用无效事件名称调用processCommand，并验证输出是否包含  
误消息。  
预期结果: 输出应该包含"Error"。  

    testOutputDetailedResult  
目的: 测试outputDetailedResult方法是否能够正确输出特定事件的详细结果。  
方法: 调用outputDetailedResult方法并验证输出中是否包含选手的详细得分信息。  
预期结果: 输出应该包含选手的姓名和详细得分。  

    testOutputDetailedResultEventNotFound  
目的: 测试processCommand方法在接收到带有详细标志但无效事件名称时的行为。  
方法: 使用无效事件名称调用processCommand，并验证输出是否包含错误消息。  
预期结果: 输出应该包含"Error"。  

    testProcessCommand  
目的: 测试processCommand方法是否能够正确处理"players"和"result"命令。  
方法: 分别传入"players"和"result"命令，并验证输出是否包含预期的信息。  
预期结果: 对于"players"命令，输出应该包含选手信息；对于"result"命令，输出应该包含选手的比赛结果。  

    testProcessCommandInvalid  
目的: 测试processCommand方法在接收到无效命令时的行为。  
方法: 传入一个无效命令，并验证输出是否包含错误消息。  
预期结果: 输出应该包含"Error"。  
    testProcessCommandWithSpaceInEvent  
目的: 测试processCommand方法是否能够正确处理包含空格的事件名称。  
方法: 传入一个包含空格的事件名称，并验证输出是否包含该事件名称。  
预期结果: 输出应该包含带有空格的事件名称。  

    testProcessCommandIncomplete  
目的: 测试processCommand方法在接收到不完整的"result"命令时的行为。  
方法: 仅传入"result"命令，没有指定事件名称，并验证输出是否包含错误消息。  
预期结果: 输出应该包含"Error"。
## 4.异常处理  
* 文件操作异常: 使用ByteArrayOutputStream和StringWriter来模拟文件写入操作，避免了实际文件操作可能引发的IOException。  
* 方法调用异常: 在测试方法中，并没有显示地处理可能由Core类方法抛出的异常。相反，测试假设这些方法在正常情况下不会抛出异常。  
* 断言异常: 使用JUnit的断言方法（如assertTrue和assertEquals）来验证测试条件。  
* 测试方法声明: 每个测试方法都声明了可能抛出的IOException，这是为了覆盖BufferedWriter的flush方法可能抛出的异常。
