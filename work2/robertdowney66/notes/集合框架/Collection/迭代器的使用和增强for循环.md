#迭代器的使用和增强for循环
##迭代器
###1.迭代器(Iterator)的作用
> 用来遍历集合元素的
###2.如何获取迭代器对象
``````java
Iterator iterator = coll.iterator();
``````
###3.如何遍历(代码实现)
``````java
 while (iterator.hasNext()){
            System.out.println(iterator.next());//next():1.指针下移 2.将下移以后集合位置上的元素返回
        }
``````
###4.增强for循环
####说明：
>针对于集合来讲，增强for循环的底层仍然是**迭代器**
>增强for循环的执行过程中，是将集合或数组中的元素依次赋值给**临时变量**
**注意**：循环体中对临时变量的修改，可能**不会**导致原有集合或数组中元素的修改

eg
``````java
   public void test1(){
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add("BB");
        coll.add("CC");
        for (Object object : coll) {
            object="MM";
        }
        for (Object object : coll) {
            System.out.println(object);//仍旧输出原来的值
        }
    }
``````