# 数组

## 数组的概念

- 数组是相同类型数据的有序集合，其中每一个数据称作一个数组元素，每个数组元素可以通过一个下标来【】（数字的下标是从0开始的）访问它们；

## 数组的声明和创建

- 首先必须声明数组变量，才能使用
  - int [] nums（首选）
  - int nums[]

- 创建一个数组（使用new）：nums = new int[10]//意思是可以放10个int类型的数字

- 可以把声明和创建合并：int[]numbs = new int[10]

- 给数组元素中赋值：int[0]=1

  **如果没有赋值，则会输出默认值**

- 获取数组的长度：arrays.length

- ```
  package com.vegetabale.www;
  
  public class C1 {
      public static void main(String[] args) {
          int []nums;
          nums = new int[10];
          nums[0]=1;
          nums[1]=2;
          nums[2]=3;
          nums[3]=4;
          nums[4]=5;
          nums[5]=6;
          nums[6]=7;
          nums[7]=8;
          nums[8]=9;
          nums[9]=10;
          int []nums1 =new int[20];
      }
  }
  ```

## 三种初始化及内存分析

- Java内存分析：

  - 堆：存放new对象和数组

  - 栈：存放变量类型和变量值的地方

  - 方法区：没细讲

    ![image-20231003203241621](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231003203241621.png)

- 三种初始化：
  - 静态初始化：创建+赋值（用大括号）
  - 动态初始化：包含默认初始化（默认初始化是指创建数组还未赋值时，数组内都含有默认值，int 是 0 ；而String是null
- 本节课联系代码

```java
package com.vegetabale.www;
public class C2 {
    public static void main(String[] args) {
        int []nums1 ={1,2,3,4,5,6,7,8,9};//静态初始化
        String []nums2;//动态初始化但是分开写
        nums2 =new String[10];
        int nums3[]= new int [10];//动态初始化但是合着写
        int nums4[] = new int[5];
        System.out.println(nums1[0]);//数组的下标是从0开始的
        System.out.println(nums1[1]);
        System.out.println(nums1[2]);
        System.out.println(nums1[3]);
        System.out.println(nums1[4]);
        System.out.println(nums1[5]);
        System.out.println(nums1[6]);
        System.out.println(nums1[7]);
        System.out.println(nums1[8]);
        //System.out.println(nums1[9]);
        nums3[0] = 1;
        nums3[1] = 2;//动态化赋值：先定义数组长度，后自行赋值
        System.out.println(nums3.length);//数组长度使用array.length进行查看
        System.out.println(nums2[1]);//新建数组而不对数组进行赋值，则输出的是默认值。当数组类型是String型时，默认初始值是null
        System.out.println(nums4[2]);//当数组类型是int型时，默认初始值是0
    }
}
```

# 数组小结

- 数组的特点：
  - 数组的长度大小一旦被创建就不可以被改变
  - 同一数组中的数据类型必须完全相同
  - 不同数组中数据的类型可以有很多种：基本类型、引用类型等
  - 数组对象本身是在堆中的，数据类型是保存在栈中的
- 数组边界：
  - ArrayIndexOutOfBoundsExceptation表示数组下标越界异常！（在循环中需要更加注意）
- 数组是相同数据类型的有序集合，数组也是对象；数组元素相当于对象的成员变量；数组的长度是一定的，数组的大小是不可变的，要注意下标的使用和下标越界问题！

# 数组的使用

- 基础

  - 打印全部的数组元素

  - 计算所有元素的和

  - 查找最大元素

- 进阶

  - 打印数组元素（使用method）
  - 反转数组
  -  For-each循环，***我还没懂这个***

- 本节课练习代码

  ```java
  package com.vegetabale.www;
  
  public class C4 {
      public static void main(String[] args) {
          int []nums1 = {1,2,3,4,5};
          printarray(nums1);
          System.out.println();
          System.out.println("======================================");
          int[]nums2 = reverse(nums1);
          printarray(nums2);
  
      }
      public static void printarray(int []nums1) {
          for(int i=0;i <nums1.length;i++){
              System.out.print(nums1[i]+" ");
          }
      }
      public static int[] reverse(int[]nums1){
          int []reverse = new int[nums1.length];
          for(int i =0,j=nums1.length-1;i<nums1.length;i++,j--){
            reverse [j] = nums1[i];//注意即使是等式两侧也不能写反东西
          }
          return reverse;
      }
  
  }
  ```

- 多维数组：数组中再嵌套了一个数组

  - ![image-20231003231145339](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231003231145339.png)
  - 二维数组：
    - 获取二维数组的长度：array.length
    - 打印二维数组的所有数据
  - 注意点：直接用sout输出二维数组中的array[0]之类的，不会输出想象中的结果，此时就必须得借助for循环来进行输出

- 本节课练习代码：

  ```java
  package com.vegetabale.www;
  
  public class C5 {
      public static void main(String[] args) {
          int [][]nums1 = {{1,2},{3,4},{5,6},{7,8},{9,10}};
          //nums1[0] = new int[]{1, 2};怎么没教我活动定义怎么定义啊！！
          printarray(nums1);//无法直接输出其中一个数组，必须借助方法
      }
      public static void printarray(int[][]nums1){
          for (int i = 0; i <nums1.length; i++) {
              for(int a = 0;a < 2 ;a++ ){
                  System.out.print(nums1[i][a]+" ");
              }//两个数字，有明显的迭代性和联系，用循环嵌套
              System.out.println();
          }
  
      }
  }
  ```

## Arrays类讲解

- 数组类的工具包：java.util.Arrays(基本上都是static修饰的方法，可以直接使用)
- 打印数组元素：Arrays.toString()>>免于再次书写方法
- 数组进行排序：Arrays.sort(a)
- 数组进行填充：Arrays.fill(a,val:0)...
- 还有其他方法：![image-20231003235640341](C:\Users\86135\AppData\Roaming\Typora\typora-user-images\image-20231003235640341.png)

## 冒泡排序

- 排序算法共有八种，冒泡排序最为出名

- 1.比较数组中，两个相邻的元素，如果第一个数 比第二个数大，我们就交换他们的位置

  2.每一次比较，都会产生出一个最大，或者最小的数字

  3.下一轮可以少一次排序

  4.依次循环直到结束 

- 外层循环，判断我们这个要走多少次

- 内层循环，

- 使用第三方变量实现交换

- 课堂练习代码：犯了个错误觉得很锻炼逻辑能力，拿出来看看！

  

  - 正确版

  ```java
  package com.vegetabale.www;
  
  public class C6 {
      public static void main(String[] args) {
          int[]nums1 = {1, 4, 3, 2, 5};
          int[]nums2  = range(nums1);
          printarray(nums2);
      }
      public static int[] range(int[] nums1) {
          int temp = 0;
          boolean flag = false ;
          for (int i = 0; i < nums1.length - 1; i++) {
              for (int j = 0; j < nums1.length -1-i; j++) {
                  if (nums1[j] > nums1[j + 1]) {
                      temp = nums1[j];
                      nums1[j] = nums1[j + 1];
                      nums1[j + 1] = temp;
                      flag = true;
                  }
              }
              if(flag==false){
                  break;//注意这个if语句的存放位置！
              }
          }
          return nums1;
      }
  
      public static void printarray(int []nums1) {
          for (int i = 0; i < nums1.length; i++) {
              System.out.print(nums1[i] + " ");
          }
      }
  }
  ```

  - 错误版

    ```
    package com.vegetabale.www;
    
    public class C6 {
        public static void main(String[] args) {
            int[]nums1 = {1, 3, 4, 2, 5};
            int[]nums2  = range(nums1);
            printarray(nums2);
        }
        public static int[] range(int[] nums1) {
            int temp = 0;
            boolean flag = false ;
            for (int i = 0; i < nums1.length - 1; i++) {
                for (int j = 0; j < nums1.length -1-i; j++) {
                    if (nums1[j] > nums1[j + 1]) {
                        temp = nums1[j];
                        nums1[j] = nums1[j + 1];
                        nums1[j + 1] = temp;
                        flag = true;
                    }
                    if(flag==false){
                        break;//注意这个if语句的放置位置，现在这样放是错的！
                    }
                }
    
    
            }
            return nums1;
        }
    
        public static void printarray(int []nums1) {
            for (int i = 0; i < nums1.length; i++) {
                System.out.print(nums1[i] + " ");
            }
        }
    }
    ```

## 稀疏数组

- ***啊啊我先开面向对象，要赶不上ddl了！！！***
- ***一定要回来看这个啊***