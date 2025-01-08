# 常见算法

## 简单认识算法

* 什么是算法
  * 解决某个实际问题的过程和方法

* 为什么要学习算法
  * 编程思维

* 学习算法的方法
  * 先搞清楚算法的流程
  * 直接去推敲如何写代码而不是去背

## 排序算法

### 冒泡排序

* 每次从数组中找到最大值放到数组后面去

* ```java
  public class Test {
      public static void main(String[] args) {
          // 1.准备一个数组
          int[] arr = {5, 2, 3, 1};
  
          // 2.定义一个循环控制排几轮
          for (int i = 0; i < arr.length - 1; i++) {
              // 3.定义一个循环控制每轮比较几次
              for (int j = 0; j < arr.length - i - 1; j++) {
                  // 判断当前位置元素值，是否大于后一个位置处元素值，如果大于则交换
                  if (arr[j] > arr[j+1]){
                      int temp = arr[j+1];
                      arr[j+1] = arr[j];
                      arr[j] = temp;
                  }
              }
          }
          System.out.println(Arrays.toString(arr));
  
      }
  }
  ```

### 选择排序

* 每轮选择当前位置，开始找出后面的较小值与该位置交换

* ```java
  public class Test2 {
      public static void main(String[] args) {
          // 1.准备好一个数组
          int[] arr = {5, 1, 3, 2};
  
          // 2.控制选择几轮
          for (int i = 0; i < arr.length - 1; i++) {
              for (int j = i + 1; j < arr.length; j++) {
                  if (arr[i] > arr[j]) {
                      int temp = arr[i];
                      arr[i] = arr[j];
                      arr[j] = temp;
                  }
              }
          }
          System.out.println(Arrays.toString(arr));
      }
  }
  ```

* 优化后算法

* ```java
  //优化算法
  for (int i = 0; i < arr.length - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
          if (arr[minIndex] > arr[j]){
              minIndex = j;
          }
      }
      // 决定是否交换
      if (i != minIndex){
          int temp = arr[i];
          arr[i] = arr[minIndex];
          arr[minIndex] = temp;
      }
  }
  ```

## 查找算法

* 二分查找
  * 前提：数组中数据必须是有序的
  * 核心思想：每次排出一半的数据，查询数据的性能得到明显提升

```java
public class Test3 {
    public static void main(String[] args) {
        // 1.准备好一个数组
        int[] arr = {7, 23, 79, 81, 103, 127, 131, 147};
        System.out.println(binarySearch(arr,79));
    }

    public static int binarySearch(int[] arr, int data){  // 返回索引
        int left = 0;
        int right = arr.length - 1;
        while (left < right){
            int middle = (left + right);
            if (data < arr[middle]){
                right = middle -1;
            }else if (data > arr[middle]){
                left = middle + 1;
            }else
                return middle;
        }
        return -1; // 未找到
    }
}
```