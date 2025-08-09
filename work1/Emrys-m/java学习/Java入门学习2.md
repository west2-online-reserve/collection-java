# Java入门学习2

## if语句

```java
String s = scanner.nextLine();

if(s.equals("Hello")) //equals:判断字符串是否相等
```

## switch语句

```java
String name = "Java";
switch(name){
    case "Java":break;
    case "C":break;
    case "C++":break;
}
//JDK7的新特性，表达式结果可以是字符串
```

## 反编译

Java-----class(字节码文件)------反编译(IEDA)

位置：Java流程控制05

## Java的格式化输入

- 使用正则表达式解析

  ```java
  import java.util.Scanner;
  import java.util.regex.Matcher;
  import java.util.regex.Pattern;
  
  public class Main {
      public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
          String input = scanner.nextLine();
          
          // 定义匹配模式：数字-字母-数字
          Pattern pattern = Pattern.compile("(\\d+)-([A-Za-z]+)-(\\d+)");
          Matcher matcher = pattern.matcher(input);
          
          if (matcher.find()) {
              int num1 = Integer.parseInt(matcher.group(1));
              String str = matcher.group(2);
              int num2 = Integer.parseInt(matcher.group(3));
              
              System.out.printf("数字1：%d，字符串：%s，数字2：%d\n", num1, str, num2);
          } else {
              System.out.println("输入格式不正确！");
          }
          
          scanner.close();
      }
  }
  ```

  

  