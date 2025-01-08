# 面向对象基础

## 理解面向对象

1. 开发一个一个的对象，把数据交给对象，再调用对象的方法来完成对数据的处理

2. 面向对象编程的好处：符合人类思维习惯，编程更简单、更直观

3. 对象是什么：对象本质上是一种特殊的数据结构

4. 对象如何创建：class也就是类，也称为对象的设计图（或者对象的模板），对象是用类new出来的，有了类就可以创建出对象

   ```java
   public class 类名{
       1 变量：用来说明对象可以处理什么数据
       2 方法：描述对象有什么功能，可以怎么处理数据
   }
   ```

5. 创建对象
   * `类名 对象名 = new 类名();`
   * 对象如何使用
     * 对象名.成员方法
     * 对象名.成员变量

## 对象在计算机中执行原理

- `Student s1 = new Student);`
- ﻿﻿每次`new Student（）`，就是在堆内存中开辟一块内存区域代表一个学生对象
- ﻿s1变量里面记住的是学生对象的地址

## 类（设计图）和对象（实例）的一些注意事项

1. 类名建议用英文单词，首字母大写，满足驼峰模式，且要有意义，比如：Student、Car..
2. 类中定义的变量也称为***成员变量***（对象的属性），类中定义的方法也称为***成员方法***（对象的行为）
3. 成员变量本身存在默认值，同学们在定义成员变量时一般来说不需要赋初始值（没有意义）
   * byte，short，int，char，long默认值是0
   * float，double默认值是0.0
   * boolean是false
   * 数组，String是null
4. 一个代码文件中，可以写多个class类，但只能一个用public修饰，且public修饰的类名必须成为代码文件名
5. ﻿﻿对象与对象之间的数据不会相互影响，但多个变量指向同一个对象时就会相互影响了（传输地址）
6. ﻿﻿如果某个对象没有一个变量引用它，则该对象无法被操作了，该对象会成为所谓的垃圾对象

## this

1.  this就是一个变量，可以用在方法中，来拿到当前对象，哪个对象调用方法，this就指向哪个对象，也就是拿到哪个对象

2. this的作用：代表了当前对象的地址，可以访问当前对象的成员变量和成员方法

3. this功能：

   * 用在有参数构造器中

     ```java
     public Car(String name, double price){
         //注意：this在构造器中，代表构造器正在初始化的那个对象
         System.out.println(this);
         this.name = name;
         this.price = price;
     }
     ```

   * 用在方法中

     ```java
     public void goWith(String name){
         //注意：this在方法中，谁调用这个方法this就代表谁
         System.out.println(this.name + "正在和" + "name" + 比赛);
     ```

## 构造器

* 构造器的常见应用场景：
  * 创建对象时，同时完成对对象成员变量（属性）的初始化赋值，并返回这个对象的地址

* 构造器的分类
  * 无参数构造器：初始化一个类的对象，并返回这个对象的地址，里面的数据都是默认值
  * 有参数构造器：初始化一个类的对象，并返回这个对象的地址，井且可以同时为对象賦值

* 创建对象时，对象会去调用构造器

```java
Student s = new Student();  //类名 对象名 = new 构造器;
```

* 注意
  * 类在设计时，如果不写构造器，Java是会为类自动生成一个无参构造器的
  * 一旦定义了有参数构造器，Java就不会帮我们的类自动生成无参构造器了，此时就建议自己手写一个无参数构造器![截圖 2024-12-06 13.29.24](/Users/cjf/Desktop/截圖 2024-12-06 13.29.24.png)

## 封装

* 什么是封装
  * 就是用类设计对象处理某一个事物的数据时，应该把要处理的数据，以及处理这些数据的方法，设计到一个对象中去
* 封装基本思想
  * 决定属性和行为归属谁的问题
* 封装设计规范
  * 合理隐藏，合理暴露
* 代码层面如何控对象的成员公开或隐藏？
  - ﻿﻿公开成员，可以使用public（公开）进行修饰
  - ﻿﻿隐藏成员，使用private![截圖 2024-12-06 13.42.24](/Users/cjf/Library/Application Support/typora-user-images/截圖 2024-12-06 13.42.24.png)（私有，隐藏)进行修饰，只能本类访问



## 实体JavaBean

* 什么是实体类：就是一种特殊形式的类

  * 这个类中的成员变量都要私有，并且要对外提供相应的getXxx，setXxx方法
  * 类中必须要有一个公共的无参的构造器

* 如何设置：右键选Generate

  * Getter and Setter
  * Constructor（创建构造器）

* 有什么应用场景：

  体类只负责数据存取，而对数据的处理交给其他类来完成，以实现数据和数据业务处理相分离

## 成员变量和局部变量的区别

1. 类中位置不同：成员变量（类中，方法外）、局部变量（常见子方法中）
2. 初始化值不同：成员变量（有默认值，不需要初始化赋值），局部变量（没有默认值，使用之前必须完成赋值）
3.  内存位置不同：成员变量（存在于堆内存），局部变量（栈内存）
4. 作用域不同：成员变量（整个对象），局部变量（在所归属的大据号中）
5. 生命周期不同：成员变量（与对象同生共死），局部变量（方法调用而生，方法结束而亡）

# Static

### Static修饰成员变量

1. Static
   * 叫静态，可以修饰***成员变量、成员方法***
2. 成员变量按照有无static修饰，分为两种，它们有各自的访问方式
   * 类变量：有static修饰，属于类，与类一起加载一次，在内存里只有一份，会被类的全部对象共享
     * 类名.类变量（推荐）
     * 对象.类变量（不推荐）
   * ﻿实例变量（对象的变量）：无static修饰，属于每个对象的，只能用对象访问
     * 对象.实例变量（只有这一种）
3. 应用场景
   * 类变量：在开发中如果这个数据只要一份，且可以被共享，应定义为类变量
   * 实例变量：每个对象都要有一份，数据各不同（如：name、score、age）
4. 注意事项：访问自己类中的类变量，可以省略类名，但访问其他类不行

### Static修饰成员方法

1. 方法按照有无static修饰，分为两种，它们有各自的访问方式
   * 类方法（静态方法）：有static修饰的成员方法，属于类
     * 类名.类方法（推荐）
     * 对象.类方法（不推荐）
   * 实例方法（对象的方法） ：无static修饰的成员方法，属于对象
     * 对象.实例方法
2. 应用场景
   * 类方法Static
     * 类方法最常见的应用场景是做工具类
     * 工具类中的方法都是一些类方法，每个方法都是用来完成一个功能的，工具类是给开发人员共同使用的
     * 用类方法设计工具类好处：提高代码复用，调用方便，提高了开发效率
   * 工具类中的方法
     * 实例方法需要创建对象来调用，此时对象只是为了调用方法，对象占内存，这样会浪费内存
     * ﻿类方法，直接用类名调用即可，调用方便，也能节省内存
     * 多学一招：工具类没有创建对象的需求，建议将工具类的***构造器进行私有***

### Static的注意事项

1. 类方法中可以直接访问类的成员，不可以直接访问实例成员
2. 实例方法中既可以直接访问类成员，也可以直接访问实例成员
3. ﻿﻿实例方法中可以出现this关键字，类方法中不可以出现this关键字的

### Static应用知识

#### 代码块

1. 概述

   * 代码块是类的5大成分之一（成员变量、构造器、方法、代码块、内部类）

2. 分类 

   * 静态代码块：

     * 格式：static｛｝

     * 特点：类加载时自动执行，由于类只会加载一次，所以静态代码块也只会执行一次

     * 作用：完成类的初始化，例如：对类变量的初始化赋值

   * 实例代码块：

     * 格式：｛｝
     * 特点：每次创建对象时，执行实例代码块，并在构造器前执行
     * 作用：和构造器一样，都是用来完成对象的初始化的，例如：对实例变量进行初始化赋值

#### 单例设计模式（开发框架使用）

1. 设计模式
   * 什么是设计模式
     * 一个问题通常有n种解法，其中肯定有一种解法是最优的，这个最优的解法被人总结出来了，称之为设计模式
     * 设计模式有20多种，对应20多种软件开发中会遇到的问题
   * 设计模式主要学什么

2. 单例设计模式：确保一个类只有一个对象

   * 怎么写（饿汉式单例）

     * 把类的构造器私有

     * ﻿﻿定义一个类变量记住类的一个对象

     * 定义一个类方法，返回对象

     * ```java
       public class A {
           //2.定义一个类变量记住类的一个对象
           private static A a = new A();
           //1.私有构造器
           private A(){
           }
           //3.定义一个类方法返回对象
           public static A getObject(){
               return a;
           }
       }
       ```

   * 应用和好处
     * ﻿任务管理器衬象、获取运行时对象
     * ﻿﻿﻿﻿在这些业务场景下，使用单例模式，可以避免浪费内存

3. 饿汉式单例：拿对象时，对象已经创建好了（上方已给出例子）

4. 懒汉式单例：拿对象时，才开始创建对象

   ```java
   public class B {
       //2.定义一个类变量，用于储存这个类的一个对象
       private static B b;
       //1.把类的构造器私有
       private B(){
           
       }
       //3.定义一个类方法，保证第一次调用这个方法的时候才创建一个对象，后面调用这个方法时都用这个对象返回
       public static B getInstance() {
           if (b == null) {
               b = new B();
           }
           return b;
       }
                   
   }
   ```

# 继承

## 继承快速入门

* 什么是继承
  * 用Java中提供一个关键字extends，可以让一个类和另一个类建立父子关系
* 继承的特点
  * 子类可以继承父类非私有成员（成员变量，方法）
  * 带继承关系的类，java会用类和其父类，一起创建类的对象
  * 对象能直接访问什么成员，是由子父类这多张设计图共同决定的，这多张设计图对外暴露了什么成员，对象就可以访问什么成员
  * ![截圖 2024-12-05 16.44.31](/Users/cjf/Library/Application Support/typora-user-images/截圖 2024-12-05 16.44.31.png)

* 继承的好处
  * 减少重复代码编写，提高代码复用性
  * ![截圖 2024-12-05 16.53.58](/Users/cjf/Library/Application Support/typora-user-images/截圖 2024-12-05 16.53.58.png)

## 继承相关注意事项

### 权限修饰符

* 什么是权限修饰符
  * 就是是用来限制类中的***成员***（成员变量、成员方法、构造器、代码块⋯）能够被访问的范围

* 种类

| 修饰符    | 在本类中 | 在同一个包下其他类里 | 任意包下的子类里 | 任意包下的任意类里 |
| --------- | -------- | -------------------- | ---------------- | ------------------ |
| private   | Yes      |                      |                  |                    |
| 缺省      | Yes      | Yes                  |                  |                    |
| protected | Yes      | Yes                  | Yes              |                    |
| Public    | Yes      | Yes                  | Yes              | Yes                |

* ```java
  package d9_modifier;
  
  public class Fu {
      // 1.只能本类访问
      private void privateMethod(){
          System.out.println("private");
      }
      // 2.本类，同一个包下的类
      void method(){
          System.out.println("缺省");
      }
      // 3.本类，同一个包下的类，任意包子孙类
      protected void protectedMethod(){
          System.out.println("protected");
      }
      // 4.公开
      public void publicMethod(){
          System.out.println("public");
      }
  }
  ```

### 单继承，Object类

* 单继承：Java是单继承的，一个类只能继承一个直接父类，Java的类不支持多继承，但是支持多层继承，可以有孙子

* Object类：是Java所有类中的祖宗，直接写一个类默认extends到Object类，也就意味着所有类可以调用Object类中的方法

### 方法重写

* 什么是方法重写
  * 子类写了一个***方法名称，形参列表***与父类某个方法一样的方法去覆盖父类的该方法
  * 注意：重写后方法的访问，Java会遵循就近原则

* 方法重写的注意事项
  * 建议加上：@Override注解，可以校验重写是否正确，同时可读性好
  * 子类重写父类方法时，访问权限必须大于或者等于父类被重写的方法的权限
  * 重写的方法返回值类型，必须与被重写方法的返回值类型一样，或者范围更小
  * 私有方法、静态方法不能被重写

![截圖 2024-12-05 20.37.56](/Users/cjf/Library/Application Support/typora-user-images/截圖 2024-12-05 20.37.56.png)

* 开发中应用场景
  * 当子类觉得父类的方法不好用，或者不满足自己的需求时，就可以用方法重写
  * toSring重写
    * 打印出内容而不是地址
    * ArrayList自带重写
    * Generate可以自动产生
    * ![截圖 2024-12-05 20.54.53](/Users/cjf/Library/Application Support/typora-user-images/截圖 2024-12-05 20.54.53.png)

### 子类中访问其他成员的特点

* 在子类方法中访问其他成员（成员变量、成员方法），是依照就近原则的
  * 先子类局部范围找
  * 然后子类成员范围找
  * 然后父类成员范围找，如果父类范围还没有找到则报错

* 如果子父类中，出现了重名的成员，会优先使用子类的，如果此时一定要在子类中使用父类的怎么办
  * 可以通过super关键字，指定访问父类的成员：super.父类成员变量/父类成员方法
* ![截圖 2024-12-05 21.12.25](/Users/cjf/Library/Application Support/typora-user-images/截圖 2024-12-05 21.12.25.png)

### 子类构造器的特点

* 特点
  * 子类的全部构造器，都会先调用父类的构造器，再执行自己

* ﻿子类构造器是如何实现调用父类构造器的
   * 默认情况下，子类全部构造器的第一行代码都是` super（）`（写不写都有），它会调用父类的无参数构造器
   * 如果父类没有无参数构造器，则我们必须在子类构造器的第一行手写super（..），指定去调用父类的有参数构造器这个的目的是为对象中包含父类的这部分成员变量赋值

```java
package d14_extends_constructor;

class F{
    public F(){
        System.out.println("父类F无参数构造器执行了");
    }
    public F( String name , int age ){
    }
}

class Z extends F{
    public Z() {
        //super();  //默认存在的
        System.out.println("子类Z的无参数构造器执行了");
    }
    public Z(String name){
        //super();  //默认存在的
        System.out.println("子类Z的有参数构造器执行了");
    }
}

public class Test1 {
    public static void main(String[] args) {
        //目标：认识子类构造器特点，在掌握这个特点的常见应用场景
        Z z = new Z(); // 先调用父类构造器，再调用子类构造
        
        Z z2 = new Z("波妞");
    }
}
```

* 子类构造器可以通过调用父类构造器，把对象包含父类这部分数据先初始化赋值，再回来把对象里包含子类这部分的数据也进行初始化赋值

```java
package d14_extends_constructor;

public class Test2 {
    public static void main(String[] args) {
        // 目标：搞清楚子类构造器为什么要调用父类构造器，有什么应用场景
        // 原因：对象的构造器被拆分到多个类里面去
        Teacher t = new Teacher("李四" , 36 , "java");
        System.out.println(t.getName());
        System.out.println(t.getAge());
        System.out.println(t.getSkill());
    }
}

// 子类 处理skill
class Teacher extends People{
    private String skill;

    // 创建一个有参构造器
    public Teacher(String name, int age, String skill){
        // 不可以直接写this.name = name;
        // 使用super,指向父类成员变量/方法
        super(name ,age); // 创建子类的时候同时为子类变量赋值
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    

    public void setSkill(String skill) {
        this.skill = skill;
    }
}

// 父类 处理name age
class People{
    private String name;
    private int age;

    public People() {
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

### this（）调用兄弟构造器

* 在任意类的构造器中，是可以通过this(...）去调用该类中的其他构造器

* ```java
  package d14_extends_constructor;
  
  public class Test3 {
      public static void main(String[] args) {
          //目标：掌握类的构造器中，是可以通过this(...）去调用兄弟构造器的作用
          //需求：如果学生没填写学校，那么学校默认A
          Student s2 = new Student("李四" , 26 , "家里蹲大学");
          System.out.println(s2.getName());
          System.out.println(s2.getAge());
          System.out.println(s2.getSchoolName());
      }
  }
  
  class Student{
      private String name;
      private int age;
      private String schoolName;
  
      public Student() {
      }
  
  //    //需求：如果学生没填写学校，那么学校默认A，这样写代码太重复了
  //    public Student(String name , int age){
  //        this.name = name;
  //        this.age = age;
  //        this.schoolName = "A";
  //    }
  
      // 这样写就大大简化了代码量 去调用别的有参构造器
      public Student(String name , int age){
          this(name , age , "A");
          //不能在一个构造器里既写this，又写super
      }
  
      public Student(String name, int age, String schoolName) {
          this.name = name;
          this.age = age;
          this.schoolName = schoolName;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public int getAge() {
          return age;
      }
  
      public void setAge(int age) {
          this.age = age;
      }
  
      public String getSchoolName() {
          return schoolName;
      }
  
      public void setSchoolName(String schoolName) {
          this.schoolName = schoolName;
      }
  }
  ```

* 注意事项

  * this（..），super（..）都只能放在构造器的第一行，因此，有了this（..）就不能写super（..）了，反之亦然

# 多态

## 认识多态

### 什么是多态

* 多态是在继承/实现情况下的一种现象，表现为：
  * 对象多态：对象多态侧重于对象的类型。它主要通过向上转型（把子类对象赋值给父类变量）来体现，例如有父类 `Animal` 和子类 `Dog、Cat`，可以写 `Animal animal = new Dog();`，这里` anima` 这个对象可以是 `Dog`类型也可以是 `Cat `类型或者其他 `Animal`的子类类型
  * 行为多态：行为多态侧重于方法的行为。它是指在继承关系中，子类重写了父类的方法，当通过父类引用调用该方法时，会根据对象的实际类型来执行相应的方法。比如` Animal `类有`makeSound（）`方法，`Dog` 和` Cat` 子类重写这个方法后，`animal.makesound（）`的具体执行内容会因 `animal` 实际指向的是` Dog` 还是`Cat `而不同

### 多态代码表示

```java
People p1 = new Teacher();
p1.run();//识别技巧：编译看左边，运行看右边

People p2 = new Student();
p2.run();
```

### 多态的前提

*  有继承/实现关系；存在父类引用子类对象；存在方法重写

### 多态的注意事项

*  多态是对象、行为的多态，Java中的属性（成员变量）不谈多态

![截圖 2024-12-06 10.39.51](/Users/cjf/Library/Application Support/typora-user-images/截圖 2024-12-06 10.39.51.png)

## 使用多态的好处

* 在多态形式下，右边对象是解耦合的，更便于扩展和维护
  * 解耦合： `People p1 = new Student();`中Student可以换成Teacher或者其他
* 定义方法时，使用父类类型的形参，可以接收一切子类对象，扩展性更强、更便利
* 弊端：多态下不能直接调用子类的独有方法

## 多态下的类型转换问题

* 类型转换
  * 自动类型转换：`父类 变量名=new 子类;`例如`People p = new Teacher();`
  * 强制类型转换：`子类 变量名=（子类）父类变量;`例如` Teacher t= (Teacher)p;` 解决了多态下不能调用子类独有方法的问题

* 强制类型转换的一个注意事项
  * 存在继承/实现关系就可以在编译阶段进行强制类型转换，编译阶段不会报错
  * 运行时，如果发现对象的真实类型与强转后的类型不同，就会报类型转换异常（ClassCastException）的错误出来

* 强转前，Java建议
  * 使用instanceof关键字，判断当前对象真实类型，再进行强转
  * `p instanceof Student`判断p是不是Student类或其子类

![截圖 2024-12-06 11.17.10](/Users/cjf/Library/Application Support/typora-user-images/截圖 2024-12-06 11.17.10.png)

# final

## 认识final

* 认识final
  * final 关键字是最终的意思，可以修饰（类、方法、变量）
  * 修饰类：该类被称为最终类，特点是不能被继承了
  * 修饰方法：该方法被称为最终方法，特点是不能被重写了
  * 修饰变量：该变量只能被赋值一次

* fianl修饰变量的注意
  * ﻿final修饰基本类型的变量，变量存储的数据不能被改变
  * ﻿final修饰引用类型的变量，变量存储的地址不能被改变，但地址所指向对象的内容是可以被改变的

```java
package d3_final;

public class Test {

    // 常量：final修饰的成员变量，建议名称全部大写，多个单词下划线连接
    public static final String SCHOOL_NAME = "黑马";
    private final String name = "张三";   // 这种做法没意义

    public static void main(String[] args) {
        // 目标：认识final作用
        // 3.final修饰变量,该变量只能被赋值一次
        /*变量：
             一，局部变量
             二，成员变量（全局的）
                 1.静态成员变量
                 2.实例成员变量
         */
        final double pai = 3.14; // 不希望别人改动
        final int[] arr = {11, 22, 33};
        // arr = null;  地址不可以改变
        //但是数据可以改
        arr[1] = 222;
    }
}

// 1.final修饰类，类不能被继承了（实际没什么用）
final class A{}
// class B extends A;

// 2.final修饰方法，不能被重写了
class C{
    public final void test(){}
}
class D extends C{
//    @Override   // 不可以重写
//    public void test(){}
}
```

## 常量详解

* 常量
  * ﻿﻿使用了 static final 修饰的成员变量就被称为常量
  * ﻿﻿作用：通常用于记录系统的配置信息
  * ﻿﻿注意：常量名的命名规范：建议使用大写英文单词，多个单词使用下划线连接起来

* 使用常量记录系统配置信息的优势、执行原理
  * ﻿﻿代码可读性更好，可维护性也更好
  * ﻿﻿程序编译后，常量会被“宏替换”：出现常量的地方全部会被替换成其记住的字面量，这样可以保证使用常量和直接用字面量的性能是一样的

# 抽象类

## 认识抽象类

* 什么是抽象类
  * 在Java中有一个关键字叫：abstract，它就是抽象的意思，可以用它修饰类、成员方法
  * abstract修饰类，这个类就是抽象类；修饰方法，这个方法就是抽象方法
  * 抽象方法只有方法名，不能写方法体
* 抽象类的注意事项，特点
  *  抽象类中不一定有抽象方法，有抽象方法的类一定是抽象类
  *  类该有的成员（成员变量、方法、构造器）抽象类都可以有
  *  抽象类最主要的特点：抽象类不能创建对象，仅作为一种特殊的父类，让子类继承并实现
  *  一个类继承抽象类，必须重写完抽象类的全部抽象方法，否则这个类也必须定义成抽象类

## 使用抽象类的好处

* 应用场景
  *  父类知道每个子类都要做某个行为，但每个子类要做的情况不一样，父类就定义成抽象方法，交给子类去重写实现，我们设计这样的抽象类，就是为了更好的支持多态
* 好处
  * 解决方法中存在重复代码的问题

## 抽象类应用场景：模版方法设计模式

* 模版方法设计模式的写法
  * 定义一个抽象类
  * 在里面定义两个方法
    * 一个是模版方法：相同代码放进去
    * 一个是抽象方法：具体实现交给子类完成
* 多学一招：建议使用final关键字修饰模版方法
  * ﻿模板方法是给对象直接使用的，不能被子类重写
  * ﻿一旦子类重写了模板方法，模板方法就失效

![截圖 2024-12-06 16.52.51](/Users/cjf/Library/Application Support/typora-user-images/截圖 2024-12-06 16.52.51.png)

# 接口

## 认识接口

* Java提供一个关键字interface，用这个关键字我们可以定义出一个特殊结构：接口

  * ```java
    package d7_interface;
    // 有且仅有以下两个东西
    public interface A {
        // 成员变量（常量）
        String SCHOOL_NAME = "黑马程序员";
    
        // 成员方法（抽象方法）
        void test();
    }
    ```

* 注意：接口不能创建对象；接口是用来被类实现（implements）的，实现接口的类称为实现类

  * ```java
    package d7_interface;
    //实现类
    public class D implements B,C{
        @Override
        public void testb1() {
    
        }
    
        @Override
        public void testb2() {
    
        }
    
        @Override
        public void testc1() {
    
        }
    
        @Override
        public void testc2() {
    
        }
    }
    
    ```

* 一个类可以实现多个接口（接口可以理解成干爹），实现类实现多个接口，必须重写完全部接口的全部抽象方法，否则实现类需要定义成抽象类

## 接口的好处（重点）

* 弥补了类单继承的不足，一个类同时可以实现多个接口，我们可以让一个类有一个亲爹的同时，还可以找多个干爹去扩展自己的功能
* 别人通过你implements的接口，就可以显性的知道你是谁，从而也就可以放心的把你当作谁来用了
* 让程序可以面向接口编程，这样程序员就可以灵活方便的切换各种业务实现

## 接口其它细节

1. JDK8开始，接口中加入的新方法

   * ```java
     //1 默认方法必须用default修饰，默认被public修饰
     //实例方法：对象的方法，必须使用实现类的对象来访问
     default void test1(){
         System.out.println("===默认方法===");
     }
     
     //2 私有方法，必须使用private修饰(jdk9之后）
     //实例方法：对象的方法
     private void test2(){
         System.out.println("===私有方法===");
     }
     
     //3 静态方法 必须用static修饰,默认使用public修饰
     public static void test3(){
         System.out.println("===静态方法===");
     }
     ```

2. 为什么要新增这些方法

   * 增强了接口的能力，更便于项目的扩展和维护

3. 接口的其它细节（接口的多继承，可以便于实现类去实现）

   * ﻿﻿一个接口继承多个接口，如果多个接口中存在方法签名冲突，则此时不支持多继承

   * ﻿﻿一个类实现多个接口，如果多个接口中存在方法签名冲突，则此时不支持多实现

   * ﻿﻿一个类继承了父类，又同时实现了接口，父类中和接口中有同名的默认方法，实现类会优先用父类的

   * ﻿﻿一个类实现了多个接口，多个接口中存在同名的默认方法，可以不冲突，这个类重写该方法即可

   * ```java
     //1、一个接口继承多个接口，如果多个接口中存在方法签名冲突，则此时不支持多继承
     interface A{
         void test1();
     }
     interface A{
         String test1();
     }
     interface K extends I , J{}  //冲突
     
     //2、一个类实现多个接口，如果多个接口中存在方法签名冲突，则此时不支持多实现
     class E implements I, J{      //冲突
     
     }
     
     //3、一个类继承了父类，又同时实现了接口，父类中和接口中有同名的默认方法，实现类会优先用父类的
     class Fu{
         public void run(){
             System.out.println("Fu run");
         }
     }
     interface IT{
         default void run(){
             System.out.println("IT run");
         }
     }
     class Zi extends Fu implements IT{
     
     }
     
     //4、一个类实现了多个接口，多个接口中存在同名的默认方法，可以不冲突，这个类重写该方法即可
     interface IT1 {
         default void test() {
             System.out.println("IT1");
         }
     }
     interface IT2 {
         default void test() {
             System.out.println("IT2");
         }
     }
     class ITN implements IT1,IT2{   //有矛盾，需要重写
     
         @Override
         public void test() {
             IT1.super.test();
         }
     }
     ```

# 面向对象高级三

## 内部类

### 内部类概述

* ﻿﻿是类中的五大成分之一（成员变量、方法、构造器、内部类、代码块），如果一个类定义在另一个类的内部，这个类就是内部类

* ﻿﻿场景：当一个类的内部，包含了一个完整的事物，且这个事物没有必要单独设计时，就可以把这个事物设计成内部类

* ```java
  public class Car{
      //内部类
      public class Engine{
          
      }
  }
  ```

### 成员内部类

* 就是类中的一个普通成员，类似前面我们学过的普通的成员变量、成员方法，从JDK16开始可以定义静态成员

* ```java
  public class Outer{
      //成员内部类
      public class Inner{
  
      }
  }
  ```

* 创建对象的格式

* ```java
  外部类名.内部类名 对象名 = new 外部类(...).new 内部类(...);
  
  Outer.Inner in = new Outer().new Inner();
  ```

* 成员内部类的实例方法中，访问其他成员有啥特点？
  - ﻿﻿可以直接访问外部类的实例成员、静态成员
  - ﻿﻿可以拿到当前外部类对象，格式是：外部类名.this

### 静态内部类

* 有static修饰的内部类，属于外部类自己持有

* ```java
  public class Outer{
      //静态内部类
      public static class Inner{
      }
  }
  ```

* 创建对象的格式（同上）

* 静态内部类中访问外部类成员的特点

  。 可以直接访问外部类的静态成员，不可以直接访问外部类的实例成员

### 局部内部类（没用）

* 局部内部类是定义在在方法中、代码块中、构造器等执行体中

### 匿名内部类（重点）

* 就是一种特殊的局部内部类；所谓匿名：指的是程序员不需要为这个类声明名字

* ```java
  new 类或接口(参数值){
      类体(一般是方法重写)
  };
  
  new animal(){
      @Override
      public void cry(){            
          
  };
  ```

* 特点：匿名内部类本质就是一个子类，并会立即创建出一个子类对象
* ﻿﻿作用：用于更方便的创建一个子类对象，在开发中，通常作为一个参数传输给方法

## 枚举

### 枚举的概述

* 枚举是一种特殊类

### 枚举的格式

* ```java
  public enum A{
      X,Y,Z;
      ........
  }
  ```

### 注意

- 枚举类中的第一行，只能写一些合法的标识符（名称），多个名称用逗号隔开
- ﻿这些名称，本质是常量，每个常量都会记住枚举类的一个对象

### 枚举的特点

* 枚举类的第一行只能罗列一些名称，这些名称都是常量，并且每个常量记住的都是枚举类的一个对象
* ﻿﻿枚举类的构造器都是私有的（写不写都只能是私有的），因此，枚举类对外不能创建对象
* ﻿﻿枚举都是最终类，不可以被继承
* ﻿﻿枚举类中，从第二行开始，可以定义类的其他各种成员
* ﻿﻿编译器为枚举类新增了几个方法，并且枚举类都是继承：java.lang.Enum类的，从enum类也会继承到一些方法

### 多学一招：使用枚举类实现单例设计模式

* 枚举常见应用场景
  * 用来表示一组信息，然后作为参数进行传输，选择定义一个一个的常量来表示一组信息，并作为参数传输
  * 参数值不受约束，选择定义枚举表示一组信息，并作为参数传输
  * 代码可读性好，参数值得到了约束，对使用者更友好，建议使用！

## 泛型

### 认识泛型

### 泛型类

### 泛型接口

### 泛型方法，泛型通配符，上下限

### 泛型的注意事项

## java.lang包下的常用API

