# PetShop 测试用例说明

本文档对应 `stage_1/src/PetShop/Test/Test.java` 的 4 个测试用例，涵盖异常与正常流程。

## Case 1: buy when shop is closed
- 目的：验证店铺未营业时禁止买入动物。
- 步骤：新建店铺但不调用 `open()`，直接 `buyAnimal`。
- 预期：抛出 `ShopClosedException`，并输出异常消息。

## Case 2: insufficient balance
- 目的：验证余额不足时无法买入动物。
- 步骤：开店后余额设为 50，尝试购买价格更高的动物。
- 预期：抛出 `InsufficientBalanceException`，并输出异常消息。

## Case 3: sell animal not in shop
- 目的：验证店内没有该动物时无法招待顾客。
- 步骤：买入一只动物 A，但给顾客出售另一只动物 B。
- 预期：抛出 `AnimalNotFountException`，并输出异常消息。

## Case 4: normal flow
- 目的：验证正常流程下的买入、招待、歇业逻辑。
- 步骤：开店 -> 买入动物 -> 招待顾客 -> 歇业。
- 预期：输出动物信息、当日顾客列表以及当日利润。

## 运行方式（可选）
在 `stage_1/src` 下编译并运行：

```bash
javac PetShop\model\*.java PetShop\Test\Test.java
java PetShop.Test.Test
```
