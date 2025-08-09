# Java入门学习1

---

## Java程序运行机制

1. 编译型(compile)

1. 解释型

***

## something about IDE

集成开发环境（IDE,Integrated Development Environment）

---


## IDEA的注释

- 单行注释(line comment)
- 多行注释(block comment)
- 文档注释(JavaDoc)
***

## 数据类型

*基本类型(primitive type)*

*引用类型(reference type)*:类、接口、数组

### 整型

1. byte: -128~127

2. short:-32768~32767

3. long//Long类型要在数字后面加L

### 浮点型

1. float//Float类型要在数字后面加F

### 字符型

- string不是关键字，而是一个类

### 布尔型

- 在Java中，是**boolean**而非**bool**

---

## 数据拓展

### 整数拓展：

二进制0b	十进制	八进制0	十六进制0x

```java
int num0 = 010 //八进制
int num1 = 0b10 //二进制
int num2 = 0x10 //十六进制
```

### 浮点数拓展

1. float 有限 离散 舍入误差 大约 接近但不等于
2. BigDecimal 数学工具类

### 字符拓展

1. 类型强制转换

   ```Java
   char c1 = 'A';
   System.out.println(c1); //output"A"
   System.out.println((int)c1); //output"65"
   ```
---

## 转义字符

- \t 制表符 table键

---

## 类型转换

低-------------------------------------------------------------------->高

byte ,short, char ---> int ---> long ---> float ---> double

  1         2         1            4            8              4               8     单位：字节

1. 强制转换:高 -> 低
2. 自动转换:低 -> 高

---

## 常量

关键字：final

书写习惯：字母大写

```Java
static final double PI = 3.14;
final static double PI = 3.14; //final修饰符不区分先后
```



---

## 其他

- JDK7新增功能，，数字之间可以用下划线分割

  ```Java
  long num = 10_0000_0000;
  ```

## Java 与 C

| Java   | C        |
| ------ | -------- |
| 类     | 类       |
| 方法   | 函数     |
| 类变量 | 数据成员 |



