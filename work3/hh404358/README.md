# quiry_weather_system
## 项目地址：[点击此处跳转](https://github.com/hh404358/quiry_weather_system)
## 功能描述：获取api查询某地的信息和三日天气情况，并将数据放入数据库的表中，然后对表进行增删查改操作
## 开发环境：idea
## 项目结构简介：
### CityController
对表中城市进行增删查改操作
### pojo
放城市City和天气Weather两个类
### Utils
获取Api和把获取的信息分别通过ToCity，ToWeather变成City类，Weather类
### WeatherController
启动该天气查询系统
## 遇到问题
1. mvn test 使用的是surefire插件
它按照指定格式的类名来查找匹配的测试类
默认包含的测试类：
*/Test.java
*/Test.java
*/TestCase.java
默认排除的测试类：
*/AbstractTest.java
*/AbstractTestCase.java
所以test不出结果的同学，可以瞅瞅是不是test文件名字不是格式不对，我就是因为test文件名叫Demo_test.java而出不来


2. [IDEA中找不到maven插件Plugin ‘org.apache.tomcat.maven:tomcat7-maven-plugin:2.2‘ not found_plugin 'org_Sicilly_琬姗的博客-CSDN博客](https://blog.csdn.net/weixin_37551036/article/details/119382042#:~:text=%E5%9C%A8Setting%E4%B8%AD%E5%8B%BE%E9%80%89Use%20plugin%20registry%20%E7%84%B6%E5%90%8E%E7%82%B9%E5%87%BBFile%20%F0%9F%A1%92%20Invalidate%20Caches%EF%BC%8C%E7%82%B9%E5%87%BBInvalidate,and%20Restart%20%E7%AD%89%E5%BE%85IDEA%E9%87%8D%E5%90%AF%E5%90%8E%E4%B8%8D%E5%86%8D%E6%8A%A5%E9%94%99%EF%BC%9A%20Maven%20Maven%20%E6%89%BE%E4%B8%8D%E5%88%B0%20%E2%80%9D%E9%94%99%E8%AF%AF%E3%80%82)

3. [Mybatis报错Error parsing SQL Mapper Configuration. Cause: java.io.IOException: Could not find resource_error parsing property name lambda$0_Pason·Chen的博客-CSDN博客](https://blog.csdn.net/weixin_37551036/article/details/119382042#:~:text=%E5%9C%A8Setting%E4%B8%AD%E5%8B%BE%E9%80%89Use%20plugin%20registry%20%E7%84%B6%E5%90%8E%E7%82%B9%E5%87%BBFile%20%F0%9F%A1%92%20Invalidate%20Caches%EF%BC%8C%E7%82%B9%E5%87%BBInvalidate,and%20Restart%20%E7%AD%89%E5%BE%85IDEA%E9%87%8D%E5%90%AF%E5%90%8E%E4%B8%8D%E5%86%8D%E6%8A%A5%E9%94%99%EF%BC%9A%20Maven%20Maven%20%E6%89%BE%E4%B8%8D%E5%88%B0%20%E2%80%9D%E9%94%99%E8%AF%AF%E3%80%82)

4. [Mysql错误:check the manual that corresponds to your MySQL server version for the right syntax_bing激凌~的博客-CSDN博客](https://blog.csdn.net/LT_lover/article/details/78912450)

5. java.sql包下给出三个与数据库相关的日期时间类型:
Date：表示日期，只有年月日，没有时分秒。会丢失时间；
Time：表示时间，只有时分秒，没有年月日。会丢失日期；
Timestamp：表示时间戳，有年月日时分秒，以及毫秒。



# order_manage_system2
## 项目地址:[点击此处跳转](https://github.com/hh404358/order_manage_system2)
## 功能描述：连接数据库里的订单表和商品单表，对订单和商品进行增删查改
## 开发环境：idea
## 项目结构简介：
### Connection
连接数据库
### Controller
对数据库的相关操作
### Goods
商品类
### Order 
订单类
### Management
对订单和商品的相关操作
### Main
启动该订单管理系统
## 编写过程遇到的问题
1. 连接mysql出现问题
重新下载
2. 程序没有输出
设置界面形式，本来就没有输出，应该加setVisible
3. sql默认值表达式不能含有其他列或其他表的字段
`Biaoming`不是单引号

4. modify
~~~
//if(jTable.getSelectedColumn()<0){
//JOptionPane.showMessageDialog(null,"请选择要修改的数据！");
//}else{
//intorderID=Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(),0).toString());
//StringorderTime=jTable.getValueAt(jTable.getSelectedRow(),1).toString();
//Stringname=jTable.getValueAt(jTable.getSelectedRow(),2).toString();
//intnum=Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(),3).toString());
//doubleprofit=Double.parseDouble(jTable.getValueAt(jTable.getSelectedRow(),4).toString());
//Stringmanager=jTable.getValueAt(jTable.getSelectedRow(),5).toString();
//LocalDateTimeot=LocalDateTime.parse(orderTime);
//Orderorder=newOrder(orderID,ot,name,num,profit,manager);
//Stringsql="deletefrom`order`whereorderID="+orderID;
//intresult=updata.upData(sql);
//if(result>0){
//System.out.println("deletesuccess");
//}else{
//System.out.println("deletefail");
//}
//
//Stringsql0="SELECT*FROM`goods`wheregoodName='"+name+"'";
//Selectselect1=newSelect();
//try{
//Object[][]data=select1.getGoods(sql0);
//}catch(SQLExceptionex){
//thrownewRuntimeException(ex);
//}
//intrest=(int)data[0][3];
//System.out.println(rest);
//if(rest>0){
////Stringsql1="UPDATE`order_manage_system`.`order`SET`orderID`="+orderID+"orderTime="+orderTime+"goodsName="+name+"num="+num+"manager="+manager+"WHERE`orderID`="+preID;
//Stringsql2="UPDATE`order_manage_system`.`goods`SET`restNum`="+(rest-num)+"WHERE`goodName`='"+name+"'";//更新库存商品数量
//Stringsql1="INSERTINTO`order`VALUES('"+orderID+"','"+orderTime+"','"+name+"','"+num+"','"+manager+"')";
//if(Updata.upData(sql1)>0){
//Updata.upData(sql2);
//System.out.println("修改成功！");
//JOptionPane.showMessageDialog(null,"修改成功！");
//}
//}
//else{
//System.out.println("此商品已无库存，无法购买");
//}

//OrdersXGorderXG=newOrdersXG(order);
//orderXG.setVisible(true);
//}
~~~

