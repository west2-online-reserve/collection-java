# Day02

## Windows快捷键

Ctrl+A=全选

Ctrl+X=剪切

Ctrl+Z=撤销

Ctrl+S=保存

Shift+Delete=永久删除

windows+R=运行命令窗口

Win+E=打开我的电脑

最下栏右键可以打开任务管理器（CtrL+Shift+Esc)

## 常见的Dos命令

1. 打开cmd方式

- 打开windows系统命令提示符

- win+R>输入cmd（推荐使用）

- 在任意地方按住SHIFT+鼠标右键>在此处打开Powershell窗口（本质是一样的）

- 直接打开我的电脑，在文件导航中的文件目录前输入cmd（注意cmd与文件目录之间需要留有空格）

  以管理员身份运行：右键以管理员身份运行命令指示符

2. 常见Dos命令：*注意一定要是英文字符*

   - 盘符切换：直接输入目标目录+冒号+回车

   - 查看当前盘符的所有目录：直接输入dir

   - 切换目录：cd （change directory）

     - **cd.. 进入上一级**（重点）

     - 进入同一盘内的目录： cd+空格+想要进入的目录

     - 进入不同盘：cd /d E:\文件名

       注意/和\的区别 /用于硬盘而\用于文件名

     - 清理屏幕：cls（clean screen）

   - 退出终端：exit

   - 查看ip：ipconfig

   - 通过命令提示符可以打开应用程序：

     - calc（计算器）
     - notepad（记事本）

   - ping命令：在命令提示符里不可以使用Crtl+C，右键就是复制，可以在命令提示符中右键就是粘贴，在命令提示符中粘贴网址可以查看某网址的ip地址

   - 建立文件夹：md test

     转移到文件夹目录后输入：cd>文件名（如a.txt）      就可以创建新文件

   - 删除文件 ：del+空格+文件名
   - 删除文件夹：rd+空格+文件夹名 **(注意删除文件夹前需要先删除文件夹里面的文件，而且删除文件夹时需要命令提示符在比文件夹高一级别的目录中进行删除，此时可以使用cd..来进行删除)**

## Hello World

       ### 基本步骤

1.随便新建一个文件夹，存放代码

2.新建一个文件，后缀为java

3.输入代码

```java
public class hello{
	public static void main(String [] args){
		System.out.print("Hello World!");
	}
}
```



4.在命令提示符中编译javac java文件，会生成一个class文件

运行一个class文件（java hello），最终命令提示符中将会出现Hello World！

### Hello World可能出现的问题：

- 必须注意大小写等等，Java大小写敏感，尽量使用英文符号，英语字体
- public class后面是类名，必须和文件名一致
- 

### 详解

- public class 表达一个类，后面是类名
- public static void 是修饰语、关键字不管；main是java的一个方法，只有使用才能执行
- main后面是参数可以不用管



## Java程序运行机制

### 编译型和解释型

1.编译型：中国人把整本书翻译成英语给美国人看(有负责翻译的程序compile，负责编译的程序成为编译器)

2.解释型：美国人直接找了个翻译官，实时翻译

3.两种方式各有优劣：

- 编译型执行速度更加快速，对操作系统性能要求较低（C/C++）
- 解释型对于速度要求不高，边执行边解释（比如网页）

### 程序运行机制

Java将java变为class文件，经过了一次Java编码（javac)器，即进行了一次预编译；再将class文件装载，校验，解释，最终显示在操作系统平台。

即**Java是编译型和解释型的结合体**

## IDEA

### 集成快捷输入

1.psvm

2.sout

## Java基础语法

### 新建一个项目

1.Empty Project》选Java》新建module》project structure（注意sdk和语言版本数字需要一致）》

2.右键src可以继续新建class开始操作！

### 注释、标识符、关键字

#### 注释：

1.注释不会被执行，是进行解释的，为了让我们看懂自己写的东西

2.书写注释是一个非常好的习惯

3.Java注释分为三种：单行注释、多行注释、文档注释

- 单行注释（block）：//  
- 多行注释（line）：/*   内容   */
- 文档注释（JavaDoc）：/**   内容   */
  - 对于文档注释，可以加入一些变量，目前阶段学习用不太到，现在先不学习

4.**平时写代码一定要注意规范**

#### 标识符和关键字

1.所有标识符都应该以字母or￥or_开始

2.首字符之后可以是字母or￥or_or数字的组合

3.已被定义的关键字不能作为变量名or方法名

![关键字](C:\Users\86135\Desktop\标识符.png)

4.**标识符大小写非常敏感**

5.标识符最好是别写拼音，但是可以写汉字**（但是为了规范，用单词，见名知意，考核要求）**

### 数据类型

1.强类型语言：要求变量的使用符合规定，所有变量必须先定义后使用

- 安全性强但是速度慢

2.弱类型语言：

3.Java的数据类型分类两大类：

- 基本类型：
  - 数值类型：
    - 整数类型：byte、short、int（最常用）、long（特殊，会在数字后加L）；
    - 浮点类型（就是小数）：float（需要在数字后加F）、double；
    - 字符类型char，只能定义字符
    - **注意String**不是关键字，是类，但是它可以定义字符串
  - boolean类型：boolean定义是非:boolean flag=true
- 引用类型:除了数据类型以外的都是引用类型，目前不做了解

## 整数拓展

### 进制

1.进制转换：八进制0 十六进制0x 二进制0b

- int i =  0x10（把10用十六进制表示出来）

  - 另外，在十六进制中，0~16用0、1、2、3、4、5、6、7、8、9、A、B、C

  ​        D、E、F来表示

- int i =010（把10用八进制表示出来）

- int i = 0b10（把10用二进制表示出来）



#### 浮点数拓展

eg. 用float和double来表示银行业务

- float f = 0.1f

- double d=0.1

- 但是用sout（f==d)时发现输出false）

eg. 

- float f1 =23143241243214f

- folat f2 =f1+1

- sout(f1=f2）时发现输出true

总结：**浮点数是有限、离散、舍入误差。因此尽量避免使用浮点数进行比较**

3.那么银行业务用什么表示？使用Bigdecimal进行，这是一个数学工具类的东西，在这里不做详解，日后再进行具体介绍。

#### 字符拓展

- char c1 ='a'

- char c2 ='中'

- sout((int)c1)>97

- sout((int)c2)>20013

**所有字符本质还是数字**，因此char还可以如此定义字符：char c3 ='\u0061' 即定义c3为字符a（因为0061代表a）（0061是十六进制）

- 这里的\u是转义字符，代表转义
   - 转义字符很多：如\t，就是制表符；  \n，就是换行

#### 布尔值扩展

先空下啊啊啊，搞不清了![less is more](C:\Users\86135\Desktop\less is more.png)

## 类型转换

### 容量

对于关键字的容量，由低到高有：byte，short，char，int，long，float，double

也就是说，**浮点数类型关键字的容量一定大于整数类型和字符类型**，即使float所占字节数小于long！

如何查看关键字的容量大小：

在这里补充一下各关键字的容量大小：

| 关键字     | 最大容量 | 最小容量 |
| ---------- | -------- | -------- |
| byte（1）  | 128      | -128     |
| short（2） |          |          |
| char（2）  |          |          |
| int（4）   |          |          |
| long（8）  |          |          |
| float(4)   |          |          |
| double(8)  |          |          |



![相关截图](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20230928214830037.png)

char占两个字节是因为char使用的是unicode编码，unicode占两个字节。

另外boolean只占一位

一个字节（Byte）包含8个位（bit），位是计算机内部存储的最小的单位，11001100就是八位二进制数，一字节。

**运算中，不同类型的数据先转换为同一类型，然后进行运算**。

### 强制转换（高到低）

int i =128;

byte b =(byte)i;>>这样导致了内存溢出，是强制转换，原因在于转换时由容量高的关键字变成了容量低的关键字。

### 自动转换（低到高）

转换时由容量低的关键字变成了容量高的关键字。

### 转换时需要注意的事项

1.不能对布尔值进行转换

2.不能把对象类型转换为不相干的类型

3.在把高容量转换为低容量的时候，强制转换

4.转换的时候可能存在内存溢出，或者精度问题。

- 精度问题可能会体现在浮点数在进行转换的时候会失去浮点等问题

- 操作比较大的数的时候，注意溢出问题，尤其是是在计算时，乘法可能会导致容量溢出，此时可以选择先将数字由int转换为long，再输出（不可在输出时转换结果为long，这样不行，原因在于在计算完成时容量已经溢出导致错误）

- ```java
  public class shiyan {
      public static void main(String[] args) {
          int money =100_000;
          int year = 20_0000_0000;
          long total =(long)money*year;
          System.out.println(total);
          System.out.println("==============================");
          System.out.println((int)23.7);
          System.out.println((int)0.8f);
      }
  }
  ```

## 变量、常量、作用域

### 变量

- 定义：变量是可以变化的量

- Java中的变量是程序中最基本的存储单元，要素包含：变量名，变量类型，作用域。

  数据类型+变量名=值；可以使用逗号来隔开，达到声明多个同类型变量的效果。

  **但是不建议一行定义多个类型**

- 注意：
  - 每个变量都必须有类型，可以是基本类型，也可以是引用类型（目前只学了string）
  - 变量名必须是合法的标识符
  - 变量声明每一个都必须以分号结束

#### 作用域

- main方法
- 类变量：关键词：static
- 局部变量：只在大括号内有用,**必须声明和初始化值**
- 实例变量：从属于对象（类），**可以不用初始化**，在方法的外面，类的里面。如果不进行初始化，会输出默认值。（后面学习如何使用）

（0、0.0、u0000、false等） 布尔值默认是false，除了基本类型，其余的默认值都是null

**为了方便初学者，目前都直接用类变量，即多加一个static

### 常量

- 定义：不会变化的值
- 常量的关键字是final，不存在先后顺序

### 变量的命名规范

- 所有变量见名知意
- 类变量：首字母小写和驼峰原则（lastName）
- 局部变量：首字母小写和驼峰原则
- 常量：大写字母和下划线（MAX_VALUE）
- 类名：首字母大写和驼峰原则（Man、GoodMan）
- 方法名：首字母小写和驼峰原则（runRun（））

## 运算符

## ![image-20230930170041652](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20230930170041652.png)

- %表示取余数。名字叫做模运算
- = 赋值运算法
- Java里面的等号是两个==，不等于号是！=
- instanceof是一个运算符
- &&与，||或，!非
- 重点掌握前四个运算符
- Ctrl+D是复制当前行到下一行
- 注意在使用/运算符时要注意结果可能是小数，此时需要对变量进行转化为double类型
- 对于运算中，运算数中有long类型的，操作结果都是long；没有long类型的，无论运算数类型中是否含有int，结果都是int类型。（把long替换成double同理）。
- 关系运算符返回的结果：布尔值（正确，错误）
- c%a即输出c/a所得余数
- 

![img](file:///C:\Users\86135\AppData\Roaming\Tencent\Users\2208526503\QQ\WinTemp\RichOle\9TL0F%6H9~PWVL$@S@5DYWN.png)

![image-20230930171727715](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20230930171727715.png)目前在学运算符，这个问题后面有待解决！现在暂时还不太敢问！

- 本节课自主学习代码

```java
public class Demo09 {
    public static void main(String[] args) {
        int a =10;
        int b =20;
        short c =35;
        byte d =40;
        double f = 10;
        float e =9.9f;
        long g =11L;
        System.out.println(a==b);//学习关系运算符的运用
        System.out.println(a+b);//算术运算符
        System.out.println(a/(double)b);//遇到除法要注意数据类型的转换以免报错
        System.out.println(a%b);//模运算
        System.out.println("===============================================");
        System.out.println((String)(c+d));//关于运算结果的数据类型和运算数据的数据类型的关系
    }

}
```

## 自增自减运算符(一元运算符)

上面提到的++（自增）、--运算符（自减）》一元运算符

- 这里以自增运算符为例，注意a++和++a的区别

- 在Java中，很多数学运算都是通过一些工具类来进行操作的！(Math类)

- 本节课的练习代码：

  ```java
  public class Demo10 {
      public static void main(String[] args) {
          int a =1;
          int b = a++;//“++”在后面时，先给b赋值，再给a+1
          int c = ++a;//“++”在前面时，先给a+1，再给c赋值
          System.out.println(a);
          System.out.println(b);
          System.out.println(c);
          System.out.println("================================================");
          int d = a--;
          int e = --a;
          System.out.println(d);
          System.out.println(e);
      }
  }
  ```

## 逻辑运算符、位运算符

逻辑运算符

- &&与（and）：两个变量都为真，结果才为真；

- ||或（or）：两个结果有一个为真，结果才为真；
- ！非（取反）：如果是真，则变为假，反之同理。
- 如何进行验证：短路运算
- 练习代码：

```java
public class Demo11 {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
        System.out.println(a&&b);//逻辑与运算，两者都为真则输出真
        System.out.println(a||b);//逻辑或运算，两者有一个为真则为真
        System.out.println(!a);//逻辑否运算，如果为真则输出否，如果为否则输出真
        System.out.println(!(a&&b));//逻辑否运算
    }
}
```



位运算符

- 位运算符和二进制有关 ！详细解析请见代码。。。
- 练习代码：

```java
public class Demo11 {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
        System.out.println(a&&b);//逻辑与运算，两者都为真则输出真
        System.out.println(a||b);//逻辑或运算，两者有一个为真则为真
        System.out.println(!a);//逻辑否运算，如果为真则输出否，如果为否则输出真
        System.out.println(!(a&&b));//逻辑否运算
        System.out.println("============================================================");
        
        /*
        位运算符是针对二进制而言的，需要将字符转化为二进制来表示：&、|、^、~、<<、>>;
        eg:
        a=1000 0101
        b=1010 0011
        则有
        a&b=1000 0001(对应位数都是1则为1，其他情况都是0）
        a|b=1010 0111（对应位数有1则为1，没有1则为0）
        a^b=0010 0110（对应位数相同为0，不相同则为1）
        ~b=0101 1100（0变1,1变0）
        1=0000 0001
        2=0000 0010
        4=0000 0100
        8=0000 1000
        16=0001 0000
        2<<3=16（将1左移三位)
        16>>3=2(将1右移三位）
         */
```

- 这里其实有自己的问题，比如如何把字符用二进制表现出来，所以字符都可以使用位运算符进行运算吗？认为有待思考，但是先往后学，自己先把问题放在这里。
- **<<和>>是效率极高的两个位运算符**
  - 死记住，***<<就是将原来的数字乘2，>>就是将原来的数字除以二***

## 三元运算符

- a+=b即a=a+b

​		a-=b即a=a-b

- 字符串连接符：+
  - 当+只要有一方出现String类型时，他会把其他操作数都变成字符串并连接
    - 如果字符串在前面，那么他会直接进行连接不进行计算
    - 如果字符串在后面，那么会先进行计算然后再转化为字符串进行连接

- x ? y : z ,如果x==true,则结果为y，否则结果为z

- 优先级：灵活使用（）等

- 练习代码

  ```java
  public class Demo12 {
      public static void main(String[] args) {
          int a = 10;
          int b = 20;
          int c = 6 ;
          System.out.println(""+a+b);//字符串在前则不进行计算
          System.out.println(a+b+"");//字符串在后则先进行计算
          System.out.println("====================================================");
          double salary = a*b;
          String type = salary>2000?"Yes":"No";//定义了一个新变量，对于变量使用了三元运算符——条件运算符，对于这个条件运算符似乎不能添加一个完整的语句，有待思考。。
          System.out.println(type);
          System.out.println("======================================================");
          System.out.println(a+b/a);//除法运算优先级大于加法运算
          System.out.println(a+b*a);//乘法运算优先级大于加法运算
          System.out.println(a+b%c);//模运算优先级大于加法运算
          System.out.println("======================================================");
          //我请问呢++、--的优先级是怎么样的呢
  
      }
  }
  ```

  课后问题：我请问呢++、--的优先级是怎么样的呢

## 包机制（感觉好像目前用不到）

- 包的本质就是一个文件夹
- 包名有规范：一般利用公司域名倒置来进行命名
- 完了再学哇，感觉用不到了

## JavaDoc（文档注释）

感觉还是没什么用，笑鼠，完了再学吧





