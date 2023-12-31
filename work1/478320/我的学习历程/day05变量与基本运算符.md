# 变量

每个变量都有类型，可以是基础类型也可以是引用类型

变量必须是合法的标识符（详情见上章）

---

一个类型可以同时输出多个变量如

```java
int a=10,b=20,c=30
```

但是会使代码降低可读性，不建议使用

## 变量类型

### 1类变量

类变量是在方式之前括号中的变量，必须含有static，可不予声明和赋予含义直接输出，输出值为默认值,用法如下，在类中定义，可直接在方法中使用（感觉比实例变量方便呢）

![](C:\Users\余思衡\Pictures\Screenshots\屏幕截图 2023-09-13 210518.png)

### 2实例变量

与类变量位置相似，但不需要static，可不予声明和赋予含义直接输出，输出值为默认值,以下为使用方法，当实例变量在自己的类中可以直接引用

```java
new Demo02() 加Alt与回车快捷自定义
```



![](C:\Users\余思衡\Pictures\Screenshots\屏幕截图 2023-09-13 205546.png)

### 3局部变量

与前二者有较大差异，在方式中，必须声明和赋予含义才可输出

### 备注

- 默认值 数字基本类型为0或0.0 除基本类型都为null 布尔类型为false
- ![](C:\Users\余思衡\Pictures\Screenshots\屏幕截图 2023-09-13 204516.png)不同方式之间的值不可互通

## 常量

常量被设定后，在程序运行过程中不允许改变，可增强程序可读性

```java
用法在static前后加上final//顺序其实无关,修饰符不区分前后
```

## 变量的命名规范

- 所有变量，方法，类名：见名知意原则，不要用拼音，特别是变量，想输入钱就输money，不要写个a啊什么的根本看不懂在干嘛
- 类成员变量（在类中的变量）：首字母小写和驼峰原则 如：monthSalary
- 局部变量：首字母小写和驼峰原则
- 常量，全大写
- 类名：首字母大写和驼峰原则
- 方法名：首字母小写和驼峰原则





