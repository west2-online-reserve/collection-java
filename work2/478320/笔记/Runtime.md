# Runtime

Runtime的方法不是静态的所以我们要先获取它的对象，但是这个类的对象我们不能直接new，而是通过public static Runtime getRuntime()的静态方法获取到的，私有化了构造方法，在这个类中它自己创建了一个对象，把它赋值给了currentRuntime这个变量被final修饰，那么它的地址值就不能被改变，也就只能有一个对象，为什么只能有一个对象呢，它表示当前虚拟机的运行环境，而在我们的电脑只能有一个运行环境，所以创建多个对象没有实际意义，所以Runtime只能有一个对象，我们可以学习这样的操作，可以指定我们的对象最多能创建几个，创建的多个对象都是同一个

第二个方法public void exit（int status）和System中停止虚拟机的方法是一样的，其实在底层System的exit方法就是调用Runtime的exit方法

第三个方法 public int availableProcessors 获取cpu的线程数

---

public long maxMemory() JvM能从系统中获取总内存大小单位byte

public long totalMemory JVM已经从系统中获取总内存大小

public long freeMemory JVM剩余内存大小

这三个方法当我们需要使用代码来监控虚拟机的内存，这几个方法就可以用上了

---

public Process（先忽略） exec（String command）运行cmd命令

它不是所以命令都可以用的，比如dir就用不了，没法指定路径（我感觉改变路径后就可以了）这个时候还会报错，需要我们抛出异常，我们可以Alt加enter来选择两种解决方法，默认选择第一种，会抛出异常，没有第一种再选第二种，try catch

shutdown操作加上参数才能进行-s默认在一分钟后关机-s -t后面加指定时间为秒，如果想取消关机操作-a 关机并重启-r
