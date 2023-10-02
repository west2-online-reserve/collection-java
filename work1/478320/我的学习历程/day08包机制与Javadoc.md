# 包机制

1. 通常用公司域名倒置作为包名，如com.baidu.www
2. 要在Java程序中导入包要用import
3. 一个包中不能放有两份相同名字的文件，在导入包时也不能导入与当前包名相同的文件
4. import在要导入的包的结尾加上*可以导入该包的全部内容

# Javadoc文档注释

可将我们的注释信息生成一个API文档

可以使用[java17帮助文档](https://doc.qzxdp.cn/jdk/17/zh/api/index.html)用于辅助

## 参数信息

- @author作者
- @version版本
- @since最早使用的JDK版本
- param参数名
- @return返回值情况
- @throw异常抛出情况

## 创建文档

在idea中选择文件在文件夹中打开，并在该文件夹中打开终端，输入javadoc -encoding UTF-8 -charset UTF-8 Doc.java在文件夹中点击index

![使用idea创建文件](C:\Users\余思衡\Pictures\Screenshots\屏幕截图 2023-09-15 215514.png)