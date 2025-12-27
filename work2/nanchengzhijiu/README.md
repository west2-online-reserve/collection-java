## 项目简介

该项目可以通过特定指令完成世界游泳锦标赛跳水项目相关数据的收集，并实现一个能够对赛事数据进行统计的控制台程序。

代码没有生搬硬套说明文件的项目结构，纯粹借鉴+个人理解

代码结构

```
|- src
 |- pojo//存放对象类	
 |- Util//存放工具类						
|- DWASearch.jar
|- README.md		
```

指令格式(仅支持小写)

```
players
result 爬取比赛名称
result 爬取比赛名称 detail
```

程序通过读取配置文件获取对应信息的url，并通过http工具爬取网页信息进行解析，将对应结果输出到output.txt文件中

## 核心模块接口设计与实现

#### pojo目录

HttpResponse：http响应体，包括返回码，响应内容等

```java
private int statusCode;
private String content;
private Map<String, List<String>> headers;
```

Player：运动员类，包括姓名，国籍等信息，同时提供展示运动员信息的方法

```java
private String FullName;
private String gender;
private String country;
public static String displayAllPlayersInfo (HttpResponse response):传入返回体进行解析,返回字符串用于写入文件
```

Result类：结果类，包含两个构造函数，一个为比赛结果，一个是运动员比赛结果详情

```java
private String FullName;
private int Rank;//总决赛排名
private int Rank1;//初赛排名排名
private int Rank2;//半决赛排名
private String Preliminary;
private String SemifinalScore;
private String FinalScore;
public Result(String fullName, int rank, int rank1,int rank2,String finalScore, String preliminary, String semifinalScore):运动员比赛结果详情
public Result(String fullName, int rank, String finalScore):比赛结果
public static String displayResultsForEachEven (HttpResponse response)：传入返回体进行解析,返回字符串用于写入文件
public static String displayDetailsForEachEven(HttpResponse response)：传入返回体进行解析,返回字符串用于写入文件
```

#### Util目录

CompetitionUrlConfig：读取配置文件信息，并返回对应指令url

```java
private static void loadConfig()：加载配置文件，只加载一次
private static void initializeUrlMap()：初始化urlMap（String,String）
public static Set<String> getUrlMapKey()：获取urlMap的键集合（支持的命令集合）
public static String findUrlByTarget(String target)：通过指令搜寻对应url
```

FileUtil：文件读取工具

```java
public static ArrayList<String> readCommands(String inputFile):读取指令，返回指令集合，便于后续逐个解析
public static void writeResults(String outputFile, ArrayList<String> outputs):写入指令，传入输出文件名和各个指令解析的结果集合
```

HttpUtil：网络请求工具

```java
 public HttpResponse getUrl(String baseUrl, String method):发送网络请求获取返回体
```

ParseCommand：指令解析工具

```java
public ArrayList<String> parseAllCommand(ArrayList<String> commands, String fileName)：解析所有指令,返回指令解析结果集合
public String parseCommand(String command, HttpResponse response, String fileName)：解析单个指令
private String handleResultCommand(String command, HttpResponse response, String fileName)：开头result处理逻辑
```

处理流程:FileUtil.readCommands读取文件指令->进入ParseCommand进行解析指令->逐一解析指令->根据指令格式调用不同的类方法（Player.displayAllPlayersInfo()，Result.displayResultsForEachEven()，Result.displayDetailsForEachEven()）->输出到output.txt文件夹

## 性能改进

CompetitionUrlConfig类使用静态代码块加载配置文件并初始化urlMap，防止程序多次加载影响性能

利用一些静态方法，使得类方法的调用更加简洁

## 单元测试展示

```java
public class TestClass {
    private HttpUtil httpUtil;
    private HttpResponse response;
    private String url;
    private ParseCommand command;
    private ArrayList<String> commands;
    @BeforeEach
    public void setUp(){
        httpUtil = new HttpUtil();
        response=new HttpResponse();
        command=new ParseCommand();
        commands=new ArrayList<>();
    }
    //读取运动员信息
    @Test
    public void displayAllPlayersInfoTest(){
        commands=new ArrayList<>();
        String target = "players";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Full Name"));
        Assertions.assertTrue(results.getFirst().contains("Gender"));
        Assertions.assertTrue(results.getFirst().contains("Country"));
    }
    //读取比赛结果
    @Test
    public void displayResultsForEachEvenTest(){
        commands=new ArrayList<>();
        String target = "result women 1m springboard";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Full Name"));
        Assertions.assertTrue(results.getFirst().contains("Rank"));
        Assertions.assertTrue(results.getFirst().contains("Score"));
    }
    //读取运动员详细信息
    @Test
    public void displayDetailForEachEvenAllTest(){
        commands=new ArrayList<>();
        String target = "result men 10m platform detail";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Final Score"));
        Assertions.assertTrue(results.getFirst().contains("Preliminary Score"));
        Assertions.assertTrue(results.getFirst().contains("Semifinal Score"));
        Assertions.assertFalse(results.getFirst().contains("Final Score:*"));
        Assertions.assertFalse(results.getFirst().contains("Preliminary Score:*"));
        Assertions.assertFalse(results.getFirst().contains("Semifinal Score:*"));
    }
    @Test
    public void displayDetailForEachEvenOnlyFinalTest(){
        commands=new ArrayList<>();
        String target = "result men 10m synchronised detail";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Final Score"));
        Assertions.assertFalse(results.getFirst().contains("Final Score:*"));
        Assertions.assertTrue(results.getFirst().contains("Preliminary Score:*"));
        Assertions.assertTrue(results.getFirst().contains("Semifinal Score:*"));
    }
    @Test
    public void displayDetailForEachEvenFinalAndPreliminaryTest(){
        commands=new ArrayList<>();
        String target = "result women 1m springboard detail";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Final Score"));
        Assertions.assertFalse(results.getFirst().contains("Final Score:*"));
        Assertions.assertTrue(results.getFirst().contains("Preliminary Score"));
        Assertions.assertFalse(results.getFirst().contains("Preliminary Score:*"));
        Assertions.assertTrue(results.getFirst().contains("Semifinal Score:*"));
    }
    //Error情况测试
    @Test
    public void ErrorTest(){
        commands=new ArrayList<>();
        String target = " ";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Error"));
    }
    @Test
    public void Error2(){
        commands=new ArrayList<>();
        String target = "resultwomen";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Error"));
    }
    //N/A情况测试
    @Test
    public void NATest(){
        commands=new ArrayList<>();
        String target = "result";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("N/A"));
    }
    @Test
    public void NATest2(){
        commands=new ArrayList<>();
        String target = "result women";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("N/A"));
    }
    @Test
    public void NATest3(){
        commands=new ArrayList<>();
        String target = "result Men 10M Synchronised";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("N/A"));
    }
}

```

## 部分**异常处理说明**

读取文件是对文件为空情况进行处理

读取url时对url无法解析的情况进行处理