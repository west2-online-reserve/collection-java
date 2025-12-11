# Java（面向对象）

本质：以类的方式组织代码，以对象的组织（封装）数据

三大特性：封装，继承，多态



## 类和对象的创建

（一个项目应该只存在一个main方法，一个主启动器）

对象通过引用来操作：栈--->堆

```java
public class Application {
    public static void main(String[] args) {
        //类：抽象的，实例化
        //类实例化后会返回一个自己的对象
        //student对象就是一个Student类的具体实例

        Student xiaoming = new Student();
        xiaoming.name = "小明";

        System.out.println(xiaoming.name);
    }
}
```

```java
//类
public class Student {
    //属性
    String name;
    int age;

    //方法
    public void study() {
        System.out.println(this.name+"在学习");
    }
}
```





## 构造器

有参构造：一旦定义了有参构造，无参就必须显示定义

（不使用，会有一个默认的构造器）

（定义有参构造之后，如果想使用无参构造，显示定义一个无参的构造

1. 使用new关键字，本质是在调用构造器
2. 用来初始化值

```java
public class Person{
    //不需要void或static,和类名相同，无返回值
    public Person(){
    }
    public Person(String name){
    }
}
//快捷键alt+insert
```



## 封装

封装大多数作用属性，方法比较少

```java
public class Student{
    //private：私有（与public相对）
    //属性私有
    private String name;
    private int id;
    private char sex;
    
    //get,set：提供一些可以操作的方法
    //get：获得这个数据
    public String getName() {
        return this.name;
    }
    //set：给这个数据设置值
    public void setName(String name) {
        this.name = name;
    }        
}//快捷键alt+insert   
```



## 继承

继承是类和类之间的关系

JAVA中只有单继承，没有多继承（接口可以多继承）

继承关系存在两种类，一个为子类（派生类），一个为父类（基类），子类继承父类（private不能继承）



### super and this

super：

1. 调用父类的构造方法，必须在构造方法的第一个
2. 必须只能出现在子类的方法或者构造方法中
3. super 和 this不能同时调用构造方法

this：

1. 调用本类的构造方法

```java
public class Student extends Person {
    public Student(){
        //隐藏代码：调用父类的无参构造
        
    }
    
    public String name = "999";
    
    public void test(){
        this.name;//调用自己的name
        super.name;//调用父类的name
        
    }
}
```



### 重写

重写都是方法的重写，与属于无关（快捷键alt+insert ）

子类重写父类的方法，那么执行子类的方法

1. 方法名必须相同
2. 参数列表必须相同
3. 修饰符：范围可以扩大但不能缩小（public>protected>default>private）
4. 抛出的异常：范围可以被缩小，但不能扩大

```java
  Person s1 = new Student();
  //父类的引用指向子类
  Student s2 = new Student();
  
  s1.text();//Person
  s2.text();//Student
  //static的静态方法：方法调用只和左边，定义的数据类型有关
  //非静态方法：重写（如果子类重写了父类的方法，则执行子类的方法）
  //此时
  s1.text();//Student
  s2.text();//Student
```



## 多态

多态是方法的多态，属于没有多态

存在条件：继承关系，方法需要重写（），父类引用指向子类

（static{属于类}，final，private 不能重写）

子类能调用的方法都是子类或父类的

父类能可以指向子类，但是不能调用子类独有的（如果子类有A方法，但是父类没有A方法，则不能调用）



### instanceof和类型转换

```java
  X x = new Y;
  System.out.println(x instanceof Y);
  System.out.println(x instanceof X);
  System.out.println(x instanceof Z);
  //能不能编译通过取决于是否与Y存在父子关系
  
//高转低：强制转换 (假设子类有go方法)
  Person obj = new Student();
  //将obj对象转换为Student类型，这样就可以使用Student类型的方法了
  ((Student)obj).go();

//低转高
  Student student = new Student();
  student.go();//可以执行
  Person person = student;
  person.go();//不能执行
  //子类转换为父可能会丢失子类的方法
  
```



## static关键字

static和类一起加载，只在初始时加载一次

final修饰后不能继承

```java
import static java.lang.Math.random;
import static java.lang.Math.PI;
//静态导入包
```



## 抽象类

不能被new，只能通过子类实现

抽象类中可以写普通方法

抽象方法必须在抽象类中

继承抽象类的子类，必须重写抽象类的方法（除非子类也是给abstract)

```java
public abstract class Action{
    public abstract void doSomething();
    //抽象方法，只有方法名称，没有方法的实现
}
```

常用于抽象对象的公有属性，避免反复创建变量

而后只要去继承这个抽象类，重写方法



## 接口

接口就是规范，定义的是一组规则

接口的本质是契约

声明类的关键字是class，声明接口的关键字是interface

接口可以多继承，抽象只能单继承

接口不能被实例，接口中没有构造方法

定义一些方法，让不同的类实现

所有的常量都是：public static final

```java
public interface UserService{
    void a(String name);
    void b(String name);
}
```

```JAVA
public interface TimeService{
    void c();
}
```



```java
public class UserServiceImp01 implements UserService,TimeService{
//类可以实现接口
    public void a(String name){ 
    }
    public void b(String name){
    }
    
    public void c(){
    }
    //实现接口的类，就必须实现接口的方法
}
```



### 内部类

```java
public class Outer{
    private int id;
    public void out(){
        System.out.println("这是外部类");
    }
    class Inner{
        public void in(){
            System.out.println("这是内部类");
        }
        //获得外部类的私有属性
        public void getid(){
            System.out.println(id);
        }
    }
}
```

```java
public class Application {
    public static void main(String[] args){
        Outer outer = new Outer();
        //通过这个外部类来实例化内部类
        Outer.Inner inner = out.new Inner();
    }
}
```



## 捕获和抛出异常

五个关键字：try,catch,finally,throw,throws

假设要捕获多个异常：从小到大

（ctrl+alt+t）

```java
int a=1;
int b=0;

try{//监控区域
    System.out.println(a/b);//异常
}catch (ArithmeticException e){//括号里面填写想要捕获的异常类型
    //catch捕获异常
    System.out.println("程序出现异常");
}finally{//处理善后工作，不是必要的
    System.out.println("finally")
}

//主动抛出(throw)异常一般在方法里面使用，方法上是用throws,方法里面是throw
public void text(int b){
    if(b==0){
        throw new ArithmeticException//主动抛出异常
    }
}
```

### 错误和异常的类型

![](C:\Users\JJJ\Desktop\work\work01\学习\3345c77c74ccaea03a99b0ce1ecc50be.png)



### 自定义异常

```java
public class MyException extends Exception{
    public MyException(String message){
        super(message)
    }
//----------------------------------------------//
    private int detail;
    public MyException(int message){
        this.detail=a;
    }
    @Override
    public String toString(){
        return "MyException{"+"detail="+detail+'}';
    }
}
```

