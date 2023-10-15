#String
####1.字符串的常量存储位置
> （1）字符串常量都存储在字符串常量池（StringTable）中
> （2）字符串常量池不允许存俩个相同的字符串常量
> （3）字符串常量池，在不同的jdk版本中，存放位置不同
> （4）jdk7前存放在方法区中，jdk7后存在在堆空间中 
####2.String的不可变性（下文位置指的是内存位置）
> (1) 当对字符串变量重新赋值时，需要重新指定一个字符串常量的位置修改，不能在原有的位置修改
>（2）当对现有字符串进行拼接操作时，我们需要重新开辟空间，不能在原有位置修改
> (3) 当调用字符串的replace（）替换现有的某个字符时，需要重新开辟空间保存修改以后的字符串，不能在原有位置修改
####String实例化的俩种方式
1.String s1 = "hello"
2.String s2 =  new String

- 面试题
  String s = new String("hello");在内存中创建了几个对象
>俩个，一个是堆空间中new的对象，
另一个是在字符串常量池中生成的字面量
####3.String的连接方式
情况1：常量+常量：结果仍储存在字符串常量池中 
- 注：此时的常量可能是字面量，可能是final修饰的变量

情况2：变量+变量 或 变量+常量：都会通过new的方式创建一个字符串，返回堆空间中字符串对象的地址
情况3：调用字符串的intern（）：返回字符串常量池中字面量的地址
情况4：调用字符串的concat（）：不管是常量调用此方法，还是变量调用，同样不管参数是常量还是变量，总之，调用完concat（）方法，都返回一个new的值
####4.String的构造器和方法
#####1.构造器
- ***String构造器的使用***
```java
public void test1(){
        String s1 = new String();
        String s2 = new String("");
        String s3 = new String(new char[] {'a','b','c'});
        System.out.println(s3);
}
```
- ***String与其他常见结构之间的转换***
  
1.String与基本数据类型，包装类之间的转换
```java
 public void test2(){
        int num = 10;
        //基本数据类型 ---> String
        //方式1
        String s1 = num + "";
        //方式2
        String s2 = String.valueOf(num);

        //String --->基本数据类型：调用包装类的parseXxx(String str)
        String s3 = "123";
        int i1 = Integer.parseInt(s3);
    }
``` 

2.String与char型数组的转换

```java
    public void test3() {
         String str = "hello";
        //String --->char[]:调用String的toCharArray()
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        //char[] --->String:调用String的构造器
        String str1 = new String(arr);
        System.out.println(str1);
    }
```

3.String与byte数组的转换 

```java
//String与byte数组的转换
    /*
    在utf-8字符集中，一个汉字占用3个字节，一个字母占用1个字节
    在gbk字符集中，一个汉字占2个字节，一个字母占用1个字节
    utf-8或gdk都向下兼容了ascii码

    编码与解码：
     编码：String ---> 字节或字节数组
     编码：字节或字节数组 ----> String
    要求：解码时使用的字符集必须与编码时使用的字符集一致！不一致，就会乱码
     */
    @Test
    public void test4() throws UnsupportedEncodingException {
        String str = new String("abc中国");
        //String ---> byte[]:调用String
        byte[] arr = str.getBytes();//使用默认的字符集
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        //getBytes(String charseName):使用指定的字符集
        byte[] arr1 = str.getBytes("gbk");
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr[i]);
        }

        //byte[] ---> String
        String str1 = new String(arr);//使用默认字符集
        System.out.println(str1);

        String str2 = new String(arr, "utf-8");
        //显示的指明使用utf-8
        System.out.println(str2);
        /*乱码
        String str3 = new String(arr, "gbk");//显示的指名使用gdk
        System.out.println(str2);
        */
    }
``````          
#####2.String常用方法
- ***isEmpty***
  >判断字符串是否为空

```java
 public void test1() throws NullPointerException{
        //isEmpty：判断字符串是否空
        String s1 = "";
        String s2 = new String();
        String s3 = new String("");
        System.out.println(s1.isEmpty());
        System.out.println(s2.isEmpty());
        System.out.println(s3.isEmpty());
        String s4 = null;
        try {
            System.out.println(s4.isEmpty());//空指针异常
        }catch (NullPointerException e){

        }
 }
 ```

- ***length***
  > 返回字符串的长度

  ```java
   String s5 = "hello";
        System.out.println(s5.length());
  ``````

- ***concat***
  >拼接

  ```java
  String s6 = "hello";
        System.out.println(s6.concat("world"));
  ```

- ***equals***
  >比较字符串是否相等（区分大小写）
  ```java
   String s5 = "hello";
        String s6 = "hello";
        String s1 = "Hello";
        //equals:比较字符串是否相等 注：区分大小写
        System.out.println(s5.equals(s6));
        System.out.println(s1.equals(s5));
  ``````      

- ***equalsIgnorecase***
  >同上（不区分大小写）
  ```java
   String s5 = "hello";
        String s6 = "hello";
        String s1 = "Hello";
         System.out.println(s1.equalsIgnoreCase(s5));
  ``````      
- ***compareTo***
  >比较字符串大小，从第一个字开始比较（区别大小写，按照Unicode编码进行大小比较）
  ``````java
  String s3 = "abcd";
        String s4 = "adef";
        System.out.println(s3.compareTo(s4));
  ``````

- ***compareToIgnorecase***
  >同上（不区分大小写）
  ``````java
  String s5 = "abcd";
        String s6 = "aBcd";
        System.out.println(s5.compareTo(s6));
        System.out.println(s5.compareToIgnoreCase(s6));
  ``````

- ***toLowerCase***
  >将字符串中的大写字母转换成小写
  ``````java
   String s1 = "HELLO";
        System.out.println(s1.toLowerCase());
  ``````

- ***toUpperCase***
  >将字符串中的小写字母转换成大写
  ``````java
  String s2 = "hello";
        System.out.println(s2.toUpperCase());
  ``````      

- ***trim***
  >去除字符串后面的空格
  ``````java
  String s9 = " he  llo ";
        System.out.println("****" + s9.trim() +"*****");
  ``````
- ***intern***
  >返回常量池字面量
  ``````java
  String s3 = "hello";
        String s4 = new String("hello");
        System.out.println(s3==s4.intern());
  `````` 
- ***comtains***
  >是否包含xx
  ``````java
    String s1 = "haoyuhaoyuyu";
        System.out.println(s1.contains("yu"));
  ``````  
- ***indexOf***
###### indexOf(xx)
   > 从前往后查找当前字符串中xx，返回第一次出现时下标 
   ``````java
   String s1 = "haoyuhaoyuyu";
   System.out.println(s1.indexOf("yu"));
        System.out.println(s1.indexOf("yu1"));//没找到指定
   ``````
###### indexOf(String str,int fromInfex)
   >增加一个参数，从fromIndex的对应下标开始搜索，且包括这个下标对应的字符
   ``````java
    String s1 = "haoyuhaoyuyu";
    System.out.println(s1.indexOf("yu",4));
   ```````

- ***lastIndexOf***
###### lastIndexOf（xx） 
   >从后往前查找当前字符串中xx，返回第一次出现时下标
   ``````java
   String s1 = "haoyuhaoyuyu";
   System.out.println(s1.lastIndexOf("yu"));
   ``````

###### lastIndexOf(String str,int fromInfex) 
   >增加一个参数，从fromIndex的对应下标开始搜索，且包括这个下标对应的字符
  ``````java
  String s1 = "haoyuhaoyuyu";
   System.out.println(s1.lastIndexOf("yu",11));
   ``````

###  注：
无论前到后，还是后到前，还是从哪个数字开始搜索，字符的数字下标**只有索引**决定，**而索引只从前往后，从0开始**，无论lastIndexOf还是indexOf,数字fromIndex所指向的字符也是按照**索引**去找，(例如fromIndex：4，那无论是哪种indexOf，都是指向第五个字符)

- ***substring***
###### substring(beginIndex)
  >返回一个字符串，它是由字符串从beginIndex开始截取到最后
  ``````java
  String s1 = "haoyuhaoyuyu";
        //单参数：开始截取位置
        System.out.println(s1.substring(5));
        //双参数：开始和结束截取位置后一位
        
  ``````
###### substring(int beginIndex,int endIndex)
   >同上，但不是截取到最后，而是endIndex前一位就停止
   ``````java
   String s1 = "haoyuhaoyuyu";
    System.out.println(s1.substring(5,10));
        //索引问题都是左闭右开(别和长度混淆)
   ``````
###  注：
索引问题中都是**左闭右开**，即本题中beginIndex可取，而endIndex不可取

- ***charAt***
  >返回[index]位置的字符
  ``````java
  String s1 = "haoyuhaoyuyu";
        System.out.println(s1.charAt(2));
  ``````

- ***toCharArray***
  >将字符串转化成一个新的字符数组返回
  ``````java
  String s1 = "haoyuhaoyuyu";
   char[] arr = s1.toCharArray();
        System.out.println(Arrays.toString(arr));
  ``````   

- ***static valueof()***
###### static valueof(char[] date,int offset,int count)
> 从offset开始，转换count长度的字符变成字符集
  ``````java
  String s2 = String.valueOf(new char[]{'a', 'b', 'c'},1,2);
  System.out.println(s2);
  ``````
###### static copyvalueof(char[] date,int offset,int count)
> 与上面相同
  ``````java
  String s3 = String.copyvalueOf(new char[]{'a', 'b', 'c'},1,2);
  System.out.println(s3);
  ``````

- ***startsWith***
###### startsWith(xx)
>测试该字符串是否以指定前缀开头
  ``````java
  String s1 = "haoyuhaoyuyu";
  System.out.println(s1.startsWith("hao"));
  ```````

###### startsWith(String profix,int toffset)
>同上，但是从offset开始看
``````java
String s1 = "haoyuhaoyuyu";
System.out.println(s1.startsWith("hao",5));
``````
- ***endsWith***
  >测试该字符串是否以指定后缀结束
``````java
String s1 = "haoyuhaoyuyu";
System.out.println(s1.endsWith("yu"));
``````

- ***replace***
###### replace(char oldchar,char newchar)
>返回一个新的字符串，通过newchar替代oldchar
``````java
        String s1 = "hello";
        String s2 = s1.replace('l','w');
        System.out.println(s1);
        System.out.println(s2);
``````

###### replace(CharSequence target,CharSequence replacement)
>用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串,replacement可以与target长度不同
``````java
String s1 = "hello";
        String s2 = s1.replace("ll","ww");
        System.out.println(s1);
        System.out.println(s2);
``````

    

  

