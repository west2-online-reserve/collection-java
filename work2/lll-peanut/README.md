# 项目结构
~~~         
├─src
│  ├─main
│  │  ├─java
│  │  │  │  ArgumentsException.java
│  │  │  │  DWASearch.java
│  │  │  │  Lib.java
│  │  │  │  Player.java
│  │  │  │  ResourceUtil.java
│  │  │  │  Result.java
│  │  │  │  Deprecated.java
│  │  │  └─Constant
│  │  │          DivingEventConstants.java
│  │  │          FileConstants.java
│  │  └─resources
│  │      ├─rowData   
│  │      └─rowData1
~~~

# 模块接口的设计与实现过程

程序总共有一个主函数DAWSearch，一个Lib库来实现对数据的处理，一个工具类ResourceUtil来获取resources里的rowData1里的文件，一个类封装Player用来排序与整理输出格式，一个类封装Result。

定义了一个异常类用来处理参数异常。

两个常量类用来封闭常量。

程序主要依赖Lib的processArgs函数来对args进行字符串处理实现对outputFinalResultsDetail，outputFinalResults，outputPlayers这些函数的调用，并将结果输出到文本文件中。

工具类ResourceUtil的作用是将json字符串返回给输出函数。

# 主要函数

## processArgs

首先对参数的个数，文件是否进行验证。输入函数一定要存在，输出函数若不存在可以创建，并且可以选择是否对输出文件的覆盖。接下来对字符串进行解析，并调用不同的函数实现不同的输出。

# Deprecated类

这个类的一个函数readAllFilesInDir()本来是用来读取所有文件的Player，再整合到一起，但是我发现我漏了个json是存放运动员数据的，所以放在Deprecated类里

还有一个函数processArgs()和Lib的processArgs()是一样的，但是Lib类里的processArgs()需要输入1或其他数来判断文件是否覆盖，为了便利测试，Deprecated类里的processArgs()里直接覆盖不需要确认。

# 异常处理
若输入文件不存在，报异常ArgumentsException。若io异常，抛出RuntimeException。

# 单元测试
由于ResourceUtil无法在test文件中对于json文件的调用，所以测试没有用断言。对于input.txt测试了14组的不同指令，并进行人工验证。对于输入文件输出文件的名字也进行了验证。

