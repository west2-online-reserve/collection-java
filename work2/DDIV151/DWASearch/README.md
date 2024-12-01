# 1. 解决思路

## 数据爬取

根据学长的方法([Pale-illusions/DWASearch.DWASearch: West2-Online Learn-Java work02](https://github.com/Pale-illusions/DWASearch))爬取

## 依赖

fastjson<img src=".\image-06.png" style="zoom: 50%;" />

图方便的FileUtil

![](.\image-08.png)

## 1.1  json数据分析

~~主要是把Idea格式化后的样子贴出来, 真分析还是在1,2节~~

### athletes数据

<img src=".\image-01.png" style="zoom: 80%;" />

<img src=".\image-02.png" style="zoom: 50%;" />

IDEA中查看到:

json为一个数组, 包含了199个国家元素, 每个国家含一个Participations数组, 里面的元素看作运动员, 包含所需要的信息.

---



### result(比赛项目)数据

<img src=".\image-03.png" style="zoom:33%;" />

首先是最外层, 整个可以看作一个项目对象, DisciplineName属性是项目名称, 含Heats数组

<img src=".\image-04.png" style="zoom: 50%;" />

Heatsl数组含多个元素, 注意到分别属于决赛, 半决赛, 以及初赛, 同时含一个Results数组

<img src=".\image-05.png" style="zoom:50%;" />

Result数组内即为需要的细节信息, 含有多个运动员比赛结果的对象, 其中Dives是每个比赛的具体得分数组. 

## 1.2 解析json

这里通过maven引入一个第三方依赖<img src=".\image-06.png" style="zoom: 50%;" />

通过fastjson的parseArray与parseObject将json反序列化是可行的, 只需根据json创建几个实体类

### athletes.json

#### Country类:

首先是最外层Country, 只需两个**成员变量**:

​	国家名countryname, 还有一个含运动员信息的数组athletes

只需要get(),set()**方法**即可

打上注解fastjson就能反序列化了

````java
    @JSONField(name = "Participations")
    private Object athletes;
    @JSONField(name = "CountryName")
    private String countryName;
````

就像这样, parseObject会识别到就行, 其它解析用的实体类变量同理, 就不列举了



#### Athlete类:

注意到, 需要按照一定次序输出, 所以继承接口:

Comparable<>

**成员变量**:

运动员基本信息: firstname, lastname, gender, country

**方法**: get(),set()

​	重写toString(),按需要格式返回运动员信息(不包括分割线)

​	实现compareTo(),(通过调用comepareTo......)

````java
    public int compareTo(Athlete o) {
        //首要关键字相同, 再按次要关键字
        if(o.getCountry().equals(this.getCountry())){
            return this.getLastName().compareTo(o.getLastName());//比较字符串的compareTo已经有了
            //主要是指定比较哪里的字符串(主次排序)
        }
        //否则按首要关键字
        else{
            return this.getCountry().compareTo(o.getCountry());
        }
    }
````

##### 节外生枝

**ProcessedAthlete**类

这个类extends Athlete

之所以有它, 是因为json中运动员性别是0,1, 但是我序列化时调用自己写的turnGenderString(int gender)方法转化为int保存, 再读取处理过的文件时就有问题了...

所以这个类唯一的作用是重写setGender(String)方法......

---



### 解析方法

此处以athlete为例, 解释一下是怎么用fastjson解析的

````java
ArrayList<Athlete> athletes = new ArrayList<>();
//fastjson嵌套数组的反序列化我还没搞懂(涉及到什么泛型, 时间紧任务重, 先摆)(序列化也不懂
//于是, 尝试把每个发生嵌套的数组当作字符串读取再次反序列化
//所以因为每种不同结构要重新写而变得很复杂(将就用吧
//如下:
//1. parseArray()把最外层序列化(内层数组Participations[]当作字符串)
ArrayList<Country> countries = new ArrayList<>(JSON.parseArray(jsonStr, Country.class));
	//2. 循环来处理内层数组
for (Country country : countries) {
	//2.1 反序列化读取到的内嵌数组字符串(每个国家元素都要单独反序列化):
	ArrayList<Athlete> athParticipations = new ArrayList<>(JSON.parseArray(country.getAthletes().toString(), 		Athlete.class));
	//2.2 把反序列化的列表加回去:
	for (Athlete athlete : athParticipations) {
        //保存上一层反序列化国家元素获取到的国家名
		athlete.setCountry(country.getCountryName());
        //加回去
		athletes.add(athlete);
	}
}
//最后排序
Collections.sort(athletes);
return athletes;
````

之后的解析也同理, 只是嵌套的数组越来越多了......



---



### xxx(项目).json

注意到,:

````json
"Event":{
    "DisciplineName": "Men 1m Springboard",...,
    "Heats":[
    	{"Name":"Finals",
        "Results":[{...},
				{
            	"Rank":"...",
                "Dives":[{...}{"DivePoints":"...",...}{...}]
        		}{...}],           
        },
		{"Name":"Semifinals"
           "Result":[
           ...]
        },{
            ...
        }
    ]
}
````

项目名称(DisciplineName)在最外层Event

比赛种类(Name)在Heats

参赛选手(FullName)/比赛结果(Rank,TotalPoints)在Result[ ]

每次得分在Dives[ ]

#### Result类(基本)

也继承接口: Comparable<> 吗?

并非, 因为获取到的信息本来就是按Rank顺序

该类包含最终输出结果, 所以成员变量很全

1. **成员变量**:

````java
    @JSONField(name = "Dives", serialize = false)
    private String divesJson;
    @JSONField(name = "Dives")
    protected ArrayList<Double> dives = new ArrayList<>();
    @JSONField(name = "FullName")
    protected String athleteFullName;//选手名称
    @JSONField(name = "Gender")
    protected String gender;
    @JSONField(name = "TotalPoints")
    protected double totalPoints;
    @JSONField(name = "Name")
    protected String name;//比赛种类名
````

2. **方法**:
   1. 重写**toString( )**:

````java
    public String toString() {
        return "Full Name:" + firstName + " " + lastName + "\nGender:" + Gender + "\nCountry:" + country;
    }
````

按格式输出基本信息(这个类只保存基本信息)

值得注意: *并不是只输出决赛结果*, 如果三种比赛都塞进来, 还要选择一下

​	2. 把每次分数dives转化成1+2+2 = totalPoints的形式的方法**parsePointsString()**			

#### 中间类

除了最后的Result,其它每层都只需要一两个信息, 但是类不少(主要是为了使用fastjson才硬塞的

因为每个类都表示一个反序列化时被当作String的自定义数组......

````java
public class Event {
    @JSONField(name = "DisciplineName")
    private String disciplineName;
    @JSONField(name = "Heats")
    private String heats;
    @JSONField(name = "Gender")
    private String gender;}

public class Heat {
    @JSONField(name = "Gender")
    private String gender;
    @JSONField(name = "Name")
    private String name;
    @JSONField(name = "Results")
    private String results;}

public class Dive {
    @JSONField(name = "DiveOrder",deserialize = false)
    private int diveOrder;
    @JSONField(name = "DivePoints")
    private double divePoints;}
````

#### Result(detailed)

1. **成员变量**

````java
//继承Result, 实现Comparable接口(要按第一次排名输出嘛)
public class DetailedResult extends Result implements Comparable<DetailedResult> {
    public DetailedResult() {
    }
    //以运动员为中心,多出了这些属性
    private int finalRank = 0;//决赛中的排名(0表示没参加)
    private int semiRank = 0;//半决赛中的排名
    private int preRank = 0;//初赛中的排名
    private ArrayList<Double> finalScores;//决赛的每次得分
    private ArrayList<Double> semiScores;//...
    private ArrayList<Double> preScores;
    private String name;//运动员名字
	}
````

接下来, 构造方法很有用: 需要以之前的Result为中心, 为新Result添加细节(**补充上初赛和半决赛内容**)

为此, 需要两个参数,:1. xxx项目的所有Result的列表 

​				 2.要添加的运动员的名字 

然后遍历列表, 判断名字是否相等, 如果相同补充信息即可

2. **方法**:
   1. parsePointsString( )
   2. toString()
   3. compareTo()

## 1.3 具体实现

可以发现, 爬去下来的文件中不需要的信息实在是太多了

为此, 决定以解析下来的列表重写json来记录数据

### DWASFile类

这个类利用解析json需要的实体类, 通过**fastjson**,**File**以及**FileUtil**等工具类实际完成数据(包括: input.txt的命令, 爬取的json)的读取和分析,

同时还有输出的能力(也就是最后jar包的功能)

#### 成员变量

````java
    private File outputFile;//输出结果文件
    private File inputFile;//输入命令文件
    private static ArrayList<Integer> orders = new ArrayList<>();//命令列表
    //这是原数据库
    private final File[] resultsJsons = new File("src/main/resources/DWAData").listFiles();
    //这是新数据库
    private File data = new File("src/main/resources/DWASearchData");
    //以下是命令和读取文件名的对照
    public static final Map<Integer, String> NumOfOrder = Map.of(
            111, "Men 1m Springboard.json",
            113, "Men 3m Springboard.json",
            133, "Men 3m Synchronised.json",
            120, "Men 10m Platform.json",
            130, "Men 10m Synchronised.json",
            211, "Women 1m Springboard.json",
            213, "Women 3m Springboard.json",
            233, "Women 3m Synchronised.json",
            220, "Women 10m Platform.json",
            230, "Women 10m Synchronised.json"

    );
````

#### 方法

1. 构造方法 public DWASearch.DWASFiles(File inputFile, File outputFile)

   我希望这个类被实例化后实例对象就能完成jar包的全部功能, 所以构造时要么空参, 要么传入两个代表输出和输入文件的File对象

   构造完成后得初始化数据库(放在构造方法内创建对象时执行一次)

2. 初始化方法 private void getReady()

   内容: 

   1. 根据放数据处理过的数据的文件夹(data)
   2. 进行数据处理

3. 数据处理方法

   1. - 准备运动员数据 private void prepareAthletes(File dir)

        处理数据后保存在dir文件夹下

      - 准备比赛数据 private void prepareResults(File dir)

        处理数据后保存在dir文件夹下

   2. 读取数据库方法 private String getJsonStr(String resourcesName)

      根据文件名查找DWA数据库数据, 以String返回读取到的文件

   3. 反序列化方法:

      - public static ArrayList<Result> parseResultsList(String jsonStr)

        根据json(String)进行反序列化, Collection.sort()排序,

        并返回结果

      - public static ArrayList<Athlete> parseAthletesList(String jsonStr)

        根据json(String)进行反序列化,Collection.sort()排序,

        并返回结果

      - public static ArrayList getResultsFromHeats()

      ​	从Heats(String)获取result

   4. 获取命令方法:

      - 读取命令 public static ArrayList readFileForOrders(File inputFile)

        读取inputFile的命令为String

        调用方法识别后保存在成员变量, 同时可返回命令列表

      - 把读取到的一行命令转化为数字 public static int getNumOrders(String inputLine)

        依靠正则表达式匹配识别命令的每一部分

   5. 其它用到的方法

      - 判断是不是txt文件 public static boolean ifTxt(String... name)

        传入(多个)文件名, 如果其中一个不是TXT文件返回false

      - 判断是否存在 public static boolean ifExists(File... file)

        传入(多个)文件名, 如果其中一个不存在返回false

### DWASearch类

main函数在这里, 没有什么成员变量

- 方法:

  1. 一个根据负责搜索和输出的方法 

     public static void printByOrders(Map<Integer, String> fileMap, ArrayList<Integer> orders, File output)

     - 有三个参数:

       1. List orders:命令列表

       2. Map fileMap:资源名和命令的对照表, 用来根据命令识别要获取哪个资源
       3. File output:要输出的位置

     - 实现:

       1. 用BufferedReader根据output对象创建对象进行文件写入

       2. 从头到尾遍历命令列表

          根据命令选择要输出的信息输出

          如果需要获取资源, 调用下面的方法 getResourcesStr

          获取到资源后, 利用DWASFile的静态方法进行解析并输出

  2. 获取jar包下资源的方法 

     private static String getResourcesStr(String name)

     用类加载器获取输入流, 读取后返回字符串

## 1.4 模块接口设计





## 1.5 封装

把用到的东西全部丢进jar包:
<img src=".\image-07.png" style="zoom:50%;" />

此处注意, 具体实现里面的DWASearch获取数据方法是按jar包路径来的, 

而且需要封装的是已经被DWASFile简化过的数据以节省空间和提高速度

# 2.性能改进

## 数据来源上

1. 问题所在:

   - 原数据库的无用信息很多, 浪费了很多资源在读取无用数据上

   - 目前DWASearch实现的方法是每条命令都可能要读取一次数据库的, 但是总共就12条命令

     如果命令很多, 可预见运行速度会很慢(内存比硬盘读写快多了)
   
2. 改进方法:

   - 用DWAFile来处理一遍数据, 同时生成新的数据库封装在jar包(已解决)

   - 第一次读取到某条命令后将命令与资源的对招Map进行修改, 将命令对应的资源名部分直接替换为接下来要输出的内容

     (即保存在内存中)进行输出, 去掉读取数据库的步骤

## 方法实现上

1. 问题所在

   - 不读取json, 而是通过模拟浏览器爬取html页面获得数据

   - 调用fastjson进行反序列化时将嵌套文件中的数组当作字符串处理, 这是一步无须有的操作

     (造成之后再次调用fastjson放进行反序列化)

2. 改进方法

   - 放弃爬页面, 就该json

   - 查阅得知运用泛型方法可以直接递归调用解决问题(并没改)

     [fastjson反序列化多层嵌套泛型类与java中的Type类型-CSDN博客](https://blog.csdn.net/weixin_30951231/article/details/96113146?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2~default~BlogCommendFromBaidu~Rate-3-96113146-blog-105091807.235^v43^pc_blog_bottom_relevance_base8&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2~default~BlogCommendFromBaidu~Rate-3-96113146-blog-105091807.235^v43^pc_blog_bottom_relevance_base8&utm_relevant_index=6)

# 3.单元测试

## 依赖

junit![](.\image-09.png)

## 测试内容

public的基本上都测试了

# 4.异常处理

姑且有自定义一个异常类InputError

作用在选择的命令文件有问题(命令为空,文件不存在)时

1. 遇到的问题
   - 封装为jar包后资源变成一个文件, 无法通过File对象来读取其中的数据库
   - 用BufferedWriter输出时出现单次输出末端部分缺失的情况
   - 无法通过一次调用Fastjson的parseArray方法获得需要的信息, 因为它无法识别json嵌套列表中的自定义元素

2. 解决方法:
   - 用类加载器获取输入流
   - 发现是缓冲区满再输出导致的, 牺牲效率修复, (每次都.flush()保证缓冲区为空)
   - 先将就以字符串形式获取其中的列表

~~其它没发现的问题留给方法调用者~~
