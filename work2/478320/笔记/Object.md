# Object

没有成员变量，只有空参构造

在继承时有一个问题，继承类默认访问父类的无参构造，为什么呢，因为在顶层的构造中只有无参的构造方法

三个常见方法

1. public String toString（）返回对象的字符串表现形式，如果对对象使用就会返回对象的地址值，地址值由三部分组成，前面一部分是对象所在的包名加类名加@加对象的地址值，源代码返回包名加类名加@利用哈希值进行十六进制等复杂计算再返回

   直接打印对象和使用toString方法结果是一样的，现在了解一下输出语句，System的out是一个静态变量，初始值是null，不过后面会给他赋值它的类型是PrintStream，System.out可以获取打印的对象，println方法会调用String里的valueof方法把对象传递过去，而valueof会判断当前对象是否为空，如果是空返回字符串null，如果不是空就会调用toString方法，再交给writeln打印并换行

2. public boolean equals比较两个对象是否相等

3. protect Object clone对象克隆

