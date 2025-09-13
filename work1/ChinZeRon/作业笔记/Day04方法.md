# Java方法

## 01：什么是方法？

- System.out.println():这里的println就是方法

  //类+对象+方法

- psvm》main方法

- 方法是解除一类问题的步骤的有序组合，方法在程序中被创建，在其他地方被引用

  ![image-20231003110904654](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231003110904654.png)

- 设计方法的原则：原子性：一个方法是完成1个功能，这样方便后面可以随时调用办法 

- 方法可以使代码更加简洁

- 定义一个方法：方法头+方法体

  void就是不返回 方法名必须遵循驼峰原则（开头小写，后面字母大写）

- 形式参数：仅定义参数（没有过多意义，保证结构完整）

  实际参数：实际调用参数，赋值、传递参数

- 方法体：包含具体的语句，定义该方法的功能

- return ；》》》用来返回值，还可以用来终止方法
  - 一般把return（返回值时）放在方法第一层
- 方法调用：一共有两种方法
  - 对象名.方法名（实参列表）
  - 还有就是按照那个格式

- Java中采取值传递（好像不作了解）

- 本节课学习代码：

  ```java
  package com.vegetabale.www;
  import java.util.Scanner;
  public class B1 {
      public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
          System.out.println("请用户输入第一个数字：");
          double num1 = scanner.nextDouble();
          System.out.println("请用户输入第二个数字：");
          double num2 = scanner.nextDouble();
          double max = max(num1,num2);
          System.out.println(max);
  
      }
      public static double max(double a,double b){
          double result = 0;
          if(a==b){
              System.out.println("num1==num2");
          }else if(a>b){
               result = a;
          }else{
               result = b;
          }
          return result ;
      }
  }
  ```

## 方法的重载

- 两种方法的名字一样，但是方法的参数类型不同，这就叫做方法的重载

​		方法的重载可以让我们的程序可以应对		更多数据类型的输入。

- 方法重载的规则

![image-20231003114455518](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231003114455518.png)

## 04:命令行传递参数

- 有时候你希望运行一个程序的时候在传递给他消息。这要靠传递命令行参数给main（）函数实现
- ***他说用不太到，而且我没学数组，我就先放放。。。***

## 05：可变参数

- 方法的重载，有时候会因为数据类型繁琐而导致方法重载过多，效率过低，因此我们可以使用可变参数

- 在参数类型后面加入..
- ***可变参数似乎指的是数量可变而不是数据类型可变？***
- 一个方法只能有一个可变参数，可变参数必须在常参数的后面

![image-20231003115229838](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231003115229838.png)

- 无所谓了他搞了一堆数组我完了回头看吧

## 递归参数

![image-20231003120232361](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231003120232361.png)

- 一个方法自己调用自己，就是递归
- 利用递归可以简单地解决一些复杂的问题
- 递归结构：
  - 递归头（边界条件）：用于限制什么时候不调用自身
  - 递归体；
- Java采用的是栈机制，使用递归会占用大量的空间，因此能不用递归就不使用递归，在基数较小时可以使用递归