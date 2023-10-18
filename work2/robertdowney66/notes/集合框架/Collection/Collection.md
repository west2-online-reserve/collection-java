#Collection
##ArrayList
###1.构造
``````java
Collection collection = new ArrayList();
``````
###2.方法
- ***add***
  >向集合中放入元素
  ``````java
      Collection coll = new ArrayList();
        //add()
        coll.add("AA");
        coll.add(123);//自动装箱
        coll.add("羽羽");
        coll.add(new Object());
        coll.add(new Person("Tom", 12));
        System.out.println(coll);
  ``````
- ***addAll(Collection other)***
  >向集合中加入另一个集合的元素
  ``````java
  coll.addAll(coll1);
  ``````
####注：
coll.add(Collection other)此种是将other集合看作一个整体加入coll

- ***size()***
  >输出该集合的中元素的个数
  ``````java
  System.out.println(coll.size());
  ``````

- ***isEmpty***
  >判断集合是否为空
  ``````java
  System.out.println(coll.isEmpty());
  ``````

- ***comtains***
  >判断集合是否为空
  ``````java
   System.out.println(coll.contains("AA"));//true
        System.out.println(coll.contains(123));
  ``````
####注：
如果元素是引用类型，其类中一定要包含重写的equals()方法,否则书写为new的格式就无法识别出contains

- ***containsAll(collection coll)***
  >查看coll集合是否为该集合的子集或者真子集
  ``````java
     System.out.println(coll.containsAll(coll1));
  ``````

- ***equals***
  >判断俩集合是否相等
  ``````java
  System.out.println(coll1.equals(coll1));
  ``````

- ***clear***
  >清除集合中所有元素
  ``````java
   coll.clear();
        System.out.println(coll);
  ``````
- ***remove(object obj)***
  >去除集合中的元素
  ``````java
  
        coll.remove(new Person("Tom", 12));
        coll.remove("AA");
        System.out.println(coll);
  ``````

- ***removeAll(Collection coll)***
  >去除集合中与coll的交集部分
  ``````java
   coll.retainAll(coll2);
        System.out.println(coll);
  ``````

- ***retainAll(Collection coll)***
  >获取当前集合交集部分
  ``````java
  coll.retainAll(coll2);
        System.out.println(coll);
  ``````

- ***toArray***
  >将集合转化成数组
  ``````java
  Object[] arr = coll.toArray();
        System.out.println(Arrays.toString(arr));
  ``````

- ***hashCode***
  >获取当前集合哈希值
  ``````java
   System.out.println(coll.hashCode());
  ``````

- ***toArray(T[] a)***
  >还没讲，泛型
- ***iterator***
  >调用迭代器进行迭代，从而达到遍历集合作用，详见迭代器的使用与增强for循环

###3.集合和数组的转化
- 集合 ---> 数组：toArray()
- 数组 ---> 集合：调用Arrays的静态方法asList(Objects...objs)
###4.向Collection中添加元素的要求
> 要求元素所属的类一定要重写equals()!

原因：
因为Collection中的相关方法(contains/remove等)使用时，要调用元素所在类的equals方法
  