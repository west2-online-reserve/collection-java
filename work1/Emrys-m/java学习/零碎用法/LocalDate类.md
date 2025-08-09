# LocalDate

## 创建LocalDate实例

```java
import java.time.LocalDate;
// 获取当前日期
LocalDate today = LocalDate.now();

// 通过指定年月日创建
LocalDate date = LocalDate.of(2023, 10, 5); // 2023年10月5日
LocalDate date2 = LocalDate.of(2023, Month.OCTOBER, 5); // 使用Month枚举

// 通过字符串解析（默认格式yyyy-MM-dd）
LocalDate parsedDate = LocalDate.parse("2023-10-05");
```

## 获取日期信息

```java
LocalDate date = LocalDate.of(2023, 10, 5);

int year = date.getYear(); // 2023
Month month = date.getMonth(); // OCTOBER
int monthValue = date.getMonthValue(); // 10
int day = date.getDayOfMonth(); // 5
DayOfWeek dayOfWeek = date.getDayOfWeek(); // THURSDAY
int dayOfYear = date.getDayOfYear(); // 278
```

## 日期修改

```java
LocalDate date = LocalDate.of(2023, 10, 5);

// 增加年/月/日
LocalDate nextYear = date.plusYears(1); // 2024-10-05
LocalDate nextMonth = date.plusMonths(1); // 2023-11-05
LocalDate tomorrow = date.plusDays(1); // 2023-10-06

// 减少年/月/日
LocalDate lastYear = date.minusYears(1); // 2022-10-05
LocalDate lastMonth = date.minusMonths(1); // 2023-09-05
LocalDate yesterday = date.minusDays(1); // 2023-10-04

// 设置年/月/日
LocalDate withYear = date.withYear(2024); // 2024-10-05
LocalDate withMonth = date.withMonth(11); // 2023-11-05
LocalDate withDay = date.withDayOfMonth(6); // 2023-10-06
```

## 日期比较

```java
LocalDate date1 = LocalDate.of(2023, 10, 5);
LocalDate date2 = LocalDate.of(2023, 12, 25);

// 比较两个日期
boolean isAfter = date2.isAfter(date1); // true（date2在date1之后）
boolean isBefore = date2.isBefore(date1); // false（date2不在date1之前）
boolean isEqual = date1.isEqual(LocalDate.of(2023, 10, 5)); // true

// 检查是否是闰年
boolean isLeapYear = date1.isLeapYear(); // 2023年不是闰年，返回false
```

##  日期格式化与解析

```java
import java.time.format.DateTimeFormatter;

LocalDate date = LocalDate.of(2023, 10, 5);

// 格式化（默认格式yyyy-MM-dd）
String formatted = date.toString(); // "2023-10-05"

// 自定义格式
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
String customFormatted = date.format(formatter); // "2023年10月05日"

// 解析自定义格式的字符串
LocalDate parsed = LocalDate.parse("2023年10月05日", formatter);
```

## 其他常用操作

```java
LocalDate date = LocalDate.of(2023, 10, 5);

// 获取当月的第一天和最后一天
LocalDate firstDayOfMonth = date.withDayOfMonth(1); // 2023-10-01
LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth()); // 2023-10-31

// 获取下一个周一
LocalDate nextMonday = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

// 计算两个日期之间的天数差
LocalDate otherDate = LocalDate.of(2023, 12, 25);
long daysBetween = ChronoUnit.DAYS.between(date, otherDate); // 82天
```

