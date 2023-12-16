#Map
##1.Map及其实现类对比
- HashMap
>主要实现类，线程不安全，效率高；可以添加null的key和value值:底层使用数组+单向链表+红黑树结构存储(jdl8)
- |-----------LinkedHashMap
>是HashMap的子类：在HashMap使用的数据基础上，添加一对双向链表，用于记录添加元素的先后顺序，进而我们遍历元素时，就可以按照添加的顺序显示。对于**频繁**的遍历元素时，建议使用此类。
- TreeMap
>底层使用红黑树存储；可以按照添加的key-value中的key元素的指定属性进行遍历。需要考虑使用 **1.自然排序**  **2.定制排序**
- Hashtable：古老实现类，线程不安全，效率低，不可以添加null的key和value值;底层使用数组+红黑树结构存储(jdk8)
  |-----------Properties
>其key和value都是String类型。常用来处理属性文件


##面试题
>区别HashMap和Hashtable的区别、HashMap和LinkedHashmap的区别、HashMap的底层实现(1.new HashMap()2.put(key,value))
##2.HashMap中元素的特点
>HashMap中所有的key彼此之间是不可重复的、无序的、所有的key就构成了一个**Set集合**。--->key所在的类要重写**hashCode()和equals()**
>HashMap中所有的value彼此之间是可重复的、无序的。所有的value就构成了一个**Collection集合**。---->value所在类要重写**equals()**
>HashMap中的一个key-value，就构成一个**entry**。
>HashMap中所有的entry彼此之间是不可重复的、无序的。所有的entry构成一个**Set集合**。
##3.Map中的常用方法
**添加、修改操作**

- 1.put(Object key,Object value)
>将指定key-value添加到(或修改)当前map对象中
- 2.putAll(Map m)
>将m中的所有key-value对存放到当前map中

**删除操作**

- 1.remove(Object key)
>移除指定key对应的value
- 2.clear()
>清空当前map的所有数据

**元素查询的操作**
- 1.get(Object key)
>获取指定key对应的value
- 2.containKey(Object key)
>是否包含指定的key
- 3.containsValue(Object value)
>是否包含指定的value
- 4.size()
>返回map中key-value对的个数
- 5.isEmpty()
>判断当前map是否为空
- 6.equals(Object obj)
>判断当前map是否为空

**元视图操作的方法**
- 1.keySet()
>返回所有key构成的Set集合
- 2.values()
>返回所有value构成的Collection集合
- 3.entrySet()
>返回所有key-value构成的Set集合

**遍历**
> 通过元视图操作，借助Iterator或者增强for循环
达到遍历效果
- 遍历key
```java
Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        for (Object o : set) {
            System.out.println(o);
        }
```
- 遍历value
```java
   Collection collection = map.values();
        Iterator iterator1 = collection.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
   Set set = map.keySet();
     for (Object o : set) {
            System.out.println(map.get(o));
        }
``````

- 遍历entry
``````java
  Set set1 = map.entrySet();
        Iterator iterator2 = set1.iterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next());
        }
``````

- 遍历entry,写成key---->value形式
``````java
 Set set1 = map.entrySet();
        Iterator iterator2 = set1.iterator();
        while (iterator2.hasNext()){
            Map.Entry entry = (Map.Entry) iterator2.next();
            System.out.println(entry.getKey()+"----->"+entry.getValue());
        }
 Set set = map.keySet();
   for (Object o : set) {

            System.out.println(o +"--->"+ map.get(o));
        }
``````

##4.TreeMap的使用
>底层使用**红黑树**进行排序
>可以按照添加的key-value中的key元素的**指定**的属性的大小进行排序
>需要考虑使用**1.自然排序** **2.选择排序**
要求：向TreeMap中添加的Key必须是同一个类的

##5.Hashtable与Properties的使用
Properties
>是Hashtable的子类，其Key和value都是String类型的，常用来处理属性文件。
