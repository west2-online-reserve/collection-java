# Java流程控制

## 01：用户交互Scanner

- Scannery对象：我们可以通过Scannery类（工具包）来获取用户的输入

  - 基本语法：Scanner s = new Scaaner(System.in)//创建一个扫描对象，用于接收键盘数据
  - 插入包：import java.util.Scanner

- 使用next（）、nextline（）来获取用户的数据

  - 对于next（）：在输入有效字符之前的空格，将自动删除；在输入有效字符之后的空格，将自动识别为暂停符和终止符

  - 对于nextline（）：空格符可以存在，输入回车之前的所有字符

- 可以使用scaaner.hasNext()，scaaner.hasNextLine()来判断用户是否输入字符串；

- 凡是属于IO流的类如果不关闭scanner会一直占用资源，要养成良好习惯用完就关掉，使用scanner.close（）关掉；

- 课程学习代码：

```java
package com.vegetabale.www;
import java.util.Scanner;
public class A1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请用户输入数据");
        String str = scanner.nextLine();
        System.out.println("用户输入的数据为："+str);
        scanner.close();
    }
}
```

## 02:Scanner进阶使用

- 第一个课堂练习

  - ```java
    `package com.vegetabale.www;
    import java.util.Scanner;
    public class A2 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int i ;
            float f ;
            System.out.println("请输入整数：" );
            if(scanner.hasNextInt()){//这里学习到了hasNextInt和nextInt的用法
                i =scanner.nextInt();
                System.out.println("用户输入的整数为"+i);
    
            } else{
                System.out.println("用户输入的为小数！");
            }
            if(scanner.hasNextFloat()){
                f = scanner.nextFloat();
                System.out.println("用户输入的小数为"+f);
    
            } else{
                System.out.println("用户输入的为整数！");
            }
            scanner.close();
    
    
        }
    }
    ```

- 第二个课堂练习(我们可以输入多个数字，并求其总和与平均数，每输一个数字用回车确认，通过输入非数字来结束输入并输入执行结果)

  - ```java
    package com.vegetabale.www;
    import java.util.Scanner;
    public class A3 {
        public static void main(String[] args) {
            double  d ;
            double sum = 0;
            int i = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入你的数字:");
            while(scanner.hasNextDouble()){
                d = scanner.nextDouble();
                sum = sum + d ;
                i = ++i;
                System.out.println("目前输入的数字总和为："+sum);
                System.out.println("目前输入数字的平均数为为："+(sum/i));
            }
    
    
        }
    }
    
    ```

  - 问题是++i和i++改一下之后发现只有I++能用，为什么呢？

## 03、顺序结构

- 他是任何算法都离不开的一种结构

## 04、05if、switch选择结构

- if单选择结构

  - if（布尔表达式）{

    //如果布尔表达式为true则执行表达的语句

    }

  - equals：用来判断字符串是否相等，尽量不要用==来判断字符串是否相等。

  - 练习代码：

    ```java
    package com.vegetabale.www;
    import java.util.Scanner;
    public class A4 {
        public static void main(String[] args) {
            System.out.println("请用户输入：");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if(str.equals ("Hello")){
                System.out.println("End");
            }
        }
    }
    ```

- if双选择结构

  - ![image-20231002183516834](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231002183516834.png)

  - ```java
    ```

  - 

- if多选择结构

![image-20231002183907896](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231002183907896.png)

```java
package com.vegetabale.www;
import java.util.Scanner;
public class A4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你的考试成绩");
        double grades = scanner.nextDouble();
        if(grades >=90 && grades <=100){
            System.out.println("A");
        } else if(grades >=80 && grades<90){
            System.out.println("B");
        } else if(grades >=70 && grades<80){
            System.out.println("C");
        } else if(grades >=60 && grades<70){
            System.out.println("D");
        } else if (grades <60 && grades > 0){
            System.out.println(("不及格"));
        } else {
            System.out.println("成绩不合法");
        }
        scanner.close();
    }
}
```



- 一个if语句中至多有一个else语句，else语句必须在else if语句之后，一旦其中一个else if语句检测为true，那么他将不会进行其他else if和else语句的执行
- 嵌套的if结构

![image-20231002184503986](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231002184503986.png)

- Switch多选择结构：

  - 语法：

  - case穿透现象：需要加入break以打破case穿透

  - switch和if的各自优势：switch对一个确定值进行匹配而言更加迅速，而if更适合匹配区间；

  - 在JDK7后，switch选择结构支持字符串进行匹配；

    - **字符的本质还是数字**，这里浅讲一下反编译：java--class（字节码文件）----反编译（IDEA）
    - 这个反编译啥意思呢，先放下蛤，看源码

  - 练习代码：

    ```java
    package com.vegetabale.www;
    import java.util.Scanner;
    public class A5 {
        public static void main(String[] args) {
            System.out.println("请输入你的结果：");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            switch(name){
                case "陈":
                    System.out.println("A");
                    break;
                case "泽":
                    System.out.println("B");
                    break;
                case"荣":
                    System.out.println("C");
            }
            scanner.close();
        }
    }
    ```

    ***小疑问：char和String到底啥时候用哪个，他们俩标点符号还不太一样？***

    ***switch的选择内容似乎确实没有if多哎***

## 06、07、08、09循环结构

- 循环结构总共分为三种：

- while循环：while是最基本的循环

  - while（布尔表达式）{

    //循环内容

    }

  - while循环需要按注意的地方：如果为布尔代码的结果一直为true的话会进入死循环，我们需要避免死循环，（除了定时检查、服务器连接等）

  - 本节课练习代码

    ```java
    package com.vegetabale.www;
    public class A6 {
        public static void main(String[] args) {
            int i = 0;
            int sum = 0;
            while(i<=100){
                sum = sum + i;
                i++;
    
            }
            System.out.println(sum);
    
        }
    }
    ```

- do...while循环

  - 如果未满足条件，则无法使用while循环，但是有时候我们即使不满足循环，也要需要至少执行一遍指令，此时我们使用的就是do..while循环；

  - 格式：do{//代码}while(布尔表达式)；

  - while是先判断后执行，而do...while是先执行后判断：

  - 本节练习代码：

    ```java
    package com.vegetabale.www;
    public class A7 {
        public static void main(String[] args) {
            int a = 0;//初始条件
            while(a<0){//限制循环条件（布尔表达式）
                System.out.println(a);
                a++;//(迭代）
            }
            System.out.println("=========================================================");
            do{
                System.out.println(a);
                a++;
            }while(a<0);
        }
    }
    ```

- For循环：

  - for循环结构更加简单，基本格式：

    for(初始化；条件判断；迭代){

    //代码语句

    }

    ```java
    package com.vegetabale.www;
    public class A8 {
        public static void main(String[] args) {
            int a = 0;//初始条件
            while (a < 0) {//限制循环条件（布尔表达式）
                System.out.println(a);
                a++;//(执行完大括号内的操作后进行的迭代）
            }
            for(a=0;a<=100;a++){
                System.out.println(a);
            }
        }
    }
    ```

    

  - for循环的注意事项：for( ; ; )也是一种for循环，这是一种死循环；

  - 本节课练习代码

    ```java
    package com.vegetabale.www;
    public class A8 {
        public static void main(String[] args) {
            int a = 0;//初始条件
            while (a < 0) {//限制循环条件（布尔表达式）
                System.out.println(a);
                a++;//(迭代）
            }
            for(a=0;a<=100;a++){
                System.out.println(a);
            }
            System.out.println("======================================================");
            //练习1：计算0到100之间所有奇数的和，所有偶数的和
            int i = 0;
            int sum01 = 0;
            int sum02 = 0;
            for(i=0;i<=100;i++){
                if(i%2==0){
                    sum01 = sum01 + i;
                }else{
                    sum02 = sum02 + i;
                }
    
            }
            System.out.println(sum01);
            System.out.println(sum02);
            System.out.println("====================================================");
            //练习二：用while或for循环1~1000能被5整除的数字，每3个一行
            int b = 0;
            for(b=0;b<=1000;b++){
                if(b%5==0){
                    System.out.print(b+" ");//也可以使用制表符\t
                }
                if(b%15==0){
                    System.out.println();//也可以使用换行符\n，另外print就是输出完不会换行，而pringln是输出完就会换行
                }
            }
    
        }
    }
    ```

  - 第三个练习：打印九九乘法表

    ```java
    package com.vegetabale.www;
    
    public class A9 {
        public static void main(String[] args) {
            int i = 1;
            int j = 1;
            for(i=1;i<=9;i++){
                for(j=1;j<=i;j++){
                    System.out.print(j+"*"+i+"="+(j*i)+"\t");//灵活运用制表符和转行符等转义字符
                }
                System.out.println();
    
            }//这里用到了循环叠加的思维，十分巧妙！注意这里的循环是怎么运作的，很巧妙的利用了这种性质！
            //在拿到一道题之后，我们可以观察它的规律，然后将问题简化，这就是编程思维，也是日后经常会用到的一种思维，我们要学会它！
    
        }
    
    }
    ```

    

## 10、增强for循环

- 先做了解，后面再重新做详细讲解：我现在也听不懂啊！增强for循环主要是与数组有关

## 11、break&continue

- break在任何循环语句的主体部分，主要用来跳出循环（不会终止程序），同时break在switch结构中起到终止case穿透现象

- continue用于跳过循环结构中一节尚未执行的语句，并且不会终止程序的运行，而是直接开始下一次循环

- 关于goto关键字：不作学习，只做了解；主要是通过continue/break+label(标签)来简化一些操作和程序的东西

- 本节课练习代码

  ```java
  
  ```

## 12、本章练习

- 打印三角形（五行）

- debug:可以清楚地看到程序的是如何运行的，可以帮助我们理解程序的运行原理！

  本节课练习代码

  ```java
  package com.vegetabale.www;
  public class A10 {
      public static void main(String[] args) {
          for(int i = 1 ; i <= 5; i++){
              for(int j = 5 ; j >= i; j-- ){
                  System.out.print(" ");//   \t和空格到底有啥区别捏
              }
              for(int a = 1 ; a <= i; a++ ){
                  System.out.print("*");
              }
              for(int a = 1 ; a < i; a++ ){
                  System.out.print("*");
              }
                  System.out.println();
          }
  
  
      }
  }
  ```

  ***空心三角形怎么搞啊***

  ***另外\t和空格到底有什么区别啊***

  