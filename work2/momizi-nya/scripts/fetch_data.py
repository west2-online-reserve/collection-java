import hashlib
import json
import sys
import time
import urllib.error
import urllib.request
from datetime import datetime, timezone
from pathlib import Path

API = "https://api.worldaquatics.com/fina"
COMPETITION_ID = 5019
REQUEST_DELAY = 0.8
TIMEOUT = 30
UA = "dwasearch-coursework/1.0 (learning project; contact: student)"

EVENTS = [
    ("women 3m springboard", "Women's 3m Springboard"),
    ("women 10m platform", "Women's 10m Platform"),
    ("women 3m synchronised", "Women's 3m Synchronised"),
    ("women 10m synchronised", "Women's 10m Synchronised"),
    ("men 3m springboard", "Men's 3m Springboard"),
    ("men 10m platform", "Men's 10m Platform"),
    ("men 3m synchronised", "Men's 3m Synchronised"),
    ("men 10m synchronised", "Men's 10m Synchronised"),
]

GENDER = {1: "Female", 0: "Male"}

def get_json(url):
    req = urllib.request.Request(url, headers={"User-Agent": UA, "Accept": "application/json"})
    with urllib.request.urlopen(req, timeout=TIMEOUT) as resp:
        if resp.status != 200:
            raise RuntimeError("HTTP %s: %s" % (resp.status, url))
        return json.loads(resp.read().decode("utf-8"))

def fetch_competition():
    return get_json("%s/competitions/%d" % (API, COMPETITION_ID))

def fetch_players():
    countries = get_json("%s/competitions/%d/athletes" % (API, COMPETITION_ID))
    players = []
    for country in countries:
        for part in country.get("Participations", []):
            last = (part.get("PreferredLastName") or "").strip()
            first = (part.get("PreferredFirstName") or "").strip()
            if not last:
                continue
            players.append({
                "fullName": (last + " " + first).strip(),
                "gender": GENDER.get(part.get("Gender"), "Unknown"),
                "country": part.get("NAT") or country.get("CountryName"),
                "countryCode": country.get("CountryCode"),
            })
    seen, unique = set(), []
    for p in players:
        key = (p["fullName"], p["countryCode"])
        if key not in seen:
            seen.add(key)
            unique.append(p)
    unique.sort(key=lambda p: (p["country"] or "", p["fullName"]))
    return unique

def _normalise_pair(name):
    if not name:
        return name
    if " / " in name:
        parts = [x.strip() for x in name.split(" / ")]
        parts.sort()
        return " & ".join(parts)
    return name

def fetch_event(discipline_id):
    data = get_json("%s/events/%s" % (API, discipline_id))
    heats = data.get("Heats") or []
    finals = [h for h in heats if (h.get("PhaseName") == "Finals" or h.get("Name") == "Finals")]
    if not finals:
        raise RuntimeError("项目 %s 找不到 Finals 场次" % discipline_id)
    results = []
    for r in finals[0].get("Results", []):
        dives = sorted(r.get("Dives") or [], key=lambda d: d.get("DiveOrder") or 0)
        results.append({
            "fullName": _normalise_pair(r.get("FullName")),
            "rank": r.get("Rank"),
            "countryCode": r.get("NAT"),
            "scores": [d.get("DivePoints") for d in dives],
            "totalPoints": r.get("TotalPoints"),
        })
    results.sort(key=lambda x: x["rank"])
    return results

def discipline_map():
    events = get_json("%s/competitions/%d/events" % (API, COMPETITION_ID))
    mapping = {}
    for sport in events.get("Sports", []):
        for d in sport.get("DisciplineList", []):
            mapping[d.get("DisciplineName")] = d.get("Id")
    return mapping

def main():
    out_path = Path(sys.argv[1]) if len(sys.argv) > 1 else Path("data.json")
    try:
        comp = fetch_competition()
        time.sleep(REQUEST_DELAY)
        players = fetch_players()
        time.sleep(REQUEST_DELAY)
        mapping = discipline_map()

        events = []
        for command, name in EVENTS:
            discipline_id = mapping.get(name)
            if not discipline_id:
                raise RuntimeError("赛事里找不到项目：%s" % name)
            results = fetch_event(discipline_id)
            events.append({
                "command": "result " + command,
                "eventId": discipline_id,
                "gender": "Women" if command.startswith("women") else "Men",
                "discipline": name,
                "eventType": "Final",
                "results": results,
            })
            print("ok: %s (%d 条)" % (name, len(results)))
            time.sleep(REQUEST_DELAY)

        data = {
            "schemaVersion": 1,
            "competition": {
                "id": str(comp.get("id")),
                "name": comp.get("officialName") or comp.get("name"),
                "from": (comp.get("dateFrom") or "")[:10],
                "to": (comp.get("dateTo") or "")[:10],
            },
            "players": players,
            "events": events,
        }
        out_path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

        manifest = {
            "schemaVersion": 1,
            "retrievedAt": datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ"),
            "source": {
                "provider": "World Aquatics",
                "competitionEndpoint": "%s/competitions/%d/events" % (API, COMPETITION_ID),
                "athletesEndpoint": "%s/competitions/%d/athletes" % (API, COMPETITION_ID),
                "eventEndpointTemplate": "%s/events/{eventId}" % API,
            },
            "files": {"data.json": {
                "sha256": hashlib.sha256(out_path.read_bytes()).hexdigest()
            }},
        }
        if out_path.name == "data.json":
            mpath = out_path.with_name("manifest.json")
        else:
            mpath = out_path.with_name(out_path.stem + ".manifest.json")
        mpath.write_text(json.dumps(manifest, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

        print("写出 %s（%d 名选手，%d 个项目）" % (out_path, len(players), len(events)))
        return 0
    except (urllib.error.URLError, urllib.error.HTTPError, RuntimeError, OSError) as exc:
        print("抓取失败：%s" % exc, file=sys.stderr)
        return 1

if __name__ == "__main__":
    sys.exit(main())
