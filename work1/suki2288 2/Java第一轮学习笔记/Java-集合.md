# 概述

* 集合是一种容器，用来装数据的，类似于数组，但集合的大小可变，开发中也非常常用，Java提供了很多不同的集合

* 集合体系结构
  * Collection 单列集合，每个元素（数据）只包含一个值
    * List系列集合特点：添加的元素是有序、可重复、有索引
      * ﻿ArrayList、LinekdList：有序、可重复、有索引
    * Set系列集合特点：添加的元素是无序、不重复、无索引
      * HashSet：无序、不重复、无索引
      * LinkedHashSet： 有序、不重复、无索引
      * TreeSet：按照大小默认升序排序、不重复、无索引
  * Map 双列集合，每个元素包含两个值（键值对）
  
* ```java
  import java.util.ArrayList;
  import java.util.HashSet;
  
  public class Test1 {
      public static void main(String[] args) {
          // 简单确认一下collection集合的特点
          ArrayList<String> list = new ArrayList<>();
          list.add("java1");
          list.add("java2");
          list.add("java3");
          list.add("java1");
          list.add("java2");
          System.out.println(list);  // [java1, java2, java3, java1, java2]
  
          HashSet<String> set = new HashSet<>();
          set.add("java1");
          set.add("java2");
          set.add("java3");
          set.add("java1");
          set.add("java2");
          System.out.println(set);  // [java3, java2, java1]
      }
  }
  ```

# Collection的常用方法

* Collection是单列集合的祖宗，它规定的方法（功能）是全部单列集合都会继承的

* ```java
  import java.util.ArrayList;
  import java.util.Collection;
  import java.util.Objects;
  
  public class Test2 {
      public static void main(String[] args) {
          Collection<String> c = new ArrayList<>(); // 多态写法
          // 1.添加元素
          c.add("java");
  
          // 2.清空集合
          c.clear();
  
          // 3.判断集合是否为空
          c.isEmpty();
  
          // 4.获取集合大小
          c.size();
  
          // 5.判断集合中是否包含某元素
          c.contains("java");
  
          // 6.删除某个元素，重复元素默认删除第一个
          c.remove("java");
  
          // 7.把集合转成数组
          Object[] arr = c.toArray();
          // 转成指定类型的数组
          String[] arr2 = c.toArray(new String[c.size()]);
  
          // 8.把一个集合的全部数据倒入到另一个结合中去（批量添加数据）
          Collection<String> c1 = new ArrayList<>();
          c1.add("java1");
          c1.add("java2");
          Collection<String> c2 = new ArrayList<>();
          c2.add("java3");
          c2.add("java4");
          c1.addAll(c2);
          System.out.println(c1);
      }
  }
  ```

# Collection的遍历方法

## 迭代器

* 迭代器是用来遍历集合的专用方式（数组没有迭代器），在Java中选代器的代表是Iterator

* Collection集合获取选代器的方法

  * `Iterator<E> iterator() ` 返回集合中的迭代器对象，该迭代器对象默认指向当前集合的第一个元素

* Iterator选代器中的常用方法

  * `boolean hasNext( )`  询问当前位置是否有元素存在，返回true/false
  * `E next( )` 获取当前位置的元素，并同时将迭代器对象指向下一个元素处

* ```java
  package d2_traverse;
  
  import java.util.ArrayList;
  import java.util.Collection;
  import java.util.Iterator;
  
  public class CollectionDemo03 {
      public static void main(String[] args) {
          Collection<String> c = new ArrayList<>();
          c.add("赵敏");
          c.add("小昭");
          c.add("素素");
          c.add("灭绝");
          System.out.println(c);
  
          // 使用迭代器遍历集合
          // 1.从集合对象中获取迭代器对象
          Iterator<String> it = c.iterator();
          System.out.println(it.next());
          System.out.println(it.next());
          System.out.println(it.next());
          System.out.println(it.next());
          // System.out.println(it.next());  //出现异常，不能超过集合元素个数
  
          // 2.使用循环结合迭代器遍历集合
          while (it.hasNext()){
              String ele = it.next();
              System.out.println(ele);
          }
      }
  }
  ```

## 增强for

* 格式`for(元素数据类型 变量名 : 数组或集合) {}`

* 增强for可以用来遍历集合或者数组

* ﻿﻿增强for遍历集合，本质就是迭代器遍历集合的简化写法

* ```java
  package d2_traverse;
  
  import java.util.ArrayList;
  import java.util.Collection;
  
  public class CollectionDemo02 {
      public static void main(String[] args) {
          Collection<String> c = new ArrayList<>();
          c.add("赵敏");
          c.add("小昭");
          c.add("素素");
          c.add("灭绝");
  
          // 使用增强for遍历集合或者数组
          for (String ele : c){
              System.out.println(ele);
          }
  
          String[] names = {"迪丽热巴", "古力娜扎", "玛尔扎哈", "卡尔扎巴"};
          for(String name : names){
              System.out.println(name);
          }
      }
  }
  ```

## lambda表达式

```java
package d2_traverse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class CollectionDemo03 {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.add("赵敏");
        c.add("小昭");
        c.add("素素");
        c.add("灭绝");

        // 结合Lambda表达式遍历集合
        c.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        
        c.forEach(s -> {
            System.out.println(s);
        });
        
        c.forEach(s -> System.out.println());
        
        c.forEach(System.out::println);
        
        
    }
}
```

## 案例：遍历集合中的自定义对象

* 电影类

* ```java
  package d2_traverse;
  
  public class Movie {
      private String name;
      private double score;
      private String actor;
  
      public Movie() {
      }
  
      public Movie(String name, double score, String actor) {
          this.name = name;
          this.score = score;
          this.actor = actor;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public double getScore() {
          return score;
      }
  
      public void setScore(double score) {
          this.score = score;
      }
  
      public String getActor() {
          return actor;
      }
  
      public void setActor(String actor) {
          this.actor = actor;
      }
  
      @Override
      public String toString() {
          return "Movie{" +
                  "name='" + name + '\'' +
                  ", score=" + score +
                  ", actor='" + actor + '\'' +
                  '}';
      }
  }
  ```

* 主程序

* ```java
  package d2_traverse;
  
  import java.util.ArrayList;
  import java.util.Collection;
  
  public class CollectionTest4 {
      public static void main(String[] args) {
          // 目标：完成电影信息的展示
          // 创建一个集合容器负责储存多部电影对象
          Collection<Movie> movies = new ArrayList<>();
          movies.add(new Movie("《肖生克的救赎》",9.7,"罗宾斯"));
          movies.add(new Movie("《霸王别姬》",9.6,"张国荣、张丰毅"));
          movies.add(new Movie("《阿甘正传》",9.5,"汤姆.汉克斯"));
          System.out.println(movies); // 输出地址
  
          for (Movie movie : movies) {
              System.out.println(movie.getName());
              System.out.println(movie.getScore());
              System.out.println(movie.getActor());
              System.out.println("=================================");
          }
      }
  }
  ```

# List集合

## 特点，特有方法

* List集合因为支持索引，所以多了很多与索引相关的方法，当然，Collection的功能List也都继承了

* ```java
  package d3_collection_list;
  
  // 掌握list集合的特点，以及提供的特有方式
  
  import java.util.ArrayList;
  import java.util.List;
  
  public class Test1 {
      public static void main(String[] args) {
          // 1.创建一个ArrayList集合对象（有序，可重复，有索引）
          List<String> list = new ArrayList<>();  // 一行经典代码
          list.add("蜘蛛精");
          list.add("至尊宝");
          list.add("至尊宝");
          list.add("牛夫人");
          System.out.println(list);
  
          // 2.在某个索引位置插入元素
          list.add(2,"紫霞仙子");
  
          // 3.根据索引删除元素，返回被删除元素
          list.remove(2);
  
          // 4.返回集合中指定位置的元素
          System.out.println(list.get(3));
  
          // 5.修改某位置处的元素值，并且返回原来的数据
          list.set(3,"牛魔王");
      }
  }
  ```

## 遍历方式

* for循环（因为list集合有索引）

* 迭代器

* 增强for循环

* Lambda表达式

* ```java
  package d3_collection_list;
  
  import java.util.ArrayList;
  import java.util.Iterator;
  import java.util.List;
  
  public class Test {
      public static void main(String[] args) {
          List<String> list = new ArrayList<>();
          list.add("糖宝宝");
          list.add("蜘蛛精");
          list.add("至尊宝");
  
          // for循环
          for (int i = 0; i < list.size(); i++) {
              String s = list.get(i);
              System.out.println(s);
          }
  
          // 迭代器
          Iterator<String> it = list.iterator();
          while (it.hasNext()){
              System.out.println(it.next());
          }
  
          // 增强for循环（foreach）
          for (String s : list) {
              System.out.println(s);
          }
  
          // lambda
          list.forEach(s -> {
              System.out.println(s);
          });
      }
  }
  ```

## ArrayList集合的底层原理

* ArrayList和LinkedList底层采用的数据结构（储存组织数据的方式）不同，应用场景不同

### ArrayList集合的底层原理

* 基于数组实现的（数组特点：查询快，增删慢）
  * 查询速度快（注意：是根据索引查询数据快）：查询数据通过地址值和索引定位，查询任意数据耗时相同
  * ﻿﻿删除效率低：可能需要把后面很多的数据进行前移
  * ﻿﻿添加效率极低：可能需要把后面很多的数据后移，再添加元素；或者也可能需要进行数组的扩容
* 底层原理
  * 利用无参构造器创建的集合，会在底层创建一个默认长度为0的数组
  * ﻿﻿添加第一个元素时，底层会创建一个新的长度为10的数组
  * ﻿﻿存满时，会扩容1.5倍
  * ﻿﻿如果一次添加多个元素，1.5倍还放不下，则新创建数组的长度以实际为准

### ArrayList集合适合的应用场景

* ArrayList适合：根据索引查询数据，比如根据随机索引取数据（高效）！或者数据量不是很大时！
* ArrayList不适合：数据量大的同时，又要频繁的进行增删操作

## LinkedList的集合的底层原理

### 基于双链表实现的

* 什么是链表？有啥特点？

  * 链表中的结点是独立的对象，在内存中是不连续的，每个结点包含数据值和下一个结点的地址（一个接一个）
  * 链表的特点1：查询慢，无论查询哪个数据都要从头开始找

  * ﻿﻿链表的特点2：链表增删相对快

### 特点

* 查询慢，增删相对较快，但对首尾元素进行增删改查的速度是极快的

### 应用场景：设计队列，设计栈

* 只是在首部增删元素，用LinkedList来实现很合适！

* 数据进入栈模型的过程称为：压/进栈（push）
* 数据离开栈模型的过程称为：弹/出栈（pop）

```java
package d3_collection_list;

import java.util.LinkedList;

public class Test3 {
    public static void main(String[] args) {
        // 1.创建一个队列
        LinkedList<String> queue = new LinkedList<>(); // 此处不要用多态

        // 入队
        queue.addLast("1");
        queue.addLast("2");
        queue.addLast("3");
        queue.addLast("4");
        System.out.println(queue);
        // 出队
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println("===============================");


        // 创建一个栈对象
        LinkedList<String> stack = new LinkedList<>();
        // 压栈(push)
        stack.addFirst("1");  // 可以用官方API stack.push
        stack.addFirst("2");
        stack.addFirst("3");
        stack.addFirst("4");
        stack.addFirst("5");
        // 出栈(pop)     // stack.pop
        System.out.println(stack.removeFirst());
        System.out.println(stack.removeFirst());
    }
}
```

# Set集合

## 特点

* 添加的元素是无序（添加数据顺序和获取出的数据顺序不一致）、不重复、无索引
  * HashSet：无序、不重复、无索引
  * LinkedHashSet： 有序、不重复、无索引
  * TreeSet：排序、不重复、无索引

```java
package d4_collection_set;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        // Hashset 无序（一般来说只无序一次） 不重复 无索引
        Set<Integer> set = new HashSet<>(); // 创建一个Hashset集合对象，一行经典代码

        // LinkedHashSet  有序 不重复 无索引
        Set<Integer> set2 = new LinkedHashSet<>();  
        
        // TreeSet 可排序（默认升序） 不重复 无索引
        Set<Integer> set3 = new TreeSet<>();


    }
}
```

## Hashset集合的底层原理

### 哈希值

* 就是一个int类型的数值，Java中每个对象都有一个哈希值
* ﻿﻿Java中的所有对象，都可以调用Obejct类提供的hashCode方法，返回该对象自己的哈希值
* `public int hashCode( )`返回对象的哈希码值
* 对象哈希值的特点
  - ﻿同一个对象多次调用hashCode（）方法返回的哈希值是相同的
  - ﻿﻿不同的对象，它们的哈希值一般不相同，但也有可能会相同（哈希碰撞）（因为int就正负21亿）

### 哈希表

- ﻿﻿JDK8之前，哈希表=数组+链表
- ﻿﻿JDK8开始，哈希表=数组+链表+红黑树
- 哈希表是一种增删改查数据，性能都较好的数据结构
- HashSet的底层原理就是﻿基于哈希表实现

### JDK8之前HashSet集合的底层原理

* 创建一个默认长度16的数组，默认加载因子为0.75，数组名table
* ﻿﻿使用元素的哈希值对数组的长度求余计算出应存入的位置
* ﻿﻿判断当前位置是否为null，如果是null直接存入
* ﻿﻿如果不为null，表示有元素，则调用equals方法比较（相等，则不存；不相等，则存入数组）
  * JDK8之前，新元素存入数组，占老元素位置，老元素挂下面
  * JDK 8开始之后，新元素直接挂在老元素下面
* 如果数组快占满了，链表会过长，导致查询性能降低，此时需要扩容

![IMG_0936](/Users/cjf/Downloads/IMG_0936.JPG)

### JDK8之后HashSet集合的底层原理

* JDK8开始，当链表长度超过8，且数组长度>=64时，自动将链表转成红黑树，进一步提高了操作数组的性能

### 数据结构（树）

* 二叉树
  * 小的存左边，大的存右边，一样的不存
  * ﻿存在的问题：当数据已经是排好序的，导致查询的性能与单链表一样，查询速度变慢（长短腿）
* 平衡二叉树
  * 在满足查找二叉树的大小规则下，让树尽可能矮小，以此提高查数据的性能
* 红黑树
  * 红黑树，就是可以自平衡的二叉树
  * ﻿﻿红黑树是一种增删改查数据性能相对都较好的结构

### HashSet集合去重复的机制

* Hashset集合默认不能对内容一样的两个不同对象去重复，两个内容一样的学生对象存入HashSet同时存在
* 结论：如果希望Set集合认为2个内容一样的对象是重复的，必须重写对象的hashCode（）和equals（）方法

![Screenshot 2024-12-11 at 20.04.11](/Users/cjf/Library/Application Support/typora-user-images/Screenshot 2024-12-11 at 20.04.11.png)

## LinkedHashSet集合的底层原理

* 依然是基于哈希表（数组、链表、红黑树）实现的
* 但是，它的每个元素都额外的多了一个双链表的机制记录它前后元素的位置
* ![IMG_0978](/Users/cjf/Downloads/IMG_0978.jpg)



## TreeSet集合

* 特点：不重复、无索引、可排序（默认升序排序，按照元素的大小，由小到大排序）
* ﻿﻿底层是基于红黑树实现的排序
* 注意：
  - ﻿﻿对于数值类型：Integer，Double，默认按照数值本身的大小进行升序排序
  - ﻿﻿对于字符串类型：默认按照首字符的编号升序排序
  - ﻿对于自定义类型如Student对象，TreeSet默认是无法直接排序的
* 有两种自定义排序规则
  * 让自定义的类（如学生类）实现Comparable接口，重写里面的compareTo方法来指定比较规则
  * ![Screenshot 2024-12-11 at 20.50.09](/Users/cjf/Desktop/Screenshot 2024-12-11 at 20.50.09.png)
  * 通过调用TreeSet集合有参数构造器，可以设置Comparator对象（比较器对象，用于指定比较规则
  * ![Screenshot 2024-12-11 at 20.50.52](/Users/cjf/Desktop/Screenshot 2024-12-11 at 20.50.52.png)
  * 两种方式中，关于返回值的规则：
    - ﻿﻿如果认为第一个元素＞第二个元素 返回正整数即可
    - ﻿﻿如果认为第一个元素<第二个元素返回负整数即可
    - ﻿﻿如果认为第一个元素=第二个元素返回0即可，此时Treeset集合只会保留一个元素，认为两者重复
  * 如果类本身有实现Comparable接口，TreeSet集合同时也自带比较器，默认使用集合自带的比较器排序

# 集合的使用总结

* 如果希望记住元素的添加顺序，需要存储重复的元素，又要频繁的根据索引查询数据？
  * 用ArrayList集合（有序、可重复、有索引），底层基于数组的（常用）
* 如果希望记住元素的添加顺序，且增删首尾数据的情况较多？
  * 用LinkedList集合（有序、可重复、有索引），底层基于双链表实现的
* 如果不在意元素顺序，也没有重复元素需要存储，只希望增删改查都快？
  * 用HashSet集合（无序，不重复，无索引），底层基于哈希表实现的（常用）

* 如果希望记住元素的添加顺序，也没有重复元素需要存储，且希望增删改查都快？
  * 用LinkedHashSet集合（有序，不重复，无索引），底层基于哈希表和双链表
* 如果要对元素进行排序，也没有重复元素需要存储？且希望增删改查都快？
  * 用TreeSet集合，基于红黑树实现。

# 集合的并发修改异常问题

* 使用迭代器遍历集合时，又同时在删除集合中的数据，程序就会出现并发修改异常的错误
* ﻿由于增强for循环遍历集合就是迭代器遍历集合的简化写法，因此，使用增强for循环遍历集合，又在同时删除集合中的数据时，程序也会出现并发修改异常的错误
* 怎么保证遍历集合同时删除数据时不出bug？
  - ﻿﻿使用迭代器遍历集合，但用迭代器自己的删除方法删除数据即可
  - ﻿﻿如果能用for循环遍历时：可以倒着遍历并删除；或者从前往后遍历，但删除元素后做i--操作

```java
package d5_collection_exception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b1");
        list.add("c1");
        list.add("d");
        list.add("e1");
        list.add("f1");
        System.out.println(list);

         // 需求：找出带 1 的名字，并且从集合中删除
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String name = it.next();
            if (name.contains("1")) {
                list.remove(name);
            }
        }
        System.out.println(list); // 报错

         // 使用for循环遍历集合并删除集合中带1的名字
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            if (name.contains("1")) {
                list.remove(name);
            }
            System.out.println(list);
        }

        System.out.println("=========================");
         // 怎么解决呢
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            if (name.contains("1")) {
                list.remove(name);
                i--;  // 还可以倒着删除
            }
            System.out.println(list);
        }

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String name = it.next();
            if (name.contains("1")) {
                // list.remove(name);  // 出现并发修改异常
                it.remove();
            }
        }
        System.out.println(list);

        // 增强for循环和lambda表达式的并发修改异常是无法解决的
    }
}
```

# Collection的其他相关知识

## 可变参数

* 就是一种特殊形参，定义在方法、构造器的形参列表里，格式是：数据类型.参数名称；
* 可变参数的特点和好处
  - ﻿特点：可以不传数据给它；可以传一个或者同时传多个数据给它；也可以传一个数组给它
  - ﻿﻿好处：常常用来灵活的接收数据
* 可变参数的注意事项：
  - ﻿﻿可变参数在方法内部就是一个数组
  - ﻿﻿一个形参列表中可变参数只能有一个
  - ﻿﻿可变参数必须放在形参列表的最后面

```java
public class Test {
    public static void main(String[] args) {
        // 特点
        test();
        test(10);
        test(10, 20, 30);
        test(new int[]{10, 20, 30, 40});

    }
    // 注意事项1: 一个形参列表中，最多一个可变参数
    // 注意事项1: 可变参数必须放在形参别表最后面

    public static void test(int... nums){
        // 可变参数在方法内部，本质就是一个数组
        System.out.println(nums.length);
        System.out.println(Arrays.toString(nums));
        System.out.println("====================================");
    }
}
```

## Collections

*  是一个用来操作集合的工具类

```java
package d2_collecitons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // 1.为集合批量添加数据
        List<String> names = new ArrayList<>();
        Collections.addAll(names, "张三", "李四", "王五");
        System.out.println(names);

        // 2.打乱List集合中的元素顺序
        Collections.shuffle(names);
        System.out.println(names);

        // 3.对List中的元素进行升序排序
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(5);
        list.add(2);
        Collections.sort(list);
        System.out.println(list);

        List<Student> students = new ArrayList<>();
        students.add(new Student("至尊宝", 28, 169.6));
        students.add(new Student("蜘蛛精", 23, 169.6));
        students.add(new Student("蜘蛛精", 23, 169.6));
        students.add(new Student("牛魔王", 48, 169.6));
        // Collections.sort(students); // 需要去实现比较规则
        System.out.println(students);

        // 4.实现比较器对象
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getHeight(), o2.getHeight());
            }
        });
        System.out.println(students);
    }
}
```

## 综合案例

* 斗地主游戏发牌的开发

### Card类

* ```java
  package d3_collection_test;
  
  public class Card {
      private String number;
      private String color;
      // 每张牌存在大小的
      private int size;
  
      public Card() {
      }
  
      public Card(String number, String color, int size) {
          this.number = number;
          this.color = color;
          this.size = size;
      }
  
      public String getNumber() {
          return number;
      }
  
      public void setNumber(String number) {
          this.number = number;
      }
  
      public String getColor() {
          return color;
      }
  
      public void setColor(String color) {
          this.color = color;
      }
  
      public int getSize() {
          return size;
      }
  
      public void setSize(int size) {
          this.size = size;
      }
  
      @Override
      public String toString() {
          return color + number;
      }
  }
  ```

### Room类

* ```java
  package d3_collection_test;
  
  import java.util.ArrayList;
  import java.util.Collections;
  import java.util.Comparator;
  import java.util.List;
  
  public class Room {
      // 必须有一副牌
      private List<Card> allCards = new ArrayList<>();
  
      public Room(){
          // 1.做出54张牌，存入到allCards中
          // a. 点数: 个数，类型确定
          String[] numbers = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
          // b. 花色：个数确定，类型确定
          String[] colors = {"♠", "♥", "♦", "♣"};
          // 表示每张牌大小
          int size = 0;
          // c. 遍历点数，再遍历花色，组织牌
          for (String number : numbers){
              size++;
              for (String color: colors){
                  // 得到一张牌
                  Card c = new Card(number, color, size);
                  allCards.add(c); // 存入牌
              }
          }
          // 单独存入大小王
          Card c1 = new Card("", "小王", ++size);
          Card c2 = new Card("", "大王", ++size);
          Collections.addAll(allCards, c1, c2);
          System.out.println("新牌" + allCards);
      }
  
      public void start() {
          // 1.洗牌
          Collections.shuffle(allCards);
          System.out.println("洗牌后" + allCards);
  
          // 2.发牌，用集合定义三个玩家 ArrayList
          List<Card> linHUChong = new ArrayList<>();
          List<Card> jiuMoZhi = new ArrayList<>();
          List<Card> renYingYing = new ArrayList<>();
          // 正式发牌给三个玩家，依次发出51张牌，剩余3张作为底牌
          for (int i = 0; i < allCards.size() - 3; i++) {
              Card c = allCards.get(i);
              // 判断牌发给谁
              if (i % 3 == 0){
                  linHUChong.add(c);
              }else if (i % 3 == 1){
                  jiuMoZhi.add(c);
              }else if (i % 3 == 2) {
                  renYingYing.add(c);
              }
          }
          // 3.对玩家的牌进行排序
          sortCards(linHUChong);
          sortCards(jiuMoZhi);
          sortCards(renYingYing);
          // 4.看牌
          System.out.println("阿冲" + linHUChong);
          System.out.println("阿鸠" + jiuMoZhi);
          System.out.println("盈盈" + renYingYing);
          // allCards 截取部分组成新集合
          List<Card> lastThreeCards = allCards.subList(allCards.size() - 3, allCards.size()); // 51 52 53
          System.out.println("底牌" + lastThreeCards);
          jiuMoZhi.addAll(lastThreeCards);
          System.out.println("阿鸠抢到地主" + jiuMoZhi);
  
      }
  
      private void sortCards(List<Card> cards) {
          Collections.sort(cards, new Comparator<Card>() {
              @Override
              public int compare(Card o1, Card o2) {
                  return o1.getSize() - o2.getSize();
              }
          });
      }
  }
  ```

### 测试类

* ```java
  package d3_collection_test;
  
  public class GameDemo {
      public static void main(String[] args) {
          // 1.牌类
          // 2.房间
          Room m = new Room();
          // 3.启动游戏
          m.start();
      }
  }
  ```

# Map集合

## 概述

* 相比于Collection这样的单列集合，Map是一种双列集合
* Map集合称为双列集合，格式：｛key1=valuel,key2=value2,key3=value3.......｝，一次需要存一对数据做为一个元素
* ﻿﻿Map集合的每个元素 “key=value" 称为一个键值对/键值对对象/一个Entry对象，Map集合也被叫做“键值对集合”
* ﻿﻿Map集合的所有键是不允许重复的，但值可以重复，键和值是一一对应的，每一个键只能找到自己对应的值
* 注意：Map系列集合的特点都是由键决定的，值只是一个附属品，值是不做要求的
* 使用场景
  * 需要存储一一对应的数据时，就可以考虑使用Map集合来做
* Map集合体系
  * HashMap（由键决定特点）：无序、不重复、无索引；（用的最多）
  * ﻿﻿LinkedHashMap（由键决定特点）：由键决定的特点：有序、不重复、无索引
  * ﻿﻿TreeMap（由键决定特点）：按照大小默认升序排序、不重复、无索引

```java
package d4_map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(); // 一行经典代码，按照键，无序，不重复，无索引
        map.put("手表", 100);
        map.put("手表", 220);  // 会覆盖前面的数据
        map.put("手机", 2);
        map.put("java", 2);
        map.put(null, null);
        System.out.println(map);

        Map<String, Integer> map2 = new LinkedHashMap<>(); // 有序，不重复，无索引
        Map<Integer, String> map3 = new TreeMap<>(); // 可排序，不重复，无索引
        map3.put(23, "java");
        map3.put(23, "MySQL");  // 覆盖
        map3.put(19, "李四");
        map3.put(28, "王五");
        System.out.println(map3);

    }
}
```

## 常用方法

* Map是双列集合的祖宗，它的功能是全部双列集合都可以继承过来使用的

* ```java
  package d4_map;
  
  import java.util.HashMap;
  import java.util.Map;
  import java.util.Set;
  
  public class Test2 {
      public static void main(String[] args) {
          Map<String, Integer> map = new HashMap<>();
          map.put("手表", 100);
          map.put("手表", 220);
          map.put("手机", 2);
          map.put("java", 2);
          map.put(null, null);
          System.out.println(map);
  
          // 获取集合大小
          System.out.println(map.size());
  
          // 清空集合
          // map.clear();
          System.out.println(map);
  
          // 判断集合是否为空
          System.out.println(map.isEmpty());
  
          // 根据键获取对应值
          int v1 = map.get("手表");
          System.out.println(v1); // 220
  
          // 根据键删除元素，删除键会返回对应值
          System.out.println(map.remove("手表"));
  
          // 判断是否包含某个键
          System.out.println(map.containsKey("手表")); // false
  
          // 判断是否包含某个值
          System.out.println(map.containsValue(2)); // true
  
          // 获取Map集合全部键 // 放在Set 无序、不重复、无索引
          Set<String> Keys = map.keySet();
          System.out.println(Keys);
  
          // 获取Map集合全部值 // 放Collection 需要可以重复
          System.out.println(map.values());
  
          // 把其他Map集合的数据导入自己的集合
          Map<String, Integer> map1 = new HashMap<>();
          map1.put("java", 10);
          Map<String, Integer> map2 = new HashMap<>();
          map2.put("java2", 10);
          map1.putAll(map2);
          System.out.println(map1);
  
      }
  }
  ```

## 遍历方式

### 键找值

* 先获取Map集合全部的键，再通过遍历键来找值

* ```java
  package d5_map_traverse;
  
  import java.util.HashMap;
  import java.util.Map;
  import java.util.Set;
  
  public class Test1 {
      public static void main(String[] args) {
          // 准备一个Map集合
          Map<String, Double> map = new HashMap<>();
          map.put("蜘蛛精", 162.5);
          map.put("蜘蛛精", 169.8);
          map.put("紫霞", 165.8);
          map.put("至尊宝", 169.5);
          map.put("牛魔王", 183.6);
          System.out.println(map);
  
          // 键找值
          // 获取Map集合全部键
          Set<String> keys = map.keySet();
          // 遍历全部键
          for (String key : keys) {
              // 根据键获取其对应的值
              double value = map.get(key);
              System.out.println(key + "======>" + value);
          }
      }
  }
  ```

### 键值对

* 把“键值对“看成一个整体进行遍历（难度较大）

* ```java
  package d5_map_traverse;
  
  import java.util.HashMap;
  import java.util.Map;
  import java.util.Set;
  
  public class Test2 {
      public static void main(String[] args) {
          Map<String, Double> map = new HashMap<>();
          map.put("蜘蛛精", 162.5);
          map.put("蜘蛛精", 169.8);
          map.put("紫霞", 165.8);
          map.put("至尊宝", 169.5);
          map.put("牛魔王", 183.6);
  
          // 先调用Map集合的entrySet方法，把Map集合转换成键值对类型的Set集合
          Set<Map.Entry<String, Double>> entries = map.entrySet(); // 快捷键 command option v
          for (Map.Entry<String, Double> entry : entries) {
              String key = entry.getKey();
              Double value = entry.getValue();
              System.out.println(key + "----->" + value);
          }
      }
  }
  ```

### Lambda

* 用到forEach方法

* ```java
  package d5_map_traverse;
  
  import java.util.HashMap;
  import java.util.Map;
  
  public class Test3 {
      public static void main(String[] args) {
          Map<String, Double> map = new HashMap<>();
          map.put("蜘蛛精", 162.5);
          map.put("蜘蛛精", 169.8);
          map.put("紫霞", 165.8);
          map.put("至尊宝", 169.5);
          map.put("牛魔王", 183.6);
  
          // 能用Lambda就用
          map.forEach((k, v) -> {
              System.out.println(k + "--->" + v);
          });
      }
  }
  ```

### 案例

* 统计投票人数
* ![IMG_1031](/Users/cjf/Downloads/IMG_1031.jpg)

```java
package d5_map_traverse;

import java.util.*;

public class Test4Demo {
    public static void main(String[] args) {
        // 把80个学生选择的景点数据拿到程序中去
        List<String> data = new ArrayList<>();
        String[] selects = {"A", "B", "C", "D"};
        Random r = new Random();
        for (int i = 0; i < 80; i++) {
            // 模拟一个学生选择一个景点，存入集合中
            int index = r.nextInt(3);
            data.add(selects[index]);
        }
        System.out.println(data);

        // 准备Map集合统计最终的结果
        Map<String, Integer> result = new HashMap<>();

        // 开始遍历80个景点数据
        for (String s : data) {
            // 询问是否有该景点
            if (result.containsKey(s)){
                // 统计过，+1
                result.put(s, result.get(s) + 1);
            }else {
                // 第一次统计，值=1
                result.put(s, 1);
            }
        }
        System.out.println(result);
    }
}
```

## HashMap原理

*  HashMap跟Hashset的底层原理是一模一样的，都是基于哈希表实现的
* 实际上：原来学的Set系列集合的底层就是基于Map实现的，只是Set集合中的元素只要键数据，不要值数据而已
* `map.put(" ", " ")` 变成Entry对象，再利用键计算哈希值，跟值无关
* 特点
  * HashMap集合是一种增删改查数据，性能都较好的集合
  * ﻿﻿但是它是无序，不能重复，没有索引支持的（由键决定特点）
  * ﻿﻿HashMap的键依赖hashCode方法和equals方法保证键的唯一
  * ﻿﻿如果键存储的是自定义类型的对象，可以通过重写hashCode和equals方法，这样可以保证多个对象内容一样时，HashMap集合就能认为是重复的

## LinkedHashMap

![IMG_1041](/Users/cjf/Downloads/IMG_1041.jpg)

## TreeMap

* 特点：不重复、无索引、可排序（按照键的大小默认升序排序，只能对键排序）
* ﻿﻿原理：TreeMap跟TreeSet集合的底层原理是一样的，都是基于红黑树实现的排序
* TreeMap集合同样也支持两种方式来指定排序规则
  * 让类实现Comparable接口，重写比较规则
  * ﻿﻿TreeMap集合有一个有参数构造器，支持创建Comparator比较器对象，以便用来指定比较规则

## 集合的嵌套

* 集合中的元素又是一个集合

* ```java
  package d7_collection_nesting;
  
  import java.util.*;
  
  public class Test {
      public static void main(String[] args) {
          // 定义一个Map集合储存省份信息
          Map<String, List<String>> map = new HashMap<>();
  
          // 江苏省
          List<String> cities01 = new ArrayList<>();
          Collections.addAll(cities01, "南京", "扬州", "苏州", "无锡", "常州");
          map.put("江苏省",cities01 );
          System.out.println(map); // {江苏省=[南京, 扬州, 苏州, 无锡, 常州]}
  
          // 湖北省
          List<String> cities02 = new ArrayList<>();
          Collections.addAll(cities02, "武汉市", "孝感市", "十堰市", "异常时", "鄂州市");
          map.put("湖北省", cities02);
  
          // 如何获取信息
          List<String> cities = map.get("湖北省");
          for (String city : cities) {
              System.out.println(city);
          }
  
          // 获取全部省份信息
          map.forEach((p, c) -> {
              System.out.println(p + "----->" + c);
          });
      }
  }
  ```

# Stream流

## 什么是Stream

* ﻿﻿也叫Stream流，是Jdk8开始新增的一套API （java.util.stream.*），可以用于操作集合或者数组的数据
* ﻿﻿优势
  * Stream流大量的结合了Lambda的语法风格来编程
  * 提供了一种更加强大，更加简单的方式操作集合或者数组中的数据，代码更简洁，可读性更好

```java
package d8_stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Test1 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        Collections.addAll(names, "张三丰", "张无忌", "周芷若", "赵敏", "张强");
        System.out.println(names);
        // 找出姓张，且3个字的名字，存入到一个新集合中去

        // 原始方法
        List<String> list1 = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("张") && name.length() == 3){
                list1.add(name);
            }
        }
        System.out.println(list1); // [张三丰, 张无忌]
        // 三体方法
        List<String> listq = names.stream().filter(s -> s.startsWith("张")).filter(a -> a.length()==3).collect(Collectors.toList());
        System.out.println(listq); // [张三丰, 张无忌]
    }
}
```

## Stream流的使用步骤

* 获取Stream流
* Stream流常见的中间方法
* Stream流常见的终结方法

## 获取Stream流

```java
package d8_stream;

import java.util.*;
import java.util.stream.Stream;

public class Test2 {
    public static void main(String[] args) {
        // 获取List集合的Stream流
        List<String> names = new ArrayList<>();
        Collections.addAll(names, "张三丰", "张无忌", "周芷若", "赵敏", "张强");
        Stream<String> stream = names.stream();

        // 获取Set集合的Stream流
        Set<String> set = new HashSet<>();
        Collections.addAll(set, "刘德华", "张曼玉", "蜘蛛精", "马德", "德玛西亚");
        Stream<String> stream1 = set.stream();
        stream1.filter(s -> s.contains("德")).forEach(s -> System.out.println(s));

        // 获取Map集合的Stream流
        Map<String, Double> map = new HashMap<>();
        map.put("古力娜扎", 172.3);
        map.put("迪丽热巴", 168.3);
        map.put("玛尔扎哈", 166.3);
        map.put("卡尔扎巴", 168.3);

        // 分开处理
        Set<String> keys = map.keySet();
        Stream<String> ks = keys.stream();

        Collection<Double> values = map.values();
        Stream<Double> vs = values.stream();

        // 统一处理
        Set<Map.Entry<String, Double>> entries = map.entrySet();
        Stream<Map.Entry<String, Double>> kvs = entries.stream();
        kvs.filter(e -> e.getKey().contains("巴")).forEach(e -> System.out.println(e.getKey() + e.getValue()));

        // 获取数组的Stream流
        String[] name2 = {"张翠山", "东方不败", "糖大山", "孤独求败"};
        Stream<String> s2 = Arrays.stream(name2);
        Stream<String> s3 = Stream.of(name2);
    }
}
```

## Stream流的中间方法

* 中间方法指的是调用完成后会返回新的Stream流，可以继续使用（支持链式编程）

![IMG_1079](/Users/cjf/Downloads/IMG_1079.jpg)

```java
package d8_stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Test3 {
    public static void main(String[] args) {
        List<Double> scores = new ArrayList<>();
        Collections.addAll(scores, 88.5, 100.0, 60.0, 99.0, 9.5, 99.6, 25.0);

        // 需求一：找出成绩大于60分的数据，并升序后，再输出
        scores.stream().filter(s -> s >= 60).sorted().forEach(s -> System.out.println(s));

        List<Student> students = new ArrayList<>();
        Student s1 = new Student("蜘蛛精", 26, 172.5);
        Student s2 = new Student("蜘蛛精", 26, 172.5);
        Student s3 = new Student("紫霞", 23, 167.6);
        Student s4 = new Student("白晶晶", 25, 169.0);
        Student s5 = new Student("牛魔王", 35, 183.3);
        Student s6 = new Student("牛夫人", 34, 168.5);
        Collections.addAll(students, s1, s2, s3, s4, s5, s6);

        // 需求二：年龄大于23，小于30，按照年龄降序输出
        students.stream().filter(s -> s.getAge() >= 23 && s.getAge() <= 30)
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .forEach(s -> System.out.println(s));
        System.out.println("======================================");

        // 需求三：取出身高最高的前三名学生，并输出
        students.stream().sorted(((o1, o2) -> Double.compare(o2.getHeight(), o1.getHeight())))
                .limit(3).forEach(s -> System.out.println(s));
        System.out.println("======================================");



        // 需求四：取出身高倒数的两名学生，并输出
        students.stream().sorted((o1, o2) -> Double.compare(o2.getHeight(), o1.getHeight()))
                .skip(students.size() - 2).forEach(System.out :: println);
        System.out.println("======================================");

        // 找出身高超过168学生名字，去除重复名字并且输出 distinct
        students.stream().filter(s -> s.getHeight() > 168).map(s -> s.getName())
                .distinct().forEach(System.out :: println);

        Stream<String> st1 = Stream.of("张三", "李四");
        Stream<String> st2 = Stream.of("张三2", "李四2", "王五");
        Stream<String> allst = Stream.concat(st1, st2);
        allst.forEach(System.out :: println);

    }
}
```

## Stream流的终结方法

* 终结方法指的是调用完成后，不会返回新Stream了，没法继续使用流了

```java
package d8_stream;

import java.util.*;
import java.util.stream.Collectors;

public class Test4 {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Student s1 = new Student("蜘蛛精", 26, 172.5);
        Student s2 = new Student("蜘蛛精", 26, 172.5);
        Student s3 = new Student("紫霞", 23, 167.6);
        Student s4 = new Student("白晶晶", 25, 169.0);
        Student s5 = new Student("牛魔王", 35, 183.3);
        Student s6 = new Student("牛夫人", 34, 168.5);
        Collections.addAll(students, s1, s2, s3, s4, s5, s6);

        // 一 forEach遍历此流元素

        // 二 计算升高超过168人数
        long size = students.stream().filter(s -> s.getHeight() > 168).count();
        System.out.println(size);
        // 三 找出身高最高 最低的对象 max min
        Student s = students.stream().max(((o1, o2) -> Double.compare(o1.getHeight(), o2.getHeight()))).get();
        System.out.println(s);
        // 四 找出身高超过170的对象，放到集合中去返回
        // List
        List<Student> students1 = students.stream().filter(a -> a.getHeight() > 170).collect(Collectors.toList());
        System.out.println(students1);
        //Set去重复
        Set<Student> students2 = students.stream().filter(a -> a.getHeight() > 170).collect(Collectors.toSet());
        System.out.println(students2);

        // 五找出身高超过170的对象，放到Map集合中去返回  不会自动去重复
        Map<String, Double> map = students.stream().filter(a -> a.getHeight() > 170)
                .distinct()
                .collect(Collectors.toMap(a -> a.getName(), a -> a.getHeight()));

        // 收集到数组中
        Object[] arr = students.stream().filter(a -> a.getHeight() > 170).toArray(len -> new Student[len]);
        System.out.println(Arrays.toString(arr));
    }
}
```
