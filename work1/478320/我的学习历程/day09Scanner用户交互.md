# Scanner很重要

用于输入（可实现程序和人的交互）

基本语法 new Scanner加Alt加回车

## next() 与 nextLine()方法

用于获取输入的字符串

不同点：next以空格为结束符

​               nextLine以回车为结束符 

***故next无法输出含空格的字符串***

## hasNext与与hasNextLine

用于判断是否还有输入的数据

## 操作图与步骤结合解释如下

1. 输入new Scanner回车
2. 在new Scanner的空格中输入System.in
3. Alt加回车快捷创建自定义变量

![](C:\Users\余思衡\Pictures\Screenshots\屏幕截图 2023-09-16 220129.png)

4. 最后一步一定要记住关闭接受器，否则它会一直占用资源

5. 整串代码含义为，创建main方法，在main方法中导入接收器并命名为scanner，在main方法中创建if方法循环结构，并在if方法中定义了一个String类型的变量str为接收器接收的内容，如果接收器接收到了下一个字符，那么就输出str，最后由于接收器是在main方法中打开的，故也需在main方法中关闭

## 拓展使用

1.还可以用于判断输入的类型

![](C:\Users\余思衡\Pictures\Screenshots\屏幕截图 2023-09-17 001808.png)

2.制作计算器

![](C:\Users\余思衡\Pictures\Screenshots\屏幕截图 2023-09-17 003637.png)





