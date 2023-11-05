#API的使用
##JDk8前API
####Date类的使用
- ***Java.util.Date***
  >输出时间格式eg：Sun Sep 24 14:41:03 CST 2023
   
   1.俩个构造器的使用
  
   2.俩个方法的使用
   （1）toString()

    (2) getTime()
    > 获得当前距1970年毫秒数，类型为long


    ``````java
     @Test
    public void test(){
        Date date = new Date();
        System.out.println(date);
        long m = date.getTime();
        System.out.println(m);
        Date date1 = new Date(m);
        System.out.println(date1);
    }
    ``````


  
- ***java.sql.Date***
  >通过构造器输入毫秒数生成时间，输出格式eg：2023-09-24
    ``````java
    public void test1(){
        java.sql.Date date = new java.sql.Date(1695524132723L);
        System.out.println(date);//sout默认为toString
        long m = date.getTime();
        System.out.println(m);
    }
    ``````
  
####SimpleDateFormat
>用于日期时间的格式化和解析，输出的未用构造器时间格式eg：2023/9/24 14:45，启用构造器则可各种各样
- 格式化：format 日期--->字符串 
- 解析：parse 字符串--->日期
``````java
  @Test
    public void test2() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Date date = new Date();
        String s = simpleDateFormat.format(date);
        System.out.println(s);
        Date date1 = simpleDateFormat.parse("2023/9/24 14:33");
        System.out.println(date1);
    }
``````
- 采用构造器模式
>通过指定结构，使得时间转化转化成自己所想要的，eg："yyyy-mm-dd hh-mm-ss"
``````java
 public void test3() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh-mm-ss");
        Date date = new Date();
        String s1 = simpleDateFormat.format(date);
        System.out.println(s1);
        Date date1 = simpleDateFormat.parse("2023-05-24 03-05-17");
        System.out.println(date1);
    }
``````
##注：
前一个format什么格式，后一个parse也是一样的，必须**保持一致**
  

####Calendar
> 具有可变性

1.实例化
>由于Calender是一个抽象类，所以我们需要创建其子类的实例。或者通过Calender的静态方法getInsatnce()即可获取

2.常用方法：
- get（int field）
  >获取当前指定时间或者日期，后面需要跟上Calendar.属性
- set（int field，xx）
  >设置当前时间或者日期
- getTime
  >获取当前时间，类型为date
- setTime
  >设置时间，需要输入date类型
- add（int field，xx）
  >增加时间
``````java
 Calendar calendar = Calendar.getInstance();
        //测试方法 get
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        //set
        calendar.set(Calendar.DAY_OF_MONTH,11);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        //add
        calendar.add(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.DAY_OF_MONTH,-2);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        //getTime
        Date date = calendar.getTime();
        System.out.println(date);

        //setTime
        Date date1 = new Date();
        calendar.setTime(date1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
``````
---
##JDK8中的API
####LocalDate，LocalTime，LocalDateTime
> 类似于Calendar，具有不可变性
  
1.实例化
(1).now()
(2).of(xxx,xx,xx)

2.方法

- get+所需提取时间()
  > 获取当前日期或者时间
  eg:  getDayOfMonth
- withXxx()
  > 更变日期或者时间 eg:  withDayOfMonth
- plusXxx()
  > 增加日期或者时间 eg： plusDayOfMonth
- minusXxx()
  > 减少日期或者时间 eg： minusDayOfMonth
``````java
 @Test
    public void test(){
        //now():获取当前日期和时间对应的实例
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);
        //of()：获取指定的日期，时间对应的实例
        LocalDateTime localDateTime2 = LocalDateTime.of(2022,12,5,11,23,45);
        LocalDate localDate2 = LocalDate.of(2021,5,23);
        System.out.println(localDateTime2);
        System.out.println(localDate2);
        //getXxx()
        System.out.println("======================");
        LocalDateTime localDateTime5 = LocalDateTime.now();
        System.out.println(localDateTime2.getDayOfMonth());
        //体现不可变性
        //withXxx()
        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1.withDayOfMonth(15));

        LocalDateTime localDateTime3 = localDateTime1.withDayOfMonth(15);
        System.out.println(localDateTime3);//2023-09-15T16:41:14.075138200
        System.out.println(localDateTime1);//2023-09-24T16:41:14.075138200
        //plusXxx()
        LocalDateTime localDateTime4 = localDateTime1.plusDays(5);
        System.out.println(localDateTime1);
        System.out.println(localDateTime4);

    }
``````

  
####Instant
> 类似于Date

1.实例化
(1)now()
> 基于现在时间创造一个实例，类似于Date无输入构造器

(2)ofEpochMilli()
> 基于输入时间戳创造一个实例，类似于Date有输入构造器

2.方法

- toEpochMill()
  > 将该时间转化成时间戳，类似于Date的getTime
  ``````java
    @Test
    public void test1(){
        Instant instant = Instant.now();
        System.out.println(instant);//2023-09-24T09:00:48.014041200Z

        OffsetDateTime instant1 = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(instant1);

        Instant instant2 = Instant.ofEpochMilli(372648732674L);
        System.out.println(instant2);//1981-10-23T01:32:12.674Z
        long m = instant.toEpochMilli();
        System.out.println(m);
        System.out.println(new Date().getTime());
    }
  
####DateTimeFormatter
>类似于SimpleDateFormat，必须要先定义格式，搭配LocalDateTime去使用
- 格式化 ：format
  > 将日期时间转化成字符串
- 解析 ：调用TemporalAccessor，然后用from去接收
  > 将字符串转化成日期时间
  eg：  TemporalAccessor temporalAccessor = dateTimeFormatter.parse(字符串);（采用写后半段补全前面）
        LocalDateTime localDateTime1 = LocalDateTime.from(temporalAccessor);
 
  ``````java
  @Test
    public void test2(){
        //自定义的格式，如：ofPattern
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String str = dateTimeFormatter.format(localDateTime);
        System.out.println(str);

        //解析：字符串---> 日期，时间
        TemporalAccessor temporalAccessor = dateTimeFormatter.parse("2023/09/24 05:18:04");
        LocalDateTime localDateTime1 = LocalDateTime.from(temporalAccessor);
        System.out.println(localDateTime1);
    }






