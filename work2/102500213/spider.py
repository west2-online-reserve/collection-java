import requests
import time
import json

API_URL_PLAYERS = "https://api.worldaquatics.com/fina/competitions/5019/athletes?gender=&countryId="
FINALS_API_URL_LIST = [
    "https://api.worldaquatics.com/fina/events/2b08da89-8232-4ffc-8df3-95865ba41b82",
    "https://api.worldaquatics.com/fina/events/581a26af-8e97-427c-9adb-5fdd8f4fc9c1",
    "https://api.worldaquatics.com/fina/events/61b483ec-4022-4555-9960-fba9cd1d702d",
    "https://api.worldaquatics.com/fina/events/c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b",
    "https://api.worldaquatics.com/fina/events/4c18d241-ad14-436a-bd38-25dfb47f1cb7",
    "https://api.worldaquatics.com/fina/events/450a9e84-5db0-40c4-a45c-4c3bb2392f48",
    "https://api.worldaquatics.com/fina/events/08d17e1c-94c2-4c56-bf2b-603eeba24f8c",
    "https://api.worldaquatics.com/fina/events/f86bd80d-a342-40f7-a521-90a50ad9d5c5",
]
HEADERS = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/156.0.0.0 Safari/537.36 Edg/156.0.0.0",
    "Accept": "application/json"
}
OUTPUT_FILE = "data.json"
MAX_RETRY = 3
DELAY = 1.5

def fetch_data():
    print("开始抓取选手数据...")
    plaOri = None
    for attempt in range(1, MAX_RETRY + 1):
        try:
            resp1 = requests.get(API_URL_PLAYERS, headers=HEADERS, timeout=15)
            resp1.raise_for_status()
            plaOri = resp1.json()
            break
        except Exception as e:
            print(f"选手接口第{attempt}次失败：{e}")
            if attempt < MAX_RETRY:
                time.sleep(DELAY)
            else:
                print("选手接口多次重试失败，程序退出")
                return False

    player_list = []
    for ctry in plaOri:
        for players in ctry["Participations"]:
            new_player = {
                "fullName": players["PreferredLastName"]+" "+players["PreferredFirstName"],
                "gender": players["Gender"],
                "country": players["NAT"]
            }
            player_list.append(new_player)

    final_list = []
    for url in FINALS_API_URL_LIST:
        ok = False
        for attempt in range(1, MAX_RETRY + 1):
            try:
                print(f"请求决赛接口：{url}")
                resp2 = requests.get(url, headers=HEADERS, timeout=15)
                resp2.raise_for_status()
                gameOri = resp2.json()
                heats = gameOri.get("Heats", [])
                if not heats:
                    print("该项目无Heats数据，跳过")
                    break
                temp2 = {
                    "disciplineName": gameOri["DisciplineName"],
                    "results": []
                }
                for item in heats[0].get("Results", []):
                    if item.get("FullName"):
                        name = item["FullName"]
                    elif item.get("Competitors") is not None:
                        name = " / ".join([c["FullName"] for c in item["Competitors"]])
                    else:
                        name = "未知选手"
                    dive_points = [d.get("DivePoints") for d in item.get("Dives", [])]
                    new_result = {
                        "fullName": name,
                        "totalPoints": item["TotalPoints"],
                        "rank": item["Rank"],
                        "scores": dive_points
                    }
                    temp2["results"].append(new_result)
                final_list.append(temp2)
                ok = True
                break
            except Exception as e:
                print(f"决赛接口 {url} 第{attempt}次失败：{e}")
                if attempt < MAX_RETRY:
                    time.sleep(DELAY)
        if not ok:
            print(f"决赛接口 {url} 多次重试失败，跳过该条")

    out_data = {
        "players": player_list,
        "finals": final_list
    }
    with open(OUTPUT_FILE, "w", encoding="utf-8") as f:
        json.dump(out_data, f, ensure_ascii=False, indent=2)
    print(f"✅ 抓取成功！保存到 {OUTPUT_FILE}")
    return True

if __name__ == "__main__":
    fetch_data()
