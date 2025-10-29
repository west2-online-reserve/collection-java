# 构造器（constructor）

## 无参构造

```java
public class Application{
    public static void main(String[] args){
        Person person = new Person(); //new调用无参构造器
    }
}

public class Person{ //创建一个Person类
    String name; //创建一个String类的数据成员
    public Person(){ //无参构造
        this.name = "null"; //数据成员初始化
    }
}
```



## 含参构造

```java
public class Application{
    public static void main(String[] args){
        Person person = new Person("Typora"); //new调用含参构造器，传入参数”Typora"
    } 
}

public class Person{ //创建一个Person类
    String name; //创建一个String类的数据成员
    public Person(String name){ //含参构造
    	this.name = name; //数据成员初始化
}
```

