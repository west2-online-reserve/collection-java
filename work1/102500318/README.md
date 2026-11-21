# 第一次作业 102500318

## 1. PetShop

宠物店作业的代码，按 `oop.PetShop` 包组织：

| 文件 | 说明 |
|---|---|
| `Animal.java` | 抽象父类：name / age / gender，抽象 getPrice() |
| `AnimalShop.java` | 宠物店接口：buyAnimal / serveCustomer / closed |
| `Dog.java` | 狗（价格 100，含是否打疫苗字段） |
| `Cat.java` | 猫（价格 200） |
| `Customer.java` | 顾客：姓名、到访次数、最近到访日期 |
| `MyAnimalShop.java` | 宠物店实现：余额、当天收入/成本、歇业判断、买卖逻辑 |
| `InsufficientBalanceException.java` | 自定义异常：余额不足 |
| `AnimalNotFoundException.java` | 自定义异常：店里没有动物 |
| `Test.java` | 测试/示例入口（main 里按顺序跑一组例子） |

## 2. screenshots

刷题截图：

- PTA 基础编程题目集：7-2、7-16、7-21、7-28、7-32、7-37
- 力扣 27. 移除元素（提交记录）
