# 1分支结构

## if

```java
//第一种
if (){

}
//第二种
if (){
    
}else {
    
}
//第三种
if (){
    
}else if (){

} else if(){
    
}else {
    
}
```

## switch

```java
switch (表达式){
    case 值1:
        执行代码;
        break;
    case 值2:
        执行代码；
        break;
    default:
        System.out.println("您输入的信息不存在");
}
```

## 注意事项

1. if功能远远大于swich，if适合区间判断，swich适合比较值
2. swich注意事项
   * 表达式类型只能是byte，short，int，char，String
   * case值不能重复，只能是自变量，不能是变量
   * 不写break会穿透

# 2循环结构

## for

```java
for (int i = 0; i < 3 ; i++) {    //由初始化语句，循环条件，迭代语句组成
    System.out.println("Hello World");    //循环体语句 
    }
}
```

## while

```java
int i = 0;  //初始化语句
while (i < 3){  //循环条件
    System.out.println("Hello World");  //循环体语句 
    i++;  //迭代语句
}
```

## do...while

```java
int i = 0;  //初始化语句
do {  
    System.out.println("Hello World");  //循环体语句 
    i++;  //迭代语句
}while (i < 3);  //循环条件
```

## 注意事项

1. for和while功能完全一样，知道循环几次用for，不知道几次用while
2. for中控制循环的**变量**只能在循环内使用，whlie可以在循环后继续使用
3. do...while先执行，后判断
4. 死循环：一直执行下去
5. 嵌套循环：外部一次，内部一轮

# 3跳转关键字

## break

跳出并结束当前所在循环的执行，或结束所在switch分支的执行

## cotinue

跳出当前循环当次执行，直接进入循环下一次执行

# 4随机数Random类

## 使用步骤

```java
import java.util.Random;  //idea自动完成导包

public class Test2 {
    public static void main(String[] args) {
        Random r = new Random();  //拿到随机数对象
        int number = r.nextInt(10);  //获取0～9之间随机数     
   }
}
```

## 注意事项

1. `nextInt(n)`只能生成 0～n-1之间的随机数，不包含n
2. 生成65～91之间的随机数`int nunber = r.nextInt(27) + 65;`
