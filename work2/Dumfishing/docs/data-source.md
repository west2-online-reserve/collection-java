# 数据来源记录（data-source）

> 赛事：**World Aquatics Diving World Cup 2026 – Super Final**
> 举办地：北京 · 国家游泳中心 | 时间：2026-05-01 ~ 2026-05-03
> 数据快照抓取时间：**2026-10-20 20:11 – 20:13**（本地文件时间戳）
> 复核时间：**2026-10-24**（重新拉取 8 个项目，SHA256 与本地快照完全一致）

## 一、接口

Base：`https://api.worldaquatics.com`

| 用途 | 方法与路径 |
|---|---|
| 赛事信息（competitionId = **5019**） | `GET /fina/competitions/5019` |
| 赛程（拿各项目的 DisciplineId） | `GET /fina/competitions/5019/schedule` |
| **单个项目的完整成绩** | `GET /fina/events/{DisciplineId}` |
| 全部参赛选手 | `GET /fina/competitions/5019/athletes` |

> 接口清单可在 `GET /fina/v3/api-docs`（OpenAPI 3.1）中查看。

## 二、文件 ← 请求 对应关系

| 本地文件 | 请求 |
|---|---|
| `data/athletes.json` | `GET /fina/competitions/5019/athletes` |
| `data/Women's 3m Springboard.json` | `GET /fina/events/2b08da89-8232-4ffc-8df3-95865ba41b82` |
| `data/Women's 10m Platform.json` | `GET /fina/events/581a26af-8e97-427c-9adb-5fdd8f4fc9c1` |
| `data/Women's 3m Synchronised.json` | `GET /fina/events/61b483ec-4022-4555-9960-fba9cd1d702d` |
| `data/Women's 10m Synchronised.json` | `GET /fina/events/c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b` |
| `data/Men's 3m Springboard.json` | `GET /fina/events/4c18d241-ad14-436a-bd38-25dfb47f1cb7` |
| `data/Men's 10m Platform.json` | `GET /fina/events/450a9e84-5db0-40c4-a45c-4c3bb2392f48` |
| `data/Men's 3m Synchronised.json` | `GET /fina/events/08d17e1c-94c2-4c56-bf2b-603eeba24f8c` |
| `data/Men's 10m Synchronised.json` | `GET /fina/events/f86bd80d-a342-40f7-a521-90a50ad9d5c5` |

## 三、数据结构要点

### `athletes.json`
- 顶层是**数组**，每项是**一个国家**：`{ CountryName, CountryCode, Participations[] }`
- `Participations[]` 里每条是一个选手：`PreferredLastName`、`PreferredFirstName`、`Gender`
- `Gender`：**0 = 男，1 = 女**
- 共 **16 个国家 / 89 条参赛记录**

### `{项目}.json`
- 顶层是**对象**：`{ DisciplineName, Heats[] }`
- `Heats[]` 有 **3 个阶段**：`Finals`（决赛）、`Semifinals`（半决赛）、`Head-To-Head`（1v1 淘汰）
- **只用 `Name == "Finals"` 的那一段**
- 决赛里每条 `Results[]`：`Rank`、`FullName`、`NAT`、`TotalPoints`、`Dives[]`
- `Dives[]` 每条：`DiveNo`、`DD`、`DivePoints`（**本跳得分**）、`TotalPoints`（**累计到本跳**）、`JudgeScores[]`（7 个裁判分）
- 人数：个人项目 **4 人**、双人项目 **8 对**；跳数 **5 跳**（3m 板）或 **6 跳**（10m 台）

## 四、范围说明

赛程里共有 **9 个项目**，比考核要求的 8 个多一个 `Mixed 3m & 10m Team`（混合团体）。
考核固定的是 8 个关键字，**混合团体不抓取、不加入查询映射**，因此 `result mixed 3m & 10m team` 属于 `N/A`。

## 五、抓取规矩（自我约束）

- 请求间隔 ≥ 500ms，不并发轰炸
- `User-Agent` 标明学习用途
- 失败重试 1–2 次后放弃，不无限重试
- 不绕过登录、验证码或访问限制
