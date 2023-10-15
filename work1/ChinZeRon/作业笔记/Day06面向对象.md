# 面向对象

## 面向对象

- 本质：以类的方式组织代码，以对象的方式组织（封装）数据。
- 特征：
  - 封装
  - 继承
  - 多态

## 回顾方法

### 回顾方法的定义

- 修饰符+返回值+方法名+（定义变量）{

​		//方法体

​			return 返回值；（如果是void的的话就不用返回值，但是要写return）}

- break和return：break是指跳出所有循环，而return是结束方法，返回一个结果

### 方法的调用

- 方法调用的模板：

- 静态方法（static）：可以直接通过类名进行调用（类名.方法名）

  - static是和类一起加载的；

- 非静态方法：

  - 实例化这个类：new 

    对象类型 对象名 = 对象值

  - 非静态方法只有在类实例化之后才存在，也就是通过对象调用了非静态方法；

- 注意点：

  - 静态方法中只有等到非静态方法实例化之后才能调用；
  - 实际参数和形式参数的类型需要一致

- 值传递和引用传递：![image-20231004104255771](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231004104255771.png)

- 吃饱了撑的。。。就不能先讲完基础再加深啊。。。。

## 类和对象的创建

- 类是一种抽象的数据类型，但是不能代表某一个具体的事物
- 一个项目只应该存在一个main方法

- 一个类里面只能存在属性和方法，
- 实例化类：new 类（）；
  - 类实例化后会返回一个自己的对象
  - 同一个类可以返回多个不同的对象

## 构造器必须要掌握

- this关键字指代这个类

- 一个类即使什么都不写，他也会存在一个方法（构造方法）
- 类中的构造器也称为构造方法，是在进行创建对象的时候必须要调用的，构造器有两个特点：
  - 必须和类的名字相同
  - 必须并没有返回类型，也不能写void
  - public 类名（）{}
  - 实例化初始值
- 构造器可以分为两种：有参构造和无参构造
  - 注意点：一旦定义了有参构造，就必须把无参构造写出来
- 构造器的核心作用：
  - 使用new关键字,必须要有构造器，new的时候的本质是调用构造器
  - 构造器用来初始化值
- alt+insert=快捷键，可以快速生成一个有参构造或无参构造；![image-20231005100156793](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231005100156793.png)

## 创建对象分析

![image-20231005101819318](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231005101819318.png)

## 简单小结类与对象

- 类是抽象的模板，对象是具体的事例

- 方法：定义、调用

- 对象的引用：对象是通过引用来操作的，也就是栈和堆之间的关系

- 属性：字段、变量

  默认初始化：

  String：null；数字：0,0.0；boolean：false；char：u0000

- 对象的创建和使用：
  - 必须要使用new关键字来创造对象，也需要构造器
  - 调用对象：对象名.方法/属性
- 类：
  - 静态的属性
  - 动态的方法
- Java的三大特征：封装、继承、多态

## 封装

![image-20231005103204707](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231005103204707.png)

- 属性私用，get/set

- private：和public可以随意调用不同

  - 提供一些可以操作这个属性的方法：
    - get获得
    - set设置
    - alt+insert快捷键

- 封装的意义：

  - 可以使我们的代码的合法性提高，使程序更加安全

  - 隐藏代码实现细节

  - 统一接口

  - 系统可维护性增加

  - ```java
    private String name;
        private int age;
        private char sex;
        private double height;
        public String setName(String name){
            if(name.length() <5 && name.length() >0) {
                this.name = name;
            }else{this.name = "shaabi";
            }
            return name;
        }
        public String getName(){
            return this.name;
        }//名字是private，但是方法是public，这样通过getset达到了封装的效果
    
    
    
        public DStudent(){}
    }
    ```

  - ```java
    package com.vegetabale.www;
    
    public class DApplication1 {
        public static void main(String[] args) {
            DStudent DStudent = new DStudent();
            DStudent.setName("笑死了我是你爹");
            System.out.println(DStudent.getName());
    
        }
    }
    ```

## 继承

- extends关键字：我extends成功人士，也就是说，我是成功人士的子类（派生类）

- 继承是类和类之间的关系，除此之外类还有其他关系，以后再聊

  ![image-20231005111802625](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231005111802625.png)

- 子类可以继承父类的所有方法和属性（前提是public）
  - 属性、方法的修饰符（没详细讲，只做了解）：
    - public
    - private
    - protected
    - default
- 在Java中，所有的类都默认继承object类：object类本身自带了一些方法；
- Ctrl+H是快捷键，可以打开继承树；
- Java中只有单继承，没有多继承，一个儿子只能有一个爸爸；但是一个爸爸可以有多个儿子；

## Super详解

- 通过super可以调用父类的一些属性或方法

- super无法调用private的东西

- 实例化子类的对象时，会优先调用父类的构造器，再调用子类的构造器

  - 子类构造器默认隐藏了一行代码：调用了父亲的无参构造super（）；而

    且这行代码必须保证在子类构造器的第一行；

- super注意点：

  - super调用父类的构造器，必须在构造器的第一个（如果父类没有无参构造器，那么子类只能选择调用父类的有参构造）；
  - super必须只能出现在子类的方法或者构造器中
  - super和this不能同时调用构造器
  - 相比于this：this在没有继承的条件下也可以使用，但是super不行

- 本节课练习代码：

  ![image-20231005122502429](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231005122502429.png)

## 方法的重写（Override）

- 重写不是重载，是不一样的
- 重写都是方法的重写，和属性无关
- 父类的引用指向了子类
- 方法的调用只和左边定义的数据类型有关
- 静态的方法和非静态的方法区别很大
  - 非静态方法可以选择重写
  - 静态方法不能进行重写
- 重写的关键词不能是private
- 重写的方法名必须相同
- 参数列表必须相同
- 修饰符：范围可以扩大：public》protectded》default》private
- 抛出的异常：范围可以被缩小，但不能扩大
- 为什么需要重写：父类的功能子类不一定需要，或者不一定满足
- Alt+Insert》Overside

## 多态  

- 多态可以实现一个非常重要的特性——动态编译
- 一个对象的实际类型是确定的，但是他可以指向的引用类型就不确定了
- Student s1 = new Person（）：父类的引用类型指向子类
- 调用看左，执行看右
- 两个类型有相同的方法时，执行子类的方法
- 注意事项：
  - 多态是方法的多态，属性没有多态
  - 父类和子类有联系，类型转换异常
  - 多态存在的条件：
    - 必须有继承关系
    - 方法需要重写
    - 父类引用指向子类对象
  - static方法不属于示例，final属于常量，private属于私有，这些都没办法实现方法重写

- ![image-20231005154251088](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231005154251088.png)

## instance of 和类型转换

- instance of是一个关键字，用于判断一个对象属于什么类型，看的是对象的实际类型；

  用法：对象名 instanceof 类名

- 类型的转化：由高变低是没问题的，但是由低变高（强制转换）会丢失一些方法

## Static关键字详解

- 属性：静态的变量（类变量）（static int）
  - 类变量可以通过类.属性来进行调用
- 方法：static方法，和类一起加载；注意静态方法和非静态方法的调用区别(什么时候能用什么时候不能用)
- 代码块：static静态代码块，和类一起加载，静态代码块只在第一次运行代码时加载。
  - 代码执行顺序：静态代码块》匿名代码块》构造方法
- 静态导入包：了解即可，Java可能是需要终身学习的东西

## 抽象类

- abstract是关键字；约束！
- 在一个类的前面加入abstract就可以把类变成抽象类；
- 在一个方法前加入abstract，可以把方法变成抽象方法（可以只定义方法名但是没有方法体），而继承该类的非抽象子类必须完成这个方法；
- abstract离不开extent，因为abstract的约束需要extend来进行实现，但是由于extend的局限性，我们需要使用到接口；
- abstract特点：
  - 无法new一个abstract类，只能new一些他的子类
  - 一旦方法是抽象方法，那么方法所在的类一定需要是抽象类，而抽象类里可以存写普通的方法
- 思考：
  - 抽象类存在构造器吗：
  - 抽象类存在的意义是什么？：为了提高开发代码的效率。

## 接口

![image-20231005165015699](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231005165015699.png)

- 接口：只有规范！自己无法写方法，约束和实现分离：面向接口编程；

- 面向对象的精髓，声明接口的关键字是interface：

  public interface 接口名

- 接口中的所有定义和方法都是抽象的（public abstract）。因此接口都需要有一个实现类，实现了接口的类，就需要重写接口中的方法；

- 实现类：

  public class 实现类名 implements 接口名{

  }

- 接口相对于抽象类的好处就在于一个实现类可以有多个接口（多继承）；而继承只能实现单继承
- 如何能锻炼抽象的思维：架构师需要自己的抽象思维非常的好，程序员不持续的提高就完蛋了捏
- 接口的作用：
  - Java的接口是约束
  - 定义一些方法让不同的人实现
  - 方法：public abstract
  - 常量：public static final
  - 接口不能被实例化，接口中没有构造器
  - implements可以实现多个接口
  - 实现类需要重写接口中的方法

## N种内部类（选修）

***他说选修我就完了再修啊啊啊啊啊***

