# 数组

## 认识数组

1. 数组是一个容器，用来储存一批同种类型的数据
2. 遇到批量数据储存和操作时，数组比变量更合适

## 数组定义以及访问

### 静态初始化数组

1. 定义

   ```java
   int[] ages = {12,24,36,48};
   int[] ages = new int[]{12,24,36,48};
   //数组是一种引用类型，数组变量名中存储数组在内存中地址信息
   ```

2. 访问

   * 使用`数组名称[索引]`来访问数组中每一个元素
   * 使用`数组名称.length`来访问数组的长度
   * 数组最大索引：`数组名.length - 1`，如果访问超过最大索引，会出现索引越界的提示

3. 遍历

   ```java
   //遍历就是一个一个数据的访问，遍历数组可以用来求和，元素搜索，找最值
   int[] ages = {20,30,40,50};
    for (int i = 0; i < ages.length; i++) {
        System.out.println(ages[i]);
    }
   ```

### 动态初始化数组

1. 定义

```java
int[] arr = new int[3];
arr[0] = 10;
 System.out.println(arr[0]); //10
```

2. 动态初始化数组中元素的默认值
   * byte，short，int，char，long元素默认值是0
   * float，double默认值是0.0
   * boolean是false，String是null

## 数组在计算机中的执行原理

### Java内存管理

1. 方法区：字节码文件加载到这里
2. 栈内存：方法运行时进入的内存，变量也在这里
3. 堆内存：new出来的东西会在这块内存开辟空间并产生地址

### 基本类型变量和引用类型变量

1. `int a = 20;`直接放在栈中，a存储的数据就是20这个值
2. `int[ ] arr = new int[3];`会在堆内存开辟三个区域存储整数
3. arr是变量，在栈中，arr存储的是数组对象在堆内存中的地址值

### 多变量指向同一个数组的问题

1. 理解地址

```java
public static void main(String[] args) {
    int[] arr1 = {22,33,35};
    int[] arr2 = arr1;
    System.out.println(arr1);  //[I@36baf30c
    System.out.println(arr2);  //[I@36baf30c
    arr2[1] = 99;  //会改变arr1中的值
    System.out.println(arr1[1]);  //99

}
```

2. 如果某个变量存储的地址是null，那么该变量不再指向任何数组对象，可以输出（null），但是不能输出元素，长度

## 专项训练：数组常见案例

### 数组求最值

```java
public static void main(String[] args) {
   int[] faceScores = {15,9000,10000,20000,9500,-5};
   int max =faceScores[0];
   for (int i = 0; i < faceScores.length; i++) {
       if (faceScores[i] > max){
           max = faceScores[i];
       }
   }
    System.out.println(max);
}
```

### 数组反转

```java
public static void main(String[] args) {
    int[] num = {10,20,30,40,50};
    for (int i = 0 , j = num.length-1; i < j; i++,j --) { //注意此处两个变量的设置
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
    System.out.println(Arrays.toString(num));
}
```

### 随机排名

```java
public static void main(String[] args) {
    int[] codes = {22,33,35,13,88};
    Random r = new Random();
    for (int i = 0; i < codes.length; i++) {
      //每遍历到一个数据，都随机一个数组索引范围内的值，然后让当前遍历的数据与该索引位置处的值交换
        int index = r.nextInt(codes.length);
        int temp = codes[index];
        codes[index] = codes[i];
        codes[i] = temp;
    }
    System.out.println(Arrays.toString(codes));
}
```

## Debug工具的使用

IDEA自带的断点调试工具，可以控制代码从断点一行一行执行，观看程序执行情况

# 方法

## 方法概述

1. 方法是什么

方法是一种语法结构，把一段代码封装成一个功能，以便重复调用

2. 方法完整格式

```java
                      //方法名称
  public static   int    sum(int a , int b){
// 修饰符 修饰符  返回值类型        形参列表
      int c = a + b;   //方法执行代码
      return c;  //方法返回值
  }
```

3. 方法如何执行

方法定义后必须调用才能跑起来，格式：`方法名称()`

4. 注意事项
   * 修饰符：目前全用public static
   * 方法声明了返回值类型，内部必须使用return返回对应数据类型
   * 形参列表可以有多个，用”,“隔开，且不能初始化

## 方法定义的其他形式

1. 定义方法时需思考
   * 方法是否需要接受数据
   * 方法是否需要返回数据
2. 注意事项
   * 如果方法不需要返回数据，返回值类型必须声明成`void`，此时方法内部不需要写return
   * 如果方法不需要接受数据，就不需要定义形参

## 方法使用时的常见问题

1. 方法在类中位置随意，但方法不可以定义在另一个方法里面
2. return之后不能编写代码，无效
3. 调用有返回值的方法，有三种方式
   * 可以定义变量接受结果
   * 直接输出调用
   * 直接调用
4. 调用无返回值的方法，只能直接调用

## 方法案例讲解

1. 设计一个方法，关注三个方面
   * 方法是否需要接受数据
   * 方法是否需要返回数据
   * 方法要处理的业务
2. 案例：计算1-n的和

```java
public static int sum() {
    Scanner sc = new Scanner(System.in);
    System.out.println("请输入n");
    int n = sc.nextInt();
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += i;
    }
    return sum;
}
```

## 方法在计算机中执行原理

1. 方法被调用时，先进入栈内存中运行
2. 栈的特点是先进后出，使用栈是为了一个方法被调用完了可以回来

## 方法参数传递

### 基本类型参数传递

1. 方法参数传递机制是值传递：传输的是实参变量中存储值的副本
2. 实参：方法内部定义的变量
3. 形参：方法定义时`(...)`中声明的变量
4. 例子

```java
public static void main(String[] args) {
    int a = 10;
    change (a) ;
    System.out.println("main:" + a); // 10
}
public static void change(int a) {
    System.out.println("change1:" + a); // 10
    a = 20;
    System.out.println("change2:" + a); // 20
}
```

### 引用类型参数传递

1. 两者有什么不同
   * 都是值传递
   * 基本类型参数传输存储的数据值
   * 引用类型参数传输存储的地址值
2. 例子

```java
public static void main(String[] args) {
    int[] arrs = new int[]{10, 20, 30};
    change(arrs);
    System.out.println(arrs[1]);  //222
}
public static void change(int[] arrs) {
    System.out.println(arrs[1]);  //20
    arrs[1] = 222;
    System.out.println(arrs[1]);  //222
}
```

## 方法重载

1. 一个类中出现多个方法名称相同，但是形参列表不同，这些方法叫方法重载
2. 修饰符，返回值类型是否一样无所谓
3. 形参不同指的是：个数，类型，顺序不同，名称无所谓
4. 应用场景：为处理一类业务，提供多种解决方法

## 单独使用return关键字

1. return 应用在无返回值的方法中，作用是**立即跳出并结束当前方法的执行**
2. 复习
   * break 跳出并结束当前所在循环的执行
   * continue 结束当前所在循环当次执行，进入下一次执行
