# DWASearch 多线程执行框架（无缓存）

本文档描述 Stage 2 在移除缓存后的多线程执行思路。目标是在一次运行内避免重复抓取、保证输出顺序正确。

## 目标

- 同一数据源在一次运行内只抓取一次
- 不同数据源可以并行执行
- 输出顺序与输入行号一致
- 不依赖跨运行缓存

## 任务模型

每条有效命令映射为一个 Task（最小信息）：

```
Task {
  lineNumber: int
  key: String      // ATHLETES 或 EVENT:<eventName>
  option: String   // "" 或 "detail"
}
```

Error / N/A 的命令在调度前直接写入输出数组。

## 分组策略

按 `key` 分组：

- key = `ATHLETES`：所有 players 命令共享一次抓取
- key = `EVENT:<eventName>`：同一项目的 result 命令共享一次抓取

每个 key 仅执行一次抓取，再对组内每个 Task 做格式化输出。

## 执行流程

1. CoreModule 解析命令
2. Error / N/A 直接写入输出数组
3. 其余命令映射为 Task 列表
4. 若存在 result 命令，先抓取一次赛事总览（summary），构建 `eventName -> eventId` 映射
5. 调度器按 key 分组（players 直接用 `ATHLETES`，result 可用 `EVENT:<eventId>`）
6. 为每个分组创建一个线程执行
7. 分组内抓取一次 → 多次格式化 → 写入 `output[lineNumber - 1]`
8. 主线程等待所有分组结束后按行号拼接输出

## 线程模型建议

- 每个分组创建一个 `Thread`
- 每个分组对应一个 `Runnable`
- 共享状态仅为输出数组
- 主线程等待所有线程 `join()` 后再读取输出

## 抓取逻辑（分组内）

- `ATHLETES`：
  - `AthleteCrawler.fetch()` 获取运动员 JSON
  - 对组内每个 Task 使用 `AthleteFormatter.format(json, "")`

- `EVENT:<eventId>`：
  - 使用 summary 构建的 `eventName -> eventId` 映射
  - `ResultCrawler.fetchEventJsonByDisciplineId(eventId)` 获取项目 JSON
  - 对组内每个 Task 使用 `ResultFormatter.format(json, option)`

## 输出数组

使用固定长度数组（长度 = 命令行数）：

```
String[] output = new String[n];
```

工作线程只写自己的 `lineNumber - 1` 位置，主线程在全部完成后读取拼接。

如需更强并发安全，可使用：
- `AtomicReferenceArray<String>`，或
- 任务返回结果，主线程 `Future.get()` 后统一写入

## 示例

输入：
```
players
result women 1m springboard
result women 1m springboard detail
result men 10m platform
result men 10m platform detail
players
```

分组：
- `ATHLETES`：第 1 行、第 6 行
- `EVENT:women 1m springboard`：第 2 行、第 3 行
- `EVENT:men 10m platform`：第 4 行、第 5 行

每组仅抓取一次，按行号写回输出。
