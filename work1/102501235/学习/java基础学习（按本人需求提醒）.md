# java学习（按本人需求记录）



## 知识前提

java是值传递

所有的标识符都应该以字母，美元符，下划线开始

要有写注释的习惯

单行注释：//这里写注释（快捷键：ctrl+/）

多行注释：/*这里写注释 */

数字之间可以用下划线分割（下划线不会被输出）

一个字节等于八位

修饰符不存在先后顺序

（四个修饰符：public，private，protected，default）

（修饰符 属性类型 属性名 = 属性值）

凡是属于IO流的类如果不关闭会一直占用资源，要养成用完就关的好习惯



### 八大基本数据类型

整数：byte(一个字节),short(2个字节),int(4个字节),long(8个字节)（long类型要在数字后面加个L）

{%d}{%nd：表示占n个字符宽度，向右对齐}{%-nd：表示占n个字符宽度，向左对齐}{%0nd：可以补齐数据前方的0}

小数：float,double（float类型要在数字后面加个F）{%f}{%.nf：表示保留n位小数}

字符：char（一个单纯的字）{%c}

字符串：String(不是关键字，是类){%s}

布尔值：boolean（代表是非，只有true or false）

byte,short,char<int<long<float<double

/*  最好完全避免使用浮点数进行比较

​     所有字符本质还是数字（编码Unicode表）  */



### String相关知识和方法应用

String不是关键字，是类{%s}

String是不可变的，而StringBuilder是可变的

每次对String的修改，都会创建一个新的String对象，可能导致内存浪费

StringBuilder可以在原地修改字符串内容，且不会创建新的对象（使用是要new一个StringBuilder）

```java
StringBuilder result = new StringBuilder();
result.append();
//这是StringBuilder的方法，可以在字符串末尾追加内容（内容可以是char,String,int,double等类型）
```



#### 保留小数位数

```java
double num = 123.456;
String formatted = String.format("%.2f",num);
//输出formatted的结果为：123.46
```



#### 删除字符串中的字符

```java
while(s1.contains(s2)){
    int index = s1.indexOf(s2);//索引字符串s1中出现s2的位置
    s1 = s1.substring(0, index) + s1.substring(index + s2.length());
}//从头开始索引字符串s1，跳过出现s2字符的位置，索引到结尾
```



#### 处理数据的一些方法

```java
s.charAt(i)//获取字符串第i个字符

String[] words = input.split(" ");//将原语句的空格作为划分，形成字符串数组
String[] inputs = input.trim().split("\\s+");//可以避免分隔处多个空格的情况，去除中间所有的空格
String word = words[i];
boolean result = word.endsWith(".");//检查字符串是否以“.” 结尾，是为true，不是为false
word = word.substring(0,word.length()-1)//从第0个字符索取到第（length-1）个
```



### 进制

二进制0b

八进制0

十六进制0x



###  转义字符

换行：\n

制表符：\t



### 布尔值扩展

```java
boolean flat = true
if (flat==true){}//新手
if (flat){}//老手
```



### 转换

强制转换 （类型）+变量名   高--低 

自动转换   低--高

/*  不能对布尔值进行转换

​     不能把对象类型转换为不相干的类型

​     转换的时候可能存在内存溢出，或者精度问题   */

```java
//使用Integer.to + 转换的类型
//例如
int number = 0;
String numStr = Integer.toString(number);
```



### 变量

每个变量都有类型，类型可以是基本类型，也可以是引用类型

所有变量，方法，类名：见名知意

类成员变量：首字母小写和驼峰原则:monthSalary

局部变量：首字母小写和驼峰原则

类名：首字母大写和驼峰原则

方法名：首字母小写和驼峰原则



```java
public class Hello {
    //类变量 static
    static double salary = 250;
    
    //实例变量：从属于对象：
    String name;
    int age;
     
    //main方法
    public static void main(String[] args) {
        //局部变量：必须声明和初始化值
        int i = 10;
        System.out.println(i);
        
        //实例变量
        new Hello()//要使用alt+enter
        //使其变成：Hello hello = new Hello();
        System.out.println(Hello.age);
        System.out.println(Hello.name);
        
        //类变量
        System.out.println(salary);
    }
}
```



### 常量

初始化后不能再改变值，不会变动的值，使用大写字符和下划线

```java
final 常量名=值
final double PI=3.14;
```



### 运算

a++：先赋值，再自增

++a：先自增，再赋值

a+=b：a=a+b

a-=b：a=a-b

幂运算：Math.pow(2,3)=8  //要使用alt+enter，获得返回值

位运算：（效率极高）

字符串比较：

```java
String c = "1";
c.equals("+") //（比较字符串）
```



#### 位运算

左移<<：高位直接丢弃，低位补0

右移>>：低位直接丢弃，符号位是什么高位补什么

无符号右移>>>：使用0填充高位

```java
/*
A = 0011_1100
B = 0000_1101

A&B = 0000_1100(两位均为1则为1，否则均为0)
A|B = 0011_1101(两位只要有一位为1就为1，其余均为0)
A^B = 0011_0001(两位相同为0，不同为1)
~B = 1111_0010

2*8 = 16 2*2*2*2
<<  *2
>>  /2
0000_0000    0
0000_0001    1
0000_0010    2
0000_0011    3
0000_0100    4
0000_1000    8

System.out.println(2<<3);
(意思是左移3位，等于16)

*/
```

（字符串连接符）

```java
int a = 10;
int b = 20;

System.out.printle(""+a+b); //结果为1020
System.out.printle(a+b+""); //结果为30
```

#### 优先级

![](C:\Users\JJJ\Desktop\work\work01\学习\屏幕截图 2025-11-14 180812.png)



### 包机制

（类似于文件夹）

一般利用公司域名倒置作为包名

```java
import package1[.package2...]
/*引用其他的包
例如：import good.night.xy;
     import good.night.*;(这个可以导入这个包下所有的类)
```



## 流程控制

### Scanner对象

两个方法

next()：

1. 一定要读取到有效字符后才可以结束输入
2. 对输入有效字符之前的空格，next()方法会自动将其去掉
3. 只有输入有效字符后才将其后面输入的空白作为分割号或结束号
4. next()不能得到带有空白的字符串

nextLine():

1. 以Enter为结束符，也就是说nextLine()方法返回值的是输入回车之前的所有字符
2. 可以获得空白



### switch

```java
switch(expression){
    case value:
        //语句
        break;
    case value:
        //语句
        break;
    //你可以有任意数量的case语句
    default:
        //语句
}
```



## 方法

方法是解决一类问题的步骤的有序组合

方法在程序中被创建，在其他地方被引用

方法包含于类或对象中

原子性：方法只完成一个功能，这样有利于后期拓展

例如：（用static使方法能被调取，或new一下该类的名称调用）

```java
public static int add(int a,int b){
        return a+b;
//静态方法：static（和类一起加载的）
//非静态方法（类实例化 之后才存在）
```



当方法返回一个值时，方法调用通常被当作一个值

```java
int larger = max(30,40);
```

当方法返回值是void，方法调用一一定是一条语句

```jade
System.out.println("Hello,world!")
```



### 方法重载

1. 方法名称必须相同
2. 参数列表必须不同（个数不同或类型不同或参数排列顺序不同）
3. 返回值可以相同，也可以不相同
4. 编译器可以根据方法参数类型，样子去判断



### 可变参数

（避免编写方法时因许多不确定的参数，而麻烦的编写大量的类）

例如：

```java
public void test(int... i){
        System.out.println(i[0]);
        System.out.println(i[1]);
        System.out.println(i[2]);
        ...
    }
```



### 递归

递归头：什么时候不需要调用自身方法。如果没有头，将陷入死循环

递归体：什么时候需要调用自身方法。

（能不用递归就不用递归）

```java

```



## 数组

数组是相同类型数据的有序集合

数据一旦被创建，大小就不可以改变

数组本身就是对象，数组对象本身是在堆中的 

数组下标是从0开始

```java
int[] nums;//声明一个数组
nums = new int[10];//创建一个数组,容纳10个元素

nums[0] = 1;//给数组元素赋值
nums[1] = 2;
nums[2] = 3;
...
nums[9] = 10;

System.out.println(nums[9]);
//获取数组长度：array.length
```

### 初始化

```java
//静态初始化
int[] a = {1,2,3,4,5};
//动态初始化:包含默认初始化
int[] b = new int[10];
```

### 二维数组

```java
int[][] array = {{1,2},{3,4},{5,6}}
```

### Arrays类

```java
System.out.println(Arrays.toString(a))//打印数组元素
//运用Arrays类可以避免重复造轮子
Arrays.sort(a)//进行升序
Arrays.fill()//赋值
    System.out.println(Arrays.toString(a))

```

### 冒泡排序

```java
//数字的冒泡排序
public static int[] sort(int[] array) {
    for(int i=0;i<array.length-1;i++){
        for(int j=0;j<array.length-1-i;j++){
            if(array[j+1]<array[j]){
                int temp = array[j];
                array[j] = array[j+1];
                array[j+1] = temp;
            }
        }
    }
    return array;
}
//字符串的冒泡排序
public static String[] sort(String[] array, int K, int N){
    for(int i = 0; i < K; i++){
        for(int j = 0; j < N-1-i; j++){
            if(array[j].compareTo(array[j + 1]) > 0){//用compareTo将两个字符串按字典序逐字符比较Unicode值
                String temp = array[j];
                array[j] = array[j+1];
                array[j+1] = temp;
            }
        }
    }
    return array;
}
```

### 将一串数字打印成数组

```java
Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int[] n = new int[s.length()];
        for (int i = 0; i < n.length; i++) {
            n[i] = s.charAt(i) - '0';
            //s.charAt(i)：获取字符串第i个字符（索引从0开始）
            //'0'是字符0的ASCII码值，它的十进制值是48
            //计算原理：'1'-'0'=49-48=1（获得整数类型的1）
        }
```



## 列表

```java
//创建常用list
List<String> list1 = new ArrayLiat<>();
List<Integer> list1 = new ArrayLiat<>();
//常用方法
list.add();//在末尾添加元素
list.add(int index,);//在指定位置插入元素 
list.get();//获得指定位置的元素
list.remove(int index);//删除指定位置的元素；
list.remove(String s);//删除指定的元素（首次出现）
list.size();//返回列表大小
liat.set(int index,String s);//修改指定位置的元素
boolean empty = list.isEmpty();//判断是否为空
boolean has = list.contains();//判断是否包含某个元素
```



## BigInteger的相关用法

（注意：相比Int和long，BigInteger的运算速度慢很多）

```java
//先导入
import java.math.BigInteger;
//创建
BigInteger bigInt = new BigInteger("123456789");
//使用常量
BigInteger zero = BigInteger.ZERO;
BigInteger one = BigInteger.ONE;
BigInteger ten = BigInteger.TEN;
//数学运算（BigInteger中不能直接加减）
BigInteger a = new BigInteger("100");
BigInteger b = new BigInteger("200");
BigInteger sum = a.add(b);
BigInteger sum = a.subtract(b);
BigInteger sum = a.multiply(b);
BigInteger sum = a.divide(b);
BigInteger sum = a.mod(b);//or BigInteger sum = a.remainder(b);
int cmp = a.compareTo(b);//比较大小
BigInteger absValue = a.abs(b);
BigInteger max = a.max(b);
BigInteger min = a.min(b);
//转换为基本类型（精度可能会丢失）
long longValue = a.longValue();
int intValue = a.intValue();
double doubleValue = a.doubleValue();
//转换为字符串
String str = a.toString();//十进制
String binaryStr = a.toString(2);//二进制
String hexStr = a.toString(16);//十六进制
```





