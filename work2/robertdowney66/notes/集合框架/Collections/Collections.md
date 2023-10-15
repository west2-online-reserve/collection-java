#Collections
##1.Collections概述
>Collections 是一个操作Set、List和Map等集合的工具类
##2.常用方法
***1.排序操作***
- reverse(List)
  >反转List中元素的排序
- shuffle(List)
  >对List集合元素进行随机排序
- sort(List)
  >根据元素的自然顺序对指定List集合元素按升序排序
- sort(List,Comparator)
  >根据指定的Comparator产生的顺序对List集合元素进行排序
- swap(List,int,int)
  >将指定list集合中的i处元素和j处交换

***2.查找***
- max(Collection)
  >根据元素的自然排序，返回集合中的最大元素
- max(Collection,Comparater)
  >根据Comparator指定的顺序，返回给定集合中的最大元素
- min(Collection)
  >根据元素的自然顺序，返回给定集合中的最小元素
- min(Collection，Comparator)
  >根据Comparator指定的顺序，返回给定集合中的最小元素
###注：
max实质上都是读取数组的最右边，而min实际上是读取数组的最左边，无比较器就按照自然排序
``````java
Object max = Collections.max(list);
        System.out.println(max);
         Object max1 = Collections.max(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Integer && o2 instanceof Integer) {
                    Integer i1 = (Integer) o1;
                    Integer i2 = (Integer) o2;

                    return -Integer.compare(i1, i2);
                }
                throw new RuntimeException();
            }
        });
        System.out.println(max1);
``````

- binarySearch(List list,T key)
  >在List集合下查找某个元素的下标，但是List的元素必须是T或T的子集
- binarySearch(List list,T key,Comparator c)
  >在List集合中寻找某个元素的下落，但是List的元素必须是

``````java
 Collections.sort(list,comparator);
        System.out.println(list);


        int m1 = Collections.binarySearch(list,34,comparator);
        System.out.println(m1);
``````
###注：
一定要用该构造器对集合进行排序，同时binarySearch方法中也要标明该构造器，才能保证该方法的正常运行
如果不使用构造器，也要对该集合进行自然排序，否则会出现问题
- frequency(Collection c ,Object)
  >返回指定集合中指定元素的出现次数

***复制，替换***
- copy(List dest，List src)
  >将src中的内容复制到dest中
``````java
  List list = Arrays.asList(34,10, 456, 675, 78, 65, 4, 54, 45);
        System.out.println(list);
        //copy() 一定得list1的size大于list的size
        /*错误写法
        List list1 = new ArrayList<>();
        Collections.copy(list1,list);
        System.out.println(list1);*/
        //正确写法
        List<Object> list1 = Arrays.asList(new Object[list.size()]);
        Collections.copy(list1,list);
        System.out.println(list1);
``````
###注：
一定要保证受复制方的size大于被复制方的size，才能保证复制的成功，这是由底层源码决定的        

- replaceAll(List list,Object oldVal,Object newVal)
  >使用新值替换List对象的所有旧值
- 提供多个unmodifiableXxx()方法
  >该方法返回指定Xxx的不可修改的原因

***添加***
- addAll(Collection c,T...elements)
  >将所有指定元素添加到指定collection中 
``````java
 //addAll:只能够添加多个元素，不是添加集合
        //Array.aslist所形成的集合本质上还是数组，所以不可用Collections.addAll
        // List list = Arrays.asList(34,10, 456, 675, 78, 65, 4, 54, 45);
        List list1 = new ArrayList<>();
        list1.add(12);
        list1.add(34);
        /*错误添加方式
        List list1 = new ArrayList<>();
        list1.add(23);
        Collections.addAll(list,list1);
         */
        Collections.addAll(list1, "AA");
        System.out.println(list1);
``````
###注：
addAll只能添加多个元素，不是添加集合，而是添加多个元素，与先前学习的不同。同时使用Arrays.aslist不能使用该方法，原因是该种写法下底层还是数组，无法动用Collections。

***同步***
- Collections 类中提供了多个synchronizedXxx()方法
  >该方法可使将指定集合包成线程同步的集合，从而可以实现
##3.区别Collection 和 Collections
- Collection：集合框架中的用于存储一个一个元素的接口
- Collections：用于操作集合框架的一个工具类，此时的集合框架包括：Set，List，Map