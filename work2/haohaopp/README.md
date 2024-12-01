
## 项目结构

```
|- src
 |-main
    |- DWASearch.java		主程序
    |-CoreModule.java		获取json信息方法类
    |- Athlete.java		运动员类
    |- Conpetiton.java		比赛类
    |- ConpetitonDetial.java	详细比赛类 
    |- resource 	    存放程序的数据
 |- test                    存放测试数据
|- DWASearch.jar
|- README.md
|- pom.xml
```
---

### 1.数据收集  
从官网爬取了运动员和比赛的josn类型文件    
### 2.数据处理  
分别创建Athlete，Conpetiton，ConpetitonDetail，三个javabean的类，用来存储数据  
### 3.数据分析  
#### DWASearch.java 
 **功能** 主程序入口，处理输入和结果输出  
 **设计**
 - 从命令行获取输入和输出地址  
 - 判断input.txt的输入是否合法  
 - 调用CoreModule里的方法获取所需的运动员信息数组  
 - 输出文件到output.txt  
#### CoreMoudle.java  
 **功能** 对json文件的读取，获得相应的运动员或比赛的类  
 **设计** 
 - 该类一共包含6个方法，其中3个是内部方法，其他三个分别用来获得Athlete，Conpetiton，ConpetitonDetail数组，供DWASearch使用  
 - 其中使用fastjson的jsonObject和jsonArray来获取数据  
 ### 4.实现流程  
 - DWASearch获取输入  
 - 调用CoreModule获得相应的运动员或比赛的数组
 - 再在DWASearch里面输出
 ### 5.性能改进  
 使用bufferreader来读取，对大文件更加友好  
 ### 6.单元测试   
 public class CoreModuleTest {  
    @Test  
    void testAthletes(){  
        Athlete[] athletes=CoreModule.getAthlete("src/main/resources/athletes.json");  
    }  
    @Test  
    void  testConpetiton(){  
        Conpetiton[] conpetitons=CoreModule.getConpetition("src/main/resources/Men 1m Springboard.json");  
    }
    @Test  
    void  testConpetitonDetail(){  
        ConpetitonDetial[] conpetitonDetials=CoreModule.getConpetitionDetial(("src/main/resources/Men 1m Springboard.json"));  
    }  
	public class DWASearchTest {  
    @Test  
    void test1()throws IOException {  
        DWASearch.main(new String[]{"input.txt"});  
    }  
    @Test  
    void test2()throws IOException {  
        DWASearch.main(new String[]{});  
    }  
    @Test
    void test3()throws IOException {  
        DWASearch.main(new String[]{"input.txt","output.txt"});  
    }  
    @Test  
    void test4()throws IOException {  
        DWASearch.main(new String[]{"input"});  
    }  
    @Test  
    void test5()throws IOException {  
        DWASearch.main(new String[]{"input.txt","output.txt","somethingelse.txt"});  
    }  
    @Test  
    void test6()throws IOException {  
        DWASearch.main(new String[]{"input.txt","badout.txt"});  
    }  
    @Test  
    void test7()throws IOException {  
        DWASearch.main(new String[]{"badinput.txt","output.txt"});  
    }  
}  
### 7.异常处理  
使用try-catch来处理IO  
抛出异常，返回给调用者处理  
