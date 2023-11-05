#List
##1.List接口中存储数据的特点
> 用于储存有序的，可以重复的数据。使用List替代数组，"动态"数组

##2.List中的常用方法
- 1.Collection中声明的15个方法
- 2.因为List是有序的。进而就有索引，进而增加一些针对索引的方法
###小结
**1. 增**
- add(Object obj)
- addAll(Collection coll)
**2.删**
- remove(Obejct obj)
- remove(int index)
  >注：如果只输入了一个数字，将会自动识别成删除该索引处元素，应该使用integer.valueOf进行转换才能删除这个数字
``````java
        //删除索引2的元素
        list.remove(2);
        System.out.println(list);
        //删除数据2
        list.remove(Integer.valueOf(2));//将2转化成一个对象
        System.out.println(list);
``````
- removeAll(int index,Collection coll)
**3.改**
- set(int index,Object ele)
**4.查**
- get(int index)
**5.插**
- add(int index,Object ele)
- addAll(int index,Collection else)
**6.长度**
- size()
**7.遍历**
- iterator(),使用迭代器进行遍历
- 增强for循环
- 一般的for循环
**8.其余**
>  类比于String中的方法
- sublist
- indexOf
- lastIndexOf
##3.List及其实现类的特点
- **ArrayList**
  >List的主要实现类：线程不安全、效率高；底层使用Object[]数组存储。在添加、查找数据时，效率较高，在插入，删除数据时，效率较低
- **LinkedList**
  >底层使用双向链表的方式进行存储。在对集合中的数据频繁插入，删除时建议使用此类。在插入，删除数据时，效率较高，在添加、查找数据时，效率较低。
- **Vector**
  >List的古老实现类：线程安全、效率低；底层使用Object[]数组存储

## 面试题
ArrayList、Voctor的区别？ ArrayList、Linkedlist的区别


