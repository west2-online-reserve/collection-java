# 数据来源说明（DATA_SOURCE）

## 1. 项目与赛事

-   项目名称：DWASearch
-   赛事名称：World Aquatics Diving World Cup 2026 - Super Final
-   举办地点：北京
-   Competition ID：`5019`

## 2. 数据来源

本项目的数据来自 **World Aquatics 官方 API**。

-   官方网站：<https://www.worldaquatics.com/>
-   API 基础地址：`https://api.worldaquatics.com/fina`

本项目使用的 API 请求路径如下：

数据内容       请求路径
  -------------- --------------------------------------------------
运动员名单     `/competitions/5019/athletes?gender=&countryId=`
比赛项目列表   `/competitions/5019/events`
单项赛事数据   `/events/{eventId}`

以上请求路径依据项目中的 `WorldAquaticsClient.java` 记录。

## 3. 数据抓取方式

项目使用 Java 25 提供的 `java.net.http.HttpClient` 发送 HTTP GET
请求，接收 API 返回的 JSON 响应，并将响应内容保存到项目的 `data` 目录。

抓取程序由 `WorldAquaticsClient.java` 实现，主要包括：

1.  获取运动员名单；
2.  获取比赛项目列表；
3.  根据赛事 ID 获取八个跳水项目的数据；
4.  将 API 返回的 JSON 保存到本地文件。

程序会检查 HTTP
状态码；请求失败时会抛出异常。重新运行抓取程序时，已存在的目标 JSON
文件会被覆盖。

## 4. 数据文件

数据快照包含以下文件：

文件                                         内容
  -------------------------------------------- --------------------
`data/players.json`                          运动员名单
`data/event_list.json`                       比赛项目列表
`data/results/men_3m_springboard.json`       男子 3 米跳板
`data/results/men_3m_synchronised.json`      男子双人 3 米跳板
`data/results/men_10m_platform.json`         男子 10 米跳台
`data/results/men_10m_synchronised.json`     男子双人 10 米跳台
`data/results/women_3m_springboard.json`     女子 3 米跳板
`data/results/women_3m_synchronised.json`    女子双人 3 米跳板
`data/results/women_10m_platform.json`       女子 10 米跳台
`data/results/women_10m_synchronised.json`   女子双人 10 米跳台

赛事数据通过以下 Event ID 获取：

项目                 Event ID
  -------------------- ----------------------------------------
男子 3 米跳板        `4c18d241-ad14-436a-bd38-25dfb47f1cb7`
男子双人 3 米跳板    `08d17e1c-94c2-4c56-bf2b-603eeba24f8c`
男子 10 米跳台       `450a9e84-5db0-40c4-a45c-4c3bb2392f48`
男子双人 10 米跳台   `f86bd80d-a342-40f7-a521-90a50ad9d5c5`
女子 3 米跳板        `2b08da89-8232-4ffc-8df3-95865ba41b82`
女子双人 3 米跳板    `61b483ec-4022-4555-9960-fba9cd1d702d`
女子 10 米跳台       `581a26af-8e97-427c-9adb-5fdd8f4fc9c1`
女子双人 10 米跳台   `c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b`

## 5. 抓取时间

-   数据抓取日期：**2026-11-03** 17:41:54


