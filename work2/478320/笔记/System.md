# System

System也是工具类，提供了一些与系统相关的方法

常见的方法有下面三个

1. public static void exit(int status)表示终止当前运行的Java虚拟机，方法的形参是状态码，如果是0，就是正常停止，如果不是0，就是异常停止，但不管怎么样都会停止
2. public static long currentTimeMillis()返回当前系统时间毫秒值形式，计算机中时间原点1970年1月1日00：00：00算C语言的生日，但在我们操作系统中由于时差时间原点是1970年1月1日08：00：00，获取从时间原点开始到我代码运行的时间点，一共过了多少毫秒，我们可以在程序开始和程序结束时候写上这个方法的返回值，我们把两个返回值相减就可以获得程序运行的总时间，从而判断不同代码之间的效率来选择我们想要的代码，我们可以多运行几次取平均值
3. public static void arraycopy(数组源数据（要拷贝的数据从哪里来），起始索引(从那个开始拷贝)，目的地数组（要拷贝到哪里），起始索引（从哪里开始拷贝进来），拷贝个数（一共要拷贝几个）) ***数组拷贝***如果数据源数组和目的数组都是基本数据类型那么两者的类型必须保持一致，如果数据源数组和目的地数组都是引用类型那么子类的数据可以copy到父类中



