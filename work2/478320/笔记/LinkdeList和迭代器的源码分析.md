# LinkedList

LinkedList在底层是双链表，查询速度慢，但是增删很快，操作首位元素非常快

因为LinkedList操作首尾元素也是极快的，所以它提供了很多直接操作首尾元素的API

独有方法 

1. void addFirst(E e)
2. void addLast(E e)，当然这和我们直接add是一样的，直接add默认也是添加到末尾
3. E getFirst
4. E getLast
5. E removeFirst
6. E removeLast

这些方法一看都知道是什么意思了，不过要注意移除元素是有返回值的，但是增加元素没有返回值，这些方法很多都有替代值，所以很少使用只要做了解就好

## 底层源码

LinkedList里面有一个内部类Node

有三个成员变量，一个item表示我要存储的成员变量，一个next表示下个结点的地址值，prev表示我要记录的前一个地址的地址值，当我们每创建一个Node对象的时候，就需要传入这三个数据

LinkedList中也有三个成员变量，一个size表示结点的总个数，一个first，头结点，一个last尾结点，是空参构造，构造后就有着三个成员了

add方法调用了linkLast方法，linkLast就是创建结点并把结点添加到链表的过程，这个方法先把last赋值给l，初始是null，然后创建了一个结点对象（l，e，null）给一个新的newNode，然后把现在创建出的新结点的地址值赋值给last，如果l是null，就然新结点的地址给first，否则就通过l，也就是上一个结点的地址，调用next方法把现在新的结点的地址赋值给上一个节点的next就完成了结点的添加

![](C:\Users\余思衡\Desktop\Markdown学习\图片保存处\Screenshot_2023-10-10-11-35-24-45_149003a2d400f6a.jpg)

# 迭代器底层源码

iterator方法底层啥也没干就是获取的一个迭代器的对象Itr，Itr是ArrayList里的一个内部类

内部类中有三个成员对象

第一个cursor，也就是指针，因为是空参构造，所以它默认初始化值是零

第二个lastRet，表示我刚刚操作索引的位置，初始为-1

hasNext方法就是一个判断，next里就是移动指针并获取元素

每次调用这个方法就会创建一个迭代器，当我们多次调用这个方法的时候，就是多次创建了多个迭代器的对象

我现在已经创建好了一个迭代器，在hasNext方法中，我开始cursor是零，它通过判断cursor的数值（下标数值）不等于总长度size，所以下一个数字还有数字，（也就是说这个光标指向的元素已经不存在了）那么就返回true

下面说next，它把光标的值拿出来给了一个i，如果i>=size,也就是多次使用next方法会出现的情况，光标指针超出了限定，（假设size是3，那么在while循环已经false的情况下，强行使用next方法，i=3这时就会触发），就会报错寻找不到这个元素，接着获取到了集合底层的数组，再把这个地址值赋值给一个elementData，下次就可以直接用而不用取外部类找了，***这样可以提高效率***，接着关键点来了，***先不看那个报错***，接着让cursor加1，也就是移动指针，然后把i赋值给了lastRet（也就是让上一次操作的索引加1），然后返回这个数组lastRet索引上的元素

![](C:\Users\余思衡\Desktop\Markdown学习\图片保存处\Screenshot_2023-10-10-11-44-08-03_149003a2d400f6a.jpg)

## 并发修改异常

### modCount

现在我们查看ArrayList里面的add源码，每次增加modcount也增加，每次remove modCount也增加，所以我们可以把它看成集合变化的次数

在Itr内部类中存在一个ecpectedModCount被赋值为modCount，在下面next方法中第一步会有一个checkForComodification的方法，表示检查次数，它比较集合现在变化的次数，和创建对象时集合的个数进行比较，如果两者不想等，就会抛出并发修改异常，这也就是为什么迭代过程中不能用集合本身的方法来修改

