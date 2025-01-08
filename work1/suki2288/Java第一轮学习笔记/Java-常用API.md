# API

## 什么是API(Application Programming Interface)

1. 全称应用程序编程接口，就是Java自己写好的程序给程序员调用，方便完成功能

2. API文档：程序使用说明书

# 包

## 什么是包

1. 包是分门别类管理程序的，别人写好的程序都放在包里
2. 建包有自己的语法格式，但是idea中会自动帮忙建

## 自己程序中调用其他包的注意事项

1. 同一个包中的程序，可以直接访问
1. 访问其他包下的程序，必须导包才可以访问

* 导包格式：`import 包名.类名`

3. 自己程序中使用Java提供的程序，必须先导包（Java.lang包除外）

4. 访问多个名称一样的包的时候，默认只能导入一个，另一个必须带包名和类名来访问

# String

## String是什么

代表字符串类型，用String定义的变量可以指向一个字符串对象

## String创建对象封装字符串的方式

1. 直接使用双引号""

   `String name = "abc";`

2. 调用new String类构造器来初始化字符串对象

​       `String a = new String(  不写 |  "内容" ｜ chars  ｜ bytes  )`

3. 两种方式的区别

   * 双引号给出的字符串对象，存在于堆内存常量池中，相同内容只会储存一份

   * String s2 = new String("abc");`此行代码其实构造两个对象，一个在常量池，一个在堆

## String常用方法（String s）

1. 获取字符串的长度

   * `s.length()`

2. 获取字符串中某个位置的字符

   * `s.charAt(i)`

   * 字符串的遍历： 先s.length fori  再用s.cahrAt记录 逐个打出来

3. 把字符串变成字符数组

   * `char[]  字符串名称 = s.toCharArray();`

4. 判断字符串内容，一致则返回true：

   * `s1.equals(s2)`

5. 忽略大小写比较内容

   * `s1.equalsIgnoreCase(s2)`

6. 截取字符串

   * `s.substring(0,i)` 注意；包前不包后

7. 从当前索引截取到字符串末尾

   * `s.substring(5)`前五个

8. 把字符串某个内容换成新内容并输出

   * `s.replace("A","B")`

9. 判断字符串中是否包含某字符 

   * `s.contains("A")`

10. 判断字符串是否以某个字符开头

    * `s.startsWith("A")`

11. 分割字符串，放到一个数组，再遍历数组即可

    * `String[]  name = s.split(",")`
    * `String s = "A,B,C,D"`

## String使用时注意事项

1. String的对象内容是***不可变字符串对象***
   * 试图改变字符串对象其实是产生了新字符串对象，通过把`""`里面内容拼接，产生新地址给String，让其看上去改变了，其实改变的是地址
2. 用`""`方式创建的字符串对象，存储到**字符串常量池**，而且相同内容只能存一份
3. 通过`new`方式创建的字符串对象，new一次产生一个新对象放在**堆内存**
4. 判断字符串内容：”==“判断字符串内容是判断地址的，会引起很多问题，需要使用`equals`方法
5. 什么时候用`==`比较容易：基本数据类型的比较

## String案例-完成用户登录

### 内容

1. 开发登录界面，提示用户输入账号密码
2. 设计一个登录方法，对用户登录名和密码进行正确性认证
3. 根据登录方法返回结果，判断用户是否登录成功
4. 使用循环控制登录界面最多显示三次

### 总结

1. 字符串比较不要用`==`比较的是地址容易出现bug，此方法是基本数据使用的
2. 用String提供的equals方法，一样返回true

## String案例-开发验证码

### 内容

1. 定义两个字符串变量，用来记住生成的验证码和记住验证码要用的所有字符
2. 用for循环控制生成多少位验证码，根据索引提取字符交给code保存，返回code

# ArrayList

## 集合

### 什么是集合

1. 是一种容器，用来装数据，类似数组
2. 集合容器中存储的每一个对象是在堆内存中的地址

### 为什么用集合

数组定义启动后长度固定了，而集合大小可变，类型可以不固定***（集合都是支持泛型）***，功能更强大，适合做元素不能确定，同时存在增删减除的业务场景，开发中用得更多，ArrayList就是一种集合

### 集合的泛型ArrayList<>

1. `<>`之中可以填引用数据类型，比如String Student Movie Integer，但是不能填写基本数据类型
2. 定义集合都应该采用泛型
   * `ArrayList list = new ArrayList();`
   * 如果集合就是什么都要存，那就使用`Object`
   * 推荐这样写`ArrayList<Object> list = new ArrayList<>();`

## ArrayList集合学什么

1. 提供创建容器对象的方式
2. 会增删改查
3. 其他特点

## ArrayList常用方法

1. 创建一个ArrayList
   * `ArrayList list = new ArrayList();`
   * `ArrayList<int> list = new ArrayList<int>();`如果需要限制类型 加入<>

2. 添加对象
   * `list.add()`  直接添加
   * `list.add(1,"java")`添加到指定位置需要先声明index再添加element
3. 返回指定索引处的元素
   * `list.get(1)`

4. 返回集合中存储的元素个数
   * `list.size()`
5. 删除元素
   * `list.remove(1)`删除指定**索引处**元素并返回该元素
   * `list.remove("java")`直接删除某个**元素值**，删除成功会返回true，默认删除第一个出现的

6. 修改指定**索引**处元素，并且返回该元素
   * `list.set(1,"java")`

## ArrayList案例-从容器中找出某些数据并删除

### 内容

1. 用ArrayList代表购物车，储存商品名
2. 遍历集合中数据，包含了枸杞就删除
3. 输出集合看是否成功删除

### 总结：删除元素如何不出bug

1. 删除数据后，索引-1
2. 从后面往前遍历以免漏删

### 例子

```java
ArrayList<String> shopping = new ArrayList();
shopping.add("java入门");
shopping.add("宁夏枸杞");
shopping.add("黑枸杞");
shopping.add("人字拖");
shopping.add("特级枸杞");
shopping.add("枸杞子");
System.out.println(shopping);
//方法一：i--防止移位
for (int i = 0; i < shopping.size(); i++) {
    String ele = shopping.get(i);
    if (ele.contains("枸杞")){
        shopping.remove(ele);
        i--;
    }
}
System.out.println(shopping);
 //方法二：倒着来
for (int i = shopping.size()-1; i >= 0; i--) {
            String ele = shopping.get(i);
            if (ele.contains("枸杞")){
                shopping.remove(ele);
            }
        }
System.out.println(shopping);
```

## ArrayList综合案例-完成菜品上架以及菜品信息浏览

见idea Day08记录

# Object类（API-d1）

## 概述

*  Object类是Java中所有类的祖宗类，因此，Java中所有类的对象都可以直接使用Object类中提供的一些方法
*  以下是Object类中常见的方法，本节课将学习以下三个
*  ![IMG_0569](/Users/cjf/Downloads/IMG_0569.JPG)

## toString方法

1. 基本作用：返回对象的字符串形式
2. toString（）方法存在的意义就是为了被子类重写，以便返回对象具体的内容
3. 在重写之前打出来是地址，重写后就是内容

## equals方法

1. 基本作用：默认比较两个对象地址是否相等
2. 直接比较两个对象的地址是否相同完全可以用“==” 替代equals，equals存在的意义就是为了被子类重写，以便子类自己来定制比较规则（比如比较对象内容）

## clone方法

* 当某个对象调用这个方法时，这个方法会复制一个一模一样的新对象返回

* ```java
  User u2 = (User) u1.clone();
  ```

* 浅克隆

  * 拷贝出的新对象，与原对象中的数据一模一样（引用类型拷贝的只是地址）

* 深克隆

  * 对象中基本类型的数据直接拷贝
  * 对象中的字符串数据拷贝的还是地址
  * 对象中还包含的其他对象，不会拷贝地址，会创建新对象

# Objects类

## 概述

* Objects是一个工具类，提供了很多操作对象的静态方法给我们使用

## equals方法

* 先做非空判断，再比较两个对象

## is/nonNull方法

* 判断对象是否为nu11

```java
public class Test {
    public static void main(String[] args) {
        // 目标：掌握Objects类的常用方法
        String s1 = "heima";
        String s2 = "heima";
        String s3 = null;

        System.out.println(s1.equals(s2));
        System.out.println(Objects.equals(s1, s2));
        System.out.println(Objects.equals(s3, s2)); // 如果有null，不会报错，更安全

        System.out.println(Objects.isNull(s3)); // s3 == null;
        System.out.println(Objects.isNull(s2));

        System.out.println(Objects.nonNull(s2));
        System.out.println(Objects.nonNull(s3));
        }
}
```

# 包装类

## 概述

* 包装类就是把基本类型的数据包装成对象

* 自动装箱：基本数据类型可以自动转换为包装类型

* 自动拆箱：基本数据类型可以自动转换为包装类型

* ```java
  // Integer a1 = new Integer(12);  //过时
  Integer a2 = Integer.valueOf(12);
  System.out.println(a2);
  
  // 自动装箱机制：把基本数据类型转换成对象
  Integer a3 = 12;
  
  //自动拆箱机制：可以自动把包装类型的对象转换成对应基本数据类型
  int a4 = a3;
  
  // 泛型和集合不支持基本数据类型，只能支持引用数据类型
  ArrayList<Integer> list = new ArrayList<>();
  list.add(12);  // 自动装箱
  ```

## 包装类提供的方法

```java
// 1.把基本类型数据转换成字符串
Integer a = 23;
String rs1 = Integer.toString(a);
System.out.println(rs1 + 1);   // 231

String rs2 = a.toString(); // "23"
System.out.println(rs2 + 1 );  // 231

// 2.把字符串类型的数值，转换成对应的基本数据类型
String ageStr = "29";
//  int ageI = Integer.parseInt(ageStr);  // 29
int ageI = Integer.valueOf(ageStr); //更为优秀的方法，只要记一个
System.out.println(ageI + 1); // 30
```

* 都可以统一使用valueOf

# Stringbuilder与StringBuffer

## 概述

* StringBuilder代表可变字符串对象，相当于是一个容器，它里面装的字符串是可以改变的，就是用来操作字符串的
* 好处：StringBuilder比String更适合做字符串的修改操作，效率会更高，代码也会更简洁
* 对于字符串相关的操作，如频繁的拼接、修改等，建议用StringBuidler，效率更高！
* 注意：如果操作字符串较少，或者不需要操作，以及定义字符串变量，还是建议用String

## 语法

```java
public class Test {
    public static void main(String[] args) {
        // 目标：搞清楚stringBuilder的用法和作用
        // StringBuilder s = new StringBuilder(); // s ""
        StringBuilder s = new StringBuilder("itheima"); // s "itheima"

        // 1.拼接内容
        s.append(12);
        s.append("黑马");
        s.append("true");

        // 支持链式编程
        s.append("666").append("888").append("999");

        // 2.反转操作
        s.reverse();
        System.out.println(s);

        // 3.返回字符串长度
        System.out.println(s.length());

        // 4.把StringBuilder对象有转换成String类型
        String rs = s.toString();
        System.out.println(rs);

    }
}
```

## StringBuffer

* StringBuffer的用法与StringBuilder是一模一样的
* 但StringBuilder是线程不安全的 StringBuffer是线程安全的

## 案例

```java
public class Test3 {
    public static void main(String[] args) {
        // 目标：完成遍历数组内容，并拼接成指定格式
        System.out.println(getArrayData(new int[]{111, 222, 333}));
    }

    public static String getArrayData(int[] arr){
        // 1.判断是否为null
        if (arr == null){
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length -1 ){
                sb.append(arr[i]);

            }else {
                sb.append(arr[i]).append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
```

# StringJoiner

## 概述

* JDK8开始才有的，跟StringBuilder一样，也是用来操作字符串的，也可以看成是一个容器，创建之后里面的内容是可变的
* 好处：不仅能提高字符串的操作效率，并且在有些场景下使用它操作字符串，代码会更简洁
* 可以在创建的时候就指定间隔符号，开始符号，结束符号

## 复写上述案例

```java
public class Test {
    public static void main(String[] args) {
        // 目标：掌握StringJoiner的使用
        StringJoiner s = new StringJoiner(",", "[", "]");
        s.add("java1");
        s.add("java2");
        s.add("java3");
        System.out.println(s);  // [java1, java2, java3]


    }

    public static String getArrayData(int[] arr){
        // 1.判断是否为null
        if (arr == null){
            return null;
        }

        // 2.用StringJoiner写
        StringJoiner j = new StringJoiner("," , "[" , "]");
        for (int i = 0; i < arr.length; i++) {
            j.add(arr[i] + "");
        }
        return j.toString();
    }
}
```

# Math,System,Runtime

## Math

```java
public class Test {
    public static void main(String[] args) {
        // 目标：了解math类提供的常用方法
        // 1. 取绝对值
        System.out.println(Math.abs(-2)); // 2

        // 2.向上取整
        System.out.println(Math.ceil(4.001)); // 5.0

        // 3.向下取整
        System.out.println(Math.floor(4.999999)); // 4.0

        // 4.四舍五入
        System.out.println(Math.round(3.4999)); // 3

        // 5.取较大/较小值
        System.out.println(Math.max(10, 20)); // 20

        // 6.取次方
        System.out.println(Math.pow(2, 3)); // 8

        // 7.取随机数
        System.out.println(Math.random()); // [0.0 , 1.0)
    }
}
```

## System

```java
public class Test {
    public static void main(String[] args) {
        // 目标：了解System类常见方法
        // 1.终止当前运行的java虚拟机
        // System.exit(0); 人为的终止虚拟机（不要使用）

        // 2.获取当前系统时间（1970.1.1开始到现在的毫秒值，返回long）
        System.out.println(System.currentTimeMillis());
        // 应用：计算一个程序需要的时间，用来性能分析
    }
}
```

## Runtime

```java
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 目标：了解下Runtime的几个常用方法

        // 1. public static Runtime getRuntime()  返回与当前Java应用程序关联的运行时对象
        Runtime r = Runtime.getRuntime();

        // 2. public void exit(int status)  终止当前运行的虚拟机
        // r.exit(0);

        // 3. public int availableProcessors)  获取虚拟机能够使用的处理器数
        System.out.println(r.availableProcessors());  //8

        // 4. public long totalMemory)  返回Java虚拟机中的内存总量
        System.out.println(r.totalMemory()/1024.0/1024.0 + "MB");

        // 5. public long freeMemory)  返回Java虚拟机中的可用内存
        System.out.println(r.freeMemory() / 1024.0 / 1024.0 + "MB");

        // 6. public Process exec(String command)  启动某个程序，并返回代表该程序的对象
//        r.exec("放入程序路径");
//        r.exec("QQ");
        Process p = r.exec("QQ");
        Thread.sleep(5000);  // 让程序在这里暂停5s后，继续往下走
        p.destroy(); //关闭程序
    }
}
```

# BigDecimal

* 用于解决浮点型运算时，出现结果失真的问题

```java
public class BigDecimalDemo01 {
    public static void main(String[] args) {
        // 目标：掌握BigDecimal使用，解决小数运算失真的问题
        double a = 0.1;
        double b = 0.3;

        // 1.把他们变成字符串封装成BigDecimal对象来运算，有两种方法
        // 不推荐
//    BigDecimalDemo01 a1 = new BigDecimalDemo01(Double.toString(a));
//    BigDecimalDemo01 b1 = new BigDecimalDemo01(Double.toString(b));

        // 推荐使用以下方式：把小数转换成字符串再得到BigDecimal对象来使用（更简洁）
        BigDecimal a1 = BigDecimal.valueOf(a);
        BigDecimal b1 = BigDecimal.valueOf(b);

        // 2.四则运算
        // BigDecimal c1 = a1. add (b1);
        // BigDecimal c1 = a1. subtract(b1);
        // BigDecimal c1 = a1.multiply(b1);
        // BigDecimal c1 = al. divide (b1);

        // 3.除法中除不尽特殊形式，需要限度精确到几位否则报错
        BigDecimal i = BigDecimal.valueOf(0.1);
        BigDecimal j = BigDecimal.valueOf(0.3);
        BigDecimal k = i.divide(j , 2 , RoundingMode.HALF_UP);
        System.out.println(k);
        
        // 4.把BigDecimal对象转换成double类型的数据
        double rs = k.doubleValue();
        System.out.println(rs);
    }
}
```

# JDK8划分的日期与时间

## 传统的日期时间

### Date

* 代表时间和日期

* ```java
  public class Test1Date {
      public static void main(String[] args) {
          // 目标：掌握Date日期类的使用
          // 1. 创建一个Date对象，代表系统当前时间信息
          Date d = new Date();
  
          // 2. 拿到时间毫秒值
          long time = d.getTime();
  
          // 3. 把时间毫秒值转换成日期对象  2s后时间是多少
          time += 2*1000;
          Date d2 = new Date(time);
  
          // 4. 直接把日期对象的时间通过setTime方法修改
          Date d3 = new Date();
          d3.setTime(time);
  
      }
  }
  ```

### SimpleDateFormat

* 简单日期格式化，可以用来把日期对象、时间毫秒值格式化成我们想要的形式，适合给用户观看

* ```java
  public class Test {
      public static void main(String[] args) throws ParseException {
          // 目标：掌握SimpleDateFormat的使用
          // 1.准备一些时间
          Date d = new Date();
          System.out.println(d);
  
          long time = d.getTime();
          System.out.println(time);
  
          // 2.格式化日期对象，和时间，毫秒值
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE a");
  
          String rs = sdf.format(d);
          String rs2 = sdf.format(time);
          System.out.println(rs);
          System.out.println(rs2);
          
          // 3.解析字符串时间成为日期对象
          String dataStr = "2022-12-12 12:12:12";
          // 创建简单格式化对象，指定的时间格式必须和被解析的时间格式一摸一样，否则程序会出bug
          SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date d2 = sdf2.parse(dataStr);
          System.out.println(d2);
      }
  }
  ```

### 秒杀案例

```java
public class Test2 {
    public static void main(String[] args) throws ParseException {
        // 目标：完成秒杀案例
        // 1. 把开始时间，结束时间，a，b客户下单时间拿到程序中
        String start = "2023年11月11日 0:0:0";
        String  end = "2023年11月11日 0:10:0";
        String  a = "2023年11月11日 0:01:18";
        String  b = "2023年11月11日 0:10:57";

        // 2.把字符串的时间解析成日期对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date startDt = sdf.parse(start);
        Date endDt = sdf.parse(end);
        Date aDt = sdf.parse(a);
        Date bDt = sdf.parse(b);

        // 3.把日期对象转换成时间毫秒值来判断是否秒杀成功
        long startTime = startDt.getTime();
        long endTime = endDt.getTime();
        long aTime = aDt.getTime();
        long bTime = bDt.getTime();

        if (aTime >= startTime && aTime <= endTime){
            System.out.println("a秒杀成功");
        }else {
            System.out.println("a秒杀失败");
        }

        if (bTime >= startTime && bTime <= endTime){
            System.out.println("b秒杀成功");
        }else {
            System.out.println("b秒杀失败");
        }

    }
}
```

### Calendar

* 代表的是系统此刻时间对应的日历，通过它可以单独获取、修改时间中的年、月、日、时、分、秒等
* 注意：Calendar是可变对象，一旦修改后其对象本身表示的时间将产生变化

* ```java
  public class Test {
      public static void main(String[] args) {
          // 目标：掌握Calendar的使用和特点
          // 1.得到系统此刻时间对应的日历对象
          Calendar now = Calendar.getInstance();
          System.out.println(now);
  
          // 2.获取日历中的某个信息
          int year = now.get(Calendar.YEAR);
          System.out.println(year);
  
          // 3.拿到日历中的日期对象
          Date d = now.getTime();
          System.out.println(d);
  
          // 4.拿到时间毫秒值
          long time = now.getTimeInMillis();
          System.out.println(time);
  
          // 5.修改日历中的某个信息
          now.set(Calendar.MONTH, 9); // 修改月份为10月
          System.out.println(now);
          
          // 6.为某个信息增加或减少多少
          now.add(Calendar.DAY_OF_YEAR,100);
          now.add(Calendar.HOUR,12);
          System.out.println(now);
      }
  }
  ```

## 新增的日期时间

### 为什么要学JDK8新增的时间

* JDK8之前传统的时间API
  * 设计不合理，使用不方便，很多都被淘汰了
  * ﻿﻿都是可变对象，修改后会丢失最开始的时间信息
  * ﻿﻿线程不安全
  * ﻿﻿只能精确到毫秒
* JDK8开始之后新增的时间API
  * 设计更合理，功能丰富，使用更方便
  * ﻿﻿都是不可变对象，修改后会返回新的时间对象，不会丢失最开始的时间
  * ﻿﻿线程安全
  * ﻿﻿能精确到毫秒、纳秒（1s = 1000毫秒  1毫秒 = 1000微秒 1微秒 = 1000纳秒）

### LocalDate，LocalTIme，LocalDateTime（代替Calendar）

* LocalDate

* ```java
  public class Test1_LocalDate {
      public static void main(String[] args) {
          // 0.获取本地日期对象
          LocalDate ld = LocalDate.now();
          System.out.println(ld);
  
          // 1.获取日期对象中的信息
          int year = ld.getYear();
          int month = ld.getMonthValue();
          int day = ld.getDayOfMonth();
          int dayOfYear = ld.getDayOfYear(); // 一年中第几天
          int dayOfWeek = ld.getDayOfWeek().getValue(); //周几
  
          // 2.直接修改某个信息，但是不会改变原来的信息  withYear/Month/DayOfMonth/DayOfYear
          LocalDate ld2 = ld.withYear(2099);
          System.out.println(ld2);
          System.out.println(ld);
          
          // 3.把某个信息增/减多少  plus/minus + Years/Months/Days/Weeks
          LocalDate ld3 = ld.plusYears(2);
          
          // 4.获取指定日期的LocalDate对象
          LocalDate ld4 = LocalDate.of(2099,12,12);
          
          // 5.判断两个日期对象是否相等，在前还是在后  equals isBefore isAfter 返回布尔值
          ld4.equals(ld2);
      }
  }
  ```

* LocalTIme

* ```java
  public class Test2_LocalTime {
      public static void main(String[] args) {
          // 0.获取本地时间对象
          LocalTime lt = LocalTime.now(); // 时，分，秒，纳秒
          
          //其他用法语法和LocalDate完全一致
      }
  }
  ```

* LocalDateTime（同上，但有一个转换）

* ```java
  public class Test3_LocalDateTime {
      public static void main(String[] args) {
          // LocalDateTime可以转换成LocalDate和LocalTime
          LocalDateTime ldt = LocalDateTime.now();
          LocalDate ld = ldt.toLocalDate();
          LocalTime lt = ldt.toLocalTime();
                  
      }
  }
  ```

### Zoneld, ZoneDateTime（代替Calendar）

* Zoneld：代表时区ID

* ```java
  public class Test {
      public static void main(String[] args) {
          // 目标：了解时区和带时区的时间
          // 1.ZoneId的常见方法
          // 获取系统默认的时区
          ZoneId zoneId = ZoneId.systemDefault();
          System.out.println(zoneId);
  
          // 获取Java支持的全部时区Id
          System.out.println(ZoneId.getAvailableZoneIds());
  
          // 把某个时区Id封装成ZoneLd对象
          ZoneId zoneId1 = ZoneId.of("America/New_York");
  
          // 2.ZonedDateTime：带时区的时间
          // 获取某个时区的ZonedDateTime对象
          ZonedDateTime now = ZonedDateTime.now(zoneId1);
          System.out.println(now);
  
          // 获取世界标准时间
          ZonedDateTime now1 = ZonedDateTime.now(Clock.systemUTC());
          System.out.println(now1);
  
          // 获取系统默认时区的ZoneDateTime对象
          ZonedDateTime now2 = ZonedDateTime.now();
          System.out.println(now2);
  
          // 其他的with plus minus和之前的一样
      }
  }
  ```

### Instant（代替Date）

* Instant 时间线上的某个时刻/时间戳

* 通过获取Instant的对象可以拿到此刻的时间，该时间由两部分组成：从1970-01-01 00:00:00开始走到此刻的总秒数＋不够1秒的纳秒数

* 传统的Date类，只能精确到毫秒，并且是可变对象

* ﻿﻿新增的Instant类，可以精确到纳秒，并且是不可变对象，推荐用Instant代替Date

* ```java
  public class Test {
      public static void main(String[] args) {
          // 1.创建Instant对象，获取此刻时间信息
          Instant now = Instant.now();
  
          // 2.获取总秒数
          long second = now.getEpochSecond();
          System.out.println(second);
  
          // 3.不够一秒的纳米数
          int nano = now.getNano();
          System.out.println(nano);
          System.out.println(now);
  
          // 4.Instant对象的作用：用于程序性能分析，记录用户操作时间点
      }
  }
  ```

### DateTimeFormatter（代替SimpleDateFormat）

* 格式化器，用于时间的格式化和解析

* ```java
  public class Test {
      public static void main(String[] args) {
          // 1.创建一个日期时间格式化信息对象出来
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
  
          // 2.对时间进行格式化
          LocalDateTime now = LocalDateTime.now();
          System.out.println(now);
  
          String rs = formatter.format(now); //正向格式化
          System.out.println(rs);
  
          // 3.格式化时间还有一种方法
          String rs2 = now.format(formatter); //反向格式化
          System.out.println(rs2);
  
          // 4.解析时间：解析时间一般用LocalDateTime提供的解析方法来解析
          String dataStr = "2029年12月12日 12:12:11";
          LocalDateTime ldt = LocalDateTime.parse(dataStr,formatter);
          System.out.println(ldt);
  
      }
  }
  ```

### Duration, Period（其他补充）

* Period（一段时期）：用于计算两个LocalDate对象，相差的年，月，天数字

* ```java
  public class d12_Period {
      public static void main(String[] args) {
          LocalDate start = LocalDate.of(2028,8,10);
          LocalDate end = LocalDate.of(2029,7,15);
  
          // 1.创建Period对象，封装两个日期对象
          Period period = Period.between(start,end);
  
          // 2.通过period对象获得两个日期对象相差信息
          System.out.println(period.getYears());
          System.out.println(period.getMonths());
          System.out.println(period.getDays());
      }
  }
  ```

* Duration（持续时间）：可以用于计算两个时间对象相差的天数、小时数、分数、秒数、纳秒数；支持LocalTime、LocalDateTime、Instant等时间

* ```java
  public class d13_Duration {
      public static void main(String[] args) {
          LocalDateTime start = LocalDateTime.of(2024,12,3,14,21,10);
          LocalDateTime end = LocalDateTime.of(2024,12,3,15,23,44);
          
          // 1.得到Duration对象
          Duration duration = Duration.between(start,end);
          
          // 2.获取两个时间对象间隔的信息
          System.out.println(duration.toDays());
          System.out.println(duration.toHours());
          System.out.println(duration.toMinutes());
          System.out.println(duration.toSeconds());
          System.out.println(duration.toMillis());
          System.out.println(duration.toNanos());
      }
  }
  ```

# Arrays类

* 用于操作数组的工具类

## 基础语法

```java
public class Test1 {
    public static void main(String[] args) {
        // 1.直接返回数组内容
        int[] arr = {10, 20, 30, 40, 50};
        System.out.println(Arrays.toString(arr));

        // 2.拷贝数组（包前不包后）
        int[] arr2 = Arrays.copyOfRange(arr,1,4);
        System.out.println(Arrays.toString(arr2));

        // 3.拷贝数组，可以指定长度
        int[] arr3 = Arrays.copyOf(arr,10);  // 剩余部分是0
        System.out.println(Arrays.toString(arr3));

        // 4.把数组中的原数据改为新数据存进去
        double[] prices = {99.8, 128, 100};
        Arrays.setAll(prices, new IntToDoubleFunction() {
            @Override
            public double applyAsDouble(int value) {
                // value每次都会拿一个索引
                return prices[value] * 0.8;
            }
        });

        // 5.对数组进行排序（默认升序排序）
        Arrays.sort(prices);
        System.out.println(Arrays.toString(prices));
    }
}
```

## 比较集合中对象的大小

### 第一种方法

* 让该对象的类实现Comparable（比较规则）接口，然后重写compareTo方法，自己来制定比较规则

* 学生类

* ```java
  public class Student implements Comparable<Student>{
      private String name;
      private double height;
      private int age;
  
      // 制定比较规则
      // this  o
      @Override
      public int compareTo(Student o) {
          // 约定：认为左边对象 大于 右边对象 请你返回正整数
          // 约定：认为左边对象 小于 右边对象 请你返回负整数
          // 约定：认为左边对象 等于 右边对象 请你一定要返回0
  //        if (this.age > o.age){
  //            return 1;
  //        }else if (this.age < o.age){
  //            return -1;
  //        }
  //        return 0;
  
          return this.age - o.age;  // 一行代码就可以搞定，升序
          // return o.age - this.age;  // 降序
      }
  
      public Student() {
      }
  
      public Student(String name, double height, int age) {
          this.name = name;
          this.height = height;
          this.age = age;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public double getHeight() {
          return height;
      }
  
      public void setHeight(double height) {
          this.height = height;
      }
  
      public int getAge() {
          return age;
      }
  
      public void setAge(int age) {
          this.age = age;
      }
  
      @Override
      public String toString() {
          return "Student{" +
                  "name='" + name + '\'' +
                  ", height=" + height +
                  ", age=" + age +
                  '}';
      }
  }
  ```

* 主程序

* ```java
  public class Test2 {
      public static void main(String[] args) {
          // 目标：掌握如何对数组中的对象进行排序
          Student[] students = new Student[4];
          students[0] = new Student("蜘蛛精", 169.5,23);
          students[1] = new Student("紫霞", 163.8,26);
          students[2] = new Student("紫霞", 163.8,26);
          students[3] = new Student("至尊宝", 167.5,24);
  
          // 1.对数组进行排序
          Arrays.sort(students);
          System.out.println(Arrays.toString(students));
      }
  }
  ```

### 第二种方法

* 使用下面这个sort方法，创建Comparator比较器接口的匿名内部类对象，然后自己制定比较规则。

* `public static <T> void sort（T[] arr, Comparator<？super T> c）对数组进行排序（支持自定义排序规则)`

* ```java
  // 2.public static <T> void sort（T[] arr, Comparator<？super T> c）
          // 参数一：需要排序的数组
          // 参数二：Comparator比较器对象（用来制定比较规则）
          Arrays.sort(students, new Comparator<Student>() {
              @Override
              public int compare(Student o1, Student o2) {
                  // 制定比较规则了：左边对象 o1  右边对象 o2
                  // 约定：认为左边对象 大于 右边对象 请你返回正整数
                  // 约定：认为左边对象 小于 右边对象 请你返回负整数
                  // 约定：认为左边对象 等于 右边对象 请你一定要返回0
  //                if (o1.getHeight() > o1.getHeight()) {
  //                    return 1;
  //                } else if (o1.getHeight() < o1.getHeight()) {
  //                    return -1;
  //                }
  //                return 0;  // 升序
                  return Double.compare(o1.getHeight(),o2.getHeight()); //升序
              }
          });
          System.out.println(Arrays.toString(students));
  ```

### 注意：两种方法都要满足官方约定

* 如果认为左边对象 大于 右边对象 应该返回正整数
* 如果认为左边对象 小于 右边对象 应该返回负整数
* 如果认为左边对象 等于 右边对象 应该返回0整数

# JDK8新特性：Lambda表达式

## 认识Lambda表达式

* Lambda表达式是JDK8开始新增的一种语法形式；作用：用于简化匿名内部类的代码写法

* 格式：`(被重写方法形参列表) ->{被重写方法代码体}`

* 注意：Lambda表达式只能简化函数式接口的匿名内部类！！！

* 什么是函数式接口？

  - ﻿有且仅有一个抽象方法的接口
  - ﻿﻿注意：将来我们见到的大部分函数式接口，上面都可能会有一个@Functionallnterface的注解，有该注解的接口就必定是函数式接口

* ```java
  public class Test1 {
      public static void main(String[] args) {
          // 目标：认识Lambda表达式
  //        Animal a = new Animal(){
  //            @Override
  //            public void run() {
  //                System.out.println("狗跑得很快");
  //            }
  //        };
  //        a.run();
  
          //注意：Lambda表达式只能简化函数式接口的匿名内部类，而并不能简化全部匿名内部类
          //错误的代码
  //        Animal a = () -> {
  //            System.out.println("狗跑得很快");
  //        }
  //        a.run();
  //        Swimming s = new Swimming() {
  //            @Override
  //            public void swim() {
  //                System.out.println("学生游泳");
  //            }
  //        };
  //        s.swim();
  
          Swimming s = () -> {
              System.out.println("学生游泳");
          };
          s.swim();
      }
  }
  
  interface Swimming{
      void swim();
  }
  
  abstract class Animal{
      public abstract void run();
  }
  ```

## Lambda表达式的省略规则

* 参数类型可以省略不写
* ﻿﻿如果只有一个参数，参数类型可以省略，同时（）也可以省略
* ﻿﻿如果Lambda表达式中的方法体代码只有一行代码，可以省略大括号不写，同时要省略分号！此时，如
   果这行代码是return语句，也必须去掉return不写

# JDK8新特性：方法引用

## 静态方法的引用

* 类名：静态方法

* 使用场景：如果某个Lambda表达式里只是调用一个静态方法，并且前后参数的形式一致，就可以使用静态方法引用

* ```java
  //原始写法：对数组中的学生对象，按照年龄升序排序
  //        Arrays.sort(students, new Comparator<Student>() {
  //            @Override
  //            public int compare(Student o1, Student o2) {
  //                return o1.getAge() - o2.getAge();  //按照年龄升序排序
  //            }
  //        });
  
          // 使用Lambda简化后的形式
  //        Arrays.sort(students, ((o1, o2) -> o1.getAge() - o2.getAge()));
  
          // 调用方法
          // Arrays.sort(students,((o1, o2) -> CompareByDate.CompareByAge(o1,o2)));
  
          //静态方法引用
          Arrays.sort(students,   CompareByDate::CompareByAge);
  ```

## 实例方法的引用

* 对象名：实例方法
* 使用场景：如果某个Lambda表达式里只是调用一个实例方法，并且前后参数的形式一致，就可以使用实例方法引用

## 特定类型方法的引用

* 类型：：方法

* 使用场景：如果某个Lambda表达式里只是调用一个实例方法，并且前面参数列表中的第一个参数是作为方法的主调，

  后面的所有参数都是作为该实例方法的入参的，则此时就可以使用特定类型的方法引用

* ```java
  public class Test2 {
      public static void main(String[] args) {
          String[] names = {"boby", "angela", "ANdy", "dlei", "caocao", "Babo", "jack", "Cici"};
  
          // 进行排序（默认是按照字符串首字母编号进行升序排序的）
          // Arrays.sort(names);
  
          // 要求忽略首字母大小写进行排序
  //        Arrays.sort(names, new Comparator<String>() {
  //            @Override
  //            public int compare(String o1, String o2) {
  //                // 制定比较规则 o1 = "Andy"  o2 = "angela"
  //                return o1.compareToIgnoreCase(o2);
  //            }
  //        });
  
  //        Arrays.sort(names, ((o1, o2) -> o1.compareToIgnoreCase(o2));
          
          // 特定类型方法引用
          Arrays.sort(names, String::compareToIgnoreCase);
  
      }
  }
  ```

## 构造器引用（不常见）

* 类名::new
* 使用场景：如果某个Lambda表达式里只是在创建对象，并且前后参数情况一致，就可以使用构造器引用

# 正则表达式

## 概念，初体验

* 正则表达式，就是由一些特定的字符组成，代表的是一个规则

* 作用

  * 用来校验数据格式是否合法
  * 在一段文本中查找满足要求的内容

* ```java
  public class Test1 {
      public static void main(String[] args) {
      }
  
      // 使用正则表达式
      public static boolean checkQQ1(String qq){
          return qq != null && qq.matches("[1-9]\\\\d{5,19}");
      }
  
  
      // 使用一般方法
      public static boolean checkQQ(String qq){
          // 1.先排除一些特殊情况
          if (qq == null || qq.startsWith("0") || qq.length() < 6 || qq.length() > 20){
              return false;
          }
  
          // 2.判断qq号码中是否全是数字
          for (int i = 0; i < qq.length(); i++) {
              // 根据索引提取当前位置处字符
              char ch = qq.charAt(i);
              // 判断ch是不是数字
              if (ch < '0' || ch > '9'){
                  return false;
              }
          }
          // 说明之后qq号肯定合法
          return true;
      }
  }
  ```

## 书写规则

* String提供了一个匹配正则表达式的方法，[判断字符串是否匹配正则表达式]()，匹配返回true，不匹配返回false

* `public boolean matches(String regex)`

* ![IMG_0757](/Users/cjf/Downloads/IMG_0757.JPG)

  ![IMG_0759](/Users/cjf/Downloads/IMG_0759.JPG)

## 作用一应用案例

```java
public class Test3 {
    public static void main(String[] args) {

    }

    // 手机号码
    public static void checkPhone(){
        while (true) {
            System.out.println("请输入您的电话号码");
            Scanner sc = new Scanner(System.in);
            String phone = sc.nextLine(); // 用于接收一行数据

            if (phone.matches("(1[3-9]//d{9}) || (0\\d{2,7}--?[1-9]{4,19})")){
                System.out.println("您输入的代码格式正确");
            }else {
                System.out.println("您输入的代码格式不正确");
            }
        }
    }

    // 邮箱
    public static void checkEmail(){
        while (true) {
            System.out.println("请输入您的邮箱号码");
            Scanner sc = new Scanner(System.in);
            String phone = sc.nextLine(); // 用于接收一行数据

            if (phone.matches("\\w{2,20}(\\.\\w{2,10}){1,2}")){
                System.out.println("您输入的邮箱格式正确");
            }else {
                System.out.println("您输入的邮箱格式不正确");
            }
        }
    }
```

## 作用二用于查找信息

![IMG_0489](/Users/cjf/Downloads/IMG_0489.JPG)

## 用于搜索替换，分割内容

```java
public class Test6 {
    public static void main(String[] args) {
        // 1.使用正则表达式进行内容的替换
        String s1 = "古力娜扎ai8888迪丽热巴999aa5566马尔扎哈fbbfsfs42425卡尔扎巴";
        System.out.println(s1.replaceAll("\\w+","-"));

        // 2.优化内容
        String s2 = "我我我喜欢编编编编编编编编编编编编程程程";
        System.out.println(s2.replaceAll("(.)\\1+","$1"));

        // 3.按照正则表达式的内容进行分割字符串，返回一个字符串数组
        String s3 = "古力娜扎ai8888迪丽热巴999aa5566马尔扎哈fbbfsfs42425卡尔扎巴";
        String[] names = s3.split("\\w+");
        System.out.println(Arrays.toString(names));
    }
}
```

# 异常

## 认识异常

* 异常就是代表程序出现的问题

* 异常的体系（Java.lang.Throwable）

  * Error：代表的系统级别错误（属于严重问题），也就是说系统一旦出现问题，sun公司会把这些问题封装成Error对象给出来，说白了，Error是给sun公司自己用的，不是给我们程序员用的，因此我们开发人员不用管它
  * Exception：叫异常，它代表的才是我们程序可能出现的问题，所以，我们程序员通常会用Exception以及它的孩子来封装程序出现的问题
    * RuntimeException：及其子类，编译阶段不会出现错误提醒，运行时出现的异常（如：数组索引越界异常）
    * 其他异常：编译阶段就会出现错误提醒的（如：日期解析异常）

* 异常的作用

  * 异常是用来查寻系统Bug的关键参考信息
  * 异常可以作为方法内部的一种特返回值，以便通知上层调用者底层的执行情况

* 异常的处理方法

  * 拋出异常（throws）：在方法上使用throws关键字，可以将方法内部出现的异常抛出去给调用者处理

  * 捕获异常（try...Catch）：直接捕获程序出现的异常

  * ```java
    package d3_exception;
    
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    
    // 异常处理方法初步
    
    public class Test1 {
        public static void main(String[] args) throws ParseException {  // 抛给JVM
            // Integer.valueOf("abc");
    
    //        int[] arr = {11, 22, 33};
    //        System.out.println(arr[5]);
    
    
            // 第一种处理方式：try-catch
            try { // 试图监视
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d = sdf.parse("2028-11-11 10:24:23");
                System.out.println(d);
            } catch (ParseException e) {  // 捕获
                throw new RuntimeException(e);
            }
    
            // 第二种处理方式：抛出（在方法之后抛出）
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = sdf.parse("2028-11-11 10:24:23");
            System.out.println(d);
        }
    }
    ```

## 自定义异常

### 概念

* Java无法为这个世界上全部的问题都提供异常类来代表，如果企业自己的某种问题，想通过异常来表示，以便用异常来管理该问题，那就需要自己来定义异常类了

### 种类

* 自定义运行时异常
  - ﻿﻿定义一个异常类继承RuntimeException
  - ﻿﻿重写构造器
  - ﻿﻿通过throw new 异常类（xxx）来创建异常对象并抛出，编译阶段不报错，提醒不强烈，运行时才可能出现！！
* 自定义编译时异常
  * 定义一个异常类继承Exception
  * ﻿﻿重写构造器
  * ﻿﻿通过throw new 异常类（xxx）来创建异常对象并抛出。
     编译阶段就报错，提醒更加强烈

### 例子

* 主程序

* ```java
  package d3_exception;
  
  // 自定义异常
  
  public class Test2 {
      public static void main(String[] args) {
          // 保存一个合法的年龄
          try {
              saveAge(160);
              System.out.println("底层执行成功");
          } catch (Exception e) {
              e.printStackTrace();
              System.out.println("底层出现了bug");
          }
      }
      // 自定义运行时异常
      public static void saveAge(int age){
          if (age > 0 && age < 150){
              System.out.println("年龄被保存成功" + 150);
          }else {
              // 用一个异常对象来封装这个问题
              // throw 抛出去这个异常对象
              throw new AgeIllegalRuntimeException("/age is illegal, your age is" + age);
          }
      }
  
      // 自定义编译时异常
      public static void saveAge2(int age) throws AgeIllegalException{
          if (age > 0 && age < 150){
              System.out.println("年龄被保存成功" + 150);
          }else {
              // 用一个异常对象来封装这个问题
              // throw 抛出去这个异常对象
              // throws 用在方法上，抛出方法内部的异常
              throw new AgeIllegalException("/age is illegal, your age is" + age);
          }
      }
  }
  ```

* 异常类继承RuntimeException

* ```java
  package d3_exception;
  // 1.必须让这个类继承RuntimeException，才能成为一个运行时异常类
  public class AgeIllegalRuntimeException extends RuntimeException{
      public AgeIllegalRuntimeException() {
      }
  
      public AgeIllegalRuntimeException(String message) {
          super(message);
      }
  }
  ```

* 异常类继承Exception

* ```java
  package d3_exception;
  // 1.必须让这个类继承Exception，才能成为一个运行时异常类
  public class AgeIllegalException extends Exception{
      public AgeIllegalException() {
      }
  
      public AgeIllegalException(String message) {
          super(message);
      }
  }
  ```

## 异常的处理

### 捕获异常，记录异常并响应合适的信息给用户

* 处理方法

* ```java
  // 异常的处理
  public class Test3 {
      public static void main(String[] args) {
          try {
              test1();
          } catch (FileNotFoundException e) {
              System.out.println("您要找的文件不存在");
              e.printStackTrace(); // 打印出这个异常对象的信息，记录下来
          } catch (ParseException e) {
              System.out.println("您要解析的时间有问题");
              e.printStackTrace();
          }
      }
  
      public static void test1() throws FileNotFoundException, ParseException {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date d = sdf.parse("2028-11-11 10:24:23");
          System.out.println(d);
          test2();
      }
  
      public static void test2() throws FileNotFoundException {
          // 读取文件的
          InputStream is = new FileInputStream("D:/meinv.png");
      }
  }
  ```

* 更好的处理：直接抛Exception

* ```java
  // 异常的处理
  public class Test3_2 {
      public static void main(String[] args) {
          try {
              test1();
          } catch (Exception e) {
              System.out.println("您要解析的时间有问题");
              e.printStackTrace();
          }
      }
  
      public static void test1() throws Exception {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date d = sdf.parse("2028-11-11 10:24:23");
          System.out.println(d);
          test2();
      }
  
      public static void test2() throws Exception {
          // 读取文件的
          InputStream is = new FileInputStream("D:/meinv.png");
      }
  }
  ```

### 捕获异常，尝试重新修复

* 例子

* ```java
  package d3_exception;
  
  // 捕获异常，尝试重新修复
  
  import java.util.Scanner;
  
  public class Test4 {
      public static void main(String[] args) {
          // 需求：调用一个方法，让用户输入一个合适的价格返回为止
          while (true) {
              try {
                  System.out.println(getMoney());
                  break;
              } catch (Exception e) {
                  System.out.println("请您输入合法的数字！！！");
              }
          }
      }
  
      public static double getMoney(){
          Scanner sc = new Scanner(System.in);
          while (true) {
              System.out.println("请输入合适的价格");
              double money = sc.nextDouble();
              if (money >= 0){
                  return money;
              }else {
                  System.out.println("您输入的价格不合适");
              }
          }
      }
  }
  ```

\
