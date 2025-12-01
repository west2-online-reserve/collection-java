# 作业说明
命令行数用方法：确保 jar 包同目录下有存放着 "input.txt"和 "output.txt" data目录，将命令行切换到jar包上级目录，输入 <br>Java -jar DWASearch.jar input.txt output.txt，jar 包会执行 input 文件里的命令到 output 
## 1. 主要类说明与接口设计
 
该命令行程序包括以下类
 - 主类 **DWASearch**  
    1. 接受命令行参数(文件名 "input.txt" 和 "output.txt" )
    2. 连接运动员、比赛的信息的 API 接口，爬取里API接口里面的信息
    3. 读取 input 文件里的命令，通过正则表达式匹配，并写入 output 文件  
- 网页爬取类 **HttpsCrawler**  (执行 **JSONFetch** 接口)
  1. 接受 API 接口的 URL  ,通过 HttpClient 向网页发送 HTTP 请求，获取响应,得到 JSON
  2. 导入 Jackson 库，解析 JSON，把 JSON 字符串转换成 Java 对象
- 核心功能执行类 **CoreModule** (执行 **OutputModule** 接口，存放有输出文件的地址)
  1. 指令未知时写入 "Error" 
  2. 写入所有运动员信息
  3. 写入比赛信息(决赛)
  4. 写入比赛详细信息(包括预赛，半决赛，决赛)
- 比赛详细结果存取类 **TotalResult**  
    1. 存储每位运动员该项目每轮的成绩 -- 排名、每个动作得分
    2. 提供第一次比赛成绩，以便后续排序
    3. 将成绩转化为字符串

- 其他类   
    按照 JSON 里的结构设计，解析后的 JSON 对象就存储在这些类里

## 2. 性能改进
1. 缓存机制：对频繁访问的 API 数据建立缓存，减少网络请求
2. 延迟写入：使用 BufferedWriter 批量写入，减少 IO 操作
3. 数据结构优化：使用 TreeMap 替代 HashMap，实现高效排序


## 3. 测试用例
```
player
Players
resultwomen 1m springboard
result women 10m springboard
result sss
result detail
result women 1m springboard details
result men 10m     synchronised
players
result women 1m springboard detail
result women 3m springboard detail
result women 10m platform detail
result women 3m synchronized detail
result women 10m synchronized detail
result men 1m springboard detail
result men 3m springboard detail
result men 10m platform detail
result men 3m synchronized detail
result men 10m synchronized detail
result women 1m springboard detail
result women 1m springboard
result women 3m springboard
result women 10m platform
result women 3m synchronised
result women 10m synchronised
result men 1m springboard
result men 3m springboard
result men 10m platform
result men 3m synchronised
result men 10m synchronised
```
通过上述样例可以成功写入
## 4. 异常处理
1. 启动阶段异常处理  
检查命令行参数数量，如果不符合立即终止程序并显示正确用法
2. 文件系统异常处理  
检测输入输出文件是否存在，若不存在则提供完整文件路径便于排查
3. IOException 处理  
   防止文件创建、读取、写入操作出现写入异常
4. InterruptedException 处理  
处理HTTP请求超时、用户主动中断等异步操作
5. 通用异常类Exception  
   捕获所有未预料异常