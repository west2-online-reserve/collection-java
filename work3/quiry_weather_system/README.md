# quiry_weather_system
## [功能描述]：获取api查询某地的信息和三日天气情况，并将数据放入数据库的表中，然后对表进行增删查改操作
## [开发环境]：idea
## [项目结构简介]：
### CityController
对表中城市进行增删查改操作
### pojo
放城市City和天气Weather两个类
### Utils
获取Api和把获取的信息分别通过ToCity，ToWeather变成City类，Weather类
### WeatherController
启动该天气查询系统
## [遇到问题]
Bug解决方案:1.mvn test 使用的是surefire插件
它按照指定格式的类名来查找匹配的测试类
默认包含的测试类：
*/Test.java
*/Test.java
*/TestCase.java
默认排除的测试类：
*/AbstractTest.java
*/AbstractTestCase.java
所以test不出结果的同学，可以瞅瞅是不是test文件名字不是格式不对，我就是因为test文件名叫Demo_test.java而出不来

来自 <https://www.bilibili.com/video/BV1Ah411S7ZE?p=11&spm_id_from=pageDriver&vd_source=02b1227bd7a5b5f5e43fc4e8cea03f81> 

2.[IDEA中找不到maven插件Plugin ‘org.apache.tomcat.maven:tomcat7-maven-plugin:2.2‘ not found_plugin 'org_Sicilly_琬姗的博客-CSDN博客](https://blog.csdn.net/weixin_37551036/article/details/119382042#:~:text=%E5%9C%A8Setting%E4%B8%AD%E5%8B%BE%E9%80%89Use%20plugin%20registry%20%E7%84%B6%E5%90%8E%E7%82%B9%E5%87%BBFile%20%F0%9F%A1%92%20Invalidate%20Caches%EF%BC%8C%E7%82%B9%E5%87%BBInvalidate,and%20Restart%20%E7%AD%89%E5%BE%85IDEA%E9%87%8D%E5%90%AF%E5%90%8E%E4%B8%8D%E5%86%8D%E6%8A%A5%E9%94%99%EF%BC%9A%20Maven%20Maven%20%E6%89%BE%E4%B8%8D%E5%88%B0%20%E2%80%9D%E9%94%99%E8%AF%AF%E3%80%82)

3.[Mybatis报错Error parsing SQL Mapper Configuration. Cause: java.io.IOException: Could not find resource_error parsing property name lambda$0_Pason·Chen的博客-CSDN博客](https://blog.csdn.net/weixin_37551036/article/details/119382042#:~:text=%E5%9C%A8Setting%E4%B8%AD%E5%8B%BE%E9%80%89Use%20plugin%20registry%20%E7%84%B6%E5%90%8E%E7%82%B9%E5%87%BBFile%20%F0%9F%A1%92%20Invalidate%20Caches%EF%BC%8C%E7%82%B9%E5%87%BBInvalidate,and%20Restart%20%E7%AD%89%E5%BE%85IDEA%E9%87%8D%E5%90%AF%E5%90%8E%E4%B8%8D%E5%86%8D%E6%8A%A5%E9%94%99%EF%BC%9A%20Maven%20Maven%20%E6%89%BE%E4%B8%8D%E5%88%B0%20%E2%80%9D%E9%94%99%E8%AF%AF%E3%80%82)

4.[Mysql错误:check the manual that corresponds to your MySQL server version for the right syntax_bing激凌~的博客-CSDN博客](https://blog.csdn.net/LT_lover/article/details/78912450)

5.java.sql包下给出三个与数据库相关的日期时间类型:
Date：表示日期，只有年月日，没有时分秒。会丢失时间；
Time：表示时间，只有时分秒，没有年月日。会丢失日期；
Timestamp：表示时间戳，有年月日时分秒，以及毫秒。



